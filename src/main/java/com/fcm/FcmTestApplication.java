package com.fcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class FcmTestApplication {
	
	static String postUrl = "https://fcm.googleapis.com/fcm/send";
	static String token = "dTgMt10wFr-gL2YDjeTjVA:APA91bG8SM2VW3qPFkrMR3mq4xkIfFdnx-TWT0WhfcJVp7cHeX9O8j0qUnHY9FNE8wYDNCM6d7wMbtei7ZXYQI6vE5T_WaC1zpuNZpeiC5-ZzFpKfaeRjkZuL9uae8dvDnicrCtoawsx";

	public static void main(String[] args) {
		SpringApplication.run(FcmTestApplication.class, args);
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "key=AAAAZtmE6_Y:APA91bHXqVeMomsY5XghcDTv1d2NsUFjSQvavUjnG81seltbOB5WX8kbSSUH9pqgjGoYIj6tDjdPMluxog7lczehVDBA-tpQfApEZ-GAnXSxsLYZiRtpzrWUspxqjUh2zFcDp5nLRcnS");
			headers.setContentType(MediaType.APPLICATION_JSON);
	
			Data body = new Data(token, "title", "body");

			String json = new ObjectMapper().writeValueAsString(body);
			HttpEntity<String> entity = new HttpEntity<>(json, headers);

			Object res = restTemplate.exchange(postUrl, HttpMethod.POST, entity, Object.class);
			System.out.println(res);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

class Data {

	String token;
	String title;
	String body;
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public Data(String token, String title, String body) {
		super();
		this.token = token;
		this.title = title;
		this.body = body;
	}

}
