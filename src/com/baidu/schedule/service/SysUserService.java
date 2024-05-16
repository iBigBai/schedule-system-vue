package com.baidu.schedule.service;

import com.baidu.schedule.pojo.SysUser;

public interface SysUserService {
    /**
     * 用户注册
     *
     * @param sysUser
     * @return
     */
    int register(SysUser sysUser);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    SysUser findUserByUsername(String username);
}
