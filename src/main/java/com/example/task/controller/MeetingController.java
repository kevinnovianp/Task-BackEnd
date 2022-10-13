package com.example.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.task.model.Meeting;
import com.example.task.repository.UserRepository;
import com.example.task.service.MeetingService;
import com.example.task.service.UserService;

@RestController
@RequestMapping("/meeting")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public void addMeeting(@RequestBody Meeting meeting) {
        meetingService.addMeeting(meeting);
    }

    @GetMapping("/all")
    public List<Meeting> getAllMeetings() {
        return meetingService.findAll();
    }

    @GetMapping("/find/{id}")
    public Meeting findMeeting(@PathVariable int id) {
        return meetingService.findMeetingById(id);
    }

    @PutMapping("/update/{id}")
    public void updateMeeting(@RequestBody Meeting meeting, @PathVariable int id) {
    	meetingService.updateMeeting(meeting, id);
	}

    @DeleteMapping("/delete/{id}")
    public void removeMeeting(@PathVariable int id) {
    	meetingService.deleteMeeting(id);
	}

    @GetMapping("/get/length")
    public int getMeetingsLength() {
        return meetingService.getMeetingsLength();
    }

    @GetMapping("/get/index")
    public int getIndex() {
        return meetingService.getCurrMeetingId();
    }
}
