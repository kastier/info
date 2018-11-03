package com.xjsoftware.com.info.manager;


public class CookieSet {
    private static final String COOKIE_PREFIX="client_token_%s";

    public static String getClientCookieName()
    {
        return String.format(COOKIE_PREFIX,"client");
    }


    public static String getAdminCookieName()
    {
        return String.format(COOKIE_PREFIX,"admin");
    }
}
