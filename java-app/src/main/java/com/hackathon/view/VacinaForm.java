package com.hackathon.view;

import com.hackathon.model.Vacina;
import com.hackathon.service.VacinaService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static java.lang.Integer.parseInt;

public class VacinaForm extends JFrame {

    private VacinaService service;
    private JLabel labelId;
    private JTextField campoId;
    private JLabel labelNomeVacina;
    private JTextField campoNomeVacina;
    private JLabel labelDescricao;
    private JTextField campoDescricao;
    private JButton botaoSalvar;
    private JButton botaoCancelar;
    private JButton botaoDeletar;
    private JButton botaoVoltar;

    private JTable tabela;
    private JFrame mainForm;

    public VacinaForm(JFrame mainForm) {
        this.mainForm = mainForm;
        service = new VacinaService();

        setTitle("Vacina");
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
        tabela.setModel(carregarDadoshackathon());
        tabela.getSelectionModel().addListSelectionListener(this::selecionarVacina);

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

        labelNomeVacina = new JLabel("Nome do Vacina:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        painelEntrada.add(labelNomeVacina, constraints);

        campoNomeVacina = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        painelEntrada.add(campoNomeVacina, constraints);

        labelDescricao = new JLabel("Descricao da vacina:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        painelEntrada.add(labelDescricao, constraints);

        campoDescricao = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        painelEntrada.add(campoDescricao, constraints);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> salvar());
        constraints.gridx = 0;
        constraints.gridy = 3;
        painelEntrada.add(botaoSalvar, constraints);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.addActionListener(e -> limparCampos());
        constraints.gridx = 1;
        constraints.gridy = 3;
        painelEntrada.add(botaoCancelar, constraints);

        botaoDeletar = new JButton("Deletar");
        botaoDeletar.addActionListener(e -> deletar());
        constraints.gridx = 2;
        constraints.gridy = 3;
        painelEntrada.add(botaoDeletar, constraints);

        botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> voltar());
        constraints.gridx = 1;
        constraints.gridy = 4;
        painelEntrada.add(botaoVoltar, constraints);
    }

    private DefaultTableModel carregarDadoshackathon() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Descricao");

        service.listarvacinas().forEach(vacina -> {
            return model.addRow(new Object[]{vacina.getId(), vacina.getNome(), vacina.getDescricao()});
        });
        return model;
    }

    private void salvar() {
        if (campoNomeVacina.getText().isEmpty() || campoDescricao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos antes de salvar.");
        } else {
            service.salvar(construirVacina());
            limparCampos();
            tabela.setModel(carregarDadoshackathon());
        }
    }
    private void deletar() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            int id = (Integer) tabela.getValueAt(selectedRow, 0);
            service.deletar(id);
            limparCampos();
            tabela.setModel(carregarDadoshackathon());
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um filme para deletar.");
        }
    }

    private void limparCampos() {
        campoNomeVacina.setText("");
        campoDescricao.setText("");
        campoId.setText("");
    }

    private Vacina construirVacina() {
        return campoId.getText().isEmpty()
                ? new Vacina(campoNomeVacina.getText(), campoDescricao.getText())
                : new Vacina(
                parseInt(campoId.getText()),
                campoNomeVacina.getText(),
                campoDescricao.getText());
    }

    private void selecionarVacina(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                var id = (Integer) tabela.getValueAt(selectedRow, 0);
                var nome = (String) tabela.getValueAt(selectedRow, 1);
                var diretor = (String) tabela.getValueAt(selectedRow, 2);

                campoId.setText(id.toString());
                campoNomeVacina.setText(nome);
                campoDescricao.setText(diretor);
            }
        }
    }

    private void voltar() {
        this.dispose();
        mainForm.setVisible(true);
    }
}
