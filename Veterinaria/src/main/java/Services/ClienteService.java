package Services;

import DAO.ClienteDAO;
import DTO.*;
import EnumErrores.OutCome;

import java.time.LocalDate;
import java.util.ArrayList;

public class ClienteService {
    ClienteDAO clienteDAO = new ClienteDAO();

    public OutCome registrarCliente (String id, String nombre,
                                     String direccion, String telefono) {
        if (clienteDAO.getCliente(id) != null) {
            return OutCome.REPEATED;
        }
        clienteDAO.registrarCliente(new ClienteDTO(id,nombre,direccion,telefono));
        return OutCome.SUCCESS;
    }

    public OutCome addMascota(String id, MascotaDTO nuevaMascota){
        ClienteDTO cliente = clienteDAO.getCliente(id);
        if (cliente == null || encontrarMascota(cliente.getId(), nuevaMascota.getId()) == OutCome.NOT_FOUND){
            return OutCome.NOT_FOUND;
        }
        clienteDAO.addMascota(cliente, nuevaMascota);
        return OutCome.SUCCESS;
    }

    public OutCome encontrarMascota(String idMascota, String idCliente){
        ClienteDTO cliente = clienteDAO.getCliente(idCliente);
        ArrayList<MascotaDTO> mascotas = cliente.getMascotas();

        for (MascotaDTO mas : mascotas){
            if (mas.getId().equals(idMascota)){
                return OutCome.SUCCESS;
            }
        }
        return OutCome.NOT_FOUND;
    }

    public void addVenta(String id, double valor, VendedorDTO vendedor, ArrayList<MascotaDTO> mascotas, ArrayList<AccesorioDTO> accesorio){
        ClienteDTO cliente = clienteDAO.getCliente(id);
        VentaDTO venta = new VentaDTO(LocalDate.now(), valor, cliente, vendedor);
        venta.setMascotas(mascotas);
        venta.setAccesorios(accesorio);
    }
}