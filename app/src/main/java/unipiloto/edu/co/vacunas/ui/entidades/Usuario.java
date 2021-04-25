package unipiloto.edu.co.vacunas.ui.entidades;

public class Usuario {
    protected String nombres;
    protected String primer_apellido;
    protected String Segundo_apellido;
    protected String doc_identidad;
    protected String correo;
    protected String profesion;
    protected String edad;


    public String getCorreo() {
        return correo;
    }

    public String getDoc_identidad() {
        return doc_identidad;
    }
    public String getEdad() {
        return edad;
    }
    public String getNombres() {
        return nombres;
    }

    public void setPrimer_apellido() {
        this.primer_apellido = primer_apellido;
    }

    public String getProfesion() {
        return profesion;
    }

    public String getSegundo_apellido() {
        return Segundo_apellido;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDoc_identidad(String doc_identidad) {
        this.doc_identidad = doc_identidad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public void setSegundo_apellido() {
       this.Segundo_apellido = getSegundo_apellido();
    }

    public Usuario(String nombres, String primer_apellido, String segundo_apellido, String doc_identidad, String correo, String profesion, String edad) {
        this.nombres = nombres;
        this.primer_apellido = primer_apellido;
        this.Segundo_apellido = segundo_apellido;
        this.doc_identidad = doc_identidad;
        this.correo = correo;
        this.profesion = profesion;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombres='" + nombres + '\'' +
                ", primer_apellido='" + primer_apellido + '\'' +
                ", Segundo_apellido='" + Segundo_apellido + '\'' +
                ", doc_identidad='" + doc_identidad + '\'' +
                ", correo='" + correo + '\'' +
                ", profesion='" + profesion + '\'' +
                ", edad='" + edad + '\'' +
                '}';
    }
}
