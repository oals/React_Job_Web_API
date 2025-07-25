package com.example.jobx_api.controller;

import com.example.jobx_api.dto.MemberRequestDto;
import com.example.jobx_api.dto.MemberResponseDto;
import com.example.jobx_api.service.MemberService;
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

    @PostMapping("/api/auth/login")
    public ResponseEntity<String> memberLogin(HttpServletResponse response, @RequestBody MemberRequestDto memberRequestDto) {

        try {
            memberService.memberLogin(response, memberRequestDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("로그인 성공");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("비밀번호가 일치하지 않습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("서버 오류로 로그인에 실패했습니다.");
        }

    }


}
