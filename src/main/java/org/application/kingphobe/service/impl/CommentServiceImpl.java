package org.application.kingphobe.service.impl;

import org.application.kingphobe.dto.CommentDTO;
import org.application.kingphobe.model.Comment;
import org.application.kingphobe.repository.CommentRepository;
import org.application.kingphobe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDTO saveComment(CommentDTO commentDTO) {
        Comment comment = convertToEntity(commentDTO);
        comment = commentRepository.save(comment);
        return convertToDTO(comment);
    }

    @Override
    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        return convertToDTO(comment);
    }

    @Override
    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        // Assuming Product and User entities are already loaded from the database
        // and set to the comment entity
        comment.setCommentId(commentDTO.getCommentId());
        comment.setContent(commentDTO.getContent());
        comment.setRating(commentDTO.getRating());
        comment.setCommentDate(commentDTO.getCommentDate());
        return comment;
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(comment.getCommentId());
        commentDTO.setProductId(comment.getProduct().getProductId());
        commentDTO.setUserId(comment.getUser().getUserId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setRating(comment.getRating());
        commentDTO.setCommentDate(comment.getCommentDate());
        return commentDTO;
    }
}