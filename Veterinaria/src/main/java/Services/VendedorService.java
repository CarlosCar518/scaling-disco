package Services;

import DAO.VendedorDAO;
import DTO.VendedorDTO;
import DTO.VentaDTO;
import EnumErrores.OutCome;

public class VendedorService {

    private VendedorDAO vendedores = new VendedorDAO();

    public OutCome addVendedor(String nombre, String id, char sexo){
        if (vendedores.findVendedor(id) != null){
            return OutCome.REPEATED;
        }
        VendedorDTO a = new VendedorDTO(nombre, id, sexo);
        vendedores.addVendedor(a);
        return OutCome.SUCCESS;
    }

    public OutCome addVenta(VentaDTO venta, String id){
        VendedorDTO vendedor = vendedores.findVendedor(id);
        if (vendedor == null){
            return OutCome.NOT_FOUND;
        }
        vendedor.setVentas(venta);
        return OutCome.SUCCESS;
    }
}
