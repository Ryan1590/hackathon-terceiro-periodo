package Controllers;

import Models.vacinaModel;

import java.sql.SQLException;
import java.util.List;

public class vacinaController {

    private final vacinaModel vacinaModel;

    public vacinaController() {
        this.vacinaModel = new vacinaModel();
    }

    public int saveVacina(String nome, String descricao) throws SQLException {
        if (!vacinaModel.verificarVacina(nome)) {
            boolean saved = vacinaModel.saveVacina(nome, descricao);
            if(saved){
                return 1;
            }
        }
        return 0;
    }

    public List<vacinaModel> getVacinas() throws SQLException {
        return vacinaModel.getVacinas();
    }

    public vacinaModel getVacina(int id) throws SQLException {
        return vacinaModel.getVacina(id);
    }

    public int updateVacina(String nome, String descricao, int id) throws SQLException {
        // Recupera a vacina atual do banco de dados
        vacinaModel currentVacina = vacinaModel.getVacina(id);
        if (currentVacina == null) {
            return -3;
        }
        if (!currentVacina.getNome().equals(nome) && vacinaModel.verificarVacina(nome)) {
            return -1;
        }

        // Tenta atualizar a vacina
        boolean sucesso = vacinaModel.updateVacina(nome, descricao, id);
        if (sucesso) {
            return 1;
        } else {
            return 0;
        }
    }


    public int deleteVacina(int id) throws SQLException {
        if(!vacinaModel.hasAgendamentos(id)){
            vacinaModel.deleteVacina(id);
            return 1;
        }
        return 2;
    }
}
