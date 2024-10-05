package com.matosbrasil.api.exception;

public class CompanyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CompanyException(String message) {
        super(message);
    }

    public CompanyException(String message, Throwable cause) {
        super(message, cause);
    }
}
