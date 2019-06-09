package com.adhd.algo.stringsq;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * herlock considers a string to be valid if all characters of the string appear the same number of times.
 *  It is also valid if he can remove just 1 character at 1 index in the string, and the remaining characters 
 * will occur the same number of times. Given a string ,
 * determine if it is valid. If so, return YES, otherwise return NO.
 */
public class SherlockAndString {

    static String isValid(String s) {
        char[] sChars = s.toCharArray();
        int[] hits = new int[126];
        for (char ch : sChars) {
            hits[ch]++;
        }
        SortedMap<Integer, Integer> countToTimes = new TreeMap<>();
        for (int i = 0; i <hits.length; i++) {
            int freq = hits[i];
            if (freq != 0) {
               Integer count = countToTimes.get(freq);
               count = count == null ? 1 : count + 1;
               countToTimes.put(freq, count);
            }
        }

        boolean val =  countToTimes.size() == 1
				|| ( countToTimes.size() == 2 && ((countToTimes.firstKey() + 1 == countToTimes.lastKey()
						&& countToTimes.get(countToTimes.lastKey()) == 1)
						|| (countToTimes.firstKey() == 1 && countToTimes.get(countToTimes.firstKey()) == 1)));
       
        return val ? "YES" : "NO";
    }

    public static void main(String[] args) {
        System.out.println(isValid("ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd"));
    }

}