package com.example.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;
import com.example.task.model.Meeting;

@Service
public class MeetingService {
    public static final String HASH_KEY = "Meeting";
    @Autowired
    private RedisTemplate meetings;
    private int meetingsLength=0;
    private int currMeetingId=1;

    public void addMeeting(Meeting meeting){
        meeting.setId(this.currMeetingId);
        meetings.opsForHash().put(HASH_KEY,meeting.getId(),meeting);
        this.currMeetingId++;
        this.meetingsLength++;
        // return "meeting added!";
    }

    public List<Meeting> findAll(){
        return meetings.opsForHash().values(HASH_KEY);
    }
    
    public Meeting findMeetingById(int id){
        return (Meeting) meetings.opsForHash().get(HASH_KEY,id);
    }

    public void updateMeeting(Meeting meeting, int id){
        meeting.setId(id);
        meetings.opsForHash().put(HASH_KEY,id,meeting);
        // return "meeting updated!";
    }

    public void deleteMeeting(int id){
         meetings.opsForHash().delete(HASH_KEY,id);
         this.meetingsLength--;
        // return "meeting removed!";
    }

    public int getCurrMeetingId(){
        return this.currMeetingId;
    }

    public int getMeetingsLength(){
        return this.meetingsLength;
    }
}
