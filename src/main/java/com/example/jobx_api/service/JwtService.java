package com.example.jobx_api.service;

import com.example.jobx_api.dto.MemberDto;
import com.example.jobx_api.dto.MemberRequestDto;
import com.example.jobx_api.dto.MemberResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface JwtService {

    String createAccessToken(MemberDto memberDto);

    boolean refreshToken(HttpServletRequest request,  HttpServletResponse response, MemberRequestDto memberRequestDto);

    String createRefreshToken(MemberDto memberDto);

    void validateJwtToken(String jwtToken);

    Authentication getAuthentication(String jwtToken);
}
