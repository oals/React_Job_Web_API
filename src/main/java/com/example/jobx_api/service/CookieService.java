package com.example.jobx_api.service;

import jakarta.servlet.http.HttpServletResponse;

public interface CookieService {

    void saveCookieAuth(HttpServletResponse response,String accessToken, String refreshToken);

    void deleteCookieAuth(HttpServletResponse response);
}
