package Views;

import Controllers.IdosoController;
import Models.IdosoModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IdosoView extends JFrame {
    private HistoricoView historicoView;
    private JTextField textFieldNome;
    private JTextField textFieldBairro;
    private JTextField textFieldEstado;
    private JTextField textFieldId;
    private JTextField textFieldCpf;
    private JFormattedTextField formattedTextFieldDataNascimento;
    private JTextField textFieldCep;
    private JTextField textFieldLogradouro;
    private JTextField textFieldNumero;
    private JTextField textFieldCidade;
    private JButton buttonSalvar;
    private JButton buttonAtualizar;
    private JButton buttonExcluir;
    private JButton buttonPesquisar;
    private JTable table;

    private IdosoController idosoController;

    public IdosoView() {
        setTitle("Gerenciamento de Idosos");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Inicialização do controller
        idosoController = new IdosoController();

        // Painel principal com BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Painel esquerdo para entrada de dados com GridBagLayout
        JPanel panelLeft = new JPanel(new GridBagLayout());
        panelLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Labels e campos de texto
        JLabel labelNome = new JLabel("Nome:");
        textFieldNome = new JTextField(20);
        panelLeft.add(labelNome, gbc);

        gbc.gridx++;
        panelLeft.add(textFieldNome, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelBairro = new JLabel("Bairro:");
        panelLeft.add(labelBairro, gbc);

        gbc.gridx++;
        textFieldBairro = new JTextField(15);
        panelLeft.add(textFieldBairro, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelEstado = new JLabel("Estado:");
        panelLeft.add(labelEstado, gbc);

        gbc.gridx++;
        textFieldEstado = new JTextField(10);
        panelLeft.add(textFieldEstado, gbc);

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

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelCep = new JLabel("CEP:");
        panelLeft.add(labelCep, gbc);

        gbc.gridx++;
        textFieldCep = new JTextField(8);
        panelLeft.add(textFieldCep, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelLogradouro = new JLabel("Logradouro:");
        panelLeft.add(labelLogradouro, gbc);

        gbc.gridx++;
        textFieldLogradouro = new JTextField(20);
        panelLeft.add(textFieldLogradouro, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelNumero = new JLabel("Número:");
        panelLeft.add(labelNumero, gbc);

        gbc.gridx++;
        textFieldNumero = new JTextField(5);
        panelLeft.add(textFieldNumero, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelCidade = new JLabel("Cidade:");
        panelLeft.add(labelCidade, gbc);

        gbc.gridx++;
        textFieldCidade = new JTextField(15);
        panelLeft.add(textFieldCidade, gbc);

        // Novo posicionamento do campo e rótulo do ID
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelId = new JLabel("ID:");
        panelLeft.add(labelId, gbc);

        gbc.gridx++;
        textFieldId = new JTextField(5);
        panelLeft.add(textFieldId, gbc);

        // Painel direito para botões e tabela com BorderLayout
        JPanel panelRight = new JPanel(new BorderLayout());

        // Subpainel para os botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonSalvar = new JButton("Salvar");
        buttonAtualizar = new JButton("Atualizar");
        buttonExcluir = new JButton("Excluir");
        buttonPesquisar = new JButton("Pesquisar");
        JButton buttonVerHistorico = new JButton("Ver Histórico");
        panelBotoes.add(buttonVerHistorico);
        panelBotoes.add(buttonSalvar);
        panelBotoes.add(buttonAtualizar);
        panelBotoes.add(buttonExcluir);
        panelBotoes.add(buttonPesquisar);

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
        loadIdosos();

        // Adicionando o painel principal ao frame
        add(panel);

        // Exibir o frame
        setVisible(true);

        // Configurar ações dos botões
        buttonSalvar.addActionListener(e -> salvarIdoso());
        buttonAtualizar.addActionListener(e -> atualizarIdoso());
        buttonExcluir.addActionListener(e -> excluirIdoso());
        buttonPesquisar.addActionListener(e -> pesquisarIdoso());

        buttonVerHistorico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (historicoView == null) {
                    historicoView = new HistoricoView();
                }
                historicoView.setVisible(true);
            }
        });



        // Configurar seleção na tabela
        table.getSelectionModel().addListSelectionListener(e -> selecionarIdoso());
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
    private void loadIdosos() {
        try {
            List<IdosoModel> idosos = idosoController.getUsers();
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nome");
            model.addColumn("Data de Nascimento");
            model.addColumn("CPF");
            model.addColumn("Logradouro");
            model.addColumn("Número");
            model.addColumn("Bairro");
            model.addColumn("CEP");
            model.addColumn("Cidade");
            model.addColumn("Estado");

            for (IdosoModel idoso : idosos) {
                model.addRow(new Object[]{
                        idoso.getId(),
                        idoso.getName(),
                        formatDate(idoso.getDataNascimento()),
                        idoso.getCpf(),
                        idoso.getLogradouro(),
                        idoso.getNumero(),
                        idoso.getBairro(),
                        idoso.getCep(),
                        idoso.getCidade(),
                        idoso.getEstado()
                });
            }

            table.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar idosos: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para formatar data
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    // Método para validar CPF
    private boolean validarCPF(String cpf) {
        // Remover caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se tem 11 dígitos
        return cpf.length() == 11;
    }

    // Método para salvar um idoso
    private void salvarIdoso() {
        String nome = textFieldNome.getText();
        String bairro = textFieldBairro.getText();
        String estado = textFieldEstado.getText();
        String cpf = textFieldCpf.getText();
        String dataNascimentoStr = formattedTextFieldDataNascimento.getText();
        String cep = textFieldCep.getText();
        String logradouro = textFieldLogradouro.getText();
        String numero = textFieldNumero.getText();
        String cidade = textFieldCidade.getText();

        if (nome.isEmpty() || bairro.isEmpty() || estado.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty() || cep.isEmpty() || logradouro.isEmpty() || numero.isEmpty() || cidade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar CPF
        if (!validarCPF(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido. Use apenas números.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar se a data de nascimento está preenchida
        if (dataNascimentoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe a data de nascimento.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convertendo a string para java.sql.Date
        java.sql.Date dataNascimento = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = sdf.parse(dataNascimentoStr);
            dataNascimento = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use o formato dd/MM/yyyy.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Chamar o método do controller para salvar
            int resultado = idosoController.saveUser(nome, dataNascimento, cpf, logradouro, numero, bairro, cep, cidade, estado);
            if (resultado == 0) {
                JOptionPane.showMessageDialog(this, "Idoso salvo com sucesso.");
                // Limpar os campos após salvar
                limparCampos();
                // Atualizar a tabela
                loadIdosos();
            } else if (resultado == 1) {
                JOptionPane.showMessageDialog(this, "Nome já existe. Por favor, escolha outro nome.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (resultado == 2) {
                JOptionPane.showMessageDialog(this, "CPF já existe. Por favor, informe outro CPF.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao salvar idoso.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar idoso: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void atualizarIdoso() {
        String nome = textFieldNome.getText();
        String bairro = textFieldBairro.getText();
        String estado = textFieldEstado.getText();
        String cpf = textFieldCpf.getText();
        String dataNascimentoStr = formattedTextFieldDataNascimento.getText();
        String cep = textFieldCep.getText();
        String logradouro = textFieldLogradouro.getText();
        String numero = textFieldNumero.getText();
        String cidade = textFieldCidade.getText();
        String idStr = textFieldId.getText();

        // Validação dos campos obrigatórios
        if(nome.isEmpty() || bairro.isEmpty() || estado.isEmpty() || cpf.isEmpty() || dataNascimentoStr.isEmpty() || cep.isEmpty() || logradouro.isEmpty() || numero.isEmpty() || cidade.isEmpty()){
            JOptionPane.showMessageDialog(this, "Preencha todos os campos",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar se foi selecionado algum idoso
        if(idStr.isEmpty()){
            JOptionPane.showMessageDialog(this, "Selecione algum idoso.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar CPF
        if (!validarCPF(cpf)) {
            JOptionPane.showMessageDialog(this, "CPF inválido. Use apenas números.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar se a data de nascimento está preenchida
        if (dataNascimentoStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe a data de nascimento.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convertendo a string para java.sql.Date
        java.sql.Date dataNascimento = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date parsedDate = sdf.parse(dataNascimentoStr);
            dataNascimento = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use o formato dd/MM/yyyy.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Chamar o método do controller para verificar e atualizar o idoso
            int resultado = idosoController.updateUser(nome, dataNascimento, cpf, logradouro, numero, bairro, cep, cidade, estado, Integer.parseInt(idStr));
            switch (resultado) {
                case 1:
                    JOptionPane.showMessageDialog(this, "Idoso atualizado com sucesso.");
                    // Limpar os campos após atualizar
                    limparCampos();
                    // Atualizar a tabela
                    loadIdosos();
                    break;
                case -1:
                    JOptionPane.showMessageDialog(this, "Nome já existe para outro idoso.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
                case -2:
                    JOptionPane.showMessageDialog(this, "CPF já existe para outro idoso.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar idoso.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar idoso: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Método para excluir um idoso
    private void excluirIdoso() {
        String idStr = textFieldId.getText();

        if(idStr.isEmpty()){
            JOptionPane.showMessageDialog(this, "Selecione algum idoso.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Confirmar exclusão
        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o idoso selecionado?",
                "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            // Chamar o método do controller para excluir
            boolean sucesso = idosoController.deleteUser(Integer.parseInt(idStr));
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Idoso excluído com sucesso.");
                // Limpar os campos após excluir
                limparCampos();
                // Atualizar a tabela
                loadIdosos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir idoso.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir idoso: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para pesquisar um idoso pelo ID
    private void pesquisarIdoso() {
        String idStr = textFieldId.getText();

        if (idStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe o ID do idoso para pesquisar.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Chamar o método do controller para buscar o idoso
            IdosoModel idoso = idosoController.getUser(Integer.parseInt(idStr));
            if (idoso != null) {
                // Preencher os campos com os dados do idoso encontrado
                textFieldNome.setText(idoso.getName());
                textFieldBairro.setText(idoso.getBairro());
                textFieldEstado.setText(idoso.getEstado());
                textFieldCpf.setText(idoso.getCpf());
                formattedTextFieldDataNascimento.setText(formatDate(idoso.getDataNascimento()));
                textFieldCep.setText(idoso.getCep());
                textFieldLogradouro.setText(idoso.getLogradouro());
                textFieldNumero.setText(idoso.getNumero());
                textFieldCidade.setText(idoso.getCidade());
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum idoso encontrado com o ID informado.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao pesquisar idoso: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para limpar os campos de texto
    private void limparCampos() {
        textFieldNome.setText("");
        textFieldBairro.setText("");
        textFieldEstado.setText("");
        textFieldId.setText("");
        textFieldCpf.setText("");
        formattedTextFieldDataNascimento.setText("");
        textFieldCep.setText("");
        textFieldLogradouro.setText("");
        textFieldNumero.setText("");
        textFieldCidade.setText("");
    }

    private void selecionarIdoso() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            textFieldId.setText(model.getValueAt(selectedRow, 0).toString()); // ID
            textFieldNome.setText(model.getValueAt(selectedRow, 1).toString()); // Nome
            textFieldCpf.setText(model.getValueAt(selectedRow, 3).toString()); // CPF

            // Data de Nascimento
            String dataNascimentoStr = model.getValueAt(selectedRow, 2).toString(); // Índice para Data de Nascimento
            try {
                // Parse da string para Date
                Date dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimentoStr);

                // Formatar a data para exibição
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dataFormatada = sdf.format(dataNascimento);

                // Setar a data formatada no campo formattedTextFieldDataNascimento
                formattedTextFieldDataNascimento.setText(dataFormatada);
            } catch (ParseException e) {
                e.printStackTrace();
                // Lidar com erro de parse aqui, se necessário
            }

            textFieldLogradouro.setText(model.getValueAt(selectedRow, 4).toString()); // Logradouro
            textFieldNumero.setText(model.getValueAt(selectedRow, 5).toString()); // Número
            textFieldBairro.setText(model.getValueAt(selectedRow, 6).toString()); // Bairro
            textFieldCep.setText(model.getValueAt(selectedRow, 7).toString()); // CEP
            textFieldCidade.setText(model.getValueAt(selectedRow, 8).toString()); // Cidade
            textFieldEstado.setText(model.getValueAt(selectedRow, 9).toString()); // Estado
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(IdosoView::new);
    }
}
