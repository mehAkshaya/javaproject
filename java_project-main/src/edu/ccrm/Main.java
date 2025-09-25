package edu.ccrm;

import edu.ccrm.cli.MenuHandler;

public class MainApp {

    public static void main(String[] args) {
        System.out.println("=== Welcome to the CCRM System ===");

        
        if (args.length > 0) {
            System.out.println("Note: Command-line arguments detected (" + args.length + ")");
        }

        
        new MenuHandler().run();

        System.out.println("Thank you for using CCRM. Goodbye!");
    }
}
