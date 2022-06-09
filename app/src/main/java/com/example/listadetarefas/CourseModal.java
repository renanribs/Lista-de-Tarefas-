package com.example.listadetarefas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
// // a linha abaixo é para definir o nome da tabela.
@Entity(tableName = "course_table")
public class CourseModal {
    // a linha abaixo é para auto incrementar id para cada curso.
    @PrimaryKey(autoGenerate = true)
    // variável para o nosso id.
    private int id;
    // variável para o nome do curso.
    private String courseName;
    // variável para a descrição do curso.
    private String courseDescription;
    // variável para a duração do curso.
    private String courseDuration;
    // abaixo da linha estamos criando uma classe de construtor.
    // Dentro da classe do construtor não estamos passando nosso id
    // porque ele está incrementando automaticamente
    public CourseModal(String courseName, String courseDescription, String
            courseDuration) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseDuration = courseDuration;
    }
    // na linha abaixo estamos criando métodos getter e setter.
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseDescription() {
        return courseDescription;
    }
    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
    public String getCourseDuration() {
        return courseDuration;
    }
    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}


