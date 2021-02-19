package com.ssafy.artmate.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.artmate.dto.ExhibitDto;
import com.ssafy.artmate.service.ExhibitService;

import io.swagger.annotations.Api;

@Component
@Controller
public class ExhibitApiController implements ApplicationRunner{ //서버 실행시 한번만 실행

	static final String ServiceKey = "5a54466f6c756e6e3130324c50755745"; // 인증키
//	static final String serviceKey2 = "NqI6L9Nl8SlLgNXMyLF58LZGxXkKHFpjhA9qPrtrs2uKoopI1HS%2BuiGF4mmq69ug48RLB3DlcoLEG60FYBCbQg%3D%3D"; //인증키2
	
	@Autowired
	private ExhibitService eservice;
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.getSeoulExhibit();
//		this.getExhibitInfo();
	}

	public void getSeoulExhibit() throws IOException, org.json.simple.parser.ParseException {
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.seoul.go.kr:8088/" + ServiceKey + "/json/ListExhibitionOfSeoulMOAInfo/1/53/"); /* URL */

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		// json파싱
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj = (JSONObject)jsonParser.parse(sb.toString());
		JSONObject jsonObj1 = (JSONObject) jsonObj.get("ListExhibitionOfSeoulMOAInfo");
		JSONArray jsonArr = (JSONArray) jsonObj1.get("row");
		
		for(int i=0; i<jsonArr.size(); i++) { //전체 전시회 리스트 검사
			JSONObject data = (JSONObject) jsonArr.get(i);
			
			ExhibitDto dto = new ExhibitDto();

			dto.setSeqNum(data.get("DP_SEQ").toString()); //전시 일련번호
			
			//만약 이미 db에 있는 전시회라면 종료
			if(eservice.selectExhibitBySeq(dto.getSeqNum()) != null) {
				System.out.println("===============최신 업데이트 된 전시회 없음!");
				break;
			}
			 
			
			dto.setName(data.get("DP_NAME").toString()); //전시회명
			dto.setLocation(data.get("DP_PLACE").toString()); //장소
			//설명 글자수 제한
			String desc = data.get("DP_INFO").toString();
			if(desc.length()>=250) desc = desc.substring(0, 249);
			dto.setDescription(desc); //설명
			dto.setExImg(data.get("DP_MAIN_IMG").toString()); //이미지
			dto.setStartDate(data.get("DP_START").toString()); //시작날짜
			dto.setEndDate(data.get("DP_END").toString()); //종료날짜
			dto.setArtist(data.get("DP_ARTIST").toString()); //작가

			System.out.println("===============새로운 전시회 데이터: "+dto.toString());
			
			//DB에 전시회 데이터 넣기(태그빼고)
			eservice.insertExhibit(dto);
			
			List<String> tagList = new ArrayList<>();
			
			//전시부문 태그 6개 분류
			//비디오,드로잉,설치,조각,사진,회화
			String artPart = data.get("DP_ART_PART").toString();
			if(artPart.contains("비디오") || artPart.contains("영상") || artPart.contains("뉴미디어")) tagList.add("비디오");
			if(artPart.contains("드로잉") || artPart.contains("판화")) tagList.add("드로잉");
			if(artPart.contains("설치")) tagList.add("설치");
			if(artPart.contains("조각")) tagList.add("조각");
			if(artPart.contains("사진")) tagList.add("사진");
			if(artPart.contains("회화")) tagList.add("회화");
			
			//전시회 id 가져오기
			int id = eservice.selectExhibitBySeq(dto.getSeqNum()).getId();
			
			if(tagList.size()==0) continue; //태그 정보가 없으면 pass
			System.out.println(tagList.toString());
			//해당 전시회가 가진 태그 db에 넣기
			for(int j=0; j<tagList.size(); j++) {
				HashMap<String, Object> tagInfo = new HashMap<>();
				tagInfo.put("exId", id);
				tagInfo.put("tagName", tagList.get(j));
				eservice.insertTag(tagInfo);
			}
		}//End for문
	}
	/* 공공데이터 아직 승인 안남
	public void getExhibitInfo() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://www.culture.go.kr/openapi/rest/publicperformancedisplays/d/"); //URL
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+serviceKey2); //Service Key
        URL url = new URL(urlBuilder.toString());
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }*/
}
