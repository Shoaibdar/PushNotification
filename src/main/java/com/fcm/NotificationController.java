package com.fcm;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class NotificationController {
	
	static String postUrl = "https://fcm.googleapis.com/fcm/send";
	//static String token = "dTgMt10wFr-gL2YDjeTjVA:APA91bG8SM2VW3qPFkrMR3mq4xkIfFdnx-TWT0WhfcJVp7cHeX9O8j0qUnHY9FNE8wYDNCM6d7wMbtei7ZXYQI6vE5T_WaC1zpuNZpeiC5-ZzFpKfaeRjkZuL9uae8dvDnicrCtoawsx";

	@GetMapping("/test")
	public String test() {
		return "working...";
	}
	
	@GetMapping("/send{token}/{fcmServerKey}")
	public void sendNotification(@PathVariable String token, @PathVariable String fcmServerKey) {

		 // Set the FCM API endpoint
        String fcmUrl = "https://fcm.googleapis.com/fcm/send";
        
        // Set the notification body
        String notificationBody = "OTP for Keycloak login 358482";
        
        // Create the HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", fcmServerKey);
        
        // Create the request body
        String requestBody = "{\n" +
                "    \"to\": \"" + token + "\",\n" +
                "    \"notification\": {\n" +
                "        \"body\": \"" + notificationBody + "\",\n" +
                "        \"title\": \"bKeycloak\",\n" +
                "        \"subtitle\": \"Subtitile\"\n" +
                "    }\n" +
                "}";
        
        // Create the HTTP entity
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        
        // Create the RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        
        // Send the HTTP request
        ResponseEntity<String> response = restTemplate.exchange(fcmUrl, HttpMethod.POST, entity, String.class);
        
        // Process the response
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Notification sent successfully");
        } else {
            System.out.println("Failed to send notification. Response: " + response.getBody());
        }
	}
}
