package DTO;

import java.util.ArrayList;

public class VendedorDTO {
    String nombre;
    String id;
    char sexo;

    private ArrayList<VentaDTO>  ventas = new ArrayList<>();

    public VendedorDTO() {
    }

    public VendedorDTO(String nombre, String id, char sexo) {
        this.nombre = nombre;
        this.id = id;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public ArrayList<VentaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(VentaDTO venta) {
        ventas.add(venta);
    }

    public void removeVentas(VentaDTO venta) {
        ventas.remove(venta);
    }

    @Override
    public String toString() {
        return "VendedorDTO{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
