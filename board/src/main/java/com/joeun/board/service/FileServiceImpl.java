package com.joeun.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.board.dto.Files;
import com.joeun.board.mapper.FileMapper;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<Files> list() throws Exception {
        List<Files> fileList = fileMapper.list();
        return fileList;
    }

    @Override
    public Files select(int boardNo) throws Exception {
        Files file = fileMapper.select(boardNo);
        return file;
    }

    @Override
    public int insert(Files board) throws Exception {
        int result = fileMapper.insert(board);
        return result;
    }

    @Override
    public int update(Files board) throws Exception {
        int result = fileMapper.update(board);
        return result;
    }

    @Override
    public int delete(int boardNo) throws Exception {
        int result = fileMapper.delete(boardNo);
        return result;
    }

    @Override
    public List<Files> listByParent(Files file) throws Exception {
        List<Files> fileList = fileMapper.listByParent(file);
        return fileList;
    }

    @Override
    public int deleteByParent(Files file) throws Exception {
        int result = fileMapper.deleteByParent(file);
        return result;
    }

    
}
