package com.nchu.blogmx.service.impl;

import com.nchu.blogmx.bean.Tag;
import com.nchu.blogmx.dao.TagRepository;
import com.nchu.blogmx.error.NotFoundException;
import com.nchu.blogmx.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServicelmpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);

    }


    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.getOne(id);
        if (t==null){
            throw new NotFoundException("没能找到当前的标签");
        }
        BeanUtils.copyProperties(tag, t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagRepository.getOne(id);

    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.getTagByName(name);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag(String ids) {
        List<Tag> list = new ArrayList<>();

        if (!"".equals(ids)&&ids!=null){
            String[] sdarray = ids.split(",");
            for (int i=0;i<sdarray.length;i++){
                Long temid = Long.parseLong(sdarray[i]);
                list.add(tagRepository.getOne(temid));
            }
        }

        return list;
    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);

        return tagRepository.findTop(pageable);
    }
}
