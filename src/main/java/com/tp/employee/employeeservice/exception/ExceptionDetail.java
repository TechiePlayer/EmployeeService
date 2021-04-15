package com.tp.employee.employeeservice.exception;

import java.util.Date;

public class ExceptionDetail {
    private String message;
    private String details;
    private Date date;

    public ExceptionDetail(String message, String details, Date date) {
        this.message = message;
        this.details = details;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public Date getDate() {
        return date;
    }
}
