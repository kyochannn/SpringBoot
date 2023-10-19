package com.joeun.board.service;

import java.util.List;

import com.joeun.board.dto.Base;

public interface BaseService {
    
    Object update = null;

    // 기본 목록
    public List<Base> list() throws Exception;

    // 기본 조회
    public Base select(int BaseNo) throws Exception;
    
    // 기본 등록
    public int insert(Base Base) throws Exception;

    // 기본 수정
    public int update(Base Base) throws Exception;

    // 기본 삭제
    public int delete(int BaseNo) throws Exception;

   
}
