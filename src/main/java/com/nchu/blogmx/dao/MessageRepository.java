package com.nchu.blogmx.dao;

import com.nchu.blogmx.bean.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {

    Page<Message> findAll(Specification<Message> title, Pageable pageable);
}
