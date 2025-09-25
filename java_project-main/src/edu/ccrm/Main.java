package edu.ccrm;

import edu.ccrm.cli.MenuHandler;

/**
 * The main starting point for the CCRM application.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Campus Course & Records Manager...");
        MenuHandler menu = new MenuHandler();
        menu.run();
    }
}               