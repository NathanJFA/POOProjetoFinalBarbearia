package pacote.controllers;

import java.util.List;
import java.util.Map.Entry;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pacote.code.servicos.SistemaBarbeariaCL;
import pacote.code.entidades.Cliente;

public class PesquisarClienteController {
    MainFrameController mainController = new MainFrameController();
    //SistemaBarbeariaCL sistema = new SistemaBarbeariaCL();
    private ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    @FXML
    private TableView<Cliente> tableExibeClientes;

    @FXML
    private TableColumn<Cliente, String> colunaNome;

    @FXML
    private TableColumn<Cliente, String> colunaTelefone;

    @FXML
    private TableColumn<Cliente, String> colunaEmail;


    @FXML 
    protected void initialize(){
        
        colunaNome.setCellValueFactory(cellData -> cellData.getValue().getNomeStringProperty());
        colunaTelefone.setCellValueFactory(cellData -> cellData.getValue().getTelefoneStringProperty());
        colunaEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailStringProperty());
        MainFrameController.addOnChangeScreenListener(new MainFrameController.OnChangeScreen(){
            @Override
            public void onScreenChanged(String newScreen){
                if(newScreen.equals("pesquisaCliente")){
                    System.out.println("Entrando no Frame pesquisaCliente...");
                    for(Cliente c: mainController.sistema.getClientes().values()){ 
                        clientes.add(c);
                        System.out.println(c.getNome());
                    }
                    //atualizarTable();
                    tableExibeClientes.setItems(clientes);   
            
                }
            }
        });
    }
    @FXML
    private TextField tfTelefoneAPesquisar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Pane panePesquisar;


    @FXML
    void pesquisarClienteTelefone(ActionEvent event) {
        if(!tfTelefoneAPesquisar.getText().isEmpty()){
            Cliente c = mainController.sistema.pesquisarCliente(tfTelefoneAPesquisar.getText());
            if(c != null){
                ObservableList<Cliente> clientesTemporarios = FXCollections.observableArrayList();
                clientesTemporarios.add(c);
                tableExibeClientes.setItems(clientesTemporarios);
                colunaNome.setCellValueFactory(cellData -> cellData.getValue().getNomeStringProperty());
                colunaTelefone.setCellValueFactory(cellData -> cellData.getValue().getTelefoneStringProperty());
                colunaEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailStringProperty());
            }else{
                System.out.println("ta sendo null");
            }
        }else{
            tableExibeClientes.setItems(clientes);
            colunaNome.setCellValueFactory(cellData -> cellData.getValue().getNomeStringProperty());
            colunaTelefone.setCellValueFactory(cellData -> cellData.getValue().getTelefoneStringProperty());
            colunaEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailStringProperty());
        }
    }
    
    /*
    //AQUI É A EXIBIÇÃO DAS POSSIBILIDADES NA TABELA
    public void atualizarTable(){
        for(Entry<String, Cliente> p: mainController.sistema.getClientes().entrySet()) {
            clientes.add(p.getValue());
        }
        
        System.out.println(clientes.size());
    }
    */
}
