package com.baidu.schedule.service.impl;

import com.baidu.schedule.dao.impl.SysScheduleDaoImpl;
import com.baidu.schedule.pojo.SysSchedule;
import com.baidu.schedule.service.SysScheduleService;

import java.util.List;

public class SysScheduleServiceImpl implements SysScheduleService {
    private final SysScheduleDaoImpl sysScheduleDao = new SysScheduleDaoImpl();

    @Override
    public List<SysSchedule> findAllSchedule(int uid) {
        return sysScheduleDao.findAllSchedule(uid);
    }

    @Override
    public Integer addSchedule(int uid) {
        return sysScheduleDao.addSchedule(uid);
    }

    @Override
    public Integer updateSchedule(SysSchedule sysSchedule) {
        return sysScheduleDao.updateSchedule(sysSchedule);
    }

    @Override
    public Integer deleteSchedule(int sid) {
        return sysScheduleDao.deleteSchedule(sid);
    }
}
