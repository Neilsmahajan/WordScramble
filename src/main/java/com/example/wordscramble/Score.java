package com.example.wordscramble;

/**
 *  CSE205 Honor's Project: Word Scramble
 * 	Name: Neil Mahajan
 *	Lecture: MWF 10:10am
 *	Description: Score object to keep track of the points in the game. The points can increase or decrease.
 **/

public class Score
{
    /**
     * Points integer to keep track of score
     */
    public static int points = 0;

    /**
     * Increases points in game
     *
     * @param num   number to increase points by
     */
    public static void increasePoints(int num) { points += num; }

    /**
     * Decreases points in game
     *
     * @param num   number to decrease points by
     */
    public static void decreasePoints(int num) { points -= num; }
}
