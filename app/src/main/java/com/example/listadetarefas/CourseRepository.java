package com.example.listadetarefas;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
public class CourseRepository {
    // abaixo da linha é criar uma variável para dao e listar todos os cursos.
    private Dao dao;
    private LiveData<List<CourseModal>> allCourses;
    // criando um construtor para nossas variáveis e passando as variáveis para ele.
    public CourseRepository(Application application) {
        CourseDatabase database = CourseDatabase.getInstance(application);
        dao = database.Dao();
        allCourses = dao.getAllCourses();
    }
    //criando um método para inserir os dados em nosso banco de dados..
    public void insert(CourseModal model) {
        new InsertCourseAsyncTask(dao).execute(model);
    }
    // criando um método para atualizar os dados no banco de dados.
    public void update(CourseModal model) {
        new UpdateCourseAsyncTask(dao).execute(model);
    }
    // criando um método para deletar os dados em nosso banco de dados.
    public void delete(CourseModal model) {
        new DeleteCourseAsyncTask(dao).execute(model);
    }
    // abaixo está o método para deletar todos os cursos.
    public void deleteAllCourses() {
        new DeleteAllCoursesAsyncTask(dao).execute();
    }
    // o método abaixo é ler todos os cursos
    public LiveData<List<CourseModal>> getAllCourses() {
        return allCourses;
    }
    // estamos criando um método de tarefa assíncrona para inserir um novo curso.
    private static class InsertCourseAsyncTask extends AsyncTask<CourseModal,
            Void, Void> {
        private Dao dao;
        private InsertCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(CourseModal... model) {
            // a linha abaixo é usada para inserir nosso modal no dao.
            dao.insert(model[0]);
            return null;
        }
    }
    // estamos criando um método de tarefa assíncrona para atualizar nosso curso.
    private static class UpdateCourseAsyncTask extends AsyncTask<CourseModal,
            Void, Void> {
        private Dao dao;
        private UpdateCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(CourseModal... models) {
            // a linha abaixo é usada para atualizar nosso modal no dao.
            dao.update(models[0]);
            return null;
        }
    }
    // estamos criando um método de tarefa assíncrona para excluir o curso.
    private static class DeleteCourseAsyncTask extends AsyncTask<CourseModal,
            Void, Void> {
        private Dao dao;
        private DeleteCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(CourseModal... models) {
            // a linha abaixo é usada para deletar nosso modal de curso nodao.
                    dao.delete(models[0]);
            return null;
        }
    }
    // estamos criando um método de tarefa assíncrona para excluir todos os cursos.
    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void,
            Void, Void> {
        private Dao dao;
        private DeleteAllCoursesAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // método chamado para deletar todos os cursos.
            dao.deleteAllCourses();
            return null;
        }
    }
}