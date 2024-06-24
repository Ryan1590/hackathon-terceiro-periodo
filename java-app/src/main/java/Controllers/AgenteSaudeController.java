package Controllers;

import Models.AgenteSaudeModel;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class AgenteSaudeController {

    private final AgenteSaudeModel agenteModel;

    public AgenteSaudeController() {
        // Exemplo de inicialização com valores padrão
        this.agenteModel = new AgenteSaudeModel(0, "", "", null);
    }

    public int saveAgenteSaude(String nome, String cpf, String dataNascimento) throws SQLException {
        if(!AgenteSaudeModel.verificarCpfExistente(cpf)){
            if(!AgenteSaudeModel.verificarNomeExistente(nome)){
                Date dataNasc = Date.valueOf(dataNascimento);
                boolean saved = AgenteSaudeModel.saveAgenteSaude(nome, cpf, dataNasc);
                if(saved){
                    return 0;
                }else{
                    return -1;
                }
            }else{
                return 1;
            }
        }else{
            return 2;
        }
    }

    public List<AgenteSaudeModel> getAgentesSaude() throws SQLException {
        return AgenteSaudeModel.getAgentesSaude();
    }

    public AgenteSaudeModel getAgenteSaude(int id) throws SQLException {
        return AgenteSaudeModel.getAgenteSaude(id);
    }

    public int updateAgenteSaude(String nome, String cpf, String dataNascimento, int id) throws SQLException {
        // Verificar se o novo nome já existe e não pertence à mesma pessoa
        if (AgenteSaudeModel.verificarNomeExistente(nome) && !agenteModel.getAgenteSaude(id).getNome().equals(nome)) {
            return -1; // Nome já existe para outro agente de saúde
        }

        // Verificar se o novo CPF já existe e não pertence à mesma pessoa
        if (AgenteSaudeModel.verificarCpfExistente(cpf) && !agenteModel.getAgenteSaude(id).getCpf().equals(cpf)) {
            return -2; // CPF já existe para outro agente de saúde
        }

        // Caso todas as verificações passem, realizar a atualização
        Date dataNasc = Date.valueOf(dataNascimento);
        if (agenteModel.updateAgenteSaude(id, nome, cpf, dataNasc)) {
            return 1; // Atualização bem-sucedida
        } else {
            return 0; // Falha ao atualizar
        }
    }



    public int deleteAgenteSaude(int id) throws SQLException {
        if(!AgenteSaudeModel.hasAgendamentos(id)){
            AgenteSaudeModel.deleteAgenteSaude(id);
            return 1;
        }
       return 2;
    }
}

