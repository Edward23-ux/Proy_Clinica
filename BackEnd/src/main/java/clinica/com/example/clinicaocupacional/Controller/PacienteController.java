package clinica.com.example.clinicaocupacional.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import clinica.com.example.clinicaocupacional.Model.Paciente;
import clinica.com.example.clinicaocupacional.Service.PacienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("pacientes", pacienteService.obtenerTodos());
        return "dashboard";
    }

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        if(!model.containsAttribute("paciente")){
            model.addAttribute("paciente", new Paciente());
        }
        return "registro"; 
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Paciente paciente, BindingResult result, RedirectAttributes flash){
        if(result.hasErrors()){
            return "registro";
        }
        try{
            pacienteService.guardar(paciente);
            flash.addFlashAttribute("success", "Paciente registrado con éxito");
            return "redirect:/pacientes/registro";
        }catch(IllegalArgumentException e){
            flash.addFlashAttribute("error", e.getMessage());
            return "redirect:/pacientes/registro";        
        }
    }
}
