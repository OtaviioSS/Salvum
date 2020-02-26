package com.example.salvum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.UUID;

import Controle.Conexao;
import Model.Usuario;

public class Activity_Cadastro_Usuario extends AppCompatActivity {
    private EditText txtnome;
    private EditText matricula;
    private EditText txtemail;
    private EditText txtsenha;
    private EditText telefone;
    private Button btncadastra;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    Usuario usuario = new Usuario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__cadastro__usuario);
        inicializarcomponentes();
        inicializarfirebase();
        cliquesdebotao();
    }

    private void cliquesdebotao() {
    btncadastra.setOnClickListener(passarparamentros);

    }
    View.OnClickListener passarparamentros = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = txtemail.getText().toString().trim();
            String senha = txtsenha.getText().toString().trim();
            criarUser(email,senha);
        }
    };

    private void inicializarfirebase() {
        FirebaseApp.initializeApp(Activity_Cadastro_Usuario.this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    private void inicializarcomponentes() {
        txtnome = findViewById(R.id.barNomeCadUse);
        matricula =findViewById(R.id.barMatriculaCadUsuario);
        txtemail =findViewById(R.id.barEmailCadUsuario);
        telefone =findViewById(R.id.barTelefoneCadUsuario);
        txtsenha = findViewById(R.id.barSenhaCadUsuario);
        btncadastra = findViewById(R.id.btnCadUsuario);

    }

    @Override
    protected void onStart(){
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    private void criarUser(String email,String senha) {
        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(Activity_Cadastro_Usuario.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                try{
                    if (task.isSuccessful()){
                        usuario.setId(UUID.randomUUID().toString());
                        usuario.setEmail(txtemail.getText().toString());
                        usuario.setNome(txtnome.getText().toString());
                        usuario.setMatricula(matricula.getText().toString());
                        usuario.setTelefone(matricula.getText().toString());
                        databaseReference.child("Usuario/Aluno").child(usuario.getId()).setValue(usuario);
                        Intent intent = new Intent(Activity_Cadastro_Usuario.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        limparcampos();
                        Toast.makeText(Activity_Cadastro_Usuario.this, "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Activity_Cadastro_Usuario.this, "Erro ao Cadastrar", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void limparcampos() {
        txtemail.setText("");
        txtnome.setText("");
        txtsenha.setText("");
        matricula.setText("");
        telefone.setText("");
    }
}

