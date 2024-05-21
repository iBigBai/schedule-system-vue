package com.baidu.schedule.service;

import com.baidu.schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleService {

    List<SysSchedule> findAllSchedule(int uid);

    Integer addSchedule(int uid);

    Integer updateSchedule(SysSchedule sysSchedule);

    Integer deleteSchedule(int sid);
}
