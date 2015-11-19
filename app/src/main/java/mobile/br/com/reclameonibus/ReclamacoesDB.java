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
 * Created by Matheus on 03/11/2015.
 */
public class ReclamacoesDB extends SQLiteOpenHelper {
    private static final String TAG = "sql";
    public static final  String NOME_BANCO = "reclamacoes_reclameonibus.sqlite";
    private static final int VERSAO_BANCO = 1;

    public ReclamacoesDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Criando tabela usuarios..");
        db.execSQL("create table if not exists bd_reclamacoes (id INTEGER PRIMARY KEY AUTOINCREMENT, linha numeric, numero_ordem numeric, hora time, data datetime, local text, tipo_reclamacao text, foto image);");
        Log.d(TAG, "Tabela criada com sucesso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String insereReclamacoes (Reclamacao reclamacao){

        long resultado;
        ContentValues values = new ContentValues();

        values.put("linha", reclamacao.getLinha());
        values.put("numero_ordem", reclamacao.getNumero_ordem());
        values.put("hora", reclamacao.getHora());
        values.put("data", reclamacao.getData());
        values.put("local", reclamacao.getLocal());
        values.put("tipo_reclamacao", reclamacao.getTipo_reclamacao());
        values.put("foto", reclamacao.getFoto());

        resultado = getWritableDatabase().insert("bd_reclamacoes", null, values);
        getWritableDatabase().close();

        if (resultado == -1) {
            return "Erro ao inserir registro";
        }else {
            return "Registro inserido com sucesso";
        }

    }

    public List<Reclamacao> getLista() {
        List<Reclamacao> reclamacoes = new ArrayList<Reclamacao>();
        Cursor c = getReadableDatabase()
                .rawQuery("SELECT * FROM " + "bd_reclamacoes" + ";", null);
        while (c.moveToNext()) {
            Reclamacao reclamacao = new Reclamacao();
            reclamacao.setLinha(c.getString(c.getColumnIndex("linha")));
            reclamacao.setTipo_reclamacao(c.getString(c.getColumnIndex("tipo_reclamacao")));
            reclamacoes.add(reclamacao);
        }
        c.close();
        return reclamacoes;
    }


}
