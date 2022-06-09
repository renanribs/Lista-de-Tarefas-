package com.example.listadetarefas;

import android.content.Intent;
import android.os.Bundle;
import java.util.Calendar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class NewCourseActivity extends AppCompatActivity {
    // criando as variáveis para o botão e para os edittext.
    private EditText courseNameEdt, courseDescEdt, courseDurationEdt;
    private Button courseBtn;
    // criando uma variável de string constante para
    // nome do curso, descrição e duração.
    public static final String EXTRA_ID =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_COURSE_NAME =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_NAME";
    public static final String EXTRA_DESCRIPTION =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DESCRIPTION";
    public static final String EXTRA_DURATION =
            "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DURATION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        // inicializando nossas variáveis para cada visão.
        courseNameEdt = findViewById(R.id.idEdtNomeCurso);
        courseDescEdt = findViewById(R.id.idEdtDescricaoCurso);
        courseDurationEdt = findViewById(R.id.idEdtDuracaoCurso);
        courseBtn = findViewById(R.id.idBtnSaveCourse);
        // a linha abaixo é para obter o intent,
        // pois estamos obtendo dados por meio de um intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            //se obtivermos id para nossos dados,
            // estaremos definindo valores para nossos campos de edição de texto.
                    courseNameEdt.setText(intent.getStringExtra(EXTRA_COURSE_NAME));
            courseDescEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            courseDurationEdt.setText(intent.getStringExtra(EXTRA_DURATION));
        }
        // adicionando ouvinte de clique para nosso botão Salvar.
        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // obtendo o valor do texto de edittext e validando
                // se os campos de texto estão vazios ou não.

                String courseName = courseNameEdt.getText().toString();
                String courseDesc = courseDescEdt.getText().toString();
                String courseDuration =
                        courseDurationEdt.getText().toString();
                if (courseName.isEmpty() || courseDesc.isEmpty() ||
                        courseDuration.isEmpty()) {
                    Toast.makeText(NewCourseActivity.this, "Entre com os detalhes da tarefa.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // chamando um método para salvar nosso curso.
                saveCourse(courseName, courseDesc, courseDuration);
            }
        });
    }
    private void saveCourse(String courseName, String courseDescription,
                            String courseDuration) {
        // dentro desse método, passamos todos os dados por meio de um intent.
        Intent data = new Intent();
        // na linha abaixo estamos passando todos os detalhes do nosso curso.
        data.putExtra(EXTRA_COURSE_NAME, courseName);
        data.putExtra(EXTRA_DESCRIPTION, courseDescription);
        data.putExtra(EXTRA_DURATION, courseDuration);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // na linha abaixo estamos passando nosso id.
            data.putExtra(EXTRA_ID, id);
        }
        // at last we are setting result as data.
        setResult(RESULT_OK, data);
        // exibindo uma mensagem toast após adicionar os dados
        Toast.makeText(this, "Tarefa salva.",
                Toast.LENGTH_SHORT).show();
    }
}
