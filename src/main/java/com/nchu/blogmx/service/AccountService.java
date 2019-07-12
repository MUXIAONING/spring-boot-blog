package com.nchu.blogmx.service;

import com.nchu.blogmx.bean.Account;

public interface AccountService {

    Account checkAccount(String username,String password);
}
