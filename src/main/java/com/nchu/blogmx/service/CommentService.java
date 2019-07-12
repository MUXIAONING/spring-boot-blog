package com.nchu.blogmx.service;

import com.nchu.blogmx.bean.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);

    void deleteComment(Long id);

    Comment getComment(Long id);

    List<Comment> listComment();

    Page<Comment> listComment(Pageable pageable);

    List<Comment> listCommentByBlogId(Long blogId);

}
