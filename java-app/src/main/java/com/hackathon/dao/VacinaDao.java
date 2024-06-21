package com.hackathon.dao;

import com.hackathon.model.Vacina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacinaDao {

    private static final String URL = "jdbc:mysql://localhost:3306/hackathon_vacina";
    private static final String USER = "root";
    private static final String PASSWORD = "senha";

    public void inserir(Vacina vacina) throws SQLException {
        String sql = "INSERT INTO vacina (nome, descricao) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vacina.getNome());
            statement.setString(2, vacina.getDescricao());
            statement.executeUpdate();
        }
    }

    public void atualizar(Vacina vacina) throws SQLException {
        String sql = "UPDATE vacina SET nome = ?, descricao = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vacina.getNome());
            statement.setString(2, vacina.getDescricao());
            statement.setInt(3, vacina.getId());
            statement.executeUpdate();
        }
    }

    public List<Vacina> listarTodos() throws SQLException {
        List<Vacina> vacinas = new ArrayList<>();
        String sql = "SELECT id, nome, descricao FROM vacina";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Vacina vacina = new Vacina();
                vacina.setId(resultSet.getInt("id"));
                vacina.setNome(resultSet.getString("nome"));
                vacina.setDescricao(resultSet.getString("descricao"));
                vacinas.add(vacina);
            }
        }
        return vacinas;
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM vacina WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
