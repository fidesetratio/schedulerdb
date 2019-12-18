package com.app.quartz.engine.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.app.quartz.engine.dto.ServerResponse;

@Component
public class RestClient {

	private static final Logger logger = LoggerFactory.getLogger(RestClient.class);

	public ServerResponse restClientOutput(String url, HttpMethod method, Object objectRequest) throws URISyntaxException {
		ServerResponse result = new ServerResponse();
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = new URI(url);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			// contoh object ke json
//			JSONObject json = new JSONObject();
//			json.put("id", 1);
//			json.put("title", "foo");
//			json.put("bar", "bar");
//			json.put("userId", 1);

			HttpEntity<Object> requestEntity = new HttpEntity<>(objectRequest, headers);
			
			ResponseEntity<String> restReponse = restTemplate.exchange(uri, method, requestEntity, String.class);	
			result.setStatusCode(restReponse.getStatusCode().value());
			result.setData(restReponse.getBody());
		} catch (HttpClientErrorException e) {
			result.setStatusCode(e.getStatusCode().value());
			result.setData(e.getMessage());
			e.printStackTrace();
			logger.debug("RestClient:restClientOutput. Http client error exception");
		} catch (Exception e1) {
			result.setStatusCode(HttpStatus.BAD_GATEWAY.value());
			result.setData(e1.getMessage());
		    e1.printStackTrace();
		    logger.debug("RestClient:restClientOutput. Exception");
		}
		
		return result;
	}
}
