package com.nchu.blogmx.service.impl;

import com.nchu.blogmx.bean.Notice;
import com.nchu.blogmx.dao.NoticeRepository;
import com.nchu.blogmx.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServicelmpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public Notice saveNotice(Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);

    }

    @Override
    public Notice updateNotice(Long id, Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public Notice getNotice(Long id) {
        return noticeRepository.getOne(id);
    }

    @Override
    public List<Notice> listNotice() {
        return noticeRepository.findAll();
    }
}
