package com.example.salvum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Controle.Conexao;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lo" ;
    private static final int RC_SIGN_IN = 0;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText barsenha;
    private EditText baremail;
    private  FirebaseAuth auth;
    private TextView esqueci;
    private Button btnlogar;
    private TextView btnCad;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();
        cliquesdebotao();
        mAuth = FirebaseAuth.getInstance();
    }

    private void inicializarComponentes() {
        baremail = findViewById(R.id.barMatTelaLogin);
        barsenha = findViewById(R.id.barSenhaTelaLogin);
        btnlogar = findViewById(R.id.btnEntrarTelaLogin);
        btnCad = findViewById(R.id.txtRegistreTelaLogin);


    }

    private void inicializarfirebase() {
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void cliquesdebotao() {
        btnlogar.setOnClickListener(logar);
        btnCad.setOnClickListener(abrirtelacad);

    }


    //[INICIO CLIQUE DE BOTÃO DE LOGAR]
    View.OnClickListener logar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                String email = baremail.getText().toString().trim();
                String senha = barsenha.getText().toString().trim();
                login(email,senha);

        }
    };
    //[FIM DE CLIQUE DE BOTÃO DE LOGAR]

    //[INICIO DE FUNÇÃO DE AUTENTICAR LOGIN]
    private void login(String email,String senha) {
        try {
            auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(MainActivity.this, Activity_Lista_Cursos.class);
                        startActivity(intent);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);


                    }else{
                        Toast.makeText(MainActivity.this,"Erro ao efetuar login",Toast.LENGTH_SHORT).show();
                    }



                }
            });
        }catch (Exception e){
            e.printStackTrace();

        }
    }
    //[FIM DE FUNÇÃ DE AUTENTICAR LOGIN]
    //[INICIO ABRIR TELA CADASTRO]
    View.OnClickListener abrirtelacad = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,Activity_Cadastro_Usuario.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();


    }
}
