import  java.util.ArrayList;
import java.util.List;
public class Enfermedad  {

    private int  idEnfermedad;
    private String tipo; 
    private String medicamento;
    private List<String> sintomas; 

    public Enfermedad(int   idEnfermedad,String tipo, List<String> sintomas,String medicamento){
        this.idEnfermedad=idEnfermedad;
        this.tipo=tipo;
        this.sintomas = new ArrayList<>();
        this.medicamento=medicamento;
    }

    public int  getidEnfermedad(){
        return idEnfermedad;
    }
    public void setidEnfermedad(int idEnfermedad){
        this.idEnfermedad=idEnfermedad;
    }

    public String  gettipo(){
        return tipo;
    }
    public void settipo(String tipo){
        this.tipo=tipo;
    }
     public List<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<String> sintomas) {
        this.sintomas = sintomas;
    }

    public String  getmedicamento(){
        return medicamento;
    }
    public void setmedicamento(String medicamento){
        this.medicamento=medicamento;
    }
    public void agregarSintoma(String sintoma) {
        this.sintomas.add(sintoma);
    }

    public void eliminarSintoma(String sintoma) {
        this.sintomas.remove(sintoma);
    }

}
