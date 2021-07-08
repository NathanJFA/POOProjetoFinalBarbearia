package pacote.code.entidades;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Funcionario {
    private StringProperty nome;
    private StringProperty dataDeNascimento;
    private StringProperty telefone;
    private StringProperty email;
    private StringProperty cpf;

    public Funcionario(String nome, String dataDeNascimento, String telefone, String email, String cpf) {
        this.nome = new SimpleStringProperty(nome);
        this.dataDeNascimento = new SimpleStringProperty(dataDeNascimento);
        this.telefone = new SimpleStringProperty(telefone);
        this.email = new SimpleStringProperty(email);
        this.cpf = new SimpleStringProperty(cpf);
    }

    public Funcionario() {

    }

    public String getNome() {
        return nome.getValue();
    }
    public StringProperty getNomeStringProperty() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = new SimpleStringProperty(nome);
    }

    public String getDataDeNascimento() {
        return dataDeNascimento.getValue();
    }
    public StringProperty getDataDeNascimentoStringProperty() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = new SimpleStringProperty(dataDeNascimento);
    }

    public String getTelefone() {
        return telefone.getValue();
    }
    public StringProperty getTelefoneStringProperty() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = new SimpleStringProperty(telefone);
    }

    public String getEmail() {
        return email.getValue();
    }
    public StringProperty getEmailStringProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    public String getCpf() {
        return cpf.getValue();
    }

    public void setCpf(String cpf) {
        this.cpf = new SimpleStringProperty(cpf);
    }

    public String toString() {
        return "O funcionario " + this.nome + ", com data de nascimento " + this.dataDeNascimento + ", telefone " + this.telefone + ", E-mail " + this.email + " e com CPF " + this.cpf;
    }

    public StringProperty getCpfStringProperty() {
        return cpf;
    }

}
