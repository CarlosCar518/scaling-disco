package DTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class VentaDTO {
    private LocalDate fecha;
    private double valor;
    private ClienteDTO cliente;
    private VendedorDTO vendedor;

    private  ArrayList<MascotaDTO>  mascotas = new ArrayList<>();
    private ArrayList<AccesorioDTO> accesorios = new ArrayList<>();

    public VentaDTO(LocalDate fecha, double valor, ClienteDTO cliente, VendedorDTO vendedor) {
        this.fecha = fecha;
        this.valor = valor;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public void setMascotas(ArrayList<MascotaDTO> mascotas) {
        this.mascotas = mascotas;
    }

    public void setAccesorios(ArrayList<AccesorioDTO> accesorios) {
        this.accesorios = accesorios;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public VendedorDTO getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorDTO vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<MascotaDTO> getMascotas() {
        return mascotas;
    }

    public ArrayList<AccesorioDTO> getAccesorios() {
        return accesorios;
    }

    public void setMascota(MascotaDTO mascota) {
        this.mascotas.add(mascota);
    }

    public void setAccesorios(AccesorioDTO accesorio) {
        this.accesorios.add(accesorio);
    }

    public void removeMascota(MascotaDTO mascota) {
        this.mascotas.remove(mascota);
    }

    public void removeAccesorio(AccesorioDTO accesorio) {
        this.accesorios.remove(accesorio);
    }
}
