package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class vacinaModel {
    private int id;
    private String nome;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean saveVacina(String nome, String descricao) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO vacina (Nome, Descricao) VALUES (?,?)")) {

            statement.setString(1, nome);
            statement.setString(2, descricao);

            int result = statement.executeUpdate();
            return result > 0;
        }
    }

    public List<vacinaModel> getVacinas() throws SQLException {
        List<vacinaModel> vacinas = new ArrayList<>();
        try (Connection connection = DataBase.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM vacina")) {

            while (resultSet.next()) {
                vacinaModel vacina = extractVacinaFromResultSet(resultSet);
                vacinas.add(vacina);
            }
        }
        return vacinas;
    }

    public vacinaModel getVacina(int id) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM vacina WHERE id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractVacinaFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    public boolean updateVacina(String nome, String descricao, int id) throws SQLException {
        // Verifica se o novo nome já existe e não pertence à vacina que estamos tentando atualizar
        if (verificarVacina(nome) && !getVacina(id).getNome().equals(nome)) {
            return false;
        }

        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE vacina SET Nome = ?, Descricao = ? WHERE id = ?")) {

            statement.setString(1, nome);
            statement.setString(2, descricao);
            statement.setInt(3, id);

            int result = statement.executeUpdate();
            return result > 0;
        }
    }

    public boolean deleteVacina(int id) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM vacina WHERE id = ?")) {

            statement.setInt(1, id);

            int result = statement.executeUpdate();
            return result > 0;
        }
    }

    public static boolean verificarVacina(String nome) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT Nome FROM vacina WHERE Nome = ?")) {

            statement.setString(1, nome);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Retorna true se encontrar algum resultado
            }
        }
    }

    private vacinaModel extractVacinaFromResultSet(ResultSet resultSet) throws SQLException {
        vacinaModel vacina = new vacinaModel();
        vacina.setId(resultSet.getInt("id"));
        vacina.setNome(resultSet.getString("Nome"));
        vacina.setDescricao(resultSet.getString("Descricao"));

        return vacina;
    }
}
