package com.nchu.blogmx.service;

import com.nchu.blogmx.bean.Message;
import com.nchu.blogmx.bean.MessageQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {


    Message getMessage(Long id);

    Message saveMessage(Message message);

    void deleteMessage(Long id);

    List<Message> listMessages();

    Page<Message> listMessages(Pageable pageable);


    Page<Message> listBlog(Pageable pageable, MessageQuery MessageQuery);

}
