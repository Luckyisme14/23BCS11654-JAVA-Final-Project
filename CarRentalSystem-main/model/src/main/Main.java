package main;

import main.controller.Controller;
import main.view.CarRentalGUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author ProgrammingGeek - Njoh Noh Prince Junior(Software Eng. and Developer)
 * @since 21-06-2021
 *
 * Car Rent Management System - Demonstrating OOP in Java Programming languages
 * 
 * Updated to use Graphical User Interface (GUI)
 */

public class Main {
    public static void main(String[] args) {
        // Use system look and feel for better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create controller instance
        Controller controller = new Controller();

        // Create and show GUI
        EventQueue.invokeLater(() -> {
            try {
                CarRentalGUI window = new CarRentalGUI(controller);
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
