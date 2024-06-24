package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescricoesModel {

    private int id;
    private String cpf;
    private String nome;
    private String alergias;
    private String condicoesMedicas;
    private String observacoes;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCondicoesMedicas() {
        return condicoesMedicas;
    }

    public void setCondicoesMedicas(String condicoesMedicas) {
        this.condicoesMedicas = condicoesMedicas;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public boolean inserirHistoricoMedico(String cpf, int id, String alergias, String condicoesMedicas, String observacoes) throws SQLException {
        // Verificar se o idosoId existe na tabela idoso
        String idosoIdString = String.valueOf(buscarIdIdosoPorCPF(cpf));
        int idosoId;
        try {
            idosoId = Integer.parseInt(idosoIdString);
        } catch (NumberFormatException e) {
            throw new SQLException("Não foi possível encontrar um idoso com o CPF informado.");
        }

        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO historico_medico (idoso_id, alergias, condicoes_medicas, outras_observacoes) " +
                             "VALUES (?, ?, ?, ?)")) {

            statement.setInt(1, idosoId);
            statement.setString(2, alergias);
            statement.setString(3, condicoesMedicas);
            statement.setString(4, observacoes);

            int result = statement.executeUpdate();
            return result > 0;
        }
    }


    public List<PrescricoesModel> buscarHistoricoMedicoPorCPF(String cpf) throws SQLException {
        List<PrescricoesModel> historicos = new ArrayList<>();
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT hm.id, i.nome, hm.alergias, hm.condicoes_medicas, hm.outras_observacoes " +
                             "FROM historico_medico hm " +
                             "JOIN idoso i ON hm.idoso_id = i.id " +
                             "WHERE i.CPF = ?")) {

            statement.setString(1, cpf);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    PrescricoesModel historico = new PrescricoesModel();
                    historico.setId(resultSet.getInt("id"));
                    historico.setNome(resultSet.getString("nome")); // Adiciona o nome do idoso
                    historico.setAlergias(resultSet.getString("alergias"));
                    historico.setCondicoesMedicas(resultSet.getString("condicoes_medicas"));
                    historico.setObservacoes(resultSet.getString("outras_observacoes"));
                    historicos.add(historico);
                }
            }
        }
        return historicos;
    }

    public String buscarIdosoPorCPF(String cpf) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT nome FROM idoso WHERE CPF = ?")) {

            statement.setString(1, cpf);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("Nome");
                }
            }
        }
        return "Deu ruim";
    }

    public int buscarIdIdosoPorCPF(String cpf) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id FROM idoso WHERE CPF = ?")) {

            statement.setString(1, cpf);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        }
        return -1;
    }

    // Método para atualizar um registro no histórico médico
    public boolean atualizarHistoricoMedico(int id, String alergias, String condicoesMedicas, String observacoes) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE historico_medico SET alergias = ?, condicoes_medicas = ?, outras_observacoes = ? WHERE id = ?")) {

            statement.setString(1, alergias);
            statement.setString(2, condicoesMedicas);
            statement.setString(3, observacoes);
            statement.setInt(4, id);

            int result = statement.executeUpdate();
            return result > 0;
        }
    }

    // Método para deletar um registro do histórico médico
    public boolean deletarHistoricoMedico(int id) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM historico_medico WHERE id = ?")) {

            statement.setInt(1, id);

            int result = statement.executeUpdate();
            return result > 0;
        }
    }
}
