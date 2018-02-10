package com.avatarcn.tourists.service;

import com.avatarcn.tourists.exception.ErrorCodeException;
import com.avatarcn.tourists.exception.TouristsErrorCode;
import com.avatarcn.tourists.mapper.AccountMapper;
import com.avatarcn.tourists.model.user.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by z1ven on 2018/1/22 09:26
 */
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public Account insert(Integer userId) {
        Account account = accountMapper.selectByServerId(String.valueOf(userId));
        if (account == null) {
            account = new Account();
            account.setServer_id(String.valueOf(userId));
            account.setVisible(1);
            accountMapper.insert(account);
        }
        return account;
    }

    public Account selectById(Integer id) throws ErrorCodeException {
        Account account = accountMapper.selectById(id);
        if (account == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
        }
        return account;
    }

    public Account selectByServerId(Integer userId) throws ErrorCodeException {
        Account account = accountMapper.selectByServerId(String.valueOf(userId));
        if (account == null) {
            throw new ErrorCodeException(TouristsErrorCode.ACCOUNT_NULL);
        }
        return account;
    }
}
