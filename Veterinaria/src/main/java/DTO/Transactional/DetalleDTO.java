package DTO.Transactional;

import DTO.AccesorioDTO;

public class DetalleDTO extends AccesorioDTO {
    int cantidad;

    public DetalleDTO(int cantidad) {
        this.cantidad = cantidad;
    }

    public DetalleDTO(String tipo, double precio, int cantidad) {
        super(tipo, precio);
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
