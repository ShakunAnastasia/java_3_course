package ua.knu.classwork.cw04.task9;

public class Task9Runner {
    public static void main(String[] args) {
        Text text = new Text(new Word("Object-Oriented Programming"));

        Sentence sentence1 = new Sentence(".");
        sentence1.addWord(new Word("Java"));
        sentence1.addWord(new Word("supports"));
        sentence1.addWord(new Word("composition"));

        Sentence sentence2 = new Sentence("!");
        sentence2.addWord(new Word("Classes"));
        sentence2.addWord(new Word("form"));
        sentence2.addWord(new Word("complex"));
        sentence2.addWord(new Word("structures"));

        text.addSentence(sentence1);
        text.addSentence(sentence2);

        System.out.println("~ Initial text output ~");
        text.printText();

        System.out.println("\n~ Adding another sentence ~");
        Sentence sentence3 = new Sentence(".");
        sentence3.addWord(new Word("Text"));
        sentence3.addWord(new Word("can"));
        sentence3.addWord(new Word("be"));
        sentence3.addWord(new Word("extended"));
        text.addSentence(sentence3);

        text.printText();
    }
}