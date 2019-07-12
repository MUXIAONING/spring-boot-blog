package com.nchu.blogmx.service.impl;

import com.nchu.blogmx.bean.Account;
import com.nchu.blogmx.dao.AccountRepository;
import com.nchu.blogmx.service.AccountService;
import com.nchu.blogmx.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServicelmpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account checkAccount(String username,String password){
        return accountRepository.findAccountByUsernameAndPassword(username, MD5Utils.code(password));
    }
}
