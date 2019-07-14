package com.adhd.recursionBackTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A 10&10 Crossword grid is provided to you, along with a set of words (or
 * names of places) which need to be filled into the grid. Cells are marked
 * either + or -. Cells marked with a - are to be filled with the word list.
 * 
 * The following shows an example crossword from the input crossword grid and
 * the list of words to fit
 * 
 * https://www.hackerrank.com/challenges/crossword-puzzle/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=recursion-backtracking
 */
public class CrosswordPuzzle {

    static String[] crosswordPuzzle(String[] crossword, String words) {
        String[] wordList = words.split(";");
        char[][] grid = new char[10][10];

        int i = 0, j = 0;
        for (String w : crossword) {
            for (char k : w.toCharArray()) {
                grid[i][j++] = k;
            }

            i++;
            j=0;
        }

        char[][] solution = solve(grid, wordList);
        String[] result = new String[10];
        int k = 0;
        for (char[] sol : solution){
            result[k++] = new String(sol);
        }
        return result;
    }

    static final int[] R_OFFSETS = { 0, 1 };
    static final int[] C_OFFSETS = { 1, 0 };
    static final int SIZE = 10;

    static char[][] solve(char[][] grid, String[] words) {
		return search(grid, Arrays.stream(words).collect(Collectors.toSet()), 0, 0, 0);
	}

	static char[][] search(char[][] grid, Set<String> remainWords, int r, int c, int direction) {
		if (r == SIZE) {
			return grid;
		}
		if (c == SIZE) {
			return search(grid, remainWords, r + 1, 0, 0);
		}
		if (direction == R_OFFSETS.length) {
			return search(grid, remainWords, r, c + 1, 0);
		}

		int insertLength = countInsertLength(grid, r, c, direction);
		if (insertLength > 1) {
			for (String remainWord : new ArrayList<>(remainWords)) {
				if (canInsert(grid, r, c, direction, insertLength, remainWord)) {
					List<Integer> insertOffsets = new ArrayList<Integer>();

					for (int insertOffset = 0; insertOffset < insertLength; insertOffset++) {
						int insertR = r + R_OFFSETS[direction] * insertOffset;
						int insertC = c + C_OFFSETS[direction] * insertOffset;

						if (grid[insertR][insertC] == '-') {
							grid[insertR][insertC] = remainWord.charAt(insertOffset);

							insertOffsets.add(insertOffset);
						}
					}
					remainWords.remove(remainWord);

					char[][] subResult = search(grid, remainWords, r, c, direction + 1);
					if (subResult != null) {
						return subResult;
					}

					remainWords.add(remainWord);
					for (int insertOffset : insertOffsets) {
						int insertR = r + R_OFFSETS[direction] * insertOffset;
						int insertC = c + C_OFFSETS[direction] * insertOffset;

						grid[insertR][insertC] = '-';
					}
				}
			}

			return null;
		} else {
			return search(grid, remainWords, r, c, direction + 1);
		}
	}

	static int countInsertLength(char[][] grid, int r, int c, int direction) {
		int prevR = r - R_OFFSETS[direction];
		int prevC = c - C_OFFSETS[direction];
		if (prevR >= 0 && prevR < SIZE && prevC >= 0 && prevC < SIZE && grid[prevR][prevC] != '+') {
			return 0;
		}

		int insertLength = 0;
		while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && grid[r][c] != '+') {
			insertLength++;

			r += R_OFFSETS[direction];
			c += C_OFFSETS[direction];
		}
		return insertLength;
	}

	static boolean canInsert(char[][] grid, int r, int c, int direction, int insertLength, String word) {
		return word.length() == insertLength && IntStream.range(0, word.length()).allMatch(insertOffset -> {
			int insertR = r + R_OFFSETS[direction] * insertOffset;
			int insertC = c + C_OFFSETS[direction] * insertOffset;

			return grid[insertR][insertC] == '-' || grid[insertR][insertC] == word.charAt(insertOffset);
		});
    }
    
    public static void main(String[] args) {
        
    }
}