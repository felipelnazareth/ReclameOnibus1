package mobile.br.com.reclameonibus;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Matheus on 03/11/2015.
 */
public class BancoControllerReclamacoes {

    private SQLiteDatabase db;
    private ReclamacoesDB banco;

    public BancoControllerReclamacoes(Context context) {
        banco = new ReclamacoesDB(context);
    }

    public String insereDado(String linha, String numero_ordem,
                             String hora, String data, String local, String tipo_reclamacao, String foto) {
        ContentValues values;
        long resultado;

        db = banco.getWritableDatabase();
        values = new ContentValues();
        values.put("linha", linha);
        values.put("numero_ordem", numero_ordem);
        values.put("hora", hora);
        values.put("data", data);
        values.put("local", local);
        values.put("tipo_reclamacao", tipo_reclamacao);
        values.put("foto", foto);

        resultado = db.insert("bd_reclamacoes", null, values);
        db.close();

        if (resultado == -1) {
            return "Erro ao inserir registro";
        }else {
            return "Registro inserido com sucesso";
        }
    }
}


