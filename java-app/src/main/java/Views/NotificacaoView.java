package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import Controllers.NotificacaoController;

public class NotificacaoView extends JFrame {

    public NotificacaoView() {
        // Definir o título da janela
        setTitle("Notificações");

        // Configurar a operação de fechamento
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Definir o tamanho da janela
        setSize(800, 600);

        // Definir layout
        setLayout(new BorderLayout());

        // Criar os dados da tabela
        String[] columnNames = {"Agente de Saude", "Nome do Idoso", "Nome da Vacina", "Mensagem"};

        // Obter dados completos de notificação
        NotificacaoController notificacaoController = new NotificacaoController();
        List<String[]> informacoesCompletas = notificacaoController.getInformacoesCompletas();

        // Converter os dados para um formato que o JTable possa usar
        Object[][] data = new Object[informacoesCompletas.size()][4];
        for (int i = 0; i < informacoesCompletas.size(); i++) {
            data[i] = informacoesCompletas.get(i);
        }

        // Criar o modelo de tabela e adicionar os dados
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tornar células não editáveis
            }
        };
        JTable table = new JTable(tableModel);

        // Configurar larguras preferidas das colunas
        table.getColumnModel().getColumn(0).setPreferredWidth(150); // Agente de Saude
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Nome do Idoso
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Nome da Vacina
        table.getColumnModel().getColumn(3).setPreferredWidth(400); // Mensagem

        // Adicionar um listener de clique na tabela para exibir a mensagem completa
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int column = table.columnAtPoint(e.getPoint());
                if (column == 3 && row != -1) { // Verifica se clicou na coluna da Mensagem
                    String mensagem = (String) table.getValueAt(row, column);
                    JOptionPane.showMessageDialog(NotificacaoView.this, mensagem, "Mensagem Completa", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        // Adicionar a tabela em um JScrollPane para suporte a rolagem
        JScrollPane scrollPane = new JScrollPane(table);

        // Adicionar o JScrollPane ao centro do BorderLayout
        add(scrollPane, BorderLayout.CENTER);

        // Tornar a janela visível
        setVisible(true);
    }

    public static void main(String[] args) {
        // Criar e mostrar a NotificacaoView
        SwingUtilities.invokeLater(() -> new NotificacaoView());
    }
}
