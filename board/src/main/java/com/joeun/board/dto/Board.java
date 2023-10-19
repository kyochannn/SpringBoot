package com.joeun.board.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * Board 
 * - 게시글 정보
 */
@Data
public class Board {
    private int boardNo; 
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private Date updDate;
    private int views;

    private List<MultipartFile> file;
}





 