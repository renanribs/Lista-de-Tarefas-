package com.example.listadetarefas;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
// adicionando anotações para nossas entidades de banco de dados e versão db..
@Database(entities = {CourseModal.class}, version = 1)
public abstract class CourseDatabase extends RoomDatabase {
    // a linha abaixo é para criar uma instância para nossa classe de banco de dados.
    private static CourseDatabase instance;
    // a linha abaixo é para criar uma variável abstrata para dao.
    public abstract Dao Dao();
    // na linha abaixo estamos obtendo instância para nosso banco de dados.
    public static synchronized CourseDatabase getInstance(Context context) {
        // abaixo da linha é para verificar se a instância é nula ou não.
        if (instance == null) {
            // se a instância for nula, estamos criando uma nova instância
            instance =
            // para criar uma instância para nosso banco de dados,
                    Room.databaseBuilder(context.getApplicationContext(),
                            CourseDatabase.class, "course_database")
                            // a linha abaixo é usada para adicionar retorno à
// migração destrutiva para nosso banco de dados.
                            .fallbackToDestructiveMigration()
// a linha abaixo é para adicionar retorno dechamada ao nosso banco de dados.
 .addCallback(roomCallback)
// a linha abaixo é para construir nosso banco dedados.
 .build();
        }
        // depois de criar uma instância, estamos retornando nossa instância
        return instance;
    }
    // a linha abaixo é para criar um retorno de chamada para nosso banco dedados ROOM.
    private static RoomDatabase.Callback roomCallback = new
            RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    // este método é chamado quando o banco de dados é criado e a linha abaixo é para preencher nossos dados.
                            new PopulateDbAsyncTask(instance).execute();
                }
            };
    // estamos criando uma classe de tarefa assíncrona para realizar a tarefaem segundo plano.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void,
            Void> {
        PopulateDbAsyncTask(CourseDatabase instance) {
            Dao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}

