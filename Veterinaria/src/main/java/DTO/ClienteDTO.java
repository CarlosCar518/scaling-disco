package DTO;

import java.util.ArrayList;

public class ClienteDTO {
    private String id;
    private String nombre;
    private String direccion;
    private String telefono;

    private ArrayList<VentaDTO> ventas = new ArrayList<>();
    private ArrayList<MascotaDTO>  mascotas = new ArrayList<>();

    public ClienteDTO() {
    }

    public ClienteDTO(String id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public  ArrayList<VentaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<VentaDTO> ventas) {
        this.ventas = ventas;
    }

    public void setVentas(VentaDTO venta) {
        ventas.add(venta);
    }

    public ArrayList<MascotaDTO> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<MascotaDTO> mascotas) {
        this.mascotas = mascotas;
    }

    public void addMascota(MascotaDTO mascota) {
        this.mascotas.add(mascota);
    }

    public void addVenta(VentaDTO venta) {
        this.ventas.add(venta);
    }
}