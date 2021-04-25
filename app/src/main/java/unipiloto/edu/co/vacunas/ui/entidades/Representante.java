package unipiloto.edu.co.vacunas.ui.entidades;

public class Representante extends Usuario {
    private String puesto_asig;

    public String getPuesto_asig() {
        return this.puesto_asig;
    }

    public void setPuesto_asig(String puesto_asig) {
        this.puesto_asig = puesto_asig;
    }

      public Representante(String nombres, String primer_apellido, String segundo_apellido, String doc_identidad, String correo, String profesion, String edad, String puesto_asig) {
        super(nombres, primer_apellido, segundo_apellido, doc_identidad, correo, profesion, edad);
        this.puesto_asig = puesto_asig;
    }
}
