package com.project.products.exceptions;

public class NoProductsFoundException extends RuntimeException {
    private static final String DEFAULT_ERROR_MESSAGE = "No Products Found.";
    private static final String DEFAULT_DETAILED_MESSAGE = "There are no products at this time.";

    public NoProductsFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

//    public ProductNotFoundException(String detailedMessage) {
//        super(DEFAULT_ERROR_MESSAGE + " " + detailedMessage);
//    }

    public String getDefaultErrorMessage() {
        return DEFAULT_ERROR_MESSAGE;
    }

    public String getDefaultDetailedMessage() {
        return DEFAULT_DETAILED_MESSAGE;
    }
}

