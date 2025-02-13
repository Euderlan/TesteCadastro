import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TarefasTabela extends JFrame {

    public TarefasTabela() {
        setTitle("Tarefas");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Dados para a tabela
        Object[][] dados = {
                {"Finalizar materiais de lançamento", "Ana", "Feito", 100, 3},
                {"Refinar objetivos", "Carlos", "Em andamento", 60, 4},
                {"Identificar recursos principais", "Maria", "Feito", 80, 2}
        };

        // Nomes das colunas
        String[] colunas = {"Tarefa", "Descrição", "Status", "Cronogramaz", "Avaliação"};

        // Modelo da Tabela
        DefaultTableModel modelo = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabela = new JTable(modelo);

        // Customizando o Status com cores
        tabela.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) value;

                if (status.equals("Feito")) {
                    label.setBackground(Color.GREEN);
                    label.setForeground(Color.WHITE);
                } else if (status.equals("Em andamento")) {
                    label.setBackground(Color.ORANGE);
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(Color.RED);
                    label.setForeground(Color.WHITE);
                }

                label.setOpaque(true);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });

        // Customizando o Cronograma com barras de progresso
        tabela.getColumnModel().getColumn(3).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {

                int progresso = (int) value;
                JProgressBar barraProgresso = new JProgressBar(0, 100);
                barraProgresso.setValue(progresso);
                barraProgresso.setStringPainted(true);

                return barraProgresso;
            }
        });

        // Customizando a Avaliação com estrelas
        tabela.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {

                int avaliacao = (int) value;
                String estrelas = "★".repeat(avaliacao) + "☆".repeat(5 - avaliacao);

                JLabel label = new JLabel(estrelas);
                label.setHorizontalAlignment(SwingConstants.CENTER);

                return label;
            }
        });

        // Scroll para a tabela
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TarefasTabela();
    }
}
