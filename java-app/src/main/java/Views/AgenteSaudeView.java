package Views;

import Controllers.AgenteSaudeController;
import Models.AgenteSaudeModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AgenteSaudeView extends JFrame {
    private JTextField textFieldNome;
    private JTextField textFieldCpf;
    private JFormattedTextField formattedTextFieldDataNascimento;
    private JButton buttonSalvar;
    private JButton buttonExcluir;
    private JButton buttonAtualizar;
    private JButton buttonPesquisar;
    private JTable table;
    private JTextField textFieldOutro;

    private AgenteSaudeController controller;

    public AgenteSaudeView() {
        setTitle("Gerenciamento Agentes de Saúde");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Inicialização do controller
        controller = new AgenteSaudeController();

        // Painel principal com BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Painel esquerdo para nome, cpf e data de nascimento com GridBagLayout
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
        JLabel labelCpf = new JLabel("CPF:");
        panelLeft.add(labelCpf, gbc);

        gbc.gridx++;
        textFieldCpf = new JTextField(15);
        panelLeft.add(textFieldCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelDataNascimento = new JLabel("Data de Nascimento:");
        panelLeft.add(labelDataNascimento, gbc);

        gbc.gridx++;
        formattedTextFieldDataNascimento = new JFormattedTextField(createMaskFormatter("##/##/####"));
        formattedTextFieldDataNascimento.setColumns(10);
        panelLeft.add(formattedTextFieldDataNascimento, gbc);

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
        loadAgentesSaude();

        // Adicionando o painel principal ao frame
        add(panel);

        // Exibir o frame
        setVisible(true);

        // Configurar ações dos botões
        buttonSalvar.addActionListener(e -> salvarAgenteSaude());
        buttonExcluir.addActionListener(e -> excluirAgenteSaude());
        buttonAtualizar.addActionListener(e -> atualizarAgenteSaude());
        buttonPesquisar.addActionListener(e -> pesquisarAgenteSaude());

        // Configurar seleção na tabela
        table.getSelectionModel().addListSelectionListener(e -> selecionarAgenteSaude());
    }

    // Método utilitário para criar máscara para data
    private MaskFormatter createMaskFormatter(String format) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(format);
            formatter.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    // Método para carregar dados na tabela
    private void loadAgentesSaude() {
        try {
            List<AgenteSaudeModel> agentes = controller.getAgentesSaude();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nome");
            model.addColumn("CPF");
            model.addColumn("Data de Nascimento");

            for (AgenteSaudeModel agente : agentes) {
                model.addRow(new Object[]{agente.getId(), agente.getNome(), agente.getCpf(), formatDate(agente.getDataNascimento())});
            }

            table.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar agentes de saúde: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para formatar data
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    // Método para validar CPF
// Método para validar CPF
    private boolean validarCPF(String cpf) {
        // Remover caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se tem 11 dígitos
        return cpf.length() == 11;
    }


    private void salvarAgenteSaude() {
        String nome = textFieldNome.getText();
        String cpf = textFieldCpf.getText();
        String dataNascimentoStr = formattedTextFieldDataNascimento.getText();

        if(nome.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar CPF
        if (!validarCPF(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido. Use o formato: xxx.xxx.xxx-xx",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifica se a data de nascimento está preenchida
        if (dataNascimentoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe a data de nascimento.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convertendo a string para java.sql.Date
        Date dataNascimento = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = sdf.parse(dataNascimentoStr);
            dataNascimento = new Date(parsedDate.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use o formato dd/MM/yyyy.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int resultado = controller.saveAgenteSaude(nome, cpf, String.valueOf(dataNascimento));

            switch (resultado) {
                case 0:
                    JOptionPane.showMessageDialog(this, "Agente de saúde salvo com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    loadAgentesSaude();
                    limparCampos();
                    break;
                case 1:
                    JOptionPane.showMessageDialog(this, "Já existe um agente de saúde com esse nome.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(this, "Já existe um agente de saúde com esse CPF.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
                case -1:
                default:
                    JOptionPane.showMessageDialog(this, "Erro ao salvar agente de saúde.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar agente de saúde: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Método para pesquisar um agente de saúde pelo ID
    private void pesquisarAgenteSaude() {
        String idTexto = textFieldOutro.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe o ID do agente de saúde a ser pesquisado.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            AgenteSaudeModel agente = controller.getAgenteSaude(id);
            if (agente != null) {
                textFieldNome.setText(agente.getNome());
                textFieldCpf.setText(agente.getCpf());

                // Exibir a data de nascimento formatada
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                formattedTextFieldDataNascimento.setText(sdf.format(agente.getDataNascimento()));
            } else {
                JOptionPane.showMessageDialog(this, "Agente de saúde não encontrado.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar agente de saúde: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para excluir um agente de saúde
    private void excluirAgenteSaude() {
        String idTexto = textFieldOutro.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe o ID do agente de saúde a ser excluído.",
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
            if (controller.deleteAgenteSaude(id)) {
                JOptionPane.showMessageDialog(this, "Agente de saúde excluído com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                loadAgentesSaude();
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir agente de saúde. Verifique o ID e tente novamente.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir agente de saúde: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para atualizar um agente de saúde
    private void atualizarAgenteSaude() {
        String idTexto = textFieldOutro.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe o ID do agente de saúde a ser atualizado.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idTexto);
        String nome = textFieldNome.getText();
        String cpf = textFieldCpf.getText();

        // Obtendo a data de nascimento formatada corretamente
        String dataNascimentoStr = formattedTextFieldDataNascimento.getText();
        Date dataNascimento = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = sdf.parse(dataNascimentoStr);
            dataNascimento = new Date(parsedDate.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use o formato dd/MM/yyyy.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nome.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int result = controller.updateAgenteSaude(nome, cpf, String.valueOf(dataNascimento), id);

            switch (result) {
                case 1:
                    JOptionPane.showMessageDialog(this, "Agente de saúde atualizado com sucesso!",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    loadAgentesSaude();
                    limparCampos();
                    break;
                case -1:
                    JOptionPane.showMessageDialog(this, "Nome já existe para outro agente de saúde.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
                case -2:
                    JOptionPane.showMessageDialog(this, "CPF já existe para outro agente de saúde.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar agente de saúde. Verifique os dados e tente novamente.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar agente de saúde: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }



    // Método para limpar os campos de entrada
    private void limparCampos() {
        textFieldNome.setText("");
        textFieldCpf.setText("");
        formattedTextFieldDataNascimento.setText("");
        textFieldOutro.setText("");
        table.clearSelection(); // Limpar seleção da tabela
    }

    private void selecionarAgenteSaude() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            textFieldOutro.setText(model.getValueAt(selectedRow, 0).toString()); // ID
            textFieldNome.setText(model.getValueAt(selectedRow, 1).toString()); // Nome
            textFieldCpf.setText(model.getValueAt(selectedRow, 2).toString()); // CPF
            formattedTextFieldDataNascimento.setText(model.getValueAt(selectedRow, 3).toString()); // Data de Nascimento
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AgenteSaudeView();
        });
    }
}
