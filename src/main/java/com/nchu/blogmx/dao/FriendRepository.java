package com.nchu.blogmx.dao;

import com.nchu.blogmx.bean.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend,Long> {

}
