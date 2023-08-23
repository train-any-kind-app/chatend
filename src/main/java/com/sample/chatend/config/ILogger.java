package com.sample.chatend.config;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;


public interface ILogger {
	public <T> Logger getLogger(T t);
}
