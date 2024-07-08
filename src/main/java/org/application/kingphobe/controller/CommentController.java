package org.application.kingphobe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.application.kingphobe.dto.CommentDTO;
import org.application.kingphobe.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/comments", consumes = {"application/json", "application/*+json", "text/json"}, produces = {"text/plain", "application/json", "text/json"})
@Tag(name = "Comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    @Operation(summary = "Create new comment")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.saveComment(commentDTO));
    }

    @GetMapping
    @Operation(summary = "Get all comments")
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get comments by ID")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete comments")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
