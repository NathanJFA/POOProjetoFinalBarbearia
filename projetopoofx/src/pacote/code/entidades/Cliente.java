package pacote.code.entidades;

import javax.print.DocFlavor.STRING;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
    private StringProperty nome;
    private StringProperty telefone;
    private StringProperty email;

    public Cliente(String nome, String telefone, String email) {
        this.nome = new SimpleStringProperty((String) nome);
        this.telefone = new SimpleStringProperty((String) telefone);
        this.email = new SimpleStringProperty((String) email);
    }

    public String getNome() {
        return nome.getValue();
    }
    public StringProperty getNomeStringProperty() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome =new SimpleStringProperty((String) nome);
    }

    public String getTelefone() {
        return telefone.getValue();
    }public StringProperty getTelefoneStringProperty() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = new SimpleStringProperty((String) telefone);
    }

    public String getEmail() {
        return email.getValue();
    }
    public StringProperty getEmailStringProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty((String) email);
    }

    public String toString() {
        return "Cliente: "+this.nome+" // Telefone para contato: "+this.telefone+" // Email: "+this.email;
    }

}
