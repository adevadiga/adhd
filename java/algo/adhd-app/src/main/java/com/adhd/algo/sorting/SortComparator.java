package com.adhd.algo.sorting;

import java.util.Comparator;
/**
 * Comparators are used to compare two objects. In this challenge, you'll create a comparator and use it to sort an array. The Player class is provided in the editor below. It has two fields:

1. name: a string.
2. score: an integer.
Given an array of  Player objects, write a comparator that sorts them in order of decreasing score. 
If  or more players have the same score, sort those players alphabetically ascending by name. To do this, you must create a Checker class that implements the Comparator interface, 
then write an int compare(Player a, Player b) method implementing the Comparator.compare(T o1, T o2) method.
 In short, when sorting in ascending order, a comparator function returns -1 if  a <b,
 0 if a = b and 1 if a > b
  
 */
class Player {
	String name;
	int score;

	Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
}

public class SortComparator implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }

        int value = o2.score - o1.score;
        if (value == 0) {
            value = o1.name.compareTo(o2.name);
        }
        return value;
    }

}