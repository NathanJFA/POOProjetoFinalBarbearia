package pacote.controllers;

import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pacote.code.entidades.Cliente;
import pacote.code.entidades.Funcionario;
import pacote.code.servicos.SistemaBarbeariaCL;

public class PesquisarFuncionarioController {

    MainFrameController mainController = new MainFrameController();

    @FXML
    private Pane panePesquisarFuncionario;

    @FXML
    private TextField tfNome;

    @FXML
    private Button btnPesquisarFuncionario;

  

    private ObservableList<Funcionario> funcionarios = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Funcionario> tableExibeFuncionarios;
    @FXML
    private TableColumn<Funcionario, String> colunaNome;

    @FXML
    private TableColumn<Funcionario, String> colunaData;

    @FXML
    private TableColumn<Funcionario, String> colunaCpf;

    @FXML
    private TableColumn<Funcionario, String> colunaTelefone;

    @FXML
    private TableColumn<Funcionario, String> colunaEmail;


    @FXML 
    protected void initialize(){
        
        colunaNome.setCellValueFactory(cellData -> cellData.getValue().getNomeStringProperty());
        colunaData.setCellValueFactory(cellData -> cellData.getValue().getDataDeNascimentoStringProperty());
        colunaCpf.setCellValueFactory(cellData -> cellData.getValue().getCpfStringProperty());
        colunaTelefone.setCellValueFactory(cellData -> cellData.getValue().getTelefoneStringProperty());
        colunaEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailStringProperty());

        MainFrameController.addOnChangeScreenListener(new MainFrameController.OnChangeScreen(){
            @Override
            public void onScreenChanged(String newScreen){
                if(newScreen.equals("pesquisaFuncionario")){
                    System.out.println("Entrando no Frame pesquisaFuncionario...");
                    for(Funcionario f: mainController.sistema.getFuncionarios().values()){ 
                        funcionarios.add(f);
                        System.out.println(f.getNome());
                    }
                    //atualizarTable();
                    tableExibeFuncionarios.setItems(funcionarios);   
                }
            }
        });
    }

    @FXML
    void pesquisarFuncionarioNome(ActionEvent event) {
        if(!tfNome.getText().isEmpty()){
            Collection<Funcionario> funcionariosRetornados = FXCollections.observableArrayList();
            funcionariosRetornados = mainController.sistema.pesquisaFuncionarioPorNome(tfNome.getText());

            ObservableList funcionariosSearch = FXCollections.observableArrayList();
            for(Funcionario f: funcionariosRetornados){
                funcionariosSearch.add(f);
            }
            if(!funcionariosRetornados.isEmpty()){
                tableExibeFuncionarios.setItems(funcionariosSearch);
                colunaNome.setCellValueFactory(cellData -> cellData.getValue().getNomeStringProperty());
                colunaData.setCellValueFactory(cellData -> cellData.getValue().getDataDeNascimentoStringProperty());
                colunaCpf.setCellValueFactory(cellData -> cellData.getValue().getCpfStringProperty());
                colunaTelefone.setCellValueFactory(cellData -> cellData.getValue().getTelefoneStringProperty());
                colunaEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailStringProperty());
            } 
        }else{
            tableExibeFuncionarios.setItems(funcionarios);
            colunaNome.setCellValueFactory(cellData -> cellData.getValue().getNomeStringProperty());
            colunaData.setCellValueFactory(cellData -> cellData.getValue().getDataDeNascimentoStringProperty());
            colunaCpf.setCellValueFactory(cellData -> cellData.getValue().getCpfStringProperty());
            colunaTelefone.setCellValueFactory(cellData -> cellData.getValue().getTelefoneStringProperty());
            colunaEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailStringProperty());
        }
    }
}
