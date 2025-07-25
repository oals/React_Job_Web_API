package com.example.jobx_api.service;

import com.example.jobx_api.dto.MemberResponseDto;

public interface RedisService {

    void saveRefreshToken(Long memberId, String refreshToken) throws Exception;

}
