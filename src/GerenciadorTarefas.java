import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GerenciadorTarefas {
    private DefaultTableModel modelo;

    public GerenciadorTarefas(JTable tabela) {
        this.modelo = (DefaultTableModel) tabela.getModel();
    }

    // Método para adicionar uma nova tarefa à tabela
    public void adicionarTarefa(String titulo, String descricao, String status, Date data) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = sdf.format(data);

        modelo.addRow(new Object[]{titulo, descricao, status, dataFormatada});
    }
}
