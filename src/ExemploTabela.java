import javax.swing.*;
import java.awt.*;

public class ExemploTabela extends JPanel {
    public ExemploTabela() {
        setSize(800, 50);
        setLayout(new BorderLayout());
        setBackground(new Color(105, 139, 34));
        setOpaque(true);

        // Painel principal
        JPanel cadastro2Panel = new JPanel(new BorderLayout());



        // Criando um painel para a linha com 4 colunas
        JPanel linhaPanel = new JPanel(new GridLayout(1, 4, 10, 0)); // 1 linha, 4 colunas, espaçamento horizontal 10px

        linhaPanel.setBackground(new Color(205, 205, 204));

        // Criando os textos e adicionando nas colunas
        String[] titulos = {"Tarefa", "Descrição", "Status", "Data Limite"};


        // Criando 4 colunas (JPanels dentro do linhaPanel)
        for (int i = 0; i < 4; i++) {
            JPanel coluna = new JPanel();
            coluna.setBackground(new Color(50 + i * 55, 50, 255)); // Cores diferentes para cada coluna
            coluna.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda para separar colunas

            coluna.setPreferredSize(new Dimension(180, 25)); // Define o tamanho de cada coluna

            JLabel label = new JLabel(titulos[i], JLabel.CENTER);
            coluna.add(label);

            linhaPanel.add(coluna, BorderLayout.NORTH);
        }


        // Adicionando o linhaPanel ao painel principal
        cadastro2Panel.setLayout(new BoxLayout(cadastro2Panel, BoxLayout.Y_AXIS));
        cadastro2Panel.add(linhaPanel);
        add(cadastro2Panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ExemploTabela();
    }
}
