package com.better.eat.service;

import com.better.eat.domain.ResponseBean;
import com.better.eat.domain.document.UserDocument;

/**
 * @author andy
 */
public interface UserService {
    /**
     * 新增用户
     * @param bean
     */
    ResponseBean addUser(UserDocument bean);
}
