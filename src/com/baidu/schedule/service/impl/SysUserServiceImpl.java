package com.baidu.schedule.service.impl;

import com.baidu.schedule.dao.SysUserDao;
import com.baidu.schedule.dao.impl.SysUserDaoImpl;
import com.baidu.schedule.pojo.SysUser;
import com.baidu.schedule.service.SysUserService;
import com.baidu.schedule.utils.MD5Util;

public class SysUserServiceImpl implements SysUserService {
    protected SysUserDao userDao = new SysUserDaoImpl();

    @Override
    public int register(SysUser sysUser) {
        // 将用户的明文密码转换为密文密码
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));
        // 调用DAO 层的方法  将sysUser信息存入数据库
        return userDao.addSysUser(sysUser);
    }

    @Override
    public SysUser findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
