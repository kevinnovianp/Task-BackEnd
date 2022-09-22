package com.example.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.task.model.Meeting;
import com.example.task.repository.MeetingRepository;

@RestController
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    private MeetingRepository meetingRepo;

    @PostMapping("/add")
    public void addMeeting(@RequestBody Meeting meeting) {
        meetingRepo.addMeeting(meeting);
    }

    @GetMapping("/all")
    public List<Meeting> getAllMeetings() {
        return meetingRepo.findAll();
    }

    @GetMapping("/find/{id}")
    public Meeting findMeeting(@PathVariable int id) {
        return meetingRepo.findMeetingById(id);
    }

    @PutMapping("/update/{id}")
    public void updateMeeting(@RequestBody Meeting meeting, @PathVariable int id) {
    	meetingRepo.updateMeeting(meeting, id);
	}

    @DeleteMapping("/delete/{id}")
    public void removeMeeting(@PathVariable int id) {
    	meetingRepo.deleteMeeting(id);
	}

    @GetMapping("/get/length")
    public int getMeetingsLength() {
        return meetingRepo.getMeetingsLength();
    }

    @GetMapping("/get/index")
    public int getIndex() {
        return meetingRepo.getIndex();
    }
}
