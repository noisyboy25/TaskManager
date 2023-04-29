package com.taskcodee.server.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.taskcodee.server.api.ApiSuccess;
import com.taskcodee.server.dto.boards.BoardCreationDTO;
import com.taskcodee.server.dto.boards.BoardDTO;
import com.taskcodee.server.dto.boards.BoardPutDTO;
import com.taskcodee.server.dto.cards.CardCreationDTO;
import com.taskcodee.server.entities.Board;
import com.taskcodee.server.entities.BoardCard;
import com.taskcodee.server.mappers.BoardMapper;
import com.taskcodee.server.services.BoardService;
import com.taskcodee.server.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardMapper boardMapper;

    @GetMapping("/boards")
    public List<BoardDTO> getBoards() {
        return boardService.findAll().stream().map(boardMapper::mapToBoardDTO).collect(toList());
    }

    @GetMapping("/boards/{id}")
    @JsonView({View.BoardBasic.class})
    public Board getBoardByIdNew(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @PostMapping("/boards")
    public ResponseEntity<Object> createBoardForOwner(@RequestBody BoardCreationDTO boardCreationDTO) {
        boardService.createBoardForOwner(boardCreationDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The board is created!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.CREATED);
    }

    @PutMapping("/boards/{id}")
    public ResponseEntity<Object> putCard(@PathVariable Long id, @RequestBody BoardPutDTO boardPutDTO) {
        Board board = boardService.update(id, boardPutDTO);
        ApiSuccess apiSuccess = new ApiSuccess("The board is updated!", boardMapper.mapToBoardDTO(board));
        return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Object> removeBoard(@PathVariable Long id) {
        boardService.deleteById(id);
        ApiSuccess apiSuccess = new ApiSuccess("The board is removed!");
        return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
    }
}
