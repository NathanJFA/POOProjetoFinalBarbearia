package pacote.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pacote.code.Horario.AgendaDeHorarios;
import pacote.code.entidades.Cliente;
import pacote.code.entidades.Funcionario;
import pacote.code.servicos.SistemaBarbeariaCL;
import javafx.collections.FXCollections;

public class MainFrameController extends Application{

    private Node ocupante;

    private static Stage stage;
    private Scene mainScene;

    public static SistemaBarbeariaCL sistema = new SistemaBarbeariaCL();

    @FXML
    private BorderPane load;
    @FXML
    private AnchorPane anchor;

    @FXML
    private MenuItem mniCadastrarCliente;

    @FXML
    private MenuItem mniPesquisarCliente;

    @FXML
    private MenuItem mniRemoverCliente;

    @FXML
    private MenuItem mniCadastrarFuncionario;

    @FXML
    private MenuItem mniPesquisarFuncionario;

    @FXML
    private MenuItem mniRemoverFuncionario;

    @FXML
    private MenuItem mniCadastrarHorario;

    @FXML
    private MenuItem mniListarHorario;


    @FXML
    void cadastrarCliente(ActionEvent event) {
        System.out.println("Iniciando Janela Interna 1");
        if(ocupante != null){
            anchor.getChildren().remove(ocupante);
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../telas/FrameCadastrarCliente.fxml"));
            Node n = (Node) fxmlLoader.load();
            anchor.getChildren().add(n);
            changeScreen("cadastraCliente");
            this.ocupante = n;
        } catch (Exception ex) {
            System.out.println("entrou no catch");
        }

    }

    @FXML
    void cadastrarFuncionario(ActionEvent event) {
        if(ocupante != null){
            anchor.getChildren().remove(ocupante);
        }
        System.out.println("Iniciando Janela Interna 3");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../telas/FrameCadastrarFuncionario.fxml"));
            Node n = (Node) fxmlLoader.load();
            anchor.getChildren().add(n);
            changeScreen("cadastraFuncionario");
            this.ocupante = n;
        } catch (Exception ex) {
            System.out.println("entrando no catch do cadastra funcionario");
        }
    }

    @FXML
    void cadastrarHorario(ActionEvent event) {
        if(ocupante != null){
            anchor.getChildren().remove(ocupante);
        }
        System.out.println("Iniciando Janela Interna 7");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../telas/FrameCadastraHorario.fxml"));
            Node n = (Node) fxmlLoader.load();
            anchor.getChildren().add(n);
            changeScreen("cadastrarHoraio");
            this.ocupante = n;
        } catch (Exception ex) {
        }
    }

    @FXML
    void listarHorario(ActionEvent event) {
        if(ocupante != null){
            anchor.getChildren().remove(ocupante);
        }
        System.out.println("Iniciando Janela Interna 8");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../telas/FrameListagemHorarios.fxml"));
            Node n = (Node) fxmlLoader.load();
            anchor.getChildren().add(n);
            changeScreen("listarHorario");
            this.ocupante = n;
        } catch (Exception ex) {
        }
    }

    @FXML
    void pesquisarCliente(ActionEvent event) {
        if(ocupante != null){
            anchor.getChildren().remove(ocupante);
        }
        System.out.println("Iniciando Janela Interna 2");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../telas/FramePesquisarCliente.fxml"));
            Node n = (Node) fxmlLoader.load();
            anchor.getChildren().add(n);
            changeScreen("pesquisaCliente");
            this.ocupante = n;
        } catch (Exception ex) {
        }
    }

    @FXML
    void pesquisarFuncionario(ActionEvent event) {
        if(ocupante != null){
            anchor.getChildren().remove(ocupante);
        }
        System.out.println("Iniciando Janela Interna 4");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../telas/FramePesquisarFuncionario.fxml"));
            Node n = (Node) fxmlLoader.load();
            anchor.getChildren().add(n);
            changeScreen("pesquisaFuncionario");
            this.ocupante = n;
        } catch (Exception ex) {
        }
    }

    @FXML
    void removerCliente(ActionEvent event) {
        /*
        System.out.println("Iniciando Janela Interna 6");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../telas/FrameCadastrarFuncionario.fxml"));
            Node n = (Node) fxmlLoader.load();
            anchor.getChildren().add(n);
            changeScreen("");
        } catch (Exception ex) {
        }
        */
    }

    @FXML
    void removerFuncionario(ActionEvent event) {
        /*
        System.out.println("Iniciando Janela Interna 5");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../telas/FrameCadastrarFuncionario.fxml"));
            Node n = (Node) fxmlLoader.load();
            anchor.getChildren().add(n);
            changeScreen("");
        } catch (Exception ex) {
        }
        */
    }
    @FXML
    void sair(ActionEvent event){
        sistema.gravarDados();
        System.exit(0);
    }
    public static void iniciaComponentes(){
        sistema.recuperarDados();
    }
    public static void main(String [] args){
        iniciaComponentes();
        launch(args);
    }

    public HashMap<String, Cliente> getClientes(){
        return sistema.getClientes();
    }
    @Override
    public void start(Stage stageIn) throws Exception {
       
        Parent fxmlMain = FXMLLoader.load(getClass().getResource("../telas/TelaPrincipal.fxml"));
        mainScene = new Scene(fxmlMain);
        Stage stage = new Stage();
        stage.setTitle("Barbearia KLStar");
        stage.setScene(mainScene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
        
    }

    public static void changeScreen(String src){
        switch(src){
            case "cadastraCliente":
                notifyAllListeners("cadastraCliente");
                break;
            case "pesquisaCliente":
                notifyAllListeners("pesquisaCliente");
                break;
            case "cadastraFuncionario":
                notifyAllListeners("cadastraFuncionario");
                break;
            case "pesquisaFuncionario":
                notifyAllListeners("pesquisaFuncionario");
                break;
            case "cadastrarHorario":
                notifyAllListeners("cadastrarHorario");
                break;
            case "listarHorario":
                notifyAllListeners("listarHorario");
        }
    }
    /*
    //CASO NÃO QUEIRA PASSAR OBJETO
    public static void changeScreen(String src){
        changeScreen(src);
    }
    

    
    //------------------------------------------------------------------------
    /**
     * ESSE SCRIPS AVISA A TODAS OS CONTROLLERS QUANDO SEU FRAME FOR ACESSADO
     * TEM UM MÉTODO NO CONSTRUTOR DOS CONTROLERS QUE FAZ ISSO
     */
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<OnChangeScreen>();

    public static interface OnChangeScreen{
        public void onScreenChanged(String newScreen);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }
    public static void notifyAllListeners(String newScreen){
        for(OnChangeScreen l : listeners){
            l.onScreenChanged(newScreen);
        }
    }

}