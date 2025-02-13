import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
// Interface gráfica CadastroBBB
public class CadastroBBB extends JFrame {
    private JTextField nomeField, idadeField, extraField;
    private JComboBox<String> tipoCombo;
    private DefaultListModel<String> listModel;
    private JList<String> participantesList;
    private ArrayList<Pessoa> participantes;

    public CadastroBBB() {
        setTitle("Cadastro BBB 2025");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240));

        participantes = new ArrayList<>();
        listModel = new DefaultListModel<>();
        participantesList = new JList<>(listModel);


        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.RED);

        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField(15);
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Idade:"));
        idadeField = new JTextField(5);
        inputPanel.add(idadeField);

        inputPanel.add(new JLabel("Tipo:"));
        tipoCombo = new JComboBox<>(new String[]{"Camarote", "Pipoca"});
        inputPanel.add(tipoCombo);

        inputPanel.add(new JLabel("Profissão:"));
        extraField = new JTextField(15);
        inputPanel.add(extraField);

        inputPanel.add(new JLabel("Cidade:"));
        extraField = new JTextField(15);
        inputPanel.add(extraField);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Adicionar");
        JButton removeButton = new JButton("Remover");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        JScrollPane scrollPane = new JScrollPane(participantesList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Participantes"));

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> adicionarParticipante());
        removeButton.addActionListener(e -> removerParticipante());

        setVisible(true);
    }

    private void adicionarParticipante() {
        String nome = nomeField.getText();
        int idade;
        try {
            idade = Integer.parseInt(idadeField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade inválida!");
            return;
        }
        String extra = extraField.getText();

        Pessoa participante;
        if (tipoCombo.getSelectedItem().equals("Camarote")) {
            participante = new Camarote(nome, idade, extra);
        } else {
            participante = new Pipoca(nome, idade, extra);
        }

        participantes.add(participante);
        listModel.addElement(participante.toString());
        limparCampos();
    }

    private void removerParticipante() {
        int index = participantesList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um participante para remover.");
            return;
        }

        participantes.remove(index);
        listModel.remove(index);
    }

    private void limparCampos() {
        nomeField.setText("");
        idadeField.setText("");
        extraField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CadastroBBB::new);
    }
}
