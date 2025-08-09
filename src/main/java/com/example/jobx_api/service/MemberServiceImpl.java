package com.example.jobx_api.service;

import com.example.jobx_api.dao.MemberDao;
import com.example.jobx_api.dto.MemberDto;
import com.example.jobx_api.dto.MemberRequestDto;
import com.example.jobx_api.dto.MemberResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;
    private final RedisService redisService;
    private final CookieService cookieService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public boolean memberRegister(MemberRequestDto memberRequestDto) {

        String rawPassword = memberRequestDto.getMemberPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        memberRequestDto.setMemberPassword(encodedPassword);

        return memberDao.insertMember(memberRequestDto) > 0;
    }

    @Override
    public MemberResponseDto memberLogin(HttpServletResponse response, MemberRequestDto memberRequestDto) throws Exception {

        MemberDto memberDto = memberDao.selectMember(memberRequestDto);

        if (!passwordEncoder.matches(memberRequestDto.getMemberPassword(), memberDto.getMemberPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtService.createAccessToken(memberDto);
        String refreshToken = jwtService.createRefreshToken(memberDto);

        redisService.saveRefreshToken(memberDto.getMemberId(),refreshToken);

        cookieService.saveCookieAuth(response,accessToken,refreshToken);

        return MemberResponseDto.builder()
                .memberId(memberDto.getMemberId())
                .message("로그인 성공")
                .build();

    }

    @Override
    public MemberResponseDto memberLogout(HttpServletResponse response) {

        cookieService.deleteCookieAuth(response);

        return MemberResponseDto.builder()
                .message("로그아웃 성공")
                .build();
    }

    @Override
    public MemberDto memberInfo(String memberEmail) {

        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .memberEmail(memberEmail)
                .build();

        return memberDao.selectMember(memberRequestDto);
    }
}
