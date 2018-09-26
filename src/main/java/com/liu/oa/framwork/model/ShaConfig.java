package com.liu.oa.framwork.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Component
@Getter
@Setter
public class ShaConfig {

	private String salt ="456852a";
	
	
	private int iterations=1026;
	
	
	
	
}
