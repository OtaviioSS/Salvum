package com.example.salvum;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import Model.Modelo_Curso;

public class Activity_AddCurso extends AppCompatActivity {

    private EditText txtTitulo;
    private EditText txtsobre;
    private EditText txtTags;
    private EditText quatParticipante;
    private Button btnconcluir;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add_curso);
        inicializarfirebase();
        inicializarcomponentes();
        cliquesdebotoes();
    }

    private void inicializarcomponentes(){
        txtTitulo = findViewById(R.id.barTituloAddCurso);
        txtsobre = findViewById(R.id.barSobreAddCurso);
        txtTags = findViewById(R.id.barTagsAddCurso);
        btnconcluir = findViewById(R.id.btnCriarAddCurso);
    }
    public void inicializarfirebase() {
        FirebaseApp.initializeApp(Activity_AddCurso.this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();}

    private void cliquesdebotoes() {
        btnconcluir.setOnClickListener(adicionarCurso);
    }

    View.OnClickListener adicionarCurso = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                Modelo_Curso curso = new Modelo_Curso();
                curso.setCursoTitulo(txtTitulo.getText().toString());
                curso.setCursoParticipante(0);
                curso.setCursoSobre(txtsobre.getText().toString());
                curso.setCursoTags(txtTags.getText().toString());
                curso.setCursoId(UUID.randomUUID().toString());
                databaseReference.child("Curso").child(curso.getCursoId()).setValue(curso);

                Toast.makeText(Activity_AddCurso.this, "Ideia Adicionada com Sucesso", Toast.LENGTH_LONG).show();
            } catch (RuntimeException err) {
                Toast.makeText(Activity_AddCurso.this, "Erro  " + err, Toast.LENGTH_LONG).show();

            }
        }
    };
}
