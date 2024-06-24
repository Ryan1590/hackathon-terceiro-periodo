package Integrations;

import Controllers.IdosoController;
import Models.IdosoModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

public class IdosoControllerTest {

    private IdosoController idosoController;

    @Before
    public void setUp() {
        // Inicializar o objeto do controller antes de cada teste
        idosoController = new IdosoController();
    }

    @After
    public void tearDown() {
        // Limpar recursos após cada teste, se necessário
    }

    @Test
    public void testSaveUser() {
        // Simular dados de um novo idoso
        String nome = "Teste";
        String cpf = "12345678901";
        String logradouro = "Rua Teste";
        String numero = "123";
        String bairro = "Bairro Teste";
        String cep = "12345678";
        String cidade = "Cidade Teste";
        String estado = "Estado Teste";
        Date dataNascimento = null;
        try {
            dataNascimento = (Date) new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1970");
        } catch (ParseException e) {
            fail("Erro ao parsear data de nascimento para teste.");
        }

        try {
            // Chamar método do controller para salvar
            int resultado = idosoController.saveUser(nome, new java.sql.Date(dataNascimento.getTime()), cpf, logradouro, numero, bairro, cep, cidade, estado);

            // Verificar se o resultado é o esperado
            assertEquals(0, resultado); // 0 indica sucesso
        } catch (SQLException e) {
            fail("Erro ao salvar idoso no teste: " + e.getMessage());
        }
    }

    @Test
    public void testGetUsers() {
        try {
            // Chamar método do controller para obter lista de idosos
            List<IdosoModel> idosos = idosoController.getUsers();

            // Verificar se a lista não está vazia
            assertNotNull(idosos);
            assertFalse(idosos.isEmpty());
        } catch (SQLException e) {
            fail("Erro ao obter lista de idosos no teste: " + e.getMessage());
        }
    }

    // Adicione mais métodos de teste conforme necessário para cobrir outros cenários do controller
}
