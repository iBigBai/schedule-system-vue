package com.baidu.schedule.dao.impl;

import com.baidu.schedule.dao.BaseDao;
import com.baidu.schedule.dao.SysScheduleDao;
import com.baidu.schedule.pojo.SysSchedule;

import java.util.List;

public class SysScheduleDaoImpl extends BaseDao implements SysScheduleDao {
    @Override
    public List<SysSchedule> findAllSchedule(int uid) {
        String sql = "select sid, uid, title, completed from sys_schedule where uid = ?";
        return baseQuery(SysSchedule.class, sql, uid);
    }

    @Override
    public Integer addSchedule(int uid) {
        String sql = "insert into sys_schedule values(default, ?, '请输入日程', 0)";
        return baseUpdate(sql, uid);
    }

    @Override
    public Integer updateSchedule(SysSchedule sysSchedule) {
        String sql = "update sys_schedule set title = ? ,completed =  ? where sid =?";
        return baseUpdate(sql, sysSchedule.getTitle(), sysSchedule.getCompleted(), sysSchedule.getSid());
    }

    @Override
    public Integer deleteSchedule(int sid) {
        String sql = "delete from sys_schedule where sid = ?";
        return baseUpdate(sql, sid);
    }
}
