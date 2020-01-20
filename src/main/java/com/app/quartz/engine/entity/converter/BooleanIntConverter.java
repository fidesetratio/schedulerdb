package com.app.quartz.engine.entity.converter;

import javax.persistence.AttributeConverter;

public class BooleanIntConverter implements AttributeConverter<Boolean,Integer> {

	@Override
	public Integer convertToDatabaseColumn(Boolean attribute) {
		// TODO Auto-generated method stub
		return (attribute!= null && attribute) ?1:0;
	}

	@Override
	public Boolean convertToEntityAttribute(Integer value) {
		// TODO Auto-generated method stub
		
		if(value == 0) {
			return false;
		}else if(value == 1) {
			return true;
		}
		return false;
		
	}

}
