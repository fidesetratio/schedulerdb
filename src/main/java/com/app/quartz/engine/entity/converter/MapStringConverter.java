package com.app.quartz.engine.entity.converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapStringConverter  implements AttributeConverter<HashMap,String>{

	@Override
	public String convertToDatabaseColumn(HashMap map) {
		// TODO Auto-generated method stub
		 ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public HashMap convertToEntityAttribute(String msg) {
		// TODO Auto-generated method stub
	
	    ObjectMapper mapper = new ObjectMapper();
	    HashMap map = new HashMap();
		try {
			if(msg != null)
			map = mapper.readValue(msg, HashMap.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return map;
	}

}
