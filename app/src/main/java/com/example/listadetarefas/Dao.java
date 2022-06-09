package com.example.listadetarefas;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
// Adicionando anotação à nossa classe Dao
@androidx.room.Dao
public interface Dao {
    // o método abaixo é usado para adicionar dados ao banco de dados.
    @Insert
    void insert(CourseModal model);
    // o método abaixo é usado para atualizar os dados em nosso banco de dados.
    @Update
    void update(CourseModal model);
    // a linha abaixo é usada para deletar um curso específico em nosso banc de dados.
    @Delete
    void delete(CourseModal model);
    // na linha abaixo estamos fazendo uma consulta para deletar todos oscursos de nosso banco de dados.
    @Query("DELETE FROM course_table")
    void deleteAllCourses();
    // a linha abaixo é para ler todos os cursos de nosso banco de dados.
    // Na linha, estamos ordenando nossos cursos em ordem crescente com o nome do nosso curso.
    @Query("SELECT * FROM course_table ORDER BY courseName ASC")
    LiveData<List<CourseModal>> getAllCourses();
}