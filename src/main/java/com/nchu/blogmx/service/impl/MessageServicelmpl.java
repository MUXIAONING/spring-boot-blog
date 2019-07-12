package com.nchu.blogmx.service.impl;

import com.nchu.blogmx.bean.Message;
import com.nchu.blogmx.bean.MessageQuery;
import com.nchu.blogmx.dao.MessageRepository;
import com.nchu.blogmx.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServicelmpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;


    @Transactional
    @Override
    public List<Message> listMessages() {

        Sort sort = new Sort("createTime");


        return messageRepository.findAll(sort.descending());
    }


    @Transactional
    @Override
    public Message saveMessage(Message message)  {
        return messageRepository.save(message);
    }

    @Override
    public Page<Message> listMessages(Pageable pageable) {

        return messageRepository.findAll(pageable);
    }

    @Override
    public Message getMessage(Long id) {
        return messageRepository.getOne(id);
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Page<Message> listBlog(Pageable pageable, MessageQuery MessageQuery) {
        return messageRepository.findAll(new Specification<Message>() {
            @Override
            public Predicate toPredicate(Root<Message> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                if(!"".equals(MessageQuery.gettouristName())&&MessageQuery.gettouristName()!=null){

                    predicates.add(criteriaBuilder.like(root.<String>get("touristName"),"%"+MessageQuery.gettouristName()+"%"));
                }

                query.where(predicates.toArray(new Predicate[predicates.size()]));

                return null;
            }
        },pageable);

    }

}
