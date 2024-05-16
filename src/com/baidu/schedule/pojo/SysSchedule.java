package com.baidu.schedule.pojo;

import java.util.Objects;

public class SysSchedule {
    private Integer sid;
    private Integer uid;
    private String title;
    private Integer completed;

    public SysSchedule() {
    }

    public SysSchedule(Integer sid, Integer uid, String title, Integer completed) {
        this.sid = sid;
        this.uid = uid;
        this.title = title;
        this.completed = completed;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysSchedule that = (SysSchedule) o;

        if (!Objects.equals(sid, that.sid)) return false;
        if (!Objects.equals(uid, that.uid)) return false;
        if (!Objects.equals(title, that.title)) return false;
        return Objects.equals(completed, that.completed);
    }

    @Override
    public int hashCode() {
        int result = sid != null ? sid.hashCode() : 0;
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysSchedule{" +
                "sid=" + sid +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
