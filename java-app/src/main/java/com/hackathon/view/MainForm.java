package com.hackathon.view;

import javax.swing.*;
import java.awt.*;

public class MainForm extends JFrame {
    private JButton vacinaButton;
    private JButton agenteButton;

    public MainForm() {
        setTitle("Menu para Vacina e Agente");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(panel, BorderLayout.CENTER);

        vacinaButton = new JButton("" +
                "Opção Vacina");
       agenteButton = new JButton("Opção Agente");

        // Estilizando os botões
        Dimension buttonSize = new Dimension(180, 30);
        vacinaButton.setPreferredSize(buttonSize);
        agenteButton.setPreferredSize(buttonSize);

        agenteButton.setBackground(new Color(70, 130, 180));
        agenteButton.setForeground(Color.WHITE); // Texto branco
        agenteButton.setFont(new Font("Arial", Font.BOLD, 14));
        agenteButton.setBorder(BorderFactory.createLineBorder(new Color(25, 25, 112), 2));

        vacinaButton.setBackground(new Color(34, 139, 34));
        vacinaButton.setForeground(Color.WHITE); // Texto branco
        vacinaButton.setFont(new Font("Arial", Font.BOLD, 14));
        vacinaButton.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 2));

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(agenteButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(vacinaButton, constraints);

        agenteButton.addActionListener(e -> {
            var agenteForm = new VacinaForm(this);
            agenteForm.setVisible(true);
            this.setVisible(false);
        });

        vacinaButton.addActionListener(e -> {
            var vacinaForm = new com.hackathon.view.VacinaForm(this);
            vacinaForm.setVisible(true);
            this.setVisible(false);
        });


        JMenuBar menuBar = new JMenuBar();


        JMenu menuArquivo = new JMenu("Arquivo");
        JMenu menuAjuda = new JMenu("Ajuda");

        JMenuItem menuItemSair = new JMenuItem("Sair");
        JMenuItem menuItemSobre = new JMenuItem("Sobre");

        menuArquivo.add(menuItemSair);
        menuAjuda.add(menuItemSobre);

        menuBar.add(menuArquivo);
        menuBar.add(menuAjuda);


        setJMenuBar(menuBar);


        menuItemSair.addActionListener(e -> System.exit(0));


        menuItemSobre.addActionListener(e -> JOptionPane.showMessageDialog(this, "Aplicação de Gerenciamento de vacinas e Agente de saude", "Sobre", JOptionPane.INFORMATION_MESSAGE));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainForm mainForm = new MainForm();
            mainForm.setVisible(true);
        });
    }
}
