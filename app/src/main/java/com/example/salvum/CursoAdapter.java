package com.example.salvum;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Model.Modelo_Curso;


    public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.MyViewHolder> {
        List<Modelo_Curso> cursos;
        int cor =0;
        public CursoAdapter(List<Modelo_Curso> cursos) {
            this.cursos = cursos;
        }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity__tela_inicio, parent, false);
            return new MyViewHolder(itemLista);
        }
        @Override
        public void onBindViewHolder(@NonNull final MyViewHolder holder, int i) {
            try {
                Modelo_Curso curso = cursos.get(i);
                holder.txtTitulo.setText(curso.getCursoTitulo());
                holder.txtSobre.setText(curso.getCursoSobre());
                holder.txtTegs.setText(curso.getCursoTags());
                holder.txtQtdParticipante.setText(curso.getCursoParticipante());

            }catch (Exception e){
                Log.i("Erro","erro",e);
            }
           /* holder.btnparticipar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(cor == 0) {
                        holder.btnparticipar.setBackground(DgetResources().getDrawable(R.drawable.btnverde));
                        cor = 1;
                    }else{
                        holder.btnparticipar.getResources().getDrawable(R.drawable.btncinza);
                        cor =0;
                    }
                }
            }); */
        }
        @Override
        public int getItemCount() {
            return cursos.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txtTitulo;
            TextView txtSobre;
            TextView txtTegs;
            TextView txtQtdParticipante;
            ToggleButton btnparticipar;





            MyViewHolder(View itemView) {
                super(itemView);
                txtTitulo = itemView.findViewById(R.id.txtTituloItemView);
                txtSobre = itemView.findViewById(R.id.txtSobreItemView);
                txtTegs = itemView.findViewById(R.id.txtTagItemView);
                txtQtdParticipante = itemView.findViewById(R.id.txtQtdParticipantes);
                btnparticipar = itemView.findViewById(R.id.btnParticipar);


            }
        }


    }

