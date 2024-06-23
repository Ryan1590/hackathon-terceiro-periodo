package Views;

import Controllers.AgendamentoController;
import Models.AgendamentoModel;
import Models.AgenteSaudeModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import Models.DataBase;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AgendamentoView extends JFrame {
    private AgenteSaudeView agenteSaudeView;
    private vacinaView vacinaView;
    private IdosoView idosoView;
    private JTextField textFieldId;
    private JComboBox<String> comboBoxAgenteSaude;
    private JLabel labelIdosoNome;
    private JLabel labelVacinaNome;
    private JFormattedTextField formattedTextFieldDataHora;
    private JComboBox<String> comboBoxStatus;
    private JButton buttonAtualizar;
    private JButton buttonPesquisar;
    private JButton buttonAgenteSaudeView;
    private JButton buttonIdosoView;
    private JButton buttonVacinaView;
    private JTable table;

    private AgendamentoController controller;

    public AgendamentoView(Connection connection) {
        setTitle("Gerenciamento de Agendamentos");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicialização do controller com a conexão fornecida
        this.controller = new AgendamentoController(connection);

        // Painel principal com BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Painel esquerdo para campos com GridBagLayout
        JPanel panelLeft = new JPanel(new GridBagLayout());
        panelLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label e campo de texto para ID (não editável)
        JLabel labelId = new JLabel("ID:");
        textFieldId = new JTextField(5);
        textFieldId.setEditable(false);
        panelLeft.add(labelId, gbc);

        gbc.gridx++;
        panelLeft.add(textFieldId, gbc);

        // Label e combobox para Agente de Saúde
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelAgenteSaude = new JLabel("Agente de Saúde:");
        comboBoxAgenteSaude = new JComboBox<>();
        comboBoxAgenteSaude.setPreferredSize(new Dimension(200, 25));
        panelLeft.add(labelAgenteSaude, gbc);

        gbc.gridx++;
        panelLeft.add(comboBoxAgenteSaude, gbc);

        // Label e campo de texto para Nome do Idoso (não editável)
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelIdoso = new JLabel("Idoso:");
        labelIdosoNome = new JLabel();
        panelLeft.add(labelIdoso, gbc);

        gbc.gridx++;
        panelLeft.add(labelIdosoNome, gbc);

        // Label e campo de texto para Nome da Vacina (não editável)
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelVacina = new JLabel("Vacina:");
        labelVacinaNome = new JLabel();
        panelLeft.add(labelVacina, gbc);

        gbc.gridx++;
        panelLeft.add(labelVacinaNome, gbc);

        // Label e campo de texto formatado para Data e Hora
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelDataHora = new JLabel("Data e Hora:");
        formattedTextFieldDataHora = new JFormattedTextField(createMaskFormatter("##/##/#### ##:##"));
        formattedTextFieldDataHora.setColumns(15);
        panelLeft.add(labelDataHora, gbc);

        gbc.gridx++;
        panelLeft.add(formattedTextFieldDataHora, gbc);

        // Label e combobox para Status (Pendente ou Finalizado)
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelStatus = new JLabel("Status:");
        comboBoxStatus = new JComboBox<>(new String[]{"Pendente", "Finalizado"});
        panelLeft.add(labelStatus, gbc);

        gbc.gridx++;
        panelLeft.add(comboBoxStatus, gbc);

        // Painel direito para botões, tabela e pesquisa com BorderLayout
        JPanel panelRight = new JPanel(new BorderLayout());

        // Subpainel para os botões
// Subpainel para os botões com GridLayout
        JPanel panelBotoes = new JPanel(new GridLayout(1, 0, 5, 5)); // 1 linha, ajuste automático de colunas, com espaçamento entre componentes

        buttonAtualizar = new JButton("Atualizar");
        buttonPesquisar = new JButton("Pesquisar");
        buttonAgenteSaudeView = new JButton("Ver Agentes");
        buttonVacinaView = new JButton("Ver Vacinas");
        buttonIdosoView = new JButton("Ver Idosos");

// Redimensionando os botões para um tamanho menor
        buttonAtualizar.setPreferredSize(new Dimension(90, 25));
        buttonPesquisar.setPreferredSize(new Dimension(90, 25));
        buttonAgenteSaudeView.setPreferredSize(new Dimension(120, 25));
        buttonVacinaView.setPreferredSize(new Dimension(90, 25));
        buttonIdosoView.setPreferredSize(new Dimension(90, 25));

        panelBotoes.add(buttonAtualizar);
        panelBotoes.add(buttonPesquisar);
        panelBotoes.add(buttonAgenteSaudeView);
        panelBotoes.add(buttonVacinaView);
        panelBotoes.add(buttonIdosoView);


        // Adicionando o painel de botões ao painel direito
        panelRight.add(panelBotoes, BorderLayout.NORTH);

        // Tabela
        table = new JTable();
        JScrollPane scrollPaneTable = new JScrollPane(table);
        panelRight.add(scrollPaneTable, BorderLayout.CENTER);

        // Adicionando os painéis esquerdo e direito ao painel principal
        panel.add(panelLeft, BorderLayout.WEST);
        panel.add(panelRight, BorderLayout.CENTER);

        // Carregar dados na tabela inicialmente
        loadAgendamentosPendentes();

        // Carregar agentes de saúde no combobox
        carregarAgentesSaude();

        // Adicionando o painel principal ao frame
        add(panel);

        // Exibir o frame
        setVisible(true);

        // Listeners para os botões
        buttonAtualizar.addActionListener(e -> {
            try {
                atualizarAgendamento();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        buttonPesquisar.addActionListener(e -> pesquisarAgendamento());

        buttonAgenteSaudeView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (agenteSaudeView == null) {
                    agenteSaudeView = new AgenteSaudeView();
                }
                if (!agenteSaudeView.isVisible()) {
                    agenteSaudeView.setVisible(true);
                }
            }
        });

        buttonIdosoView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idosoView == null) {
                    idosoView = new IdosoView();
                }
                if (!idosoView.isVisible()) {
                    idosoView.setVisible(true);
                }
            }
        });

        buttonVacinaView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vacinaView == null) {
                    vacinaView = new vacinaView();
                }
                if (!vacinaView.isVisible()) {
                    vacinaView.setVisible(true);
                }
            }
        });


        // Configurar seleção na tabela
        table.getSelectionModel().addListSelectionListener(e -> selecionarAgendamento());
    }

    // Método utilitário para criar máscara para data e hora
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

    // Método para carregar dados na tabela de agendamentos pendentes
    private void loadAgendamentosPendentes() {
        List<AgendamentoModel> agendamentos = controller.getAgendamentos();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Agente de Saúde");
        model.addColumn("Idoso");
        model.addColumn("Vacina");
        model.addColumn("Data e Hora");
        model.addColumn("Status");

        for (AgendamentoModel agendamento : agendamentos) {
            String agenteSaude = agendamento.getAgenteSaudeNome();
            if (agenteSaude == null) {
                agenteSaude = ""; // Set an empty string or handle as needed
            }
            model.addRow(new Object[]{
                    agendamento.getId(),
                    agenteSaude,
                    agendamento.getIdosoNome(),
                    agendamento.getVacinaNome(),
                    formatarDataHora(agendamento.getDataHoraVisita()),
                    agendamento.getStatus()
            });
        }

        table.setModel(model);
    }


    // Método para formatar a data e hora
    private String formatarDataHora(Timestamp dataHoraVisita) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(dataHoraVisita);
    }

    private void carregarAgentesSaude() {
        try {
            List<String> nomesAgentes = AgenteSaudeModel.getNomesAgentesSaude();
            for (String nome : nomesAgentes) {
                comboBoxAgenteSaude.addItem(nome);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar agentes de saúde: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para validar data e hora
    private boolean validarDataHora(String dataHoraStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sdf.setLenient(false);

        try {
            sdf.parse(dataHoraStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Método para limpar os campos de entrada
    private void limparCampos() {
        textFieldId.setText("");
        comboBoxAgenteSaude.setSelectedIndex(0); // Limpar seleção no combobox
        labelIdosoNome.setText(""); // Limpar o texto do label
        labelVacinaNome.setText(""); // Limpar o texto do label
        formattedTextFieldDataHora.setText("");
        comboBoxStatus.setSelectedIndex(0); // Definir para "Pendente"
        table.clearSelection(); // Limpar seleção da tabela
    }


    // Método para selecionar um agendamento na tabela
    private void selecionarAgendamento() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            textFieldId.setText(model.getValueAt(selectedRow, 0).toString()); // ID
            comboBoxAgenteSaude.setSelectedItem(model.getValueAt(selectedRow, 1).toString()); // Agente de Saúde
            labelIdosoNome.setText(model.getValueAt(selectedRow, 2).toString()); // Nome do Idoso
            labelVacinaNome.setText(model.getValueAt(selectedRow, 3).toString()); // Nome da Vacina
            formattedTextFieldDataHora.setText(model.getValueAt(selectedRow, 4).toString()); // Data e Hora
            comboBoxStatus.setSelectedItem(model.getValueAt(selectedRow, 5).toString()); // Status
        }
    }

    private void atualizarAgendamento() throws SQLException {
        String idTexto = textFieldId.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um agendamento a ser atualizado.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idTexto);
        String agenteSaudeNome = comboBoxAgenteSaude.getSelectedItem().toString();
        String idosoNome = labelIdosoNome.getText();
        String vacinaNome = labelVacinaNome.getText();
        String dataHoraStr = formattedTextFieldDataHora.getText();
        String status = comboBoxStatus.getSelectedItem().toString(); // Pegar a opção selecionada

        if (agenteSaudeNome.isEmpty() || idosoNome.isEmpty() || vacinaNome.isEmpty() || dataHoraStr.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar data e hora
        if (!validarDataHora(dataHoraStr)) {
            JOptionPane.showMessageDialog(this, "Data e hora inválidas. Use o formato: dd/MM/yyyy HH:mm",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Converter a String de data e hora para Timestamp
        Timestamp dataHoraVisita = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            java.util.Date parsedDate = dateFormat.parse(dataHoraStr);
            dataHoraVisita = new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Erro ao converter data e hora para Timestamp: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Chamar o método do controller para atualizar o agendamento
        boolean result = controller.atualizarAgendamento(id, agenteSaudeNome, idosoNome, vacinaNome, Timestamp.valueOf(String.valueOf(dataHoraVisita)), status);

        if (result) {
            JOptionPane.showMessageDialog(this, "Agendamento atualizado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            loadAgendamentosPendentes();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar agendamento. Verifique os dados e tente novamente.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }



    // Método para pesquisar um agendamento pelo ID
    private void pesquisarAgendamento() {
        String idTexto = textFieldId.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe o ID do agendamento a ser pesquisado.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            AgendamentoModel agendamento = controller.buscarAgendamentoPorId(id);
            if (agendamento != null) {
                comboBoxAgenteSaude.setSelectedItem(agendamento.getAgenteSaudeNome());
                labelIdosoNome.setText(agendamento.getIdosoNome());
                labelVacinaNome.setText(agendamento.getVacinaNome());
                formattedTextFieldDataHora.setText(formatarDataHora(agendamento.getDataHoraVisita()));
                comboBoxStatus.setSelectedItem(agendamento.getStatus()); // Selecionar status
            } else {
                JOptionPane.showMessageDialog(this, "Agendamento não encontrado.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar agendamento: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Configuração da conexão com o banco de dados
            Connection connection = null;
            try {
                // Obter a conexão utilizando a classe DataBase
                connection = DataBase.connection();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados: " + e.getMessage(),
                        "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

            // Iniciar a aplicação Swing passando a conexão
            new AgendamentoView(connection).setVisible(true);

            // Você pode continuar executando outras operações após iniciar a view aqui, se necessário.
        });
    }
}