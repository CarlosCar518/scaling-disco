package Services;

import DAO.MascotaDAO;
import DTO.MascotaDTO;
import DTO.VacunaDTO;
import EnumErrores.OutCome;

import java.time.LocalDate;

public class MascotaService {

    private MascotaDAO mascotaDAO = new MascotaDAO();

    public void registrarMascota (String nombre, String raza, int edad, String tipo, String id, double peso,
                                 String fechaingreso, String lugarOrigen, char genero, double precio, boolean adoptado, boolean fechaActual){

        String fecha = fechaActual ? fechaingreso : LocalDate.now().toString();
        for (MascotaDTO mascota: mascotaDAO.getMascotas()) {
            if (mascota.getId().equals(id)) {
                return;
            }
        }
        mascotaDAO.registrar(new MascotaDTO(nombre, raza, edad, tipo, id, peso, fecha, lugarOrigen, genero, precio, adoptado));
    }

    public OutCome venderMascota (String id){
        MascotaDTO mascota = mascotaDAO.buscarMascotaPorId(id);
        if (mascota == null) return OutCome.NOT_FOUND;
        if (mascota.isAdoptado())  return OutCome.REPEATED;
        mascota.setAdoptado(true);
        return OutCome.SUCCESS;
    }

    public void vacunarMascota (String id, String tipo, double cantidad, LocalDate fecha, boolean actual){
        LocalDate fechaFinal = actual ? LocalDate.now() : fecha;
        VacunaDTO vacunaDTO =  new VacunaDTO(tipo);
        mascotaDAO.setVacuna(vacunaDTO, id, cantidad,  fechaFinal);
    }

    public void registrarConsulta (LocalDate fecha, String sintomas, String tratamiento, String id, boolean actual){
        LocalDate fechaFinal = actual ? LocalDate.now() : fecha;
        mascotaDAO.setConsulta(id, fechaFinal, sintomas, tratamiento);
    }
}