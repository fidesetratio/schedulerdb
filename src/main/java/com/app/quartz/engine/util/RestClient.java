package com.app.quartz.engine.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

	private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

	public String restClientOutput(String url, HttpMethod method, Object objectRequest) throws URISyntaxException {
		ResponseEntity<String> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(url);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			// testing object
//			JSONObject json = new JSONObject();
//			json.put("id", 1);
//			json.put("title", "foo");
//			json.put("bar", "bar");
//			json.put("userId", 1);

			HttpEntity<Object> requestEntity = new HttpEntity<>(objectRequest, headers);
			
			result = restTemplate.exchange(uri, method, requestEntity, String.class);			
			
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			logger.debug("RestClient. Http client error exception");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("RestClient. Exception");
		}
		return result.getBody();
	}
}
