package com.ssafy.artmate.service;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AwsS3Service {

	@Autowired
	private AmazonS3Client AwsS3Client;
	
	@Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;

	private String bucketName="artmatebucket";
	
	//@Value("${cloud.aws.region.static}")
	private String region="ap-northeast-2";

	public String uploadObject(MultipartFile multipartFile, String storedFileName, String folder) throws IOException {
		System.out.println("서버에 이미지 업로드 하러 넘어옴");
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentType(multipartFile.getContentType());
		omd.setContentLength(multipartFile.getSize());
		omd.setHeader("filename", multipartFile.getOriginalFilename());

		// Copy file to the target location (Replacing existing file with the same name)
		int random = (int) Math.floor(Math.random()*99999);
		
		AwsS3Client.putObject(new PutObjectRequest(bucketName+"/"+folder, random+storedFileName,
				multipartFile.getInputStream(), omd));
		
		//return "https://"+ bucketName +".s3." + region + ".amazonaws.com/"+date.format(new Date())+ "/" + storedFileName; //이미지 url 리턴
		String url = AwsS3Client.getResourceUrl(bucketName, folder+"/"+random+storedFileName); //서버에 저장된 이미지 url 리턴
		System.out.println(url);
		return url;
	}

	public void deleteObject(String url, String folder) throws AmazonServiceException {
		System.out.println("서버에 올라간 이미지도 삭제 완료");
		String totalFileName = url.substring(url.indexOf(folder+"/"));
		System.out.println(totalFileName);
		AwsS3Client.deleteObject(new DeleteObjectRequest(bucketName, totalFileName));
	}

	public Resource getObject(String date, String storedFileName) throws IOException {
		S3Object o = AwsS3Client.getObject(new GetObjectRequest(bucketName + "/" + date, storedFileName));
		S3ObjectInputStream objectInputStream = o.getObjectContent();
		byte[] bytes = IOUtils.toByteArray(objectInputStream);
		
		Resource resource = new ByteArrayResource(bytes);
		return resource;
	}
	
}
