package org.lequochai.fashionshop.entities;

public class RestCookie {
//    Fields:
    private String cookieKey;
    private String cookieValue;

//    Constructors:
    public RestCookie() {
    }

    public RestCookie(String cookieKey, String cookieValue) {
        this.cookieKey = cookieKey;
        this.cookieValue = cookieValue;
    }

//    Getters / setters:
    public String getCookieKey() {
        return cookieKey;
    }

    public void setCookieKey(String cookieKey) {
        this.cookieKey = cookieKey;
    }

    public String getCookieValue() {
        return cookieValue;
    }

    public void setCookieValue(String cookieValue) {
        this.cookieValue = cookieValue;
    }
}
