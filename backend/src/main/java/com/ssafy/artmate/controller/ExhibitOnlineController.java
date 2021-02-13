package com.ssafy.artmate.controller;

import java.io.IOException;

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
	private ExhibitService eservice;

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
			
			String name = element.getElementsByTag("a").text().replaceAll("  자세히 보기 닫기 자세히 보기", ""); //제목
			String description = element.getElementsByClass("txt").text(); //설명
			String artist = element.getElementsByTag("span").get(0).text(); //작가
			String exImg = "http://www.savinamuseum.com/"+element.getElementsByTag("img").attr("src"); //이미지주소
			String vrLink = totalLink.substring(totalLink.indexOf("http://"), totalLink.indexOf(")")).replaceAll("'", ""); //vr링크
			
			ExhibitDto dto = new ExhibitDto(name, description, exImg, artist, vrLink);
			//만약 이미 db에 있는 전시회라면 종료(vrLink로 판단)
			if(eservice.selectExhibitByVrLink(dto.getVrLink()) != null) {
				System.out.println("===============최신 업데이트 된 온라인 전시회 없음!");
				break;
			}
			System.out.println("===============새로운 온라인 전시회 데이터: "+dto.toString());
			//DB에 전시회 데이터 넣기
			eservice.insertOnlineExhibit(dto);
		}
	}

}
