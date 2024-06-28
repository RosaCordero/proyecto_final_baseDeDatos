package com.calificaciones.Controller;

import com.calificaciones.DTO.DetallesNota;
import com.calificaciones.DTO.FormsIndex;
import com.calificaciones.DTO.ListadoMaterias;
import com.calificaciones.Model.Persona;
import com.calificaciones.Service.HomeworkService;
import com.calificaciones.Service.PersonService;
import com.calificaciones.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/profesor")
public class StudentController {


    @Autowired private SubjectService subjectService;
    @Autowired private PersonService personService;

    @Autowired private HomeworkService homeworkService;

    @GetMapping("/formStudent")
    public String obtenerFormularioEstudiante(Model model) {

        model.addAttribute("informacion", new Persona());
        model.addAttribute("informe", new HashMap<String, ArrayList<DetallesNota>>());
        model.addAttribute("buscar", new FormsIndex());
        model.addAttribute("promedios", new ArrayList<ListadoMaterias>());
        return "informeEstudiante";
    }

    @PostMapping("/formStudent")
    public String buscarUsuario(@ModelAttribute("buscar") FormsIndex busqueda, Model model) {

        if (busqueda.getIdstudent() != null || !busqueda.getIdstudent().equals("")) {
            HashMap<String, ArrayList<DetallesNota>> informe = subjectService.obtenerInformacionNotasMateria(busqueda.getIdstudent());
            model.addAttribute("informacion", personService.getStudent(busqueda.getIdstudent()));
            model.addAttribute("informe", informe);
            model.addAttribute("buscar", new FormsIndex());
            List<ListadoMaterias> listadoMaterias = homeworkService.obtenerPromedios(Integer.valueOf(busqueda.getIdstudent()));
            model.addAttribute("promedios", listadoMaterias);
            return "informeEstudiante";
        }
        return "redirect:/profesor/formStudent";
    }

}
