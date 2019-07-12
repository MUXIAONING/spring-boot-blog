package com.nchu.blogmx.service;

import com.nchu.blogmx.bean.Notice;

import java.util.List;

public interface NoticeService {

    Notice saveNotice(Notice notice);

    void deleteNotice(Long id);

    Notice updateNotice(Long id,Notice notice);

    Notice getNotice(Long id);

    List<Notice> listNotice();
}
