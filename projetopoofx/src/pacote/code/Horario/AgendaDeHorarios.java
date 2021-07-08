package pacote.code.Horario;

import java.util.Calendar;
import java.util.HashMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AgendaDeHorarios {

    private static HashMap <String, Calendar> horariosAgendados;


    public AgendaDeHorarios(){
        horariosAgendados = new HashMap <>();
    }
    public HashMap <String, Calendar> getHorariosAgendados(){
        return this.horariosAgendados;
    }
    public ObservableList<String> getHorariosAgendadosPropertySemSerProperty(){
        ObservableList<String> stringProperty = FXCollections.observableArrayList();
        for(String k: this.horariosAgendados.keySet()){
            int dia = horariosAgendados.get(k).get(Calendar.DAY_OF_MONTH);
            int mes = horariosAgendados.get(k).get(Calendar.MONTH);
            int ano = horariosAgendados.get(k).get(Calendar.YEAR);
            int hora = horariosAgendados.get(k).get(Calendar.HOUR_OF_DAY);
            String horario = dia + "/" + mes + "/" + ano + " as " + hora +"hrs por " + k ;
            stringProperty.add(horario);
        }
        return stringProperty;
    }
    public void addRetornoDoArquivo(Calendar c, String key){
        this.horariosAgendados.put(key, c);
    }

    public boolean addHorario(Calendar calendar, String telefone) {
        if(!verificaHorario(calendar)){
            this.horariosAgendados.put(telefone, calendar);
            return true;
        }else{
            return false;
        }
    }
    public boolean verificaHorario(Calendar calendar){
    	System.out.println("ENTROU");
        int choqueDeHorario = 0;
        if(!horariosAgendados.isEmpty()){
        	
            for (Calendar calendarRegistrado : horariosAgendados.values()) {
                int day = calendarRegistrado.get(Calendar.DAY_OF_MONTH);
                int month = calendarRegistrado.get(Calendar.MONTH);
                int year = calendarRegistrado.get(Calendar.YEAR);
                int hour = calendarRegistrado.get(Calendar.HOUR); 
                //int minute = calendarRegistrado.get(Calendar.MINUTE);
                
                if(year == calendar.get(Calendar.YEAR)){
                    if(month == calendar.get(Calendar.MONTH)){
                        if(day == calendar.get(Calendar.DAY_OF_MONTH)){
                            //VERIFICANDO HORARIO DE TRABALHO , INCOMPLETO,  FALTA A PAUSA P ALMOÇO
                            if(calendar.get(Calendar.HOUR) < 8 || calendar.get(Calendar.HOUR) > 17){
                                if(hour  >= calendar.get(Calendar.HOUR)){
                                    //FALTA VERIFICAR HORARIOS QUEBRADOS
                                    if(!((hour - calendar.get(Calendar.HOUR) < 2))){
                                        choqueDeHorario++;
                                    }
                                }if(hour  < calendar.get(Calendar.HOUR)){
                                    if(!((calendar.get(Calendar.HOUR) - hour) > -2)){
                                        choqueDeHorario++;
                                    }
                                }
                            }
                        }
                    }
                }     
            }        
        }
        if(choqueDeHorario > 0){
            return true;
        }else{
            return false;
        }
    }
}
