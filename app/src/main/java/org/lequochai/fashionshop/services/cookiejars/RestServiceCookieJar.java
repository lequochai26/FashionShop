package org.lequochai.fashionshop.services.cookiejars;

import org.lequochai.fashionshop.entities.RestCookie;
import org.lequochai.fashionshop.models.FashionShopDBHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class RestServiceCookieJar implements CookieJar {
//    Fields:
    private FashionShopDBHelper dbHelper;

//    Constructors:
    public RestServiceCookieJar(FashionShopDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            dbHelper.saveCookie(
                    new RestCookie(
                            cookie.name(),
                            cookie.domain(),
                            cookie.value()
                    )
            );
        }
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookies = new ArrayList<>();

        for (RestCookie restCookie : dbHelper.getAllCookies()) {
            Cookie cookie = new Cookie.Builder()
                    .name(restCookie.getCookieKey())
                    .domain(restCookie.getCookieDomain())
                    .value(restCookie.getCookieValue())
                    .build();

            cookies.add(cookie);
        }

        return cookies;
    }
}
