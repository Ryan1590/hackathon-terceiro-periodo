import Models.IdosoModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IdosoModelTest {

    private IdosoModel idosoModel;

    @BeforeEach
    void setUp() {
        idosoModel = new IdosoModel();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSaveUser() {
        try {
            assertTrue(idosoModel.saveUser("Teste", new java.sql.Date(System.currentTimeMillis()), "12345678900",
                    "Rua Teste", "123", "Centro", "12345-678", "Cidade Teste", "Estado Teste"));
        } catch (SQLException e) {
            fail("SQLException lançada: " + e.getMessage());
        }
    }

    @Test
    void testGetUsers() {
        try {
            List<IdosoModel> users = idosoModel.getUsers();
            assertNotNull(users);
            assertTrue(users.size() > 0);
        } catch (SQLException e) {
            fail("SQLException lançada: " + e.getMessage());
        }
    }

    @Test
    void testGetUser() {
        try {
            IdosoModel user = idosoModel.getUser(1);
            assertNotNull(user);
        } catch (SQLException e) {
            fail("SQLException lançada: " + e.getMessage());
        }
    }

    @Test
    void testUpdateUser() {
        try {
            assertTrue(idosoModel.updateUser("Nome Atualizado", new java.sql.Date(System.currentTimeMillis()), "12345678900",
                    "Rua Atualizada", "456", "Novo Bairro", "54321-987", "Nova Cidade", "Novo Estado", 1));
        } catch (SQLException e) {
            fail("SQLException lançada: " + e.getMessage());
        }
    }

    @Test
    void testDeleteUser() {
        try {
            assertTrue(idosoModel.delete(1));
        } catch (SQLException e) {
            fail("SQLException lançada: " + e.getMessage());
        }
    }

    @Test
    void testVerificarCpfExistente() {
        try {
            assertTrue(IdosoModel.verificarCpfExistente("12345678900"));
        } catch (SQLException e) {
            fail("SQLException lançada: " + e.getMessage());
        }
    }

    @Test
    void testVerificarUser() {
        try {
            assertTrue(IdosoModel.verificarUser("Teste"));
        } catch (SQLException e) {
            fail("SQLException lançada: " + e.getMessage());
        }
    }

    @Test
    void testHasAppointments() {
        try {
            assertFalse(idosoModel.hasAppointments(1)); // Aqui você deve ajustar o ID do idoso conforme o seu banco de dados
        } catch (SQLException e) {
            fail("SQLException lançada: " + e.getMessage());
        }
    }
}

