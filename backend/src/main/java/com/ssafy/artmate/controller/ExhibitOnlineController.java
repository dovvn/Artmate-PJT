package com.ssafy.artmate.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.ssafy.artmate.dto.ExhibitDto;
import com.ssafy.artmate.service.ExhibitService;


@Component
@Controller
public class ExhibitOnlineController implements ApplicationRunner{ //서버 실행시 한번만 실행

	@Autowired
	ExhibitService eservice;  
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.getSavinaExhibit();
	}
	
	//SAVINA 버추얼 미술관 - 온라인전시회
	public void getSavinaExhibit() {
		String url = "http://www.savinamuseum.com/kor/exlist.action?exdgb=VR";

		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		Elements elements = doc.select("div.online_list");
		for(org.jsoup.nodes.Element element : elements) {
			
			String totalLink = element.getElementsByTag("a").attr("href");

			String vrLink = totalLink.substring(totalLink.indexOf("http://"), totalLink.indexOf(")")).replaceAll("'", ""); //vr링크

			//만약 이미 db에 있는 전시회라면 종료(vrLink로 판단)
			if(eservice.selectExhibitByVrLink(vrLink) != null) {
			System.out.println("===============최신 업데이트 된 온라인 전시회 없음!"); break; }
						
			String name = element.getElementsByTag("a").text().replaceAll("  자세히 보기 닫기 자세히 보기", ""); //제목
			String description = element.getElementsByClass("txt").text(); //설명
			String artist = element.getElementsByTag("span").get(0).text(); //작가
			String exImg = "http://www.savinamuseum.com/"+element.getElementsByTag("img").attr("src"); //이미지주소
			
			
			ExhibitDto dto = new ExhibitDto(name, description, exImg, artist, vrLink);
			
			System.out.println("===============새로운 온라인 전시회 데이터: "+dto.toString());
			//DB에 전시회 데이터 넣기(태그빼고)
			eservice.insertOnlineExhibit(dto);
			
			
			List<String> tagList = new ArrayList<>();
			
			//전시부문 태그 6개 분류
			//비디오,드로잉,설치,조각,사진,회화
			String artPart = element.getElementsByTag("span").get(1).text();
			
			if(artPart.contains("비디오") || artPart.contains("영상") || artPart.contains("뉴미디어")) tagList.add("비디오");
			if(artPart.contains("드로잉") || artPart.contains("판화")) tagList.add("드로잉");
			if(artPart.contains("설치")) tagList.add("설치");
			if(artPart.contains("조각")) tagList.add("조각");
			if(artPart.contains("사진")) tagList.add("사진");
			if(artPart.contains("회화")) tagList.add("회화");
			
			//전시회 id 가져오기
			int id = eservice.selectExhibitByVrLink(dto.getVrLink()).getId();
			
			if(tagList.size()==0) continue; //태그 정보가 없으면 pass
			System.out.println(tagList.toString());
			//해당 전시회가 가진 태그 db에 넣기
			for(int j=0; j<tagList.size(); j++) {
				HashMap<String, Object> tagInfo = new HashMap<>();
				tagInfo.put("exId", id);
				tagInfo.put("tagName", tagList.get(j));
				eservice.insertTag(tagInfo);
			}
		}
	}
}
