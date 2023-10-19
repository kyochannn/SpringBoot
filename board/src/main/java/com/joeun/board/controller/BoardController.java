package com.joeun.board.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.board.dto.Board;
import com.joeun.board.dto.Files;
import com.joeun.board.service.BoardService;
import com.joeun.board.service.FileService;

import lombok.extern.slf4j.Slf4j;





/**
 * 게시판 컨트롤러
 * - 게시글 목록 - [GET] - /board/list
 * - 게시글 조회 - [GET] - /board/read
 * - 게시글 등록 - [GET] - /board/insert
 * - 게시글 목록 처리 - [POST] - /board/insert
 * - 게시글 수정 - [GET] - /board/update
 * - 게시글 수정 처리 - [POST] - /board/update
 * - 게시글 삭제 처리 - [POST] - /board/delete
 */

@Slf4j // 로거를 간단하게 사용하는 방법 : 이 어노테이션 쓰기
@Controller
@RequestMapping("/board")
public class BoardController {

    // 한꺼번에 import : alt + shift + O

    @Autowired // 의존성 자동 주입 :) 
    private BoardService boardService;

    @Autowired
    private FileService fileService;

    /**
     * 게시글 목록
     * [GET]
     * /board/list
     * model : boardList
     * 
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/list")
    public String list(Model model) throws Exception {
        log.info("[GET] - /board/list");

        // 데이터 요청
        List<Board> boardList = boardService.list();
        // 모델 등록
        model.addAttribute("boardList", boardList);             // :(   : 모델 등록 관련해서 조금 더 공부할 필요
        // 뷰 페이지 지정
        return "board/list";
    }


    /**
     * 게시글 조회
     * * [GET]
     * /board/read
     * model : board
     * @param boardNo
     * @return
     * @throws Exception
     */
    @GetMapping(value="/read")
    public String read(Model model, int boardNo, Files files) throws Exception {


        // 데이터 요청
        Board board = boardService.select(boardNo);     // 게시글 정보

        files.setParentTable("board");
        files.setParentNo(boardNo);
        List<Files> fileList = fileService.listByParent(files);     // 파일 정보

        // 모델 등록        
        model.addAttribute("board", board);
        model.addAttribute("fileList", fileList);
        // 뷰 페이지 지정
        return "board/read";
    }
    

    /**
     * 게시글 쓰기
     * [GET]
     * /board/insert
     * model : ✖
     * @return
     */
    @GetMapping(value="/insert")
    public String insert(@ModelAttribute Board board) {
        return "board/insert";
    }

    /**
     * 게시글 쓰기 처리
     * [POST]
     * /board/list
     * model : ✖
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping(value="/insert")
    public String insertProString(@ModelAttribute Board board) throws Exception {
        // @ModelAttribute : 모델에 자동으로 등록해주는 어노테이션      // :(       @ModelAttribute     : ?
        // 데이터 처리
        int result = boardService.insert(board);

        // result == 0 이라면, 게시글 쓰기 실패
        // 따라서 board/insert로 다시 보내기
        if(result == 0 ) return "board/insert";

        // 뷰 페이징 처리
        return "redirect:/board/list";              // :(   redirect:/ 에 대한 이해 필요
    }

    /**
     * 게시글 수정
     * [GET]
     * /board/update
     * model : board
     * @param model                                 // :(   주석에 이런식으로 해주면 어떤 역할을 하는지 궁금함
     * @param boardNo
     * @return
     * @throws Exception
     */
    @GetMapping(value="/update")
    public String update(Model model, int boardNo) throws Exception {
        // 데이터 요청
        Board board = boardService.select(boardNo);
        // 모델 등록
        model.addAttribute("board", board);
        // 뷰 페이지 처리
        return "board/update";
    }
    
    /**
     * 게시글 수정 처리
     * [POST]
     * /board/update
     * model : board
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping(value="/update")
    public String updatePro(@ModelAttribute Board board) throws Exception {
        // 데이터 처리
        int result = boardService.update(board);
        int boardNo = board.getBoardNo();                                          // :) 롬복 관련 에러 : 재실행 하니까 해결

        // 게시글 수정 실패 > 게시글 수정 화면
        if ( result == 0 ) return "redirect:/board/update?boardNo=" + boardNo;     // :(  이렇게 해준 이유 다시 한 번 생각해보기

        // 뷰 페이지 이동
        return "redirect:/board/list";                                              // :) 여기서 에러가 발생했는데, 
                                                                                    // return "redirect:/board/update"; redirect로 보내니까 
                                                                                    // boardNo를 가지지 않고 update로 redirect해주니까 아무것도 들고가지 않기 때문에
                                                                                    // 즉 null을 보내기 때문에 에러 발생함.
                                                                                    
    }
    
    /**
     * 게시글 삭제 처리
     * [POST]
     * /board/delete
     * model : X
     * @param boardNo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/delete")
    public String deletePro(int boardNo) throws Exception{
        // 데이터 처리
        int result = boardService.delete(boardNo);
        
        // 게시글 수정 실패 > 게시글 수정 화면
        if(result == 0) return "redirect:/board/update?boardNo=" + boardNo;     // :(  이렇게 해준 이유 다시 한 번 생각해보기

        // 뷰 페이지 이동
        return "redirect:/board/list";
    }
    

}
