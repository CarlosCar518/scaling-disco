package DAO;

import DTO.VendedorDTO;
import DTO.VentaDTO;

import java.util.ArrayList;

public class VendedorDAO {
    private ArrayList<VendedorDTO>  vendedores =  new ArrayList<>();

    public void addVendedor(VendedorDTO vendedor){
        vendedores.add(vendedor);
    }

    public void removeVendedor(VendedorDTO vendedor){
        vendedores.remove(vendedor);
    }

    public ArrayList<VendedorDTO> getVendedores() {
        return vendedores;
    }

    public void setVendedores(ArrayList<VendedorDTO> vendedores) {
        this.vendedores = vendedores;
    }

    public void addVenta(String id, VentaDTO venta){
        for (VendedorDTO vendedor : vendedores) {
            if (vendedor.getId().equals(id)) {
                vendedor.setVentas(venta);
            }
        }
    }

    public VendedorDTO findVendedor(String id){
        for (VendedorDTO ven : vendedores){
            if (ven.getId().equals(id)){
                return ven;
            }
        }
        return null;
    }
}