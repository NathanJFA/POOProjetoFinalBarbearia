package pacote.controllers;

import java.util.Calendar;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pacote.code.Horario.AgendaDeHorarios;

public class CadastraHorarioController {

    @FXML
    private Pane paneCadastrarHorario;
    
    MainFrameController mainController = new MainFrameController();

    @FXML
    private TextField tfDia;

    @FXML
    private TextField tfMes;

    @FXML
    private TextField tfAno;

    @FXML
    private TextField tfTelefone;
    @FXML
    private TextField tfHora;

    @FXML
    private Button btnCadastraHorario;

    @FXML 
    protected void initialize(){
        MainFrameController.addOnChangeScreenListener(new MainFrameController.OnChangeScreen(){
            private HashMap<String, Calendar> horarios;

            @Override
            public void onScreenChanged(String newScreen){
                if(newScreen.equals("cadastraHorario")){
                    System.out.println("Entrando no Frame cadastraHorario...");
                    this.horarios = (HashMap<String, Calendar>) horarios;
                }
            }
        });
    }

    @FXML
    void cadastraHorario(ActionEvent event) {
        if(!(tfTelefone.getText().isEmpty() && tfAno.getText().isEmpty() && tfDia.getText().isEmpty() && tfMes.getText().isEmpty())){
            Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tfDia.getText()));
            c.set(Calendar.YEAR, Integer.parseInt(tfAno.getText()));
            c.set(Calendar.MONTH, Integer.parseInt(tfMes.getText()));
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tfHora.getText()));
            Boolean cadastrou = mainController.sistema.agendaDeHorarios.addHorario(c, tfTelefone.getText());
            System.out.println("status de cadastrou: "+cadastrou);
            tfHora.setText("");
            tfDia.setText("");
            tfMes.setText("");
            tfAno.setText("");
            tfTelefone.setText("");
        }
    }
}