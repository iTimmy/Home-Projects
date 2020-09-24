/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.bullscows.controller;

import com.sg.bullscows.service.*;
import com.sg.bullscows.view.*;

/**
 *
 * @author timmy
 */
public class Controller {
    View view = new View();
    GameService service = new GameServiceImpl();
    
    public void run() {
        String select = "";

        while(!select.equals("yes") || !select.equals("no")) {
            view.display();

            switch(select) {
                case "yes":
                    beginGame();
                case "no":
                    terminate();
                    break;
                default:
                    System.out.println("?");
            }
        }
    }

    public void beginGame() {
        service.beginGame();
    }

    public void terminate() {
        view.displayTermination();
    }
}
