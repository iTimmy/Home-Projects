package com.bullscows.bullscows.view;

import java.util.*;

import com.bullscows.bullscows.dto.Round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author timmy
 */
@Component
public class View {

    @Autowired
    UserIO io = new UserIOConsoleImpl();

    int round = 0;

    public void display() {
        io.println("**********************\nLet the games begin.\n**********************");
    }

    public void displayRound(int round) {
        this.round = round;
        io.println("Round " + round + "\n_________________");
    }

    public int displayChoice() {
        int select = io.readInt("1. Show answer?\n2. Quit");
        return select;
    }

    public void displayShowAnswer(int showAnswer) {
        System.out.println(showAnswer);
    }

    public int generateAnswer() {
        int generatedAnswer = (int) (Math.random() * 9999);
        io.println("4 digits have been generated.");
        // checkAnswer();
        return generatedAnswer;
    }

    public void checkAnswer(int userAnswer, int showAnswer) {
        String showAnswerString = Integer.toString(showAnswer);
        char firstDigit = showAnswerString.charAt(0);
        char secondDigit = showAnswerString.charAt(1);
        char thirdDigit = showAnswerString.charAt(2);
        char fourthDigit = showAnswerString.charAt(3);

        String userAnswerString = Integer.toString(userAnswer);
        char firstGuess = userAnswerString.charAt(0);
        char secondGuess = userAnswerString.charAt(1);
        char thirdGuess = userAnswerString.charAt(2);
        char fourthGuess = userAnswerString.charAt(3);

        io.println("======================");
        if (firstDigit == firstGuess) {
            io.println("✓ 1st");
        } else {
            io.println("X 1st");
        }
        
        if (secondDigit == secondGuess) {
            io.println("✓ 2nd");
        } else {
            io.println("X 2nd");
        }
        
        if (thirdDigit == thirdGuess) {
            io.println("✓ 3rd");
        } else {
            io.println("X 3rd");
        }
        
        if (fourthDigit == fourthGuess) {
            io.println("✓ 4th");
        } else {
            io.println("X 4th");
        }
        io.println("======================");

        System.out.println("checkAnswer: " + firstDigit + secondDigit + thirdDigit + fourthDigit);
    }

    public Round saveRound(int round, int generatedAnswer, int userAnswer) {
        Round currentRound = new Round();
        String generatedAnswerString = Integer.toString(generatedAnswer);
        String userAnswerString = Integer.toString(userAnswer);
        currentRound.setGuess(userAnswerString);
        currentRound.setResult(generatedAnswerString);
        return currentRound;
    }

	public void displayTermination() {
        io.println("Terminating...");
	}

	public int userAnswer() {
        int userAnswerChoice = 0;
        String userAnswerChoiceString = "";
        while(userAnswerChoiceString.length() > 4 || userAnswerChoiceString.length() < 4 || userAnswerChoice == 0) {
            userAnswerChoice = io.readInt("Type your 4-digit answer: ");
            userAnswerChoiceString = Integer.toString(userAnswerChoice);
            userAnswerChoice = Integer.parseInt(userAnswerChoiceString);
        }
        return userAnswerChoice;
	}

	public void displaySuccess() {
        io.println("Success!");
	}
}
