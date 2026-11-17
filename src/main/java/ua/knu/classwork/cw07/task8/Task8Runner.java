package ua.knu.classwork.cw07.task8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task8Runner {
    public static void main(String[] args) {
        String text = "Java is a powerful programming language. " +
                "Learning JAVA collections makes coding in java much easier and faster. " +
                "Programming requires practice.";

        List<String> targetWords = (args.length > 0)
                ? Arrays.asList(args)
                : Arrays.asList("java", "programming", "collections", "python");

        calculateRelativeFrequencies(text, targetWords);
    }

    public static void calculateRelativeFrequencies(String text, List<String> targetWords) {
        String[] allWords = text.toLowerCase().split("[^a-zA-Zа-яА-ЯіїєґІЇЄҐ0-9]+");
        int totalWords = 0;
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String w : allWords) {
            if (!w.isEmpty()) {
                totalWords++;
                frequencyMap.put(w, frequencyMap.getOrDefault(w, 0) + 1);
            }
        }

        System.out.println("~ Text Analysis ~");
        System.out.println("Total words in text: " + totalWords);
        System.out.println("Target words: " + targetWords);
        System.out.println("\n~ Relative Word Frequencies ~");

        for (String target : targetWords) {
            String key = target.toLowerCase();
            int count = frequencyMap.getOrDefault(key, 0);
            double relativeFreq = totalWords > 0 ? (double) count / totalWords : 0.0;
            System.out.printf("Word '%s': count = %d, relative frequency = %.4f (%.2f%%)%n",
                    target, count, relativeFreq, relativeFreq * 100);
        }
    }
}