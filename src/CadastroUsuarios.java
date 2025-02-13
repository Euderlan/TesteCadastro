import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
// Classe principal com a interface gráfica
public class CadastroUsuarios extends JFrame {
    private JTextField nomeField, idadeField, emailField;
    private DefaultListModel<String> listModel;
    private JList<String> userList;
    private ArrayList<Usuario> usuarios;

    public CadastroUsuarios() {
        setTitle("Cadastro de Usuários");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        usuarios = new ArrayList<>();
        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);

        // Painel principal
        JPanel cadastroPanel = new JPanel(new BorderLayout());
        cadastroPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Painel de entrada (campos de texto)
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Dados do Usuário"));

        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField(15);
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Idade:"));
        idadeField = new JTextField(5);
        inputPanel.add(idadeField);

        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField(15);
        inputPanel.add(emailField);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(193, 255, 193));

        JButton addButton = new JButton("Adicionar");
        addButton.addActionListener(e -> adicionarUsuario());
        buttonPanel.add(addButton);

        JButton editButton = new JButton("Editar");
        editButton.addActionListener(e -> editarUsuario());
        buttonPanel.add(editButton);

        JButton removeButton = new JButton("Remover");
        removeButton.addActionListener(e -> removerUsuario());
        buttonPanel.add(removeButton);

        // Lista de usuários com rolagem
        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Usuários"));

        // Adicionando componentes ao painel principal
        cadastroPanel.add(inputPanel, BorderLayout.NORTH);
        cadastroPanel.add(scrollPane, BorderLayout.CENTER);
        cadastroPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adicionando ao JFrame
        add(cadastroPanel);

        setVisible(true);
    }

    private void adicionarUsuario() {
        String nome = nomeField.getText();
        int idade;
        try {
            idade = Integer.parseInt(idadeField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade inválida!");
            return;
        }
        String email = emailField.getText();

        Usuario usuario = new Usuario(nome, idade, email);
        usuarios.add(usuario);
        listModel.addElement(usuario.toString());
        limparCampos();
    }

    private void editarUsuario() {
        int index = userList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.");
            return;
        }

        String nome = nomeField.getText();
        int idade;
        try {
            idade = Integer.parseInt(idadeField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Idade inválida!");
            return;
        }
        String email = emailField.getText();

        Usuario usuario = usuarios.get(index);
        usuario.setNome(nome);
        usuario.setIdade(idade);
        usuario.setEmail(email);

        listModel.set(index, usuario.toString());
        limparCampos();
    }

    private void removerUsuario() {
        int index = userList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.");
            return;
        }

        usuarios.remove(index);
        listModel.remove(index);
    }

    private void limparCampos() {
        nomeField.setText("");
        idadeField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CadastroUsuarios::new);
    }
}