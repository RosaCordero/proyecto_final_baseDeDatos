package com.calificaciones.Repository;

import com.calificaciones.DTO.ListadoMaterias;
import com.calificaciones.Model.Tarea;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public interface HomeworkRepository extends JpaRepository<Tarea, Integer> {

    @Query(value = "DELETE FROM tarea WHERE id_materia = :materia", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteBySubject(@Param("materia") Integer materia);


    @Query("SELECT t FROM Tarea t WHERE t.id IN (SELECT n.homeworkId FROM Nota n WHERE n.id = :id_nota)")
    Optional<Tarea> getHwByGrade(@Param("id_nota") Integer id);

    @Query("SELECT t FROM Tarea t WHERE t.subject = :materia")
    Optional<ArrayList<Tarea>> hwBySubject(@Param("materia") Integer id_materia);

    @Query(value = "CALL cantidad(:id_materia)", nativeQuery = true)
    Double porcentajeRestante(@Param("id_materia") Long id_materia);

    @Query(value="CALL listado_promedios_materia(:estudiante)", nativeQuery = true)
    List<ListadoMaterias> obtenerPromedios(@Param("estudiante") Integer identificacion);
}
