package com.example.salvum;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Model.Modelo_Curso;

public class Activity_Lista_Cursos extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private CursoAdapter cursoAdapter;
    List<Modelo_Curso> listadecursos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__lista__cursos);
        inicializarfirebase();
        recyclerView = findViewById(R.id.recyclerInicio);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        cursoAdapter = new CursoAdapter(listadecursos);
        recyclerView.setAdapter(cursoAdapter);
        RecuperarCursos();
        verificarAutenticacao();
        BottomNavigationView navigationView = findViewById(R.id.navegtion);
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);



    }
    private void inicializarfirebase() {
        FirebaseApp.initializeApp(Activity_Lista_Cursos.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }


    private void RecuperarCursos(){
        DatabaseReference cursoRef = databaseReference.child("Curso");
        cursoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listadecursos.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    listadecursos.add(ds.getValue(Modelo_Curso.class) );
                }
                cursoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void abriraddideais(){
        Intent intent = new Intent(Activity_Lista_Cursos.this,Activity_AddCurso.class);
        startActivity(intent);
    }
    private void abrirperfil() {
        Intent intent = new Intent(Activity_Lista_Cursos.this,Activity_Perfil.class);
        startActivity(intent);

    }
    private void verificarAutenticacao() {
        if(FirebaseAuth.getInstance().getUid() == null){
            Intent intent =  new Intent(Activity_Lista_Cursos.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
}
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.addIdeiaicon:
                    abriraddideais();
                    return  true;
                case R.id.sair:
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(Activity_Lista_Cursos.this,MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.perfil:
                    abrirperfil();
                    break;
            }
            return false;
        }
    };


}
