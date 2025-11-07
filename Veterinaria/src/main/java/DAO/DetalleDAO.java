package DAO;

import DTO.Transactional.DetalleDTO;

import java.util.ArrayList;

public class DetalleDAO {
    private ArrayList<DetalleDTO> accesorios = new ArrayList<>();

    public void addAcc(DetalleDTO detalle){
        accesorios.add(detalle);
    }

    public DetalleDTO findAcc(String tipo){
        for (DetalleDTO a : accesorios){
            if (a.getTipo().equals(tipo)){
                return a;
            }
        }
        return null;
    }

    public void removeAcc(String tipo){
        DetalleDTO ac = findAcc(tipo);
        accesorios.remove(ac);
    }
}
