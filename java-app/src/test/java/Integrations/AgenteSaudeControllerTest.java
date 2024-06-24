package Integrations;

import Controllers.AgenteSaudeController;
import Models.AgenteSaudeModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static java.sql.DriverManager.getConnection;
import static org.junit.Assert.*;

public class AgenteSaudeControllerTest {

    private AgenteSaudeController controller;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        // Configuração inicial
        controller = new AgenteSaudeController();
        connection = getConnection("jdbc:h2:mem:test;MODE=MYSQL;DB_CLOSE_DELAY=-1");
        createTable(); // Método para criar a tabela de teste
    }

    @After
    public void tearDown() throws Exception {
        // Limpeza após os testes
        dropTable(); // Método para excluir a tabela de teste
        connection.close();
    }

    @Test
    public void testSaveAgenteSaudeIntegration() throws SQLException {
        // Dados para teste
        String nome = "TesteNome";
        String cpf = "12345678909";
        Date dataNascimento = Date.valueOf("1990-01-01");

        // Chama o método de salvar agente de saúde no controller
        int result = controller.saveAgenteSaude(nome, cpf, String.valueOf(dataNascimento));

        // Verifica se o agente de saúde foi salvo corretamente (result = 0)
        assertEquals(0, result);

        // Verifica se o agente de saúde está na base de dados
        List<AgenteSaudeModel> agentes = getAgentesFromDatabase();
        assertNotNull(agentes);
        assertFalse(agentes.isEmpty());
        assertEquals(nome, agentes.get(0).getNome());
        assertEquals(cpf, agentes.get(0).getCpf());
        assertEquals(dataNascimento, agentes.get(0).getDataNascimento());
    }

    @Test
    public void testUpdateAgenteSaudeIntegration() throws SQLException {
        // Primeiro, salva um agente de saúde para atualização
        String nomeOriginal = "TesteNomeOriginal";
        String cpfOriginal = "12345678909";
        Date dataNascimentoOriginal = Date.valueOf("1990-01-01");
        controller.saveAgenteSaude(nomeOriginal, cpfOriginal, String.valueOf(dataNascimentoOriginal));

        // Obtém o ID do agente salvo
        List<AgenteSaudeModel> agentes = getAgentesFromDatabase();
        int id = agentes.get(0).getId();

        // Dados atualizados
        String novoNome = "NovoNome";
        String novoCpf = "98765432109";
        Date novaDataNascimento = Date.valueOf("1980-01-01");

        // Chama o método de atualizar agente de saúde no controller
        int result = controller.updateAgenteSaude(novoNome, novoCpf, String.valueOf(novaDataNascimento), id);

        // Verifica se a atualização foi bem-sucedida (result = 1)
        assertEquals(1, result);

        // Verifica se os dados foram atualizados corretamente na base de dados
        List<AgenteSaudeModel> agentesAtualizados = getAgentesFromDatabase();
        assertNotNull(agentesAtualizados);
        assertFalse(agentesAtualizados.isEmpty());
        assertEquals(novoNome, agentesAtualizados.get(0).getNome());
        assertEquals(novoCpf, agentesAtualizados.get(0).getCpf());
        assertEquals(novaDataNascimento, agentesAtualizados.get(0).getDataNascimento());
    }

    @Test
    public void testDeleteAgenteSaudeIntegration() throws SQLException {
        // Primeiro, salva um agente de saúde para exclusão
        String nome = "TesteNome";
        String cpf = "12345678909";
        Date dataNascimento = Date.valueOf("1990-01-01");
        controller.saveAgenteSaude(nome, cpf, String.valueOf(dataNascimento));

        // Obtém o ID do agente salvo
        List<AgenteSaudeModel> agentes = getAgentesFromDatabase();
        int id = agentes.get(0).getId();

        // Chama o método de excluir agente de saúde no controller
        boolean result = controller.deleteAgenteSaude(id);

        // Verifica se a exclusão foi bem-sucedida (result = true)
        assertTrue(result);

        // Verifica se o agente de saúde foi removido da base de dados
        List<AgenteSaudeModel> agentesExcluidos = getAgentesFromDatabase();
        assertTrue(agentesExcluidos.isEmpty());
    }

    private void createTable() throws SQLException {
        String sql = "CREATE TABLE agente_saude (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL," +
                "cpf VARCHAR(11) NOT NULL," +
                "data_nascimento DATE NOT NULL)";
        connection.createStatement().executeUpdate(sql);
    }

    private void dropTable() throws SQLException {
        String sql = "DROP TABLE IF EXISTS agente_saude";
        connection.createStatement().executeUpdate(sql);
    }

    private List<AgenteSaudeModel> getAgentesFromDatabase() throws SQLException {
        return controller.getAgentesSaude();
    }
}
