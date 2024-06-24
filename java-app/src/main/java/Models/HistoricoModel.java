package Models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoricoModel {

    public List<Object[]> buscarHistoricoPorCPF(String cpf) {
        List<Object[]> historicos = new ArrayList<>();
        String sql = "SELECT v.nome AS vacina, a.data_hora_visita " +
                "FROM idoso i " +
                "JOIN agendamento a ON i.id = a.idoso_id " +
                "JOIN vacina v ON a.vacina_id = v.id " +
                "WHERE i.cpf = ?";

        try (Connection conn = DataBase.connection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String vacina = rs.getString("vacina");
                    Timestamp dataHoraVisita = rs.getTimestamp("data_hora_visita");
                    historicos.add(new Object[]{vacina, dataHoraVisita});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return historicos;
    }
}
