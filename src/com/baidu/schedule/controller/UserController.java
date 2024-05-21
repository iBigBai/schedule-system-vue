package com.baidu.schedule.controller;

import com.baidu.schedule.common.Result;
import com.baidu.schedule.common.ResultCodeEnum;
import com.baidu.schedule.pojo.SysUser;
import com.baidu.schedule.service.SysUserService;
import com.baidu.schedule.service.impl.SysUserServiceImpl;
import com.baidu.schedule.utils.MD5Util;
import com.baidu.schedule.utils.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/*")
public class UserController extends BaseController {
    private final SysUserService userService = new SysUserServiceImpl();

    public void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取请求体对象
        SysUser registUser = WebUtil.readJson(req, SysUser.class);
        int rows = userService.register(registUser);
        Result result = null;
        // 3 根据注册结果(成功  失败) 做页面跳转
        if (rows > 0) {
            result = Result.ok(null);
        } else {
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp, result);
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SysUser loginUser = WebUtil.readJson(req, SysUser.class);
        String username = loginUser.getUsername();
        String password = loginUser.getUserPwd();
        SysUser user = userService.findUserByUsername(username);
        Result result = null;
        if (null == user) {
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        } else if (!MD5Util.encrypt(password).equals(user.getUserPwd())) {
            //3 判断密码是否匹配
            result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        } else {
            req.getSession().setAttribute("sysUser", user);
            //将密码隐藏后返回给客户端
            user.setUserPwd("******");
            result = Result.ok(user);
        }
        WebUtil.writeJson(resp, result);
    }

    public void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        SysUser registUser = userService.findUserByUsername(username);
        Result result = null;
        if (null == registUser) {
            result = Result.ok(null);
        } else {
            // 占用, 创建一个结果为505的对象
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        // 将result对象转换成JSON并响应给客户端
        WebUtil.writeJson(resp, result);
    }
}
