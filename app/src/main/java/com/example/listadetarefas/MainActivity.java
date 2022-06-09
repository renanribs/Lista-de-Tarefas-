package com.example.listadetarefas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    
    private RecyclerView coursesRV;
    private static final int ADD_COURSE_REQUEST = 1;
    private static final int EDIT_COURSE_REQUEST = 2;
    private ViewModal viewmodal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coursesRV = findViewById(R.id.idCursos);
        FloatingActionButton fab = findViewById(R.id.idBotaoAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,
                        NewCourseActivity.class);
                startActivityForResult(intent, ADD_COURSE_REQUEST);
            }
        });
        // setting layout manager to our adapter class.
        coursesRV.setLayoutManager(new LinearLayoutManager(this));
        coursesRV.setHasFixedSize(true);
        // adaptador de inicialização para visualização do reciclador.
        final CourseRVAdapter adapter = new CourseRVAdapter();
        // configurando a classe do adaptador para visualização do reciclador.
        coursesRV.setAdapter(adapter);
        // passando um dado do modo de exibição.
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);
        // a linha abaixo é usada para obter todos os cursos do modal de exibição.
                viewmodal.getAllCourses().observe(this, new
                Observer<List<CourseModal>>() {
                    @Override
                    public void onChanged(List<CourseModal> models) {
                        // quando os dados são alterados em nossos modelos,
                        // estamos adicionando essa lista à nossa classe de adaptador.
                        adapter.submitList(models);
                    }
                });
        // o método abaixo é usado para adicionar o método de deslizar para excluir para o item da visualização do reciclador.
                new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull
                    RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                // na visualização do reciclador, o item deslocado, então estamos excluindo o item de nossa visualização do reciclador.

                        viewmodal.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Tarefa deletada.",
                        Toast.LENGTH_SHORT).show();
            }
        }).
                // a linha abaixo é usada para anexar isso à visualização do reciclador.
                        attachToRecyclerView(coursesRV);
        // a linha abaixo é usada para definir o ouvinte de clique de item para nosso item de visualização do reciclador.
                adapter.setOnItemClickListener(new
                                                       CourseRVAdapter.OnItemClickListener() {
                                                           @Override
                                                           public void onItemClick(CourseModal model) {
                                                               // após clicar no item da visualização do reciclador,
                                                               // estamos abrindo uma nova atividade e passando dados para a nossa atividade.
                                                               Intent intent = new Intent(MainActivity.this,
                                                                       NewCourseActivity.class);
                                                               intent.putExtra(NewCourseActivity.EXTRA_ID, model.getId());
                                                               intent.putExtra(NewCourseActivity.EXTRA_COURSE_NAME,
                                                                       model.getCourseName());
                                                               intent.putExtra(NewCourseActivity.EXTRA_DESCRIPTION,
                                                                       model.getCourseDescription());
                                                               intent.putExtra(NewCourseActivity.EXTRA_DURATION,
                                                                       model.getCourseDuration());
                                                               // a linha abaixo é para iniciar uma nova atividade e adicionar uma constante de edição de curso.
                                                                       startActivityForResult(intent, EDIT_COURSE_REQUEST);
                                                           }
                                                       });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable
            Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_COURSE_REQUEST && resultCode == RESULT_OK) {
            String courseName =
                    data.getStringExtra(NewCourseActivity.EXTRA_COURSE_NAME);
            String courseDescription =
                    data.getStringExtra(NewCourseActivity.EXTRA_DESCRIPTION);
            String courseDuration =
                    data.getStringExtra(NewCourseActivity.EXTRA_DURATION);
            CourseModal model = new CourseModal(courseName, courseDescription,
                    courseDuration);
            viewmodal.insert(model);
            Toast.makeText(this, "Tarefa salvo.", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_COURSE_REQUEST && resultCode ==
                RESULT_OK) {
            int id = data.getIntExtra(NewCourseActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Tarefa não atualizada.",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            String courseName =
                    data.getStringExtra(NewCourseActivity.EXTRA_COURSE_NAME);
            String courseDesc =
                    data.getStringExtra(NewCourseActivity.EXTRA_DESCRIPTION);
            String courseDuration =
                    data.getStringExtra(NewCourseActivity.EXTRA_DURATION);
            CourseModal model = new CourseModal(courseName, courseDesc,
                    courseDuration);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Tarefa atualizada.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Tarefa sem salvar.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
