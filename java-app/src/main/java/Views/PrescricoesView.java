package Views;

import Controllers.PrescricoesController;
import Models.PrescricoesModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class PrescricoesView extends JFrame {

    private JTextField idField, cpfField, nomeField, alergiasField, condicoesField, observacoesField;
    private JButton btnPesquisar, btnSalvar, btnAtualizar, btnDeletar;
    private JTable table;
    private DefaultTableModel tableModel;
    private PrescricoesController controller;

    public PrescricoesView() {
        setTitle("Prescrições Médicas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        initComponents();
        setListeners();

        controller = new PrescricoesController(); // Inicializa o controller

        setVisible(true); // Torna a janela visível após configurar os componentes
    }

    private void initComponents() {
        // Labels
        JLabel lblId = new JLabel("ID:");
        JLabel lblCpf = new JLabel("CPF:");
        JLabel lblNome = new JLabel("Nome:");
        JLabel lblAlergias = new JLabel("Alergias:");
        JLabel lblCondicoes = new JLabel("Condições Médicas:");
        JLabel lblObservacoes = new JLabel("Observações:");

        // Fields
        idField = new JTextField(10);
        idField.setEditable(false); // Torna o campo não editável

        cpfField = new JTextField(10);
        nomeField = new JTextField(50);
        nomeField.setEditable(false); // Torna o campo não editável

        alergiasField = new JTextField(50);
        condicoesField = new JTextField(50);
        observacoesField = new JTextField(50);

        // Buttons
        btnPesquisar = new JButton("Pesquisar");
        btnSalvar = new JButton("Salvar");
        btnAtualizar = new JButton("Atualizar");
        btnDeletar = new JButton("Deletar");

        // Table
        table = new JTable();
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Não permitir edição na tabela
            }
        };
        table.setModel(tableModel);

        // Add columns to the table model
        tableModel.addColumn("ID");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Alergias");
        tableModel.addColumn("Condições Médicas");
        tableModel.addColumn("Observações");

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 150));

        // Layout manager and constraints
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Adding components to the panel with GridBagConstraints
        add(lblId, gbc);
        add(idField, gbc);
        add(lblCpf, gbc);
        add(cpfField, gbc);
        add(lblNome, gbc);
        add(nomeField, gbc);
        add(lblAlergias, gbc);
        add(alergiasField, gbc);
        add(lblCondicoes, gbc);
        add(condicoesField, gbc);
        add(lblObservacoes, gbc);
        add(observacoesField, gbc);
        add(scrollPane, gbc);

        // Buttons in a separate panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnPesquisar);
        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnDeletar);

        // Add the button panel with a new GridBagConstraints
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // span across columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(buttonPanel, gbc);

        // Add ListSelectionListener to the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Load data from selected row into input fields
                        idField.setText(table.getValueAt(selectedRow, 0).toString()); // ID
                        cpfField.setText(table.getValueAt(selectedRow, 1).toString()); // CPF
                        nomeField.setText(table.getValueAt(selectedRow, 2).toString()); // Nome
                        alergiasField.setText(table.getValueAt(selectedRow, 3).toString()); // Alergias
                        condicoesField.setText(table.getValueAt(selectedRow, 4).toString()); // Condições Médicas
                        observacoesField.setText(table.getValueAt(selectedRow, 5).toString()); // Observações
                    }
                }
            }
        });
    }

    private void setListeners() {
        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpf = cpfField.getText();
                if (!cpf.isEmpty()) {
                    buscarHistoricoMedicoPorCPF(cpf);
                } else {
                    JOptionPane.showMessageDialog(PrescricoesView.this, "Digite um CPF válido para pesquisar.");
                }
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarHistoricoMedico();
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarHistoricoMedico();
            }
        });

        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarHistoricoMedico();
            }
        });
    }

    private void buscarHistoricoMedicoPorCPF(String cpf) {
        List<PrescricoesModel> historicos = controller.buscarHistoricoMedicoPorCPF(cpf);
        if (historicos != null && !historicos.isEmpty()) {
            limparTabela();
            for (PrescricoesModel historico : historicos) {
                Object[] rowData = {
                        historico.getId(),
                        cpf,
                        historico.getNome(),
                        historico.getAlergias(),
                        historico.getCondicoesMedicas(),
                        historico.getObservacoes()
                };
                tableModel.addRow(rowData);
            }
        } else {
            limparTabela();
            JOptionPane.showMessageDialog(this, "Nenhum registro encontrado para o CPF informado.");
        }
    }

    private void salvarHistoricoMedico() {
        // Obter os dados dos campos
        String cpf = cpfField.getText();
        String alergias = alergiasField.getText();
        String condicoes = condicoesField.getText();
        String observacoes = observacoesField.getText();

        if(cpf.isEmpty() || alergias.isEmpty() || condicoes.isEmpty() || observacoes.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha todos os campos",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean sucesso = controller.salvarHistoricoMedico(cpf, alergias, condicoes, observacoes);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Histórico médico salvo com sucesso.");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao salvar histórico médico.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar histórico médico: " + e.getMessage());
        }
    }

    private void atualizarHistoricoMedico() {
        try {
            int id = Integer.parseInt(idField.getText());
            String alergias = alergiasField.getText();
            String condicoes = condicoesField.getText();
            String observacoes = observacoesField.getText();

            if(alergias.isEmpty() || condicoes.isEmpty() || observacoes.isEmpty()){
                JOptionPane.showMessageDialog(this, "Preencha todos os campos",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean sucesso = controller.atualizarHistoricoMedico(id, alergias, condicoes, observacoes);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Histórico médico atualizado com sucesso.");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao atualizar histórico médico.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido. Selecione um registro da tabela para atualizar.");
        }
    }

    private void deletarHistoricoMedico() {
        try {
            int id = Integer.parseInt(idField.getText());
            boolean sucesso = controller.deletarHistoricoMedico(id);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Histórico médico deletado com sucesso.");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao deletar histórico médico.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido. Selecione um registro da tabela para deletar.");
        }
    }

    private void limparCampos() {
        idField.setText("");
        cpfField.setText("");
        nomeField.setText("");
        alergiasField.setText("");
        condicoesField.setText("");
        observacoesField.setText("");
        limparTabela();
    }

    private void limparTabela() {
        tableModel.setRowCount(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PrescricoesView();
            }
        });
    }
}