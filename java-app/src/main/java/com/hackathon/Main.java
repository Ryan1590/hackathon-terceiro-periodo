package com.hackathon;

import com.hackathon.view.MainForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var mainForm = new MainForm();
            mainForm.setVisible(true);
        });
    }
}
