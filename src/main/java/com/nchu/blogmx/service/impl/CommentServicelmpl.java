package com.nchu.blogmx.service.impl;

import com.nchu.blogmx.bean.Comment;
import com.nchu.blogmx.dao.CommentRepository;
import com.nchu.blogmx.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServicelmpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment saveComment(Comment comment) {

        Long pId = comment.getParentComment().getId();
        if (pId!=-1){
            comment.setParentComment(commentRepository.getOne(pId));

        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.getOne(id);
    }

    @Override
    public List<Comment> listComment() {
        Sort sort = new Sort("createTime");
        return commentRepository.findAll();
    }

    @Override
    public Page<Comment> listComment(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = new Sort("createTime");

        return commentRepository.findByBlogId(blogId,sort);
    }
}
