package com.example.jobx_api.service;

import com.example.jobx_api.dto.MemberDto;
import com.example.jobx_api.dto.MemberRequestDto;
import com.example.jobx_api.dto.MemberResponseDto;
import jakarta.servlet.http.HttpServletResponse;

public interface MemberService {

    boolean memberRegister(MemberRequestDto memberRequestDto);

    MemberResponseDto memberLogin(HttpServletResponse response, MemberRequestDto memberRequestDto) throws Exception;

    MemberResponseDto memberLogout(HttpServletResponse response);

    MemberDto memberInfo(String memberEmail);
}
