package com.ppdream.xweb.service;

import com.ppdream.xweb.dto.LoginUser;
import com.ppdream.xweb.dto.RegisterUser;
import com.ppdream.xweb.entity.WebUser;

import java.util.List;

/**
 * @Author: x43125
 * @Date: 22/01/02
 */
public interface UserService {
    boolean login(LoginUser user);

    boolean register(RegisterUser user);

    List<WebUser> getUserList();
}
