package com.app.quartz.engine.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

	public String restClientOutput(final String url) throws URISyntaxException {
		ResponseEntity<String> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();

//			final String baseUrl = url;
			URI uri = new URI(url);

			HttpHeaders headers = new HttpHeaders();
//			headers.set("X-COM-PERSIST", "true"); 
//			headers.set("X-COM-LOCATION", "USA");

//			HttpEntity<Employee> requestEntity = new HttpEntity<>(null, headers);

			result = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);

		} catch (HttpClientErrorException e) {
			System.out.println(e.getStatusCode());
			System.out.println(e.getResponseBodyAsString());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return result.getBody();
	}
}
