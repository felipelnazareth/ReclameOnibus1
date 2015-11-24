package mobile.br.com.reclameonibus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matheus on 01/11/2015.
 */
public class DBUsuario extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "usuarios.sqlite";
    private static final String TAG = "sql";
    private static final int VERSAO_BANCO = 1;
    private SQLiteOpenHelper openHelper;

    public DBUsuario(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando tabela usuarios..");
        db.execSQL("create table if not exists table_usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nome text, telefone numeric, email text unique, senha numeric, bairro text);");
        Log.d(TAG, "Tabela criada com sucesso");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public String insereUsuarios(GetSetUsuarios getSetUsuarios) {

        long resultado;
        ContentValues values = new ContentValues();

        values.put("nome", getSetUsuarios.getNome());
        values.put("telefone", getSetUsuarios.getTelefone());
        values.put("email", getSetUsuarios.getEmail());
        values.put("senha", getSetUsuarios.getSenha());
        values.put("bairro", getSetUsuarios.getBairro());

        resultado = getWritableDatabase().insert("table_usuarios", null, values);

        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }

    }

    public List<GetSetUsuarios> validarUsuarios() {

        List<GetSetUsuarios> usuarios = new ArrayList<GetSetUsuarios>();
        Cursor c = getReadableDatabase()
                .rawQuery("SELECT * FROM " + "table_usuarios" + ";", null);
        while (c.moveToNext()) {
            GetSetUsuarios getSetUsuarios = new GetSetUsuarios();
            getSetUsuarios.setEmail(c.getString(c.getColumnIndex("email")));
            getSetUsuarios.setSenha(c.getString(c.getColumnIndex("senha")));
            usuarios.add(getSetUsuarios);
        }
        c.close();
        return usuarios;

//        Cursor c =  getReadableDatabase().rawQuery("SELECT * FROM table_usuarios WHERE email = " +
//                email +  "AND" + "=" + senha + "", null);
//
//        if (c.getCount()>0)
//            return true;
//        return false;
    }


}

