package Controllers;

import Models.PrescricoesModel;

import java.sql.SQLException;
import java.util.List;

public class PrescricoesController {

    private PrescricoesModel model;

    public PrescricoesController() {
        this.model = new PrescricoesModel();
    }

    public boolean salvarHistoricoMedico(String cpf, String alergias, String condicoes, String observacoes) throws SQLException {

        int idosoId = Integer.parseInt(String.valueOf(model.buscarIdIdosoPorCPF(cpf)));
        if (idosoId != -1) {

            return model.inserirHistoricoMedico(cpf, idosoId, alergias, condicoes, observacoes);
        } else {
            return false;
        }
    }

    // Método para buscar histórico médico pelo CPF do idoso
    public List<PrescricoesModel> buscarHistoricoMedicoPorCPF(String cpf) {
        try {
            return model.buscarHistoricoMedicoPorCPF(cpf);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para atualizar um registro no histórico médico
    public boolean atualizarHistoricoMedico(int id, String alergias, String condicoesMedicas, String observacoes) {
        try {
            return model.atualizarHistoricoMedico(id, alergias, condicoesMedicas, observacoes);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para deletar um registro do histórico médico
    public boolean deletarHistoricoMedico(int id) {
        try {
            return model.deletarHistoricoMedico(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
