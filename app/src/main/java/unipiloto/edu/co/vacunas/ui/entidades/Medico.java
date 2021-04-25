package unipiloto.edu.co.vacunas.ui.entidades;

public class Medico extends Usuario {
    String entidad;
    public String getEntidad() {
        return this.entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public Medico(String nombres, String primer_apellido, String segundo_apellido, String doc_identidad, String correo, String profesion, String edad, String entidad) {
        super(nombres, primer_apellido, segundo_apellido, doc_identidad, correo, profesion, edad);
        this.entidad = entidad;
    }
}



