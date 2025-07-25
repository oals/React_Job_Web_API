package com.example.jobx_api.service;

import com.example.jobx_api.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{

//    private final JwtService jwtService;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    private final StringRedisTemplate redisTemplate;

    @Override
    public void saveRefreshToken(Long memberId, String refreshToken) throws Exception {
        try {
            String key = "RT:" + memberId;
            String value = refreshToken;

            long ttlSeconds = refreshTokenExpiration / 1000;

            redisTemplate.opsForValue().set(key, value, ttlSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Redis에 RefreshToken 저장 실패: : " + e.getMessage());
            throw new Exception("Refresh Token 저장 실패", e);
        }
    }

}
