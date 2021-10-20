package com.better.eat.service;

import com.better.eat.domain.ResponseBean;
import com.better.eat.domain.document.UserDocument;

import java.util.HashMap;

/**
 * @author andy
 */
public interface UserService {
    /**
     * add user info
     * @param bean
     */
    ResponseBean addUser(UserDocument bean);

    /**
     * update user info
     * @param bean
     * @return
     */
    ResponseBean updateUser(UserDocument bean);

    /**
     * delete user info by user id
     * @param id user id
     * @return
     */
    ResponseBean deleteUser(String id);

    /**
     *
     * query by conditions
     * @param params conditions
     * @return
     */
    ResponseBean getUserByConditions(HashMap<String, String> params);
}
