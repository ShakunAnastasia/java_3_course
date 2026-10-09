package ua.knu.classwork.cw04.task9;

import java.util.ArrayList;
import java.util.List;

public class Text {
    private final Word title;
    private final List<Sentence> sentences = new ArrayList<>();

    public Text(Word title) {
        this.title = title;
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public void printTitle() {
        System.out.println("Title: " + title.getValue());
    }

    public void printText() {
        printTitle();
        for (Sentence s : sentences) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}