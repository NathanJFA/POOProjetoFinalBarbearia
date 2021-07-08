package pacote.code.controller;

import pacote.code.servicos.SistemaBarbearia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgendaAddController implements ActionListener {
    private SistemaBarbearia agenda;
    private JFrame janelaPrincipal;

    public AgendaAddController(SistemaBarbearia agenda, JFrame janela) {
        this.agenda = agenda;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nome = JOptionPane.showInputDialog(this,
                "Qual o nome do cliente?");
        String telefone = JOptionPane.showInputDialog(this,
                "Qual o telefone do cliente?");
        String email = JOptionPane.showInputDialog(this,
                "Qual o email do cliente?");
        boolean cadastrou = agenda.cadastrarCliente(nome, telefone, email);
        if (cadastrou) {
            JOptionPane.showMessageDialog(janelaPrincipal, this,
                    "Cliente cadastrado", 0);
        } else {
            JOptionPane.showMessageDialog(janelaPrincipal, this,
                    "Cliente não foi cadastrado. " +
                            "Verifique se já não existia", 0);
        }
    }

}