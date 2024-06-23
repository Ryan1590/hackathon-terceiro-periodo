package Controllers;

import Models.AgendamentoModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AgendamentoController {

    private final AgendamentoModel agendamentoModel;

    public AgendamentoController(Connection connection) {
        this.agendamentoModel = new AgendamentoModel(connection); // Inicialização do modelo com a conexão
    }

    public List<AgendamentoModel> getAgendamentos() {
        try {
            return agendamentoModel.getAgendamentosPendentes();
        } catch (SQLException e) {
            System.err.println("Erro ao obter agendamentos do banco de dados: " + e.getMessage());
            return null;
        }
    }

    public int salvarAgendamento(String agenteSaude, String idoso, String vacina, Timestamp dataHoraVisita, String status) {
        try {
            return agendamentoModel.saveAgendamento(agenteSaude, idoso, vacina, dataHoraVisita, status);
        } catch (SQLException e) {
            System.err.println("Erro ao salvar agendamento: " + e.getMessage());
            return -1; // Retornar um código de erro, se necessário
        }
    }

    public boolean atualizarAgendamento(int id, String agenteSaude, String idoso, String vacina, Timestamp dataHoraVisita, String status) {
        try {
            return agendamentoModel.updateAgendamento(id, agenteSaude, idoso, vacina, dataHoraVisita, status);
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar agendamento: " + e.getMessage());
            e.printStackTrace(); // Para depuração, imprima o stack trace completo
            return false;
        }
    }

    public AgendamentoModel buscarAgendamentoPorId(int id) {
        try {
            return agendamentoModel.getAgendamentoById(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar agendamento por ID: " + e.getMessage());
            return null;
        }
    }
}