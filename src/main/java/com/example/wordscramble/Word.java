package com.example.wordscramble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *  CSE205 Honor's Project: Word Scramble
 * 	Name: Neil Mahajan
 *	Lecture: MWF 10:10am
 *	Description: Creates word object used in game that player tries to guess from unscrambled word. The word can reveal
 *	a character as a hint
 **/

public class Word
{
    /**
     * String of word being played
     */
    private String wordStr;

    /**
     * Scrambled string of word being played
     */
    private String scrambledWord;

    /**
     * Revealed indexes of characters activated by pressing hint button
     */
    private ArrayList<Integer> revealedIndexes;

    /**
     * Constructor for word
     *
     * @param wordStr
     */
    public Word(String wordStr)
    {
        this.wordStr = wordStr;
        this.scrambledWord = scrambleWord();
        revealedIndexes = new ArrayList<Integer>();
    }

    /**
     * Scramble word randomly for the player to guess
     *
     * @return  shuffled word
     */
    private String scrambleWord()
    {
        ArrayList<Character> charList = new ArrayList<>();
        for (Character c : wordStr.toCharArray()) charList.add(c);
        Collections.shuffle(charList);
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : charList) stringBuilder.append(c);
        return stringBuilder.toString();
    }

    /**
     * Reveals character at random index of word to help player
     *
     * @return String of asterisks except for the characters at indexes in the revealed Indexes Array List
     */
    public String revealHint()
    {
        Random random = new Random();
        int index = -1;
        do index = random.nextInt(wordStr.length());
        while (revealedIndexes.contains(index));
        revealedIndexes.add(index);
        char[] chars = new char[wordStr.length()];
        for (int i = 0; i < chars.length; i++)
        {
            if (revealedIndexes.contains(i)) chars[i] = wordStr.charAt(i);
            else chars[i] = '*';
        }
        return new String(chars);
    }

    /**
     * Getter method for scrambled word
     *
     * @return scrambled string of word being played
     */
    public String getScrambledWord() { return scrambledWord; }

    /**
     * Getter method for word length
     *
     * @return  word length
     */

    /**
     * Getter method for word string
     *
     * @return  unscrambled word string
     */
    public String getWordStr() { return wordStr; }
    public int getWordLength() { return wordStr.length(); }

    /**
     * Checks guess with correct word
     *
     * @param guess Player guess for correct word
     * @return      boolean informing whether guessed word was coorect
     */
    public boolean checkGuess(String guess)
    {
        if (guess.equalsIgnoreCase(wordStr)) return true;
        return false;
    }
}
