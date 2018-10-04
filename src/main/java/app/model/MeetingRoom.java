package app.model;

import java.util.List;

public class MeetingRoom {
    private long id;
    private List<Meeting> meetings;

    public MeetingRoom() {}
    public MeetingRoom(long id, List<Meeting> meetings) {
        this.id = id;
        this.meetings = meetings;
    }

    public long getId() {
        return id;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }
}
