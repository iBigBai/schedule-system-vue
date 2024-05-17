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
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        SysUser user = userService.findUserByUsername(username);
        if (null == user) {
            resp.sendRedirect("/regist.html");
        } else if (!MD5Util.encrypt(password).equals(user.getUserPwd())) {
            //3 判断密码是否匹配
            // 跳转到密码有误提示页
            resp.sendRedirect("/loginUserPwdError.html");
        } else {
            req.getSession().setAttribute("sysUser", user);
            //4 跳转到首页
            resp.sendRedirect("/showSchedule.html");
        }
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
