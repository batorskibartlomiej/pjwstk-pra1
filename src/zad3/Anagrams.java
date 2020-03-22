/**
 *
 *  @author Batorski Bartłomiej PD2654
 *
 */

package zad3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {
    private List<String> allWords = new ArrayList<>();
    List<List<String>> sortedAnagrams = new ArrayList<>();

    public Anagrams(String allWordsFile) {
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader(allWordsFile));
            String line;
            while ((line = inputStream.readLine()) != null) {
                Arrays.asList(line.split(" "));
                allWords.addAll(Arrays.asList(line.split(" ")));
            }
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Blad wczytania danych");
        }
    }

    public List<List<String>> getSortedByAnQty() {
        Map<String, List<String>> anagrams = allWords.stream()
                .collect(Collectors.groupingBy(this::sort));
        sortedAnagrams = anagrams.entrySet().stream()
                .sorted(Comparator.comparing(stringListEntry -> stringListEntry.getValue().size()))
                .map(stringListEntry -> stringListEntry.getValue())
                .collect(Collectors.toList());
        Collections.reverse(sortedAnagrams);
        return sortedAnagrams;
    }

    public String getAnagramsFor(String next) {
        if (!allWords.contains(next)) {
            return next + ": null";
        }
        String sortedWord = sort(next);
        List<String> anagrams = new ArrayList<>();
        sortedAnagrams.stream().filter(strings -> sort(strings.get(0)).contains(sortedWord)).forEach(strings -> {
            strings.stream().forEach(s -> {
                if (!next.equals(s)) {
                    anagrams.add(s);
                }
            });
        });

        return next + ": " + anagrams;
    }

    private String sort(String w) {
        char[] chars = w.toLowerCase().toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
