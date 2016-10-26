package com.zeus.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/***
 * ≈‰÷√Œƒº˛
 * 
 * @author zengjin
 *
 */
@Component
public class ApiCfg {
	@Value("#{configProperties['name']}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
