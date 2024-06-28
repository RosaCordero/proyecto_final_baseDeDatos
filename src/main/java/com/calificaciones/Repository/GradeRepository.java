package com.calificaciones.Repository;

import com.calificaciones.Model.Nota;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Nota, Integer> {


    @Query("SELECT n FROM Nota n WHERE n.homeworkId IN (SELECT t.id FROM Tarea t WHERE t.subject = :materia)")
    Optional<ArrayList<Nota>> getGrades(@Param("materia") Integer id);

    @Query(value = "DELETE FROM nota WHERE idtarea IN (SELECT idtarea FROM tarea WHERE id_materia = :materia)", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteBySubject(@Param("materia") Integer materia);

    @Query("DELETE FROM Nota n WHERE n.student = :id")
    @Transactional
    @Modifying
    void deleteByStudent(@Param("id") Integer id);


    @Query("UPDATE Nota n SET n.grade = :grade, n.comments = :comentario WHERE n.student = :student AND n.homeworkId = :tarea")
    @Transactional
    @Modifying
    void updateGrade(Integer student, Integer tarea, Float grade, String comentario);


    @Query("SELECT n FROM Nota n WHERE n.student = :student AND n.homeworkId = :tarea")
    Optional<Nota> obtenerNota(@Param("student") Integer student, @Param("tarea") Integer tarea);
}
