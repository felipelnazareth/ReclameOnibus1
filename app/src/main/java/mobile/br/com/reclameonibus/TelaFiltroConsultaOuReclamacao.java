package mobile.br.com.reclameonibus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TelaFiltroConsultaOuReclamacao extends Activity implements OnClickListener {


    public void startTelaConsulta(){
        Button btConsultar = (Button) findViewById(R.id.btConsultar);
        btConsultar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaFiltroConsultaOuReclamacao.this, TelaConsulta.class));
            }
        });

    }

    public void startTelaReclamacao(){
        Button btReclamacao = (Button) findViewById(R.id.btReclamacao);
        btReclamacao.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaFiltroConsultaOuReclamacao.this, TelaReclamacao.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_filtro_consulta_ou_reclamacao);

        //Receber dados do intent
//        Intent it = getIntent();
//        String mensagem = it.getStringExtra("mensagem");
//
//        TextView textView = new TextView(this);
//        textView.setTextSize(30);
//        textView.setText(mensagem);
//
//        setContentView(textView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_filtro_pesquisa_ou_reclamacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btConsultar:
                startTelaConsulta();
                break;

            case R.id.btReclamacao:
                startTelaReclamacao();
                break;

        }
    }
}
