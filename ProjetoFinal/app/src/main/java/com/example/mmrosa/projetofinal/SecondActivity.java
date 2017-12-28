package com.example.mmrosa.projetofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    //criando botoes
    Button btnInserir;
    Button btnVoltar;
    Button btnListar;

    EditText ctNome;
    EditText ctCpf;
    EditText ctIdade;
    EditText ctTel;
    EditText ctEmail;


    //utilitario db
    private DBHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        //instanciando botoes
        btnInserir = (Button) findViewById(R.id.btnInserir);
        btnVoltar  = (Button) findViewById(R.id.btnVoltar);
        btnListar = (Button) findViewById(R.id.btnListar);

        this.dh = new DBHelper(this);

        ctNome = (EditText) findViewById(R.id.ctNome);
        ctCpf = (EditText) findViewById(R.id.ctCpf);
        ctIdade = (EditText) findViewById(R.id.ctIdade);
        ctTel = (EditText) findViewById(R.id.ctTel);
        ctEmail = (EditText) findViewById(R.id.ctEmail);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarTelaInicial();
            }
        });


        btnInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ctNome.getText().length() > 0 && ctCpf.getText().length() > 0 && ctTel.getText().length() > 0 && ctIdade.getText().length() > 0
                        && ctEmail.getText().length() > 0){
                    dh.insert(ctNome.getText().toString(),ctCpf.getText().toString(),ctTel.getText().toString(),ctIdade.getText().toString(),ctEmail.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Sucesso");
                    adb.setMessage("Cadastro Realizado");
                    adb.show();

                    ctNome.setText("");
                    ctCpf.setText("");
                    ctTel.setText("");
                    ctIdade.setText("");
                    ctEmail.setText("");

                }else{
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Erro");
                    adb.setMessage("Todos os campos devem ser preenchidos");
                    adb.show();

                    ctNome.setText("");
                    ctCpf.setText("");
                    ctTel.setText("");
                    ctIdade.setText("");
                    ctEmail.setText("");

                }

            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Pessoa> pessoas =  dh.queryGetAll();

                if (pessoas == null) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Mensagem");
                    adb.setMessage("Nao ha registros");
                    adb.show();

                    return;

                }else{

                    for (int i = 0;i < pessoas.size(); i++){
                        Pessoa pessoa =  (Pessoa) pessoas.get(i);
                        AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                        adb.setTitle("Registro: "+i+1);
                        adb.setMessage("Nome: "+pessoa.getNome()+"\nCpf: "+pessoa.getCpf()
                        +"\nIdade: "+pessoa.getIdade()+"\nTelefone: "+pessoa.getTelefone()+"\nEmail: "+pessoa.getEmail());
                        adb.show();
                        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        adb.show();
                    }
                }

            }
        });

    }

    void voltarTelaInicial(){
        Intent intent = new Intent();
        intent.setClass(SecondActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
