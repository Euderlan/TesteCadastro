import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class Tarefa extends JFrame {
    private JTextField tituloField, descricaoField;
    private JComboBox<String> statusField;
    private JSpinner dataField;
    private JButton adicionarBtn, editarBtn, removerBtn;
    private JPanel listaTarefas;
    private ExemploTabela exemploTabela;
    private ArrayList<JPanel> tarefas;
    private JPanel tarefaSelecionada;

    public Tarefa() {
        setTitle("Gerenciador de Tarefas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tarefas = new ArrayList<>();

        JPanel cadastroPanel = new JPanel(new BorderLayout());
        cadastroPanel.setLayout(new BoxLayout(cadastroPanel, BoxLayout.Y_AXIS));
        cadastroPanel.setBorder(BorderFactory.createEmptyBorder(40, 50, 20, 50));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 5));
        inputPanel.setMaximumSize(new Dimension(800, 150));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Nova Tarefa"));

        inputPanel.add(new JLabel("Título:"));
        tituloField = new JTextField();
        inputPanel.add(tituloField);

        inputPanel.add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        inputPanel.add(descricaoField);

        inputPanel.add(new JLabel("Status:"));
        statusField = new JComboBox<>(new String[]{"Feito", "Em andamento", "Parado"});
        inputPanel.add(statusField);

        inputPanel.add(new JLabel("Data Limite:"));
        SpinnerDateModel model = new SpinnerDateModel();
        dataField = new JSpinner(model);
        dataField.setEditor(new JSpinner.DateEditor(dataField, "dd/MM/yyyy"));
        inputPanel.add(dataField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setMaximumSize(new Dimension(800, 50));
        adicionarBtn = new JButton("Adicionar");
        editarBtn = new JButton("Editar");
        editarBtn.setEnabled(false);
        removerBtn = new JButton("Remover");
        removerBtn.setEnabled(false);
        buttonPanel.add(adicionarBtn);
        buttonPanel.add(editarBtn);
        buttonPanel.add(removerBtn);

        exemploTabela = new ExemploTabela();
        JPanel exemploTabelaPanel = new JPanel();
        exemploTabelaPanel.add(exemploTabela, BorderLayout.CENTER);
        exemploTabelaPanel.setMaximumSize(new Dimension(800, 100));


        listaTarefas = new JPanel(new BorderLayout());


        listaTarefas.setMaximumSize(new Dimension(750, 30));
        listaTarefas.setBorder(BorderFactory.createTitledBorder(""));

        adicionarBtn.addActionListener(e -> adicionarTarefa());
        editarBtn.addActionListener(e -> editarTarefa());
        removerBtn.addActionListener(e -> removerTarefa());


        cadastroPanel.add(inputPanel);
        cadastroPanel.add(buttonPanel);
        cadastroPanel.add(exemploTabelaPanel);
        cadastroPanel.add(listaTarefas);
        add(cadastroPanel);

        setVisible(true);
    }

    private void adicionarTarefa() {
        String titulo = tituloField.getText();
        String descricao = descricaoField.getText();
        String status = (String) statusField.getSelectedItem();
        String data = new JSpinner.DateEditor(dataField, "dd/MM/yyyy").getFormat().format(dataField.getValue());

        if (!titulo.isEmpty() && !descricao.isEmpty()) {
            JPanel tarefaPanel = new JPanel(new GridLayout(1, 4, 10, 5));
            JLabel tituloLabel = new JLabel(titulo);
            JLabel descricaoLabel = new JLabel(descricao);
            JLabel statusLabel = new JLabel(status);
            JLabel dataLabel = new JLabel(data);
            tarefaPanel.add(tituloLabel);
            tarefaPanel.add(descricaoLabel);
            tarefaPanel.add(statusLabel);
            tarefaPanel.add(dataLabel);
            tarefaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            tarefaPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tarefaSelecionada = tarefaPanel;
                    editarBtn.setEnabled(true);
                    removerBtn.setEnabled(true);
                    tituloField.setText(tituloLabel.getText());
                    descricaoField.setText(descricaoLabel.getText());
                    statusField.setSelectedItem(statusLabel.getText());
                    dataField.setValue(new Date());
                }
            });

            tarefas.add(tarefaPanel);
            listaTarefas.add(tarefaPanel);
            listaTarefas.revalidate();
            listaTarefas.repaint();
            limparCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarTarefa() {
        if (tarefaSelecionada != null) {
            ((JLabel) tarefaSelecionada.getComponent(0)).setText(tituloField.getText());
            ((JLabel) tarefaSelecionada.getComponent(1)).setText(descricaoField.getText());
            ((JLabel) tarefaSelecionada.getComponent(2)).setText((String) statusField.getSelectedItem());
            ((JLabel) tarefaSelecionada.getComponent(3)).setText(new JSpinner.DateEditor(dataField, "dd/MM/yyyy").getFormat().format(dataField.getValue()));
            limparCampos();
        }
    }

    private void removerTarefa() {
        if (tarefaSelecionada != null) {
            tarefas.remove(tarefaSelecionada);
            listaTarefas.remove(tarefaSelecionada);
            listaTarefas.revalidate();
            listaTarefas.repaint();
            tarefaSelecionada = null;
            editarBtn.setEnabled(false);
            removerBtn.setEnabled(false);
        }
    }

    private void limparCampos() {
        tituloField.setText("");
        descricaoField.setText("");
        statusField.setSelectedIndex(0);
        dataField.setValue(new Date());
    }

    public static void main(String[] args) {
        new Tarefa();
    }
}
