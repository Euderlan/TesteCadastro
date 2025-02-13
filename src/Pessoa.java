import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Classe base
class Pessoa {
    protected String nome;
    protected int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIdade(int idade) { this.idade = idade; }
}