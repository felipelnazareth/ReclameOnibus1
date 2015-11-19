package mobile.br.com.reclameonibus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Matheus on 01/11/2015.
 */
public class UsuarioDB extends SQLiteOpenHelper {

    private static final String TAG = "sql";
    public static final  String NOME_BANCO = "usuarios_reclameonibus.sqlite";
    private static final int VERSAO_BANCO = 1;

    public UsuarioDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando tabela usuarios..");
        db.execSQL("create table if not exists bd_usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nome text not null, telefone numeric not null, email text unique not null, senha numeric not null, bairro text not null);");
        Log.d(TAG, "Tabela criada com sucesso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}

