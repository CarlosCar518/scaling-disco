package DAO;

import DTO.ClienteDTO;
import DTO.MascotaDTO;
import DTO.VentaDTO;
import EnumErrores.OutCome;

import java.util.ArrayList;

public class ClienteDAO {
    private ArrayList<ClienteDTO> clientes = new ArrayList<>();

    public ClienteDTO getCliente(String id) {
        for (ClienteDTO cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }

        return null;
    }

    public void registrarCliente(ClienteDTO cliente) {
        clientes.add(cliente);
    }

    public void addMascota(ClienteDTO cliente, MascotaDTO mascota) {
        cliente.addMascota(mascota);
    }

    public void addVenta(String id, VentaDTO venta) {
        getCliente(id).addVenta(venta);
    }
}