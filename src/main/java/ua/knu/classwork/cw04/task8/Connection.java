package ua.knu.classwork.cw04.task8;

public class Connection {
    private final int id;

    Connection(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Connection #" + id;
    }
}