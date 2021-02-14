package com.ssafy.artmate.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ssafy.artmate.dto.ExhibitDto;

@Component
public class ChromeController implements ApplicationRunner{
	
	private static WebDriver driver;
	
	static final String WEB_DRIVER_ID="webdriver.chrome.driver";
	static final String WEB_DRIVER_PATH="src/main/resources/static/chrome/chromedriver.exe";
	
	static final String url = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%A0%84%EC%8B%9C%ED%9A%8C";
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.chrome();
	}
	
	public void chrome() throws InterruptedException {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		driver  = new ChromeDriver();
		driver.get(url);
		
		String[] endPageE = driver.findElement(By.className("_page_navi")).findElement(By.className("_navi_tpl")).getText().split("/");
		int endPage = Integer.parseInt(endPageE[1].trim());
		
		List<ExhibitDto> exhibitions = new ArrayList<ExhibitDto>();

		try {
			for(int i =0;i<endPage;i++){
				Thread.sleep(1000);
				List<WebElement> btns = driver.findElement(By.className("_page_navi")).findElements(By.tagName("a"));
				WebElement prevBtn = btns.get(0);
				WebElement nextBtn = btns.get(1);
				
				WebElement list_info = driver.findElement(By.className("list_info"));
				List<WebElement> items =  list_info.findElements(By.className("list_item"));
				for(WebElement item : items) {
					System.out.println(item.getText());
					ExhibitDto exhibit = new ExhibitDto();
					
					exhibit.setExImg(item.findElement(By.tagName("img")).getAttribute("src"));
					List<WebElement> dd = item.findElements(By.tagName("dd"));
					
					exhibit.setName(dd.get(0).getText().trim());
					String[] date = dd.get(1).getText().split("~");
					
					exhibit.setStartDate(date[0].trim());
					exhibit.setEndDate(date[1].trim());
					if(dd.size()>2) {
						exhibit.setLocation(dd.get(2).getText().trim());						
					}
					
					Calendar cal = Calendar.getInstance();
					int currentYear = cal.get(Calendar.YEAR);
					int currentMonth = cal.get(Calendar.MONTH)+1;
					int currentDay = cal.get(Calendar.DAY_OF_MONTH);
					
					int year = Integer.parseInt(exhibit.getStartDate().substring(0, 4));
					int month = Integer.parseInt(exhibit.getStartDate().substring(5, 7));
					int day = Integer.parseInt(exhibit.getStartDate().substring(8, 10));
					
					if(year >= currentYear && month >= currentMonth && day >= currentDay) {
						exhibitions.add(exhibit);						
					}
				}
				nextBtn.click();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			driver.quit();
		}
		for(ExhibitDto e : exhibitions) {
			System.out.println(e);
		}
	}

}
