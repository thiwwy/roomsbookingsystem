package app.service;

import app.exceptions.InvalidMeetingException;
import app.exceptions.RoomNotFoundException;
import app.model.Meeting;
import app.model.MeetingRoom;
import org.springframework.stereotype.Service;
import java.util.*;


@Service("roomServiceHashMap")
public class RoomServiceHashMap implements RoomService{

    //TODO unit test - readme giustificare scelte
    private HashMap<Long, MeetingRoom> roomsStorage;


    public RoomServiceHashMap()
    {
        // Few rooms are initialized only for demo purposes.
        roomsStorage = new HashMap<>();
        roomsStorage.put(1L, new MeetingRoom(1L, new ArrayList<Meeting>()));
        roomsStorage.put(2L, new MeetingRoom(2L, new ArrayList<Meeting>()));
        roomsStorage.put(3L, new MeetingRoom(3L, new ArrayList<Meeting>()));
    }

    @Override
    public MeetingRoom findById(long id) {
        MeetingRoom room = roomsStorage.get(id);
        if(room == null)
            throw  new RoomNotFoundException(String.format("Room %d not found", id));

        return room;
    }

    @Override
    public List<Long> listRoom()
    {
        return new ArrayList<>(roomsStorage.keySet());
    }

    @Override
    public void saveMeeting(long roomId, Meeting meeting) {
        MeetingRoom room = findById(roomId);
        if(room == null)
            throw  new RoomNotFoundException(String.format("Room %d not found", roomId));

        for(Meeting m : room.getMeetings()){

            // Check if a meeting with the same id already exists.
            if(m.getId() == meeting.getId())
                throw  new InvalidMeetingException(String.format("Meeting %d already exists", meeting.getId()));

            // Check if the meeting overlaps with another one.
            if(meeting.getStartTime() <= m.getEndTime() &&
                    meeting.getEndTime() >= m.getStartTime())
                throw new InvalidMeetingException("The meeting that you are trying to create overlaps with another existing one, impossible to book");
        }
        room.getMeetings().add(meeting);
    }

    @Override
    public void updateMeeting(long roomId, long meetingId, Meeting meeting) {
        ListIterator<Meeting> meetingIterator = roomsStorage.get(roomId).getMeetings().listIterator();
        while(meetingIterator.hasNext()){
            Meeting m = meetingIterator.next();
            if(m.getId() == meetingId){
                meetingIterator.set(meeting);
                break;
            }
        }
    }

    @Override
    public void deleteMeeting(long roomId, long meetingId) {
        Iterator<Meeting> meetingIterator = roomsStorage.get(roomId).getMeetings().iterator();
        while(meetingIterator.hasNext()){
            Meeting meeting = meetingIterator.next();
            if(meeting.getId() == meetingId){
                meetingIterator.remove();
                break;
            }
        }
    }
}
