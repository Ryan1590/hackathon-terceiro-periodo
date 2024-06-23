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
        if (!vacinaModel.verificarVacina(nome)) {
            boolean saved = vacinaModel.updateVacina(nome, descricao, id);
            if(saved){
                return 1;
            }
        }
        return 0;
    }

    public boolean deleteVacina(int id) throws SQLException {
        return vacinaModel.deleteVacina(id);
    }
}
