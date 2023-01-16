package com.olmez.core.myalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MorganStanley {

    // 1.MISSING NUMBER ******************************************
    public static int getMissingNumber(int[] list) {
        int size = list.length;
        if (size == 0) {
            return 1;
        }

        Arrays.sort(list);
        int last = list[size - 1];
        int expectedSum = (last * (last + 1)) / 2; // Gauss Formula

        int sum = 0;
        for (int i : list) {
            sum += i;
        }
        return (expectedSum == sum) ? last + 1 : expectedSum - sum;
    }

    // 2.COUNT SUBSTRING ******************************************
    public static int countSubstring(String sub, String source) {
        if (sub == null || source == null || sub.isEmpty() || source.isEmpty()) {
            return 0;
        }
        String[] split = source.split(sub);
        return split.length - 1;
    }

    // 3.ORDER TICKETS ******************************************
    public static String orderTickes() {
        // Using HashMap
        Map<String, String> ticket = new HashMap<>();
        ticket.put("Bombay", "Delhi");
        ticket.put("Delhi", "Goa");
        ticket.put("Goa", "Chennai");
        ticket.put("Chennai", "Banglore");

        HashSet<String> toList = new HashSet<>();
        toList.addAll(ticket.values());

        String from = "";
        for (var departure : ticket.keySet()) {
            if (!toList.contains(departure)) {
                from = departure;
            }
        }

        StringBuilder sb = new StringBuilder();

        String to = ticket.get(from);
        while (to != null) {
            sb.append(from + " -> " + to + ", ");
            from = to;
            to = ticket.get(from);
        }
        return sb.toString();
    }

    // 4.GET MAX ******************************************
    public static int getMax(int[] list) {
        Arrays.sort(list);
        return list[list.length - 1];
    }

    // 5.GET MAX LIST AS TO ITS RIGHT ***
    public static List<Integer> getMaxAsItsRight(int[] list) {

        List<Integer> maxList = new ArrayList<>();
        int size = list.length;

        int max = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (list[i] > max) {
                max = list[i];
                maxList.add(max);
            }
        }
        return maxList;
    }

    // 6.DELETE MIDDLE ELEMENT FROM A LIST ***************************
    public static boolean deleteMiddleElement(List<String> list) {
        int midIndex = list.size() / 2; // if list size is odd number and list is LinkedList
        String midObj = list.get(midIndex);
        return list.remove(midObj);
    }

    // 7.PALINDROME ******************************************
    public static boolean isPalindrome(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        String str = word.toLowerCase(); // case sensitive
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }

    // 8. FIST NON-REPEATABLE CHAR ******************************
    public static String findFirstNonRepeatableChar(String text) {
        if (text == null || text.isEmpty()) {
            return "No text";
        }

        char[] cList = text.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        for (var ch : cList) {
            if (map.isEmpty()) {
                map.put(ch, 1);
            } else {
                if (map.containsKey(ch)) {
                    map.put(ch, map.get(ch) + 1);
                } else {
                    map.put(ch, 1);
                }
            }
        }

        for (var e : map.entrySet()) {
            if (e.getValue() == 1) {
                return String.valueOf(e.getKey());
            }
        }
        return "No found non-repeatable char in " + text;
    }

    // 9.ANAGRAM ******************************************
    public static boolean isAnagram(String first, String second) {
        if (first == null || first.isEmpty() || second == null || second.isEmpty()) {
            return false;
        }

        if (first.length() != second.length()) {
            return false;
        }

        char[] f = first.toCharArray();
        char[] s = second.toCharArray();
        Arrays.sort(f);
        Arrays.sort(s);

        return Arrays.equals(f, s);
    }

    // 10.FIND ALL ANAGRAMS ******************************************
    public static String findAnagrams(String[] dic) {
        StringBuilder sb = new StringBuilder();
        HashSet<String> set = new HashSet<>();
        for (var w : dic) {
            String sorted = getSortedChar(w);
            if (!sorted.isEmpty() && !set.add(sorted)) {
                sb.append("Anagram: " + new StringBuilder(w).reverse().toString() + " - " + w + "\n");
            }

        }
        return sb.toString();
    }

    private static String getSortedChar(String word) {
        if (word == null || word.isEmpty()) {
            return "";
        }
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return Arrays.toString(charArray);
    }

    // **************TEST**************TEST**************TEST**************TEST**************TEST**************TEST*/
    public static void main(String[] args) {

        // 1.
        int[] list = { 1, 2, 3, 5 };
        int res1 = getMissingNumber(list);
        log.info("01-Missing number: {}", res1);

        // 2.
        int res2 = countSubstring("abc", "abcdabceabcfabcg");
        log.info("02-Number of sub (abc): {}", res2);

        // 3.
        String res3 = orderTickes();
        log.info("03-Ordered list: from->to: \n{}", res3);

        // 4.
        int res4 = getMax(list);
        log.info("04-Max number: {}", res4);

        // 5.
        int[] mList = { 17, 5, 13, 8, 16, 1, 2 }; // Output:17, 16, 2
        List<Integer> res5 = getMaxAsItsRight(mList);
        log.info("05-Max list: {}", res5);

        // 6.
        LinkedList<String> linked = new LinkedList<>();
        linked.addAll(Arrays.asList("AA", "BB", "CC"));
        var res6 = deleteMiddleElement(linked);
        log.info("06-Middle element is deleted: {}", res6);

        // 7.
        String word = "kayaK";
        boolean res7 = isPalindrome(word);
        log.info("07-Is {} a palindrome?: {}", word, res7);

        // 8.
        String text = "zzzzzbbbccccdehhhhiii";
        String res8 = findFirstNonRepeatableChar(text);
        log.info("08-In {}, first non-repeatable char: {}", text, res8);

        // 9.
        String word1 = "race";
        String word2 = "care";
        boolean res9 = isAnagram(word1, word2);
        log.info("09-Anagram: {} - {} ? {}", word1, word2, res9);

        // 10.
        String[] dic = { "cat", "dog", "tac", "god", "other" };
        String res10 = findAnagrams(dic);
        log.info("10-Anagrams: {}", res10);
    }

}
