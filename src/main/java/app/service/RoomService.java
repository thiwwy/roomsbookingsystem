package app.service;

import app.model.Meeting;
import app.model.MeetingRoom;

import java.util.List;


public interface RoomService {

    MeetingRoom findById(long id);

    List<Long>  listRoom();

    void saveMeeting(long roomId, Meeting meeting);

    void updateMeeting(long roomId, long meetingId, Meeting meeting);

    void deleteMeeting(long roomId, long meetingId);

}