package com.queasy.utility.enums;

public enum RequestType {
    UNFRIEND("unfriend"),
    UNSEND_REQUEST("unsend_request"),
    ACCEPT_REQUEST("accept_request"),
    SEND_REQUEST("send_request");
    private final String text;

    RequestType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
