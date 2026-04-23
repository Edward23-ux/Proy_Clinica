package clinica.com.example.clinicaocupacional.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinica.com.example.clinicaocupacional.Model.Paciente;
import clinica.com.example.clinicaocupacional.Repository.PacienteRepository;

@Service
public class PacienteService {
    @Autowired

    private PacienteRepository pacienteRepository;

    public List<Paciente> obtenerTodos(){
        return pacienteRepository.findAll();
    }

    public Paciente guardar(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

}
