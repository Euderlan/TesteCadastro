import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
// Classe Pipoca (herda de Pessoa)
class Pipoca extends Pessoa {
    private String cidadeOrigem;

    public Pipoca(String nome, int idade, String cidadeOrigem) {
        super(nome, idade);
        this.cidadeOrigem = cidadeOrigem;
    }

    public String getCidadeOrigem() { return cidadeOrigem; }
    public void setCidadeOrigem(String cidadeOrigem) { this.cidadeOrigem = cidadeOrigem; }

    @Override
    public String toString() {
        return "[Pipoca] " + nome + " - " + idade + " anos - " + cidadeOrigem;
    }
}