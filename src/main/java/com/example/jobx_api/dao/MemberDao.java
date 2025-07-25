package com.example.jobx_api.dao;

import com.example.jobx_api.dto.MemberDto;
import com.example.jobx_api.dto.MemberRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

    int insertMember(MemberRequestDto memberRequestDto);

    MemberDto selectMember(MemberRequestDto memberRequestDto);

}
