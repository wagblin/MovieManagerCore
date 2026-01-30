package com.bcefit.projet.service.message;

import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MessageString implements MessageCreator{

	private String message;
	
	public MessageString(String message) {
		this.message = message;
	}

	@Override
	public Message createMessage(Session session) throws JMSException {
		TextMessage tMessage=session.createTextMessage(message);
		return tMessage;
	}

}
