package com.infogain.training.model;

import org.springframework.context.ApplicationEvent;

public class AnotherCustomEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private int code;

	public AnotherCustomEvent(Object source, String name, int code) {
        super(source);
		this.name = name;
		this.code = code;
        
    }

	public String getName() {
		return name;
	}
	
	public int getCode() {
		return code;
	}
}
