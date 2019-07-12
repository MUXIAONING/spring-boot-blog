package com.nchu.blogmx.dao;

import com.nchu.blogmx.bean.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
