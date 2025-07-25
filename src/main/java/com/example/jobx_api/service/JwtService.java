package com.example.jobx_api.service;

import com.example.jobx_api.dto.MemberDto;
import com.example.jobx_api.dto.MemberResponseDto;
import org.springframework.security.core.Authentication;

public interface JwtService {

    String createAccessToken(MemberDto memberDto);

    String createRefreshToken(MemberDto memberDto);

    void validateJwtToken(String jwtToken);

    Authentication getAuthentication(String jwtToken);
}
