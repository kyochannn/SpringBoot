package com.joeun.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Files {
    private int fileNo;
    private String parentTable;
    private int parentNo;
    private String fileName;
    private String originName;
    private String filePath;
    private long fileSize;
    private Date regDate;
    private Date updDate;
    private int fileCode;
}
