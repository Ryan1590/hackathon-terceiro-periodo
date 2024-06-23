package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificacaoModel {
    private int agendamentoId;
    private String mensagem;
    private String tipo;

    public NotificacaoModel(int agendamentoId, String mensagem, String tipo) {
        this.agendamentoId = agendamentoId;
        this.mensagem = mensagem;
        this.tipo = tipo;
    }

    public int getAgendamentoId() {
        return agendamentoId;
    }

    public void setAgendamentoId(int agendamentoId) {
        this.agendamentoId = agendamentoId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static List<String[]> getInformacoesCompletas() {
        List<String[]> informacoesCompletas = new ArrayList<>();

        String query = "SELECT ag.nome AS agente_nome, i.nome AS nome_idoso, v.nome AS nome_vacina, al.mensagem " +
                "FROM alertas al " +
                "JOIN agendamento a ON al.agendamento_id = a.id " +
                "JOIN idoso i ON a.idoso_id = i.id " +
                "JOIN vacina v ON a.vacina_id = v.id " +
                "JOIN agente_saude ag ON a.agenteSaude_id = ag.id";

        try (Connection connection = DataBase.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String nomeAgente = resultSet.getString("agente_nome");
                String nomeIdoso = resultSet.getString("nome_idoso");
                String nomeVacina = resultSet.getString("nome_vacina");
                String mensagem = resultSet.getString("mensagem");

                informacoesCompletas.add(new String[]{
                        nomeAgente,
                        nomeIdoso,
                        nomeVacina,
                        mensagem
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return informacoesCompletas;
    }

}
