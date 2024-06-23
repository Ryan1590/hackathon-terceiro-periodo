package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoModel {
    private int id;
    private String agenteSaudeNome;
    private String idosoNome;
    private String vacinaNome;
    private Timestamp dataHoraVisita;
    private String status;

    private Connection connection;

    public AgendamentoModel(Connection connection) {
        this.connection = connection;
    }

    public AgendamentoModel(int id, String agenteSaudeNome, String idosoNome, String vacinaNome, Timestamp dataHoraVisita, String status, Connection connection) {
        this.id = id;
        this.agenteSaudeNome = agenteSaudeNome;
        this.idosoNome = idosoNome;
        this.vacinaNome = vacinaNome;
        this.dataHoraVisita = dataHoraVisita;
        this.status = status;
        this.connection = connection;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAgenteSaudeNome() {
        return agenteSaudeNome;
    }

    public void setAgenteSaudeNome(String agenteSaudeNome) {
        this.agenteSaudeNome = agenteSaudeNome;
    }

    public String getIdosoNome() {
        return idosoNome;
    }

    public void setIdosoNome(String idosoNome) {
        this.idosoNome = idosoNome;
    }

    public String getVacinaNome() {
        return vacinaNome;
    }

    public void setVacinaNome(String vacinaNome) {
        this.vacinaNome = vacinaNome;
    }

    public Timestamp getDataHoraVisita() {
        return dataHoraVisita;
    }

    public void setDataHoraVisita(Timestamp dataHoraVisita) {
        this.dataHoraVisita = dataHoraVisita;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Método para obter todos os agendamentos pendentes
// Método para obter todos os agendamentos, incluindo os que não têm agente de saúde associado
    public List<AgendamentoModel> getAgendamentosPendentes() throws SQLException {
        List<AgendamentoModel> agendamentos = new ArrayList<>();
        String sql = "SELECT a.id, ag.nome AS agente_saude_nome, i.nome AS idoso_nome, v.nome AS vacina_nome, a.data_hora_visita, a.status " +
                "FROM agendamento a " +
                "LEFT JOIN agente_saude ag ON a.agenteSaude_id = ag.id " +
                "INNER JOIN idoso i ON a.idoso_id = i.id " +
                "INNER JOIN vacina v ON a.vacina_id = v.id " +
                "WHERE a.status = 'pendente'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String agenteSaudeNome = rs.getString("agente_saude_nome");
                String idosoNome = rs.getString("idoso_nome");
                String vacinaNome = rs.getString("vacina_nome");
                Timestamp dataHoraVisita = rs.getTimestamp("data_hora_visita");
                String status = rs.getString("status");
                agendamentos.add(new AgendamentoModel(id, agenteSaudeNome, idosoNome, vacinaNome, dataHoraVisita, status, connection));
            }
        }
        return agendamentos;
    }


    // Método para obter um agendamento por ID
    public AgendamentoModel getAgendamentoById(int agendamentoId) throws SQLException {
        String sql = "SELECT a.id, ag.nome AS agente_saude_nome, i.nome AS idoso_nome, v.nome AS vacina_nome, a.data_hora_visita, a.status " +
                "FROM agendamento a " +
                "INNER JOIN agente_saude ag ON a.agenteSaude_id = ag.id " +
                "INNER JOIN idoso i ON a.idoso_id = i.id " +
                "INNER JOIN vacina v ON a.vacina_id = v.id " +
                "WHERE a.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, agendamentoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String agenteSaudeNome = rs.getString("agente_saude_nome");
                String idosoNome = rs.getString("idoso_nome");
                String vacinaNome = rs.getString("vacina_nome");
                Timestamp dataHoraVisita = rs.getTimestamp("data_hora_visita");
                String status = rs.getString("status");
                return new AgendamentoModel(agendamentoId, agenteSaudeNome, idosoNome, vacinaNome, dataHoraVisita, status, connection);
            }
        }
        return null;
    }

    // Método para salvar um novo agendamento
    public int saveAgendamento(String agenteSaudeNome, String idosoNome, String vacinaNome, Timestamp dataHora, String status) throws SQLException {
        int agenteSaudeId = getAgenteSaudeIdPorNome(agenteSaudeNome);
        int idosoId = getIdosoIdPorNome(idosoNome);
        int vacinaId = getVacinaIdPorNome(vacinaNome);

        String sql = "INSERT INTO agendamento (agenteSaude_id, idoso_id, vacina_id, data_hora_visita, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, agenteSaudeId);
            stmt.setInt(2, idosoId);
            stmt.setInt(3, vacinaId);
            stmt.setTimestamp(4, dataHora);
            stmt.setString(5, status);
            int rowsAffected = stmt.executeUpdate();
            return 1;
        }
    }

    // Método para atualizar um agendamento existente
    public boolean updateAgendamento(int id, String agenteSaudeNome, String idosoNome, String vacinaNome, Timestamp dataHora, String status) throws SQLException {
        int agenteSaudeId = getAgenteSaudeIdPorNome(agenteSaudeNome);
        int idosoId = getIdosoIdPorNome(idosoNome);
        int vacinaId = getVacinaIdPorNome(vacinaNome);

        String sql = "UPDATE agendamento SET agenteSaude_id = ?, idoso_id = ?, vacina_id = ?, data_hora_visita = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, agenteSaudeId);
            stmt.setInt(2, idosoId);
            stmt.setInt(3, vacinaId);
            stmt.setTimestamp(4, dataHora);
            stmt.setString(5, status);
            stmt.setInt(6, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Método auxiliar para obter o ID do agente de saúde pelo nome
    private int getAgenteSaudeIdPorNome(String nome) throws SQLException {
        String sql = "SELECT id FROM agente_saude WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("Agente de saúde não encontrado com o nome fornecido: " + nome);
    }

    // Método auxiliar para obter o ID do idoso pelo nome
    private int getIdosoIdPorNome(String nome) throws SQLException {
        String sql = "SELECT id FROM idoso WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("Idoso não encontrado com o nome fornecido: " + nome);
    }

    // Método auxiliar para obter o ID da vacina pelo nome
    private int getVacinaIdPorNome(String nome) throws SQLException {
        String sql = "SELECT id FROM vacina WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("Vacina não encontrada com o nome fornecido: " + nome);
    }
}
