package app.model;

import java.util.List;

public class Meeting {
    private long id;
    private List<Employee> employees;
    private Agenda agenda;
    private long startTime; //Timestamp
    private long endTime; //Timestamp

    public Meeting(){}
    public Meeting(long id, List<Employee> employees, Agenda agenda, long startTime, long endTime) {
        this.id = id;
        this.employees = employees;
        this.agenda = agenda;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getId() {
        return id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
