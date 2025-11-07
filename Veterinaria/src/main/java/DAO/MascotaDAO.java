package DAO;
import DTO.ConsultaDTo;
import DTO.MascotaDTO;
import DTO.Transactional.DosisDTO;
import DTO.VacunaDTO;
import DTO.ClienteDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class MascotaDAO {
    private ArrayList<MascotaDTO> mascotas = new ArrayList<>();

    public void registrar(MascotaDTO mascota) {
        mascotas.add(mascota);
    }

    public MascotaDTO buscarMascotaPorId(String id) {
        for (MascotaDTO mascota : mascotas) {
            if (mascota.getId().equals(id)) {
                return mascota;
            }
        }
        return null;
    }

    public ArrayList<MascotaDTO> getMascotas() {
        return mascotas;
    }

    public void setVacuna(VacunaDTO vacuna, String id, double cantidad, LocalDate fecha) {
        buscarMascotaPorId(id).setVacuna(new DosisDTO(vacuna.getTipo(), fecha, cantidad));
    }

    public void setConsulta(String id, LocalDate fecha, String sintomas, String tratamiento) {
        MascotaDTO mascotaDTO = buscarMascotaPorId(id);
        mascotaDTO.setConsulta(new ConsultaDTo(fecha, sintomas, tratamiento, mascotaDTO));
    }

    public void venderMascota(MascotaDTO mascota, ClienteDTO cliente){
        mascota.setAdoptado(true);
        cliente.addMascota(mascota);
    }

    public void eliminar(String id) {
         mascotas.remove(buscarMascotaPorId(id));
    }

    public void setMascotas(ArrayList<MascotaDTO> mascotas) {
        this.mascotas = mascotas;
    }
}