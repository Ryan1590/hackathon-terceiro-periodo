package Integrations;

import Controllers.vacinaController;
import Models.vacinaModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class VacinaControllerTest {

    private vacinaController controller;

    @Before
    public void setUp() {
        // Inicializar o controller antes de cada teste
        controller = new vacinaController();
    }

    @After
    public void tearDown() {
        // Limpar recursos após cada teste, se necessário
    }

    @Test
    public void testCRUDVacina() {
        // Teste de criação (saveVacina)
        String nomeVacina = "Vacina Teste";
        String descricaoVacina = "Descrição da Vacina Teste";

        try {
            int resultSave = controller.saveVacina(nomeVacina, descricaoVacina);
            assertThat(resultSave).isEqualTo(1); // Deve retornar 1 se a vacina foi salva com sucesso

            // Teste de leitura (getVacinas)
            List<vacinaModel> vacinas = controller.getVacinas();
            assertThat(vacinas).isNotNull();
            assertThat(vacinas).isNotEmpty();
            assertThat(vacinas).extracting("nome").contains(nomeVacina); // Verificar se a vacina salva está na lista

            // Teste de atualização (updateVacina)
            String novoNomeVacina = "Novo Nome Vacina";
            String novaDescricaoVacina = "Nova Descrição da Vacina";
            int idVacinaAtualizada = vacinas.get(0).getId(); // Supondo que a primeira vacina da lista seja a que vamos atualizar

            int resultUpdate = controller.updateVacina(novoNomeVacina, novaDescricaoVacina, idVacinaAtualizada);
            assertThat(resultUpdate).isEqualTo(1); // Deve retornar 1 se a vacina foi atualizada com sucesso

            // Teste de exclusão (deleteVacina)
            int idVacinaParaExcluir = vacinas.get(0).getId(); // Excluir a primeira vacina da lista
            boolean resultDelete = controller.deleteVacina(idVacinaParaExcluir);
            assertThat(resultDelete).isTrue(); // Deve retornar true se a vacina foi excluída com sucesso

        } catch (SQLException e) {
            fail("Erro durante o teste de CRUD de vacina: " + e.getMessage());
        }
    }
}
