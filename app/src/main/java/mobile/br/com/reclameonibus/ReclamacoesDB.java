package mobile.br.com.reclameonibus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        db.execSQL("create table if not exists bd_reclamacoes (linha numeric primary key, numero_ordem numeric, hora time, data datetime, local text, tipo_reclamacao text, foto image);");
        Log.d(TAG, "Tabela criada com sucesso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
