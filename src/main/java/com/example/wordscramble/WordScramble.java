package com.example.wordscramble;

/**
 *  CSE205 Honor's Project: Word Scramble
 * 	Name: Neil Mahajan
 *	Lecture: MWF 10:10am
 *	Description: Creates the game pane for the word scramble game
 **/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WordScramble extends Application
{
    public static final int WINSIZE_X = 800, WINSIZE_Y = 800;

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) { launch(args); }

    /**
     * Creates GamePane and sets up stage
     *
     * @param stage Stage for Word Scramble
     */
    @Override
    public void start(Stage stage)
    {
        GamePane gamePane = new GamePane();
        gamePane.setPrefSize(WINSIZE_X, WINSIZE_Y);
        Scene scene = new Scene(gamePane, WINSIZE_X, WINSIZE_Y);
        String winTitle = "Word Scramble";
        stage.setTitle(winTitle);
        stage.setScene(scene);
        stage.show();
    }
}
