package app.model;

public class Agenda {
    private long id;
    private String agenda;

    public Agenda(){}
    public Agenda(long id, String agenda) {
        this.id = id;
        this.agenda = agenda;
    }

    public long getId() {
        return id;
    }

    public String getAgenda() {
        return agenda;
    }
}
