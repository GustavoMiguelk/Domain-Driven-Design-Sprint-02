import java.util.ArrayList;

public class Agenda {

    private Profissional profissional;
    private ArrayList<Consulta> consultas = new ArrayList<>();

    public Agenda(Profissional profissional) {
        this.profissional = profissional;
    }

    public void adicionarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }
}
