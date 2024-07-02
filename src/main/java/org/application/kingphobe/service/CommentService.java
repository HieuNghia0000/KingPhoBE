package org.application.kingphobe.service;

import org.application.kingphobe.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO saveComment(CommentDTO commentDTO);
    List<CommentDTO> getAllComments();
    CommentDTO getCommentById(Integer id);
    void deleteComment(Integer id);
}
