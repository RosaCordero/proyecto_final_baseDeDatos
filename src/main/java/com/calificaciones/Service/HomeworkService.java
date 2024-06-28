package com.calificaciones.Service;

import com.calificaciones.DTO.ListadoMaterias;
import com.calificaciones.Model.Tarea;
import com.calificaciones.Repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkService {

    @Autowired private HomeworkRepository homeworkRepository;

    public void addHomework(Tarea tarea) {
        homeworkRepository.save(tarea);
    }


    public ArrayList<Tarea> getListOfHomeworks(Integer id_materia) {
        return homeworkRepository.hwBySubject(id_materia).orElseGet(ArrayList<Tarea>::new);
    }

    public Tarea getHwByGrade(Integer id_grade) {
        return homeworkRepository.getHwByGrade(id_grade).orElseGet(null);
    }

    public Tarea getHomework(Integer id_subject) {
        return homeworkRepository.findById(id_subject).orElseGet(null);
    }

    public Double getPorcentajeRestante(Long id_subject) {
        return homeworkRepository.porcentajeRestante(id_subject);
    }

    public List<ListadoMaterias> obtenerPromedios(Integer identificacion) {
        return homeworkRepository.obtenerPromedios(identificacion);
    }
}
