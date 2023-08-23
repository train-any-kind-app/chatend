package com.sample.chatend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

//@Component
public class LoggerImpl {

//	private T t = (T) new Object();
//
//
//	public LoggerImpl(final T t) {
//		this.t=t;
//	}
	@Autowired

	public static <T> Logger getLogger(T t) {
		return (Logger) LoggerFactory.getLogger(t.getClass());
	}
}
