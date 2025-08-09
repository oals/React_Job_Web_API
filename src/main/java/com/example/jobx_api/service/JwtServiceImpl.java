package com.example.jobx_api.service;

import com.example.jobx_api.dao.MemberDao;
import com.example.jobx_api.dto.MemberDto;
import com.example.jobx_api.dto.MemberRequestDto;
import com.example.jobx_api.dto.MemberResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final MemberDao memberDao;
    private final CookieService cookieService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Override
    public String createAccessToken(MemberDto memberDto) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(memberDto.getMemberId()));

        Date now = new Date();
        Date expiry = new Date(now.getTime() + accessTokenExpiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private String getRefreshTokenFromCookie(HttpServletRequest request, String findTokenTitle) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if (findTokenTitle.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    @Override
    public boolean refreshToken(HttpServletRequest request, HttpServletResponse response, MemberRequestDto memberRequestDto) {

        try {

            String refreshToken = getRefreshTokenFromCookie(request, "refreshToken");

            if (refreshToken == null || refreshToken.isBlank()) {
                return false;
            }

            validateJwtToken(refreshToken);

            MemberDto memberDto = memberDao.selectMember(memberRequestDto);

            String accessToken = createAccessToken(memberDto);

            cookieService.saveCookieAuth(response, accessToken, refreshToken);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public String createRefreshToken(MemberDto memberDto) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + refreshTokenExpiration);

        return Jwts.builder()
                .setSubject(String.valueOf(memberDto.getMemberId()))
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    @Override
    public Authentication getAuthentication(String jwtToken) {
        User userDetails = new User(getUserId(jwtToken), "", new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    @Override
    public void validateJwtToken(String jwtToken) {

        Jwts.parserBuilder()
                .setSigningKey(secretKey) // 서명 검증용 키
                .build()
                .parseClaimsJws(jwtToken);

    }

}
