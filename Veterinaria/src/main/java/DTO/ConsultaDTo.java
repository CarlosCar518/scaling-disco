package DTO;

import java.time.LocalDate;
import java.util.Date;

public class ConsultaDTo {
    LocalDate fecha;
    String sintomas;
    String tratamiento;
    MascotaDTO mascota;

    public ConsultaDTo() {
    }

    public ConsultaDTo(LocalDate fecha, String sintomas, String tratamiento, MascotaDTO mascota) {
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.tratamiento = tratamiento;
        this.mascota = mascota;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    
    

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
}
