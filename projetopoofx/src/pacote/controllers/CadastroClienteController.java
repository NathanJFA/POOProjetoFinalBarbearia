package pacote.controllers;

import pacote.code.entidades.Cliente;
import pacote.code.servicos.SistemaBarbeariaCL;

import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastroClienteController{
    MainFrameController mainController = new MainFrameController();

    ObservableList<Cliente> clientesOBL = FXCollections.observableArrayList();
    //HashMap<String, Cliente> clientes = new HashMap<String, Cliente>();

    @FXML
    public static Pane paneTest;

    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextField tfEmail;

    @FXML 
    protected void initialize(){
        MainFrameController.addOnChangeScreenListener(new MainFrameController.OnChangeScreen(){
            private HashMap<String, Cliente> clientes;

            @Override
            public void onScreenChanged(String newScreen){
                if(newScreen.equals("cadastraCliente")){
                    System.out.println("Entrando no Frame cadastraCliente...");
                    
                    this.clientes = (HashMap<String, Cliente>) clientes;
                }
            }
        });
    }

    @FXML
    void cadastrarClienteSystem(ActionEvent event) {
        if(!(tfNome.getText().isEmpty() && tfTelefone.getText().isEmpty() && tfEmail.getText().isEmpty())){
            boolean cadastrou = mainController.sistema.cadastrarCliente(tfNome.getText(), tfTelefone.getText(), tfEmail.getText());
            if(mainController.sistema.getClientes().size() == 1){
                System.out.println("zerado2");
            }
            if(cadastrou){
                tfNome.setText("");
                tfTelefone.setText("");
                tfEmail.setText("");
                System.out.println("cadastrou");
            }else{
                System.out.println("not a bad");
            }
            //VERIFICAR O BOLLEAN
        }
    }

}
