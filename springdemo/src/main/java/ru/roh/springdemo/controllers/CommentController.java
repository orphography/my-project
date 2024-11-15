package ru.roh.springdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.roh.springdemo.models.Comment;
import ru.roh.springdemo.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Создание нового комментария
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestParam Long taskId, @RequestParam Long userId, @RequestBody String text) {
        return commentService.createComment(taskId, userId, text);
    }

    // Получение комментариев по задаче
    @GetMapping("/task/{taskId}")
    public List<Comment> getCommentsByTask(@PathVariable Long taskId) {
        return commentService.getCommentsByTask(taskId);
    }

    // Получение комментариев по пользователю
    @GetMapping("/user/{userId}")
    public List<Comment> getCommentsByUser(@PathVariable Long userId) {
        return commentService.getCommentsByUser(userId);
    }

    // Удаление комментария
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
