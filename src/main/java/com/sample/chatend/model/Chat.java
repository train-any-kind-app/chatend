package com.sample.chatend.model;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Chat {
	@Id
	private String id;
	private String sender;
	private String recipent;
	private String date;
	private String message;
	private String session;
	//private MsgStatus status;
}
