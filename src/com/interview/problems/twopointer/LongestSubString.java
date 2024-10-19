package com.interview.problems.twopointer;

import java.util.*;

public class LongestSubString {
    public static void main(String[] args) {
        System.out.println("Result = " + new LongestSubString().run2("ababcabcddab"));
    }

    /**
     * Length of the longest substring find
     * (without repeating characters)
     *
     * @param input string of any size
     * @return Longest substring length
     */
    private int run(String input) {

        List<String> charList = Arrays.asList(input.split(""));

        Set<String> currentLongestSubStr = new HashSet<>();
        Set<String> currentUniqueSubSet = new HashSet<>();
        for (int i = 0; i < charList.size(); i++) {
            for (int j = i; j < charList.size(); j++) {
                String nextChar = charList.get(j);
                //System.out.println("Next char = "+nextChar);
                if (!currentUniqueSubSet.contains(nextChar)) {
                    currentUniqueSubSet.add(nextChar);
                } else {
                    // Stop executing second loop
                    // Memorize the current size
                    // if current size > currentLongestSubStr.length() set currentLongestSubStr = current.size()
                    // collect = new HashSet();
                    int nextUniqueSubStrSize = currentUniqueSubSet.size();
                    if (nextUniqueSubStrSize > currentLongestSubStr.size()) {
                        currentLongestSubStr = currentUniqueSubSet;
                    }
                    currentUniqueSubSet = new HashSet<>();
                    break;
                }
            }
        }
        System.out.println("Substring = " + currentLongestSubStr);

        return currentLongestSubStr.size();
    }

    private int run2(String s) {
        // HashMap to store the character and its latest index
        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;  // Maximum length of the unique substring
        int start = 0;  // Start pointer of the sliding window

        // Loop through the string with 'end' pointer
        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            // If the character is already in the map and within the current window, update the start
            if (charIndexMap.containsKey(currentChar)) {
                // Move the start to the right of the last occurrence of the current character
                start = Math.max(start, charIndexMap.get(currentChar) + 1);
            }

            // Add/update the character's latest index in the map
            charIndexMap.put(currentChar, end);

            // Calculate the length of the current window
            maxLength = Math.max(maxLength, end - start + 1);
        }

        System.out.println("Substring = " + maxLength);

        return maxLength;
    }
}
