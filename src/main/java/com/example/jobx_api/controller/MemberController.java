package com.example.jobx_api.controller;

import com.example.jobx_api.dto.MemberRequestDto;
import com.example.jobx_api.dto.MemberResponseDto;
import com.example.jobx_api.service.JwtService;
import com.example.jobx_api.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;

    @PostMapping("/api/auth/register")
    public ResponseEntity<String> memberRegister(@RequestBody MemberRequestDto memberRequestDto){

        boolean result = memberService.memberRegister(memberRequestDto);

        if (result) {
            return ResponseEntity.ok("회원가입 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("회원가입 실패");
        }
    }

    @PostMapping("/api/auth/refresh")
    public ResponseEntity<String> memberTokenRefresh(HttpServletRequest request, HttpServletResponse response, @RequestBody MemberRequestDto memberRequestDto){

        boolean result = jwtService.refreshToken(request, response, memberRequestDto);

        if (result) {
            return ResponseEntity.ok("갱신 성공");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("갱신 실패");
        }
    }

    @PostMapping("/api/auth/logout")
    public ResponseEntity<MemberResponseDto> memberLogout( HttpServletResponse response) {

        try {
            MemberResponseDto memberResponseDto = memberService.memberLogout(response);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(memberResponseDto);
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(MemberResponseDto.builder()
                            .message("아이디 또는 비밀번호가 일치하지 않습니다.")
                            .build());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MemberResponseDto.builder()
                            .message("서버 오류가 발생했습니다.")
                            .build());
        }

    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<MemberResponseDto> memberLogin(
            HttpServletResponse response,
            @RequestBody MemberRequestDto memberRequestDto) {

        try {
            MemberResponseDto memberResponseDto = memberService.memberLogin(response, memberRequestDto);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(memberResponseDto);
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(MemberResponseDto.builder()
                            .message("아이디 또는 비밀번호가 일치하지 않습니다.")
                            .build());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MemberResponseDto.builder()
                            .message("서버 오류가 발생했습니다.")
                            .build());
        }
    }



}
