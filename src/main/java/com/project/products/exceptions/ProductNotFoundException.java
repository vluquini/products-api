package com.project.products.exceptions;

public class ProductNotFoundException extends RuntimeException {

//    public ProductNotFoundException() {
//        super("Product not found.");
//    }
//
//    public ProductNotFoundException(String message) {
//        super(message);
//    }

    private static final String DEFAULT_ERROR_MESSAGE = "Product not found.";
    private static final String DEFAULT_DETAILED_MESSAGE = "The requested product could not be found.";

    public ProductNotFoundException() {
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

