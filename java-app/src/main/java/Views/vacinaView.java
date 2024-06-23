package Views;

import Controllers.vacinaController;
import Models.vacinaModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class vacinaView extends JFrame {
    private JTextField textFieldNome;
    private JTextArea textAreaDescricao;
    private JButton buttonSalvar;
    private JButton buttonExcluir;
    private JButton buttonAtualizar;
    private JButton buttonPesquisar;
    private JTable table;
    private JTextField textFieldOutro;

    private vacinaController controller;

    public vacinaView() {
        setTitle("Gerenciamento Vacina");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Inicialização do controller
        controller = new vacinaController();

        // Painel principal com BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Painel esquerdo para nome e descrição com GridBagLayout
        JPanel panelLeft = new JPanel(new GridBagLayout());
        panelLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label e campo de texto para Nome
        JLabel labelNome = new JLabel("Nome:");
        textFieldNome = new JTextField(20);
        panelLeft.add(labelNome, gbc);

        gbc.gridx++;
        panelLeft.add(textFieldNome, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelDescricao = new JLabel("Descrição:");
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panelLeft.add(labelDescricao, gbc);

        gbc.gridx++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        textAreaDescricao = new JTextArea(5, 20);
        JScrollPane scrollPaneDescricao = new JScrollPane(textAreaDescricao);
        panelLeft.add(scrollPaneDescricao, gbc);

        // Painel direito para botões, tabela e textFieldOutro com BorderLayout
        JPanel panelRight = new JPanel(new BorderLayout());

        // Subpainel para os botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonSalvar = new JButton("Salvar");
        buttonExcluir = new JButton("Excluir");
        buttonAtualizar = new JButton("Atualizar");
        buttonPesquisar = new JButton("Pesquisar");
        panelBotoes.add(buttonSalvar);
        panelBotoes.add(buttonExcluir);
        panelBotoes.add(buttonAtualizar);
        panelBotoes.add(buttonPesquisar);

        // Adicionando o painel de botões ao painel direito
        panelRight.add(panelBotoes, BorderLayout.NORTH);

        // Tabela
        table = new JTable();
        JScrollPane scrollPaneTable = new JScrollPane(table);
        panelRight.add(scrollPaneTable, BorderLayout.CENTER);

        // TextField abaixo da tabela
        textFieldOutro = new JTextField(20);
        JPanel panelTextFieldOutro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelOutro = new JLabel("ID:");
        panelTextFieldOutro.add(labelOutro);
        panelTextFieldOutro.add(textFieldOutro);
        panelRight.add(panelTextFieldOutro, BorderLayout.SOUTH);

        // Adicionando os painéis esquerdo e direito ao painel principal
        panel.add(panelLeft, BorderLayout.WEST);
        panel.add(panelRight, BorderLayout.CENTER);

        // Carregar dados na tabela inicialmente
        loadVacinas();

        // Adicionando o painel principal ao frame
        add(panel);

        // Exibir o frame
        setVisible(true);

        // Configurar ações dos botões
        buttonSalvar.addActionListener(e -> salvarVacina());
        buttonExcluir.addActionListener(e -> excluirVacina());
        buttonAtualizar.addActionListener(e -> atualizarVacina());
        buttonPesquisar.addActionListener(e -> pesquisarVacina());

        // Configurar seleção na tabela
        table.getSelectionModel().addListSelectionListener(e -> selecionarVacina());
    }

    // Método para carregar dados na tabela
    private void loadVacinas() {
        try {
            List<vacinaModel> vacinas = controller.getVacinas();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nome");
            model.addColumn("Descrição");

            for (vacinaModel vacina : vacinas) {
                model.addRow(new Object[]{vacina.getId(), vacina.getNome(), vacina.getDescricao()});
            }

            table.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar vacinas: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para salvar uma vacina
    private void salvarVacina() {
        String nome = textFieldNome.getText();
        String descricao = textAreaDescricao.getText();

        try {
            int saved = controller.saveVacina(nome, descricao);
            if (saved == 1) {
                JOptionPane.showMessageDialog(this, "Vacina salva com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                loadVacinas();
                limparCampos();
            } else if(saved == 0){
                JOptionPane.showMessageDialog(this, "Ja existe uma vacina com esse nome.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar vacina: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para excluir uma vacina
    private void excluirVacina() {
        String idTexto = textFieldOutro.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe o ID da vacina a ser excluída.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o idoso selecionado?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            if (controller.deleteVacina(id)) {
                JOptionPane.showMessageDialog(this, "Vacina excluída com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                loadVacinas();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir vacina. Verifique o ID e tente novamente.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir vacina: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para atualizar uma vacina
    private void atualizarVacina() {
        String idTexto = textFieldOutro.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe o ID da vacina a ser atualizada.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idTexto);
        String nome = textFieldNome.getText();
        String descricao = textAreaDescricao.getText();

        try {
            int saved = controller.updateVacina(nome, descricao, id);
            if (saved == 1) {
                JOptionPane.showMessageDialog(this, "Vacina atualizada com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                loadVacinas();
                limparCampos();
            } else if(saved == 0){
                JOptionPane.showMessageDialog(this, "Ja existe uma vacina com esse nome.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar vacina: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para pesquisar uma vacina pelo ID
    private void pesquisarVacina() {
        String idTexto = textFieldOutro.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe o ID da vacina a ser pesquisada.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            vacinaModel vacina = controller.getVacina(id);
            if (vacina != null) {
                textFieldNome.setText(vacina.getNome());
                textAreaDescricao.setText(vacina.getDescricao());
            } else {
                JOptionPane.showMessageDialog(this, "Vacina não encontrada.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar vacina: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpar os campos de entrada
    private void limparCampos() {
        textFieldNome.setText("");
        textAreaDescricao.setText("");
        textFieldOutro.setText("");
    }

    // Método para selecionar uma vacina na tabela
    private void selecionarVacina() {
        if (table.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int row = table.getSelectedRow();
            textFieldOutro.setText(model.getValueAt(row, 0).toString()); // ID
            textFieldNome.setText(model.getValueAt(row, 1).toString()); // Nome
            textAreaDescricao.setText(model.getValueAt(row, 2).toString()); // Descrição
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new vacinaView();
        });
    }
}
