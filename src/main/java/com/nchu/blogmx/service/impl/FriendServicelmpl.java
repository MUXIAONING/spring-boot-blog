package com.nchu.blogmx.service.impl;

import com.nchu.blogmx.bean.Friend;
import com.nchu.blogmx.dao.FriendRepository;
import com.nchu.blogmx.error.NotFoundException;
import com.nchu.blogmx.service.FriendService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServicelmpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public Friend saveFriend(Friend friend) {
        return friendRepository.save(friend);
    }

    @Override
    public void deleteFriend(Long id) {
        friendRepository.deleteById(id);

    }

    @Override
    public Friend updateFriend(Long id, Friend friend) {
        Friend f = friendRepository.getOne(id);
        if (f==null){
            throw new NotFoundException("没有当前的友链");
        }
        BeanUtils.copyProperties(friend,f);
        return friendRepository.save(f);
    }

    @Override
    public Friend getFriend(Long id) {
        return friendRepository.getOne(id);
    }

    @Override
    public List<Friend> listFriend() {
        return friendRepository.findAll();
    }
}
