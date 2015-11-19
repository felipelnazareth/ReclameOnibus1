package mobile.br.com.reclameonibus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Matheus on 03/11/2015.
 */
public class BancoControllerUsuario {

    private SQLiteDatabase db;
    private UsuarioDB banco;

    public BancoControllerUsuario(Context context) {
        banco = new UsuarioDB(context);
    }

    public String insereDado(String nome, String telefone, String email, String senha, String bairro) {
        ContentValues values;
        long resultado;

        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put("nome", nome);
        values.put("telefone", telefone);
        values.put("email", email);
        values.put("senha", senha);
        values.put("bairro", bairro);

        resultado = db.insert("bd_usuarios", null, values);
        db.close();

        if (resultado == -1) {
            return "Erro ao inserir registro";
        }else {
            return "Registro inserido com sucesso";
        }
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos = {"email","senha"};
        db = banco.getReadableDatabase();
        cursor = db.query("bd_usuarios", campos, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
