import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import app.exceptions.InvalidMeetingException;
import app.exceptions.RoomNotFoundException;
import app.model.Agenda;
import app.model.Employee;
import app.model.Meeting;
import app.model.MeetingRoom;
import app.service.RoomServiceHashMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceHashMapTest {

    @Test
    public void findByIdShouldReturnTheRightRoomIfExists() {
        RoomServiceHashMap roomService = new RoomServiceHashMap();

        MeetingRoom room1 = roomService.findById(1L);
        MeetingRoom room2 = roomService.findById(2L);
        MeetingRoom room3 = roomService.findById(3L);
        assertEquals(1, room1.getId(), "Meeting room 1 should have id 1");
        assertEquals(2, room2.getId(), "Meeting room 2 should have id 2");
        assertEquals(3, room3.getId(), "Meeting room 3 should have id 3");
        try {
            roomService.findById(4L);
            fail("Expected RoomNotFoundException");
        }
        catch (RoomNotFoundException exception){
            assertEquals(exception.getMessage(), "Room 4 not found");
        }
        catch (Exception exception){
            fail("Expected RoomNotFoundException");
        }
    }

    @Test
    public void  listRoomReturnsAllTheAvailableRoomsIDs(){
        RoomServiceHashMap roomService = new RoomServiceHashMap();
        List<Long> rooms = roomService.listRoom();

        //Since hashMap, for demo purposes is initialised with 3 rooms, checks that only those rooms are available
        assert (rooms.contains(1L));
        assert (rooms.contains(2L));
        assert (rooms.contains(3L));
        assert (rooms.size() == 3);
    }

    @Test
    public void isNotPossibleToSaveAMeetingInInvalidRoom(){
        RoomServiceHashMap roomService = new RoomServiceHashMap();
        Meeting meeting = new Meeting(1, new ArrayList<Employee>(), new Agenda(1L, "dummy-agenda"), 10L, 20L);
        try {
            roomService.saveMeeting(99L, meeting);
            fail("Expected RoomNotFoundException");
        }
        catch (RoomNotFoundException exception){
            assertEquals(exception.getMessage(), "Room 99 not found");
        }
        catch (Exception exception){
            fail("Expected RoomNotFoundException");
        }
    }

    @Test
    public void isNotPossibleToSaveAMeetingThatAlreadyExists(){
        RoomServiceHashMap roomService = new RoomServiceHashMap();
        Meeting meeting = createDummyMeeting(1L);
        roomService.saveMeeting(1L, meeting);
        try {
            roomService.saveMeeting(1L, meeting);
            fail("Expected InvalidMeetingException");
        }
        catch (InvalidMeetingException exception){
            assertEquals(exception.getMessage(), "Meeting 1 already exists");
        }
        catch (Exception exception){
            fail("Expected InvalidMeetingException");
        }
    }

    @Test
    public void isNotPossibleToSaveAMeetingThatOverlaps(){
        RoomServiceHashMap roomService = new RoomServiceHashMap();
        Meeting meeting1 = createDummyMeeting(1L);
        Meeting meeting2 = new Meeting(2L, new ArrayList<Employee>(), new Agenda(1L, "dummy-agenda"), 15L, 25L);
        roomService.saveMeeting(1L, meeting1);
        try {
            roomService.saveMeeting(1L, meeting2);
            fail("Expected InvalidMeetingException");
        }
        catch (InvalidMeetingException exception){
            assertEquals(exception.getMessage(), "The meeting that you are trying to create overlaps with another existing one, impossible to book");
        }
        catch (Exception exception){
            fail("Expected InvalidMeetingException");
        }

    }

    @Test
    public void saveAMeeting(){
        RoomServiceHashMap roomService = new RoomServiceHashMap();

        assertEquals(roomService.findById(1L).getMeetings().size(),0);

        Meeting meeting = createDummyMeeting(1L);
        roomService.saveMeeting(1L, meeting);

        assertEquals(roomService.findById(1L).getMeetings().size(),1);
        assert(roomService.findById(1L).getMeetings().contains(meeting));
    }

    @Test
    public void updateMeeting(){
        RoomServiceHashMap roomService = new RoomServiceHashMap();
        Meeting meeting1 = createDummyMeeting(1L);
        Meeting meeting2 = createDummyMeeting(2L);
        assertEquals(roomService.findById(1L).getMeetings().size(),0);

        roomService.saveMeeting(1L, meeting1);

        assert(roomService.findById(1L).getMeetings().contains(meeting1));
        assertEquals(roomService.findById(1L).getMeetings().size(),1);

        roomService.updateMeeting(1L, 1L, meeting2);

        assert(roomService.findById(1L).getMeetings().contains(meeting2));
        assertEquals(roomService.findById(1L).getMeetings().size(),1);

    }


    @Test
    public void deleteMeeting(){
        RoomServiceHashMap roomService = new RoomServiceHashMap();
        Meeting meeting = createDummyMeeting(1L);

        assertEquals(roomService.findById(1L).getMeetings().size(),0);

        roomService.saveMeeting(1L, meeting);
        assertEquals(roomService.findById(1L).getMeetings().size(),1);

        roomService.deleteMeeting(1L, 1L);
        assertEquals(roomService.findById(1L).getMeetings().size(),0);
    }

    private Meeting createDummyMeeting(long meetingId){
        return new Meeting(meetingId, new ArrayList<Employee>(), new Agenda(1L, "dummy-agenda"), 10L, 20L);
    }
}
