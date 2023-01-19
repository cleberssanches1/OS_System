package com.valdir.os.exception;

import java.io.Serializable;

/**
 * 
 * @author 7700364525
 *
 */
public class StandardError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7081239580829520452L;
	
	private Long TimeStamp;
	private Integer status;
	private String messages;

	public Long getTimeStamp() {
		return TimeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		TimeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public StandardError(Long timeStamp, Integer status, String messages) {
		super();
		TimeStamp = timeStamp;
		this.status = status;
		this.messages = messages;
	}

	public StandardError() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TimeStamp == null) ? 0 : TimeStamp.hashCode());
		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardError other = (StandardError) obj;
		if (TimeStamp == null) {
			if (other.TimeStamp != null)
				return false;
		} else if (!TimeStamp.equals(other.TimeStamp))
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
