package com.hackathon.view;

import com.hackathon.model.Agente;
import com.hackathon.model.Agente;
import com.hackathon.service.AgenteService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static java.lang.Integer.parseInt;

public class AgenteForm extends JFrame {

    private AgenteService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNomeAgente;
    private JTextField campoNomeAgente;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JButton botaoDeletar;
    private JButton botaoVoltar;

    private JTable tabela;
    private JFrame mainForm;

    public AgenteForm(JFrame mainForm) {
        this.mainForm = mainForm;
        service = new AgenteService();

        setTitle("Agente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 550);

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        painelEntrada(constraints, painelEntrada);

        JPanel painelSaida = montarPainelSaida();

        getContentPane().add(painelEntrada, BorderLayout.NORTH);
        getContentPane().add(painelSaida, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private JPanel montarPainelSaida() {
        JPanel painelSaida = new JPanel(new BorderLayout());

        tabela = new JTable();
        tabela.setDefaultEditor(Object.class, null);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.setModel(carregarDadosDiretores());
        tabela.getSelectionModel().addListSelectionListener(this::selecionarAgente);

        JScrollPane scrollPane = new JScrollPane(tabela);
        painelSaida.add(scrollPane, BorderLayout.CENTER);
        return painelSaida;
    }

    private void painelEntrada(GridBagConstraints constraints, JPanel painelEntrada) {
        labelId = new JLabel("ID:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        painelEntrada.add(labelId, constraints);

        campoId = new JTextField(20);
        campoId.setEnabled(false);
        constraints.gridx = 1;
        constraints.gridy = 0;
        painelEntrada.add(campoId, constraints);

        labelNomeAgente = new JLabel("Nome do Diretor:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNomeAgente, constraints);

        campoNomeAgente = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNomeAgente, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> salvar());
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(botaoSalvar, constraints);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> limparCampos());
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(botaoCancelar, constraints);

        botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(e -> deletar());
        constraints.gridx = 2;
        constraints.gridy = 2;
        painelEntrada.add(botaoDeletar, constraints);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> voltar());
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(botaoVoltar, constraints);
    }

    private DefaultTableModel carregarDadosDiretores() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");

        service.listarAgentes().forEach(agente-> model.addRow(new Object[]{agente.getId(), agente.getNome()}));
        return model;
    }

    private void salvar() {
        service.salvar(construirAgente());
        limparCampos();
        tabela.setModel(carregarDadosDiretores());
    }

    private void deletar() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            int id = (Integer) tabela.getValueAt(selectedRow, 0);
            service.deletar(id);
            limparCampos();
            tabela.setModel(carregarDadosDiretores());
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um agente para deletar.");
        }
    }

    private void limparCampos() {
        campoNomeAgente.setText("");
        campoId.setText("");
    }

    private Agente construirAgente() {
        return campoId.getText().isEmpty()
                ? new Agente(campoNomeAgente.getText())
                : new Agente(
                parseInt(campoId.getText()),
                campoNomeAgente.getText());
    }

    private void selecionarAgente(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                var id = (Integer) tabela.getValueAt(selectedRow, 0);
                var nome = (String) tabela.getValueAt(selectedRow, 1);

                campoId.setText(id.toString());
                campoNomeAgente.setText(nome);
            }
        }
    }

    private void voltar() {
        this.dispose();
        mainForm.setVisible(true);
    }
}
