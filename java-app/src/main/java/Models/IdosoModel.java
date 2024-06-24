package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IdosoModel {
    private int id;
    private String name;
    private String bairro;
    private String estado;
    private String cpf;
    private Date dataNascimento;
    private String cep;
    private String logradouro;
    private String numero;
    private String cidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public boolean saveUser(String name, Date dataNascimento, String cpf, String logradouro, String numero, String bairro, String cep, String cidade, String estado) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO idoso (Nome, Data_nascimento, CPF, Logradouro, Numero_logradouro, Bairro, CEP, Cidade, Estado) " +
                             "VALUES (?,?,?,?,?,?,?,?,?)")) {

            statement.setString(1, name);
            statement.setString(2, String.valueOf(dataNascimento));
            statement.setString(3, cpf);
            statement.setString(4, logradouro);
            statement.setString(5, numero);
            statement.setString(6, bairro);
            statement.setString(7, cep);
            statement.setString(8, cidade);
            statement.setString(9, estado);

            int result = statement.executeUpdate();
            return result > 0;
        }
    }

    public List<IdosoModel> getUsers() throws SQLException {
        List<IdosoModel> users = new ArrayList<>();
        try (Connection connection = DataBase.connection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM idoso")) {

            while (resultSet.next()) {
                IdosoModel user = extractUserFromResultSet(resultSet);
                users.add(user);
            }
        }
        return users;
    }

    public IdosoModel getUser(int id) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM idoso WHERE id = ?")) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    public boolean updateUser(String name, Date dataNascimento, String cpf, String logradouro, String numero, String bairro, String cep, String cidade, String estado, int id) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE idoso SET Nome = ?, Data_nascimento = ?, CPF = ?, Logradouro = ?, Numero_logradouro = ?, Bairro = ?, CEP = ?, Cidade = ?, Estado = ? WHERE id = ?")) {

            statement.setString(1, name);
            statement.setString(2, String.valueOf(dataNascimento));
            statement.setString(3, cpf);
            statement.setString(4, logradouro);
            statement.setString(5, numero);
            statement.setString(6, bairro);
            statement.setString(7, cep);
            statement.setString(8, cidade);
            statement.setString(9, estado);
            statement.setInt(10, id);

            int result = statement.executeUpdate();
            return result > 0;
        }
    }


    public boolean delete(int id) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM idoso WHERE id = ?")) {

            statement.setInt(1, id);

            int result = statement.executeUpdate();
            return result > 0;
        }
    }

    public static boolean verificarCpfExistente(String cpf) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM idoso WHERE CPF = ?")) {

            statement.setString(1, cpf);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }

    private IdosoModel extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        IdosoModel user = new IdosoModel();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("Nome"));
        user.setDataNascimento(Date.valueOf(resultSet.getString("Data_nascimento")));
        user.setCpf(resultSet.getString("CPF"));
        user.setLogradouro(resultSet.getString("Logradouro"));
        user.setNumero(resultSet.getString("Numero_logradouro"));
        user.setBairro(resultSet.getString("Bairro"));
        user.setCep(resultSet.getString("CEP"));
        user.setCidade(resultSet.getString("Cidade"));
        user.setEstado(resultSet.getString("Estado"));

        return user;
    }


    public static boolean verificarUser(String name) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT Nome FROM idoso WHERE Nome = ?")) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    public boolean hasAppointments(int idosoId) throws SQLException {
        try (Connection connection = DataBase.connection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM agendamento WHERE idoso_id = ?")) {

            statement.setInt(1, idosoId);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}
