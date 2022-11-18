package com.example.wordscramble;

import java.util.ArrayList;
import java.util.Random;

/**
 *  CSE205 Honor's Project: Word Scramble
 * 	Name: Neil Mahajan
 *	Lecture: MWF 10:10am
 *	Description: Word bank object stores a bunch of words relating to a subject that the player chooses. It provides a
 *	random word from the bank for the player to guess.
 **/

public class WordBank
{
    /**
     * String array list of words in the word bank
     */
    private ArrayList<String> words;

    /**
     * String array list of words already used in the game
     */
    private ArrayList<String> wordsUsed;

    /**
     * Current word being used in the game
     */
    private Word currentWord;

    /**
     * Constructor which adds the words to the words array list and creates a new current word
     *
     * @param words Array list of words
     */
    public WordBank(ArrayList<String> words)
    {
        this.words = (ArrayList) words.clone();
        wordsUsed = new ArrayList<>();
        getRandomNewWord();
    }

    /**
     * Gets new random word that has not been used
     *
     * @return word that has not been used
     */
    public void getRandomNewWord()
    {
        Random random = new Random();
        int index = -1;
        do index = random.nextInt(words.size());
        while (wordsUsed.contains(words.get(index)));
        wordsUsed.add(words.get(index));
        currentWord = new Word(words.get(index));
    }

    /**
     * Getter method that reaturns current word
     *
     * @return  current word used in game
     */
    public Word getCurrentWord() { return currentWord; }
}
