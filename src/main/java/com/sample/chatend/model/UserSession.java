package com.sample.chatend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.annotation.Documented;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserSession {
	@Id
	private String id;
	private String username;
	private Boolean status;
}
