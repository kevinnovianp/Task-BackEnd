package com.example.task.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.task.model.Meeting;

@Repository
public class MeetingRepository {
    public static final String HASH_KEY = "Meeting";
    @Autowired
    private RedisTemplate meetings;
    private int meetingsLength=0;
    private int index=0;

    public void addMeeting(Meeting meeting){
        setIndex(1);
        setMeetingsLength(1);
        meeting.setId(getIndex());
        meetings.opsForHash().put(HASH_KEY,meeting.getId(),meeting);
        // return "meeting added!";
    }

    public List<Meeting> findAll(){
        return meetings.opsForHash().values(HASH_KEY);
    }

    public Meeting findMeetingById(int id){
        return (Meeting) meetings.opsForHash().get(HASH_KEY,id);
    }

    public void updateMeeting(Meeting meeting, int id){
        // meeting.setId(id);
        meetings.opsForHash().put(HASH_KEY,meeting.getId(),meeting);
        // return "meeting updated!";
    }

    public void deleteMeeting(int id){
         meetings.opsForHash().delete(HASH_KEY,id);
         setMeetingsLength(-1);
         meetings.opsForHash().values(HASH_KEY);
        // return "meeting removed!";
    }

    public int getIndex(){
        return this.index;
    }

    public void setIndex(int num){
        this.index+=num;
    }

    public int getMeetingsLength(){
        return this.meetingsLength;
    }

    public void setMeetingsLength(int num){
        this.meetingsLength+=num;
    }
}
