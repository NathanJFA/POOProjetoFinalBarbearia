package pacote.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pacote.code.entidades.Cliente;
import pacote.code.entidades.Funcionario;
import pacote.code.servicos.SistemaBarbeariaCL;

public class CadastroFuncionarioController {
    SistemaBarbeariaCL sistema = new SistemaBarbeariaCL();
    

    @FXML
    private Pane paneCadastroFuncionario;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfDataNascimento;

    @FXML
    private TextField tfCpf;
    
    private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();

    @FXML 
    protected void initialize(){
        MainFrameController.addOnChangeScreenListener(new MainFrameController.OnChangeScreen(){
            @Override
            public void onScreenChanged(String newScreen){
                if(newScreen.equals("cadastraFuncionario")){
                    System.out.println("Entrando no Frame cadastraFuncionario...");
                }
            }
        });
    }

    @FXML
    void cadastraFuncionario(ActionEvent event) {

        if(!(tfNome.getText().isEmpty() && tfDataNascimento.getText().isEmpty() && tfTelefone.getText().isEmpty() && tfEmail.getText().isEmpty() && tfCpf.getText().isEmpty())){
            Boolean cadastrou = sistema.cadastrarFuncionario(tfNome.getText(), tfDataNascimento.getText(), tfTelefone.getText(), tfEmail.getText(), tfCpf.getText());
            tfNome.setText("");
            tfDataNascimento.setText("");
            tfTelefone.setText("");
            tfEmail.setText("");
            tfCpf.setText("");
            if(cadastrou){ System.out.println("cadastrou funcionario");}
            //VERIFICAR BOOLEAN
        }
    }

}