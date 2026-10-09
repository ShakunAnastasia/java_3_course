package ua.knu.classwork.cw04.task9;

import java.util.ArrayList;
import java.util.List;

public class Sentence {
    private final List<Word> words = new ArrayList<>();
    private final String punctuation;

    public Sentence(String punctuation) {
        this.punctuation = punctuation;
    }

    public void addWord(Word word) {
        words.add(word);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            sb.append(words.get(i).getValue());
            if (i < words.size() - 1) {
                sb.append(" ");
            }
        }
        sb.append(punctuation);
        return sb.toString();
    }
}