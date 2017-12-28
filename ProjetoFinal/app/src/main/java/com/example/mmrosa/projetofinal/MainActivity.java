package com.example.mmrosa.projetofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Mayara Marques da Rosa
// Ola, tive problemas com a parte do banco
// se vc retirar a parte do bando funciona corretamente
// depois que inseri essa parte do banco não consegui
//mais rodar o app
//******************************************************
public class MainActivity extends AppCompatActivity {

    Button btnInserir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInserir = (Button) findViewById(R.id.btnInserir);

        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaTelaCadastro();
            }
        });
    }

    void chamaTelaCadastro(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        finish();
    }
}
