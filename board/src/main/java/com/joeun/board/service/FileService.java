package com.joeun.board.service;

import java.util.List;

import com.joeun.board.dto.Files;

public interface FileService {

    // 파일 조회 목록
    public List<Files> list() throws Exception;

    // 파일 조회 조회
    public Files select(int boardNo) throws Exception;
    
    // 파일 조회 등록
    public int insert(Files board) throws Exception;

    // 파일 조회 수정
    public int update(Files board) throws Exception;

    // 파일 조회 삭제
    public int delete(int boardNo) throws Exception;



    // 파일 목록 - 부모 기준
    public List<Files> listByParent(Files file) throws Exception;
    // 파일 삭제 - 부모 기준
    public int deleteByParent(Files file) throws Exception;

   
}
