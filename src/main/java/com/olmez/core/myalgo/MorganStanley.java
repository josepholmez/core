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
            sb.append(from + " -> " + to + "\n");
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

    // 5.GET MAX LIST AS TO ITS RIGHT***// input: { 17, 5, 13, 8, 16, 1, 2 };
    // Output: 17, 16, 2
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
    public static boolean deleteMiddleElement(LinkedList<String> list) {
        int midIndex = list.size() / 2; // if list size is odd number
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

    // **************TEST**************TEST**************TEST**************TEST**************TEST**************TEST*/
    public static void main(String[] args) {

        // 1
        int[] list = { 1, 2, 3, 5 };
        int missing = getMissingNumber(list);
        log.info("Missing number: {}", missing);

        // 2
        int cnt = countSubstring("abc", "abcdabceabcfabcg");
        log.info("Number of sub (abc): {}", cnt);

        // 3
        String result = orderTickes();
        log.info("Ordered list: from->to: \n{}", result);

        // 4
        int num = getMax(list);
        log.info("Max number: {}", num);

        // 5
        int[] mList = { 17, 5, 13, 8, 16, 1, 2 }; // 17, 16, 2
        List<Integer> maxList = getMaxAsItsRight(mList);
        log.info("Max list: {}", maxList);

        // 6
        LinkedList<String> lis = new LinkedList<>();
        lis.add("AA");
        lis.add("BB");
        lis.add("CC");
        var res = deleteMiddleElement(lis);
        log.info("Middle element is deleted: {}", res);

        // 7
        boolean res7 = isPalindrome("kayaK");
        log.info("Palindrome: {}", res7);
    }

}
