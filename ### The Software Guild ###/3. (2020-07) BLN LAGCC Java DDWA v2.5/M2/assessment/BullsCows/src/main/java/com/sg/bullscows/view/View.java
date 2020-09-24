package com.sg.bullscows.view;

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

    public void display() {
        io.readString("Begin game? ");
    }

	public void displayTermination() {
        io.println("Terminating...");
	}
}
