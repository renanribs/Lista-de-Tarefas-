package com.example.listadetarefas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
public class CourseRVAdapter extends ListAdapter<CourseModal,
        CourseRVAdapter.ViewHolder> {
    // criando uma variável para o ouvinte de clique no item.
    private OnItemClickListener listener;
    // criando uma classe de construtor para nossa classe de adaptador.
    CourseRVAdapter() {
        super(DIFF_CALLBACK);
    }
    // criando um retorno de chamada para o item da visualização doreciclador.
    private static final DiffUtil.ItemCallback<CourseModal> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CourseModal>() {
                @Override
                public boolean areItemsTheSame(CourseModal oldItem, CourseModal
                        newItem) {
                    return oldItem.getId() == newItem.getId();
                }
                @Override
                public boolean areContentsTheSame(CourseModal oldItem, CourseModal
                        newItem) {
                    // a linha abaixo é para verificar o nome do curso, descrição eduração do curso.
                    return oldItem.getCourseName().equals(newItem.getCourseName()) &&

                            oldItem.getCourseDescription().equals(newItem.getCourseDescription()) &&

                            oldItem.getCourseDuration().equals(newItem.getCourseDuration());
                }
            };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        // a linha abaixo é usada para aumentar nosso arquivo de layout
        // para cada item de nossa visualização do reciclador.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(item);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // a linha de código abaixo é usada para
        // definir dados para cada item de nossa visão do reciclador.
        CourseModal model = getCourseAt(position);
        holder.courseNameTV.setText(model.getCourseName());
        holder.courseDescTV.setText(model.getCourseDescription());
        holder.courseDurationTV.setText(model.getCourseDuration());
    }
    // criando um método para obter o modal do curso para uma posição específica.
    public CourseModal getCourseAt(int position) {
        return getItem(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        // classe portadora de visão para criar uma variável para cada visão.
        TextView courseNameTV, courseDescTV, courseDurationTV;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // inicializando cada visão de nossa visão recicladora.
            courseNameTV = itemView.findViewById(R.id.idTVNomeCurso);
            courseDescTV = itemView.findViewById(R.id.idTVDescricaoCurso);
            courseDurationTV = itemView.findViewById(R.id.idTVDuracaoCurso);
            // adicionando ouvinte de clique para cada item da visualização do reciclador.
                    itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // dentro do listener do clique, estamos passando a posição para
                    // o nosso item de visualização do reciclador.
                    int position = getAdapterPosition();
                    if (listener != null && position !=
                            RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(CourseModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
