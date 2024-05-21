package com.baidu.schedule.dao;

import com.baidu.schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleDao {
    List<SysSchedule> findAllSchedule(int uid);

    Integer addSchedule(int uid);

    Integer updateSchedule(SysSchedule sysSchedule);

    Integer deleteSchedule(int sid);
}
