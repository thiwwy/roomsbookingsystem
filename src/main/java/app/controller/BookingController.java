package app.controller;

import app.model.Meeting;
import app.model.MeetingRoom;
import app.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    public static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    @Qualifier("roomServiceHashMap")
    private RoomService roomService;

    @GetMapping("/rooms")
    public ResponseEntity<List<Long>> getRooms() {
        return new ResponseEntity<List<Long>>(roomService.listRoom(), HttpStatus.OK);
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<MeetingRoom> getRoomDetails(@PathVariable("id") Long roomId) {
        return new ResponseEntity<MeetingRoom>(roomService.findById(roomId), HttpStatus.OK);
    }

    @PostMapping("/rooms/{id}/meetings")
    public ResponseEntity<Meeting> postMeeting(@PathVariable("id") Long roomId, @RequestBody Meeting meeting) {
        roomService.saveMeeting(roomId, meeting);
        return new ResponseEntity<Meeting>(meeting, HttpStatus.CREATED);
    }

    @PutMapping("/rooms/{room-id}/meetings/{meeting-id}")
    public ResponseEntity<Long> updateMeeting(
            @PathVariable("room-id") Long roomId,
            @PathVariable("meeting-id") Long meetingId,
            @RequestBody Meeting meeting) {
        roomService.updateMeeting(roomId, meetingId, meeting);
        return new ResponseEntity<Long>(meetingId, HttpStatus.OK);
    }

    @DeleteMapping("/rooms/{room-id}/meetings/{meeting-id}")
    public ResponseEntity<Long> deleteMeeting(
            @PathVariable("room-id") Long roomId,
            @PathVariable("meeting-id") Long meetingId) {
        roomService.deleteMeeting(roomId, meetingId);
        return new ResponseEntity<Long>(meetingId, HttpStatus.OK);
    }
}