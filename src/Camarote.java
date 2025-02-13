import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
// Classe Camarote (herda de Pessoa)
class Camarote extends Pessoa {
    private String profissao;

    public Camarote(String nome, int idade, String profissao) {
        super(nome, idade);
        this.profissao = profissao;
    }

    public String getProfissao() { return profissao; }
    public void setProfissao(String profissao) { this.profissao = profissao; }

    @Override
    public String toString() {
        return "[Camarote] " + nome + " - " + idade + " anos - " + profissao;
    }
}
