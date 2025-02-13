import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
class Usuario extends Pessoa {
    private String email;

    public Usuario(String nome, int idade, String email) {
        super(nome, idade);
        this.email = email;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade + ", Email: " + email;
    }
}