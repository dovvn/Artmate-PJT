package com.ssafy.artmate.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/exhibit")
@Api(value = "전시회 공공데이터 API")
public class ExhibitApiController {

	static final String ServiceKey = "5a54466f6c756e6e3130324c50755745"; // 인증키

	@ResponseBody
	@GetMapping("/seoul")
	public String getSeoulExhibit(HttpServletRequest request, HttpServletResponse response) throws IOException, org.json.simple.parser.ParseException {
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
		//System.out.println(jsonObj.get("ListExhibitionOfSeoulMOAInfo"));
		JSONObject jsonObj1 = (JSONObject) jsonObj.get("ListExhibitionOfSeoulMOAInfo");
		JSONArray jsonArr = (JSONArray) jsonObj1.get("row");
		
		for(int i=0; i<jsonArr.size(); i++) {
			JSONObject data = (JSONObject) jsonArr.get(i);
			
			System.out.println("DP_NAME: "+data.get("DP_PLACE"));
			System.out.println("DP_START: "+data.get("DP_START"));
			System.out.println("DP_END: "+data.get("DP_END"));
			System.out.println("DP_ARTIST: "+data.get("DP_ARTIST"));
			System.out.println("DP_INFO: "+data.get("DP_INFO"));
			System.out.println("DP_MAIN_IMG: "+data.get("DP_MAIN_IMG"));
			System.out.println("DP_ART_PART: "+data.get("DP_ART_PART"));
			System.out.println("");
		}
		return sb.toString();
	}
}
