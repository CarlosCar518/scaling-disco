package Services;

import DAO.DetalleDAO;
import DTO.Transactional.DetalleDTO;
import EnumErrores.OutCome;

public class DetalleService {
    DetalleDAO accs = new DetalleDAO();

    public void addAccs(String tipo, double precio, int cantidad){
        accs.addAcc(new DetalleDTO(tipo, precio, cantidad));
    }

    public DetalleDTO find(String tipo){
        return accs.findAcc(tipo);
    }

    public OutCome sellAccs(String tipo){
        DetalleDTO a = accs.findAcc(tipo);
        if (a == null){
            return OutCome.NOT_FOUND;
        }
        return OutCome.SUCCESS;
    }
}