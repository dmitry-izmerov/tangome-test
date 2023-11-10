package ru.dizmirov;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class StringSort {
    public static String sort(String input, List<Character> alphabet) {
        var map = alphabet.stream().collect(Collectors.toMap(Function.identity(), ch -> 0));
        var rest = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            var ch = input.charAt(i);
            if (map.containsKey(ch)) {
                var count = map.get(ch);
                map.put(ch, ++count);
            } else {
                rest.append(ch);
            }
        }

        var res = new StringBuilder();
        for (Character ch : alphabet) {
            LongStream.iterate(map.getOrDefault(ch, 0), val -> val > 0, val -> val - 1)
                .forEach(i -> res.append(ch));
        }
        res.append(rest);

        return res.toString();
    }
}