package com.nchu.blogmx.dao;

import com.nchu.blogmx.bean.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AccountRepository extends JpaRepository<Account,Long> {

    Account findAccountByUsernameAndPassword(String username,String password);

}
