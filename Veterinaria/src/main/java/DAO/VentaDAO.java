package DAO;

import DTO.VentaDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class VentaDAO {
    private ArrayList<VentaDTO> ventas = new ArrayList<>();

    public void registrar(VentaDTO venta) {
        ventas.add(venta);
    }

    public VentaDTO buscarPorFecha(LocalDate fecha) {
        for (VentaDTO venta : ventas) {
            if (venta.getFecha().isEqual(fecha)) {
                return venta;
            }
        }
        return null;
    }

    public ArrayList<VentaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<VentaDTO> ventas) {
        this.ventas = ventas;
    }
}
