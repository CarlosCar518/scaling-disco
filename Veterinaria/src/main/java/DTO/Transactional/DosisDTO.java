package DTO.Transactional;

import DTO.VacunaDTO;

import java.time.LocalDate;

public class DosisDTO extends VacunaDTO {
    LocalDate fecha;
    double cantidad;

    public DosisDTO(LocalDate fecha, double cantidad) {
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public DosisDTO(String tipo, LocalDate fecha, double cantidad) {
        super(tipo);
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
}
