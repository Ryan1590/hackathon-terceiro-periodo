import javax.swing.*;
import Views.AgendamentoView;
import java.sql.Connection;
import java.sql.SQLException;
import Models.DataBase;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Configuração da conexão com o banco de dados
            Connection connection = null;
            try {
                connection = DataBase.connection();

                AgendamentoView agendamentoView = new AgendamentoView(connection);
                agendamentoView.setVisible(true);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados: " + e.getMessage(),
                        "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                // Certifique-se de fechar a conexão quando não precisar mais dela
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
