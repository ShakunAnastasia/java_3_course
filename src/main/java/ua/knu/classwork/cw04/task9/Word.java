package ua.knu.classwork.cw04.task9;

public class Word {
    private final String value;

    public Word(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}