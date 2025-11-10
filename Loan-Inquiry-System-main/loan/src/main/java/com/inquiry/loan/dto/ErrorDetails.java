package com.inquiry.loan.dto;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	private String message;
	private String details;
    
    // Replaced @NoArgsConstructor
    public ErrorDetails() {
    }

    // Replaced @AllArgsConstructor
    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Replaced @Getter
    public Date getTimestamp() {
        return timestamp;
    }

    // Replaced @Setter
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    // Replaced @Getter
    public String getMessage() {
        return message;
    }

    // Replaced @Setter
    public void setMessage(String message) {
        this.message = message;
    }

    // Replaced @Getter
    public String getDetails() {
        return details;
    }

    // Replaced @Setter
    public void setDetails(String details) {
        this.details = details;
    }
}