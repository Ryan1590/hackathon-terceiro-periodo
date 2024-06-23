package Models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AgenteSaudeModel {
    private int id;
    private String nome;
    private String cpf;
    private Date dataNascimento;

    // Construtor com todos os campos
    public AgenteSaudeModel(int id, String nome, String cpf, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    // Método para salvar um agente de saúde no banco de dados
    public static boolean saveAgenteSaude(String nome, String cpf, Date dataNascimento) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO agente_saude (nome, cpf, data_nascimento) VALUES (?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, nome);
            statement.setString(2, cpf);
            statement.setDate(3, dataNascimento);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return true;
                }
            }
        }
        return false;
    }


    // Método para obter todos os agentes de saúde do banco de dados
    public static List<AgenteSaudeModel> getAgentesSaude() throws SQLException {
        List<AgenteSaudeModel> agentes = new ArrayList<>();
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM agente_saude");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                Date dataNascimento = resultSet.getDate("data_nascimento");

                AgenteSaudeModel agente = new AgenteSaudeModel(id, nome, cpf, dataNascimento);
                agentes.add(agente);
            }
        }
        return agentes;
    }

    // Método para obter um agente de saúde pelo ID
    public static AgenteSaudeModel getAgenteSaude(int id) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM agente_saude WHERE id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String cpf = resultSet.getString("cpf");
                    Date dataNascimento = resultSet.getDate("data_nascimento");

                    return new AgenteSaudeModel(id, nome, cpf, dataNascimento);
                }
            }
        }
        return null;
    }

    public static boolean verificarCpfExistente(String cpf) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM agente_saude WHERE cpf = ?")) {

            statement.setString(1, cpf);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }


    // Método para atualizar um agente de saúde no banco de dados
    public boolean updateAgenteSaude(int id, String nome, String cpf, Date dataNascimento) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE agente_saude SET nome = ?, cpf = ?, data_nascimento = ? WHERE id = ?")) {

            statement.setString(1, nome);
            statement.setString(2, cpf);
            statement.setDate(3, dataNascimento);
            statement.setInt(4, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }


    // Método para deletar um agente de saúde do banco de dados pelo ID
    public static boolean deleteAgenteSaude(int id) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM agente_saude WHERE id = ?")) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }

    public static boolean verificarNomeExistente(String nome) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM agente_saude WHERE nome = ?")) {

            statement.setString(1, nome);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }


    // Método utilitário para extrair um agente de saúde de um ResultSet
    private static AgenteSaudeModel extractAgenteSaudeFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nome = resultSet.getString("nome");
        String cpf = resultSet.getString("cpf");
        Date dataNascimento = resultSet.getDate("data_nascimento");

        return new AgenteSaudeModel(id, nome, cpf, dataNascimento);
    }
}
