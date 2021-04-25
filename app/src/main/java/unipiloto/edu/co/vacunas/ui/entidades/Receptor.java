package unipiloto.edu.co.vacunas.ui.entidades;

public class Receptor extends Usuario{
    String localidad;
    String cantidad_vac;

    public String getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCantidad_vac() {
        return cantidad_vac;
    }

    public void setCantidad_vac(String cantidad_vac) {
        this.cantidad_vac = cantidad_vac;
    }

    public Receptor(String nombres, String primer_apellido, String segundo_apellido, String doc_identidad, String correo, String profesion, String edad, String localidad, String cantidad_vac) {
        super(nombres, primer_apellido, segundo_apellido, doc_identidad, correo, profesion, edad);
        this.localidad = localidad;
        this.cantidad_vac = cantidad_vac;
    }
}
