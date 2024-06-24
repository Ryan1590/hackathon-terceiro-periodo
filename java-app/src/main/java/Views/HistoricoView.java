package Views;

import Controllers.HistoricoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoricoView extends JFrame {

    private JTextField searchField;
    private JButton searchButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private HistoricoController controller;

    public HistoricoView() {
        controller = new HistoricoController();

        // Configurações da janela principal
        setTitle("Histórico Idoso");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Painel de busca
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Pesquisar");
        searchPanel.add(new JLabel("Buscar:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Configuração da JTable
        String[] columnNames = {"Vacina", "Data e Hora da Visita"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Listener do botão de pesquisa
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = searchField.getText();
                if (cpf.isEmpty()) {
                    JOptionPane.showMessageDialog(HistoricoView.this,
                            "CPF não pode estar vazio.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                controller.buscarHistoricoPorCPF(cpf, tableModel);
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HistoricoView().setVisible(true);
            }
        });
    }
}
