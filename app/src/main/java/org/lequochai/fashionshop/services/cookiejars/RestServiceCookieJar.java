package org.lequochai.fashionshop.services.cookiejars;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class RestServiceCookieJar implements CookieJar {
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return null;
    }
}
