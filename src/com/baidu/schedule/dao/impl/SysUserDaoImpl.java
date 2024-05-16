package com.baidu.schedule.dao.impl;

import com.baidu.schedule.dao.BaseDao;
import com.baidu.schedule.dao.SysUserDao;
import com.baidu.schedule.pojo.SysUser;

import java.util.List;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {
    @Override
    public int addSysUser(SysUser sysUser) {
        String sql = "insert into sys_user values(DEFAULT,?,?)";
        return baseUpdate(sql, sysUser.getUsername(), sysUser.getUserPwd());
    }

    @Override
    public SysUser findUserByUsername(String username) {
        String sql = "select uid,username, user_pwd userPwd from sys_user where username = ?";
        List<SysUser> userList = baseQuery(SysUser.class, sql, username);
        return null != userList && userList.size() > 0 ? userList.get(0) : null;
    }
}
