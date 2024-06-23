package Controllers;

import Models.IdosoModel;

import java.sql.Date;
import java.sql.SQLException;

import java.util.List;

public class IdosoController {

    private final IdosoModel idosoModel;

    public IdosoController() {
        this.idosoModel = new IdosoModel();
    }

    public int saveUser(String name, Date dataNascimento, String cpf, String logradouro, String numero, String bairro, String cep, String cidade, String estado) throws SQLException {
        if (!idosoModel.verificarUser(name)) {
            if (!idosoModel.verificarCpfExistente(cpf)) {
                boolean saved = idosoModel.saveUser(name, dataNascimento, cpf, logradouro, numero, bairro, cep, cidade, estado);
                if (saved) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return 2;
            }
        } else {
            return 1;
        }
    }

    public List<IdosoModel> getUsers() throws SQLException {
        return idosoModel.getUsers();
    }

    public IdosoModel getUser(int id) throws SQLException {
        return idosoModel.getUser(id);
    }

    public int updateUser(String nome, Date dataNascimento, String cpf, String logradouro, String numero, String bairro, String cep, String cidade, String estado, int id) throws SQLException {

        if (IdosoModel.verificarUser(nome)) {
            return -1;
        }

        if (IdosoModel.verificarCpfExistente(cpf)) {
            return -2;
        }

        boolean sucesso = idosoModel.updateUser(nome, dataNascimento, cpf, logradouro, numero, bairro, cep, cidade, estado, id);
        if (sucesso) {
            return 1; // Atualização bem-sucedida
        } else {
            return 0; // Falha geral na atualização
        }
    }


    public boolean deleteUser(int id) throws SQLException {
        if (!idosoModel.hasAppointments(id)) {
            return idosoModel.delete(id);
        }
        return false;
    }
}
