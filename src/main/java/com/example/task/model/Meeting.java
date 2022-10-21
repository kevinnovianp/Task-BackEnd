package com.example.task.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @AllArgsConstructor
// @NoArgsConstructor
@RedisHash("Meeting")
public class Meeting implements Serializable{
    @Id
    private int id;
    private String startTime;
    private String endTime;
    private String date;
    private String title;
    private String desc;
    private int creator;

    public Meeting(){
    }

    public void setMeetingId(int number) {
        this.id=number;
    }

    public Meeting(int id, String startTime, String endTime, String date, String title, String desc, int creator) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.title = title;
        this.desc = desc;
        this.creator = creator;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCreator() {
        return this.creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }


}
