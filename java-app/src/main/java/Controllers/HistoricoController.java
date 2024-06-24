package Controllers;

import Models.HistoricoModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistoricoController {
    private HistoricoModel model;

    public HistoricoController() {
        this.model = new HistoricoModel();
    }

    public void buscarHistoricoPorCPF(String cpf, DefaultTableModel tableModel) {
        List<Object[]> historicos = model.buscarHistoricoPorCPF(cpf);
        tableModel.setRowCount(0); // Limpar a tabela

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (Object[] historico : historicos) {
            String vacina = (String) historico[0];
            Timestamp dataHoraVisita = (Timestamp) historico[1];
            String dataHora = sdf.format(dataHoraVisita);
            tableModel.addRow(new Object[]{vacina, dataHora});
        }

        if (historicos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum resultado encontrado para o CPF informado.");
        }
    }
}
