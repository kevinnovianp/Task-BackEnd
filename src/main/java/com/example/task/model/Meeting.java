package com.example.task.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Meeting")
public class Meeting implements Serializable{
    @Id
    private int id;
    private String startTime;
    private String endTime;
    private String date;
    private String title;
    private String desc;

    public void setMeetingId(int number) {
        this.id=number;
    }
}
