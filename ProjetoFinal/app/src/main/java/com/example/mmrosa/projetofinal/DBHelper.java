package com.example.mmrosa.projetofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmrosa on 17/12/17.
 */

//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Mayara Marques da Rosa
// Ola, tive problemas com a parte do banco
// se vc retirar a parte do bando funciona corretamente
// depois que inseri essa parte do banco não consegui
//mais rodar o app
//******************************************************

public class DBHelper {

    private static final String DATABASE_NAME = "bancodedados.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pessoa";

    private Context context;

    private SQLiteDatabase db;
    private SQLiteStatement insertStnt;
    private final static String INSERT = "insert into " + TABLE_NAME + " (nome, cpf, idade, telefone, email) values (?,?,?,?,?)";

    public DBHelper(Context context){
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStnt = this.db.compileStatement(INSERT);

    }

    public long insert(String nome,String cpf,String idade,String telefone,String email){
        this.insertStnt.bindString(1,nome);
        this.insertStnt.bindString(2,cpf);
        this.insertStnt.bindString(3,idade);
        this.insertStnt.bindString(4,telefone);
        this.insertStnt.bindString(5,email);

        return this.insertStnt.executeInsert();
    }

    public List<Pessoa> queryGetAll(){
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        try{

            Cursor cursor = this.db.query(TABLE_NAME, new String [] {"nome","cpf","idade","telefone","email"},
                    null,null,null,null,null,null);

            int nRegistros = cursor.getCount();

            if(nRegistros != 0){
                cursor.moveToFirst();

                do {

                    Pessoa pessoa =  new Pessoa(cursor.getString(0),
                            cursor.getString(1) ,cursor.getString(2),
                            cursor.getString(3),cursor.getString(4));

                    pessoas.add(pessoa);

                }while (cursor.moveToNext());

                if(cursor != null && !cursor.isClosed())
                    cursor.close();
                    return pessoas;

            }else
                return null;


        }catch (Exception err) {
            return null;
        }
    }

    private static class OpenHelper extends SQLiteOpenHelper{
        OpenHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db){
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cpf TEXT, " +
                    "idade TEXT, telefone TEXT, email TEXT);";

            db.execSQL(sql);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
        }

    }
}
