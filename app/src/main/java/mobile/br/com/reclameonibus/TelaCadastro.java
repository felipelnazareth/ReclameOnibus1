package mobile.br.com.reclameonibus;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.regex.Pattern;

public class TelaCadastro extends Activity implements OnClickListener {

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$"
    );
    public final Pattern TELEPHONE_PATTERN = Pattern.compile(
            "[0-9]{10,11}"
    );
    private EditText tnome;
    private EditText tTelefone;
    private EditText tEmail;
    private EditText tSenha;
    private Spinner spBairro;
    private ObjetoUsuarios helper;

    //****************************** FIM DAS VARIÁVEIS *************************************

    // MÉTODO PARA VALIDAR EMAIL
    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    // MÉTODO PARA VALIDAR TELEFONE
    private boolean checkTelephone(String tel) {
        return TELEPHONE_PATTERN.matcher(tel).matches();
    }

    // SPINNER COM LISTA DE BAIRROS
    public void listBairros() {
        Spinner spinner = (Spinner) findViewById(R.id.spBairro);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.bairros,
                android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
    }


    public void textWatcher() {

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                callClearErrors(s);
            }
        };

        tnome = (EditText) findViewById(R.id.tNome);
        tnome.addTextChangedListener(textWatcher);
        tTelefone = (EditText) findViewById(R.id.tTelefone);
        tTelefone.addTextChangedListener(textWatcher);
        tEmail = (EditText) findViewById(R.id.tEmail);
        tEmail.addTextChangedListener(textWatcher);
        tSenha = (EditText) findViewById(R.id.tSenha);
        tSenha.addTextChangedListener(textWatcher);

    }

    // Chama o método para limpar erros
    private void callClearErrors(Editable s) {
        if (!s.toString().isEmpty()) {
            clearErrorFields(tnome);
        }
    }

    private boolean validarCampos() {
        String user = tnome.getText().toString().trim();
        String tel = tTelefone.getText().toString().trim();
        String email = tEmail.getText().toString().trim();
        String pass = tSenha.getText().toString().trim();
        return (!isEmptyFields(user, tel, email, pass)
                && checkCampos(tel, email));
    }

    private boolean isEmptyFields(String user, String tel, String email, String pass) {
        boolean ret = false;
        if (TextUtils.isEmpty(user)) {
            tnome.requestFocus(); //seta o foco para o campo user
            tnome.setError("Nome completo obrigatório");
            ret = true;
        }
        if (TextUtils.isEmpty(tel)) {
            tTelefone.requestFocus(); //seta o foco para o campo password
            tTelefone.setError("Telefone obrigatório");
            ret = true;
        }
        if (TextUtils.isEmpty(email)) {
            tEmail.requestFocus(); //seta o foco para o campo password
            tEmail.setError("Email obrigatório");
            ret = true;
        }
        if (TextUtils.isEmpty(pass)) {
            tSenha.requestFocus(); //seta o foco para o campo password
            tSenha.setError("Senha obrigatória");
            ret = true;
        }

        return ret;
    }

    private boolean checkCampos(String tel, String email) {

        boolean ret = false;
        if (!(checkTelephone(tel))) {
            tTelefone.requestFocus();
            tTelefone.setError("Telefone inválido");
            ret = true;
        }
        if (!(checkEmail(email))) {
            tEmail.requestFocus();
            tEmail.setError("Email inválido");
            ret = true;
        }
        return ret;
    }

//    private boolean jaExisteEmail(String email){
//
//        if(email.equals(new DBReclamacoes(getApplicationContext()))){
//            tEmail.requestFocus();
//            tEmail.setError("Email já existente. Tente outro");
//            return false;
//        }
//        return true;
//    }


    //Limpa os ícones e as mensagens de erro dos campos desejados
    private void clearErrorFields(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setError(null);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        helper = new ObjetoUsuarios(this);
        listBairros();
        textWatcher();

        //ACTION BAR DA TELA
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);
    }

    ;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_confirmar) {
            return true;
        }

        if (id == R.id.action_refresh) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    //*******************************************************************************************//

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btLogin) {
//            if (validarCampos()) {

                tnome = (EditText) findViewById(R.id.tNome);
                tTelefone = (EditText) findViewById(R.id.tTelefone);
                tEmail = (EditText) findViewById(R.id.tEmail);
                tSenha = (EditText) findViewById(R.id.tSenha);
                spBairro = (Spinner) findViewById(R.id.spBairro);

                String resultado;
            GetSetUsuarios getSetUsuarios = helper.buscaParaInserir();
            DBUsuario rec = new DBUsuario(TelaCadastro.this);
            resultado = rec.insereUsuarios(getSetUsuarios);
            Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_SHORT).show();

            Intent it = new Intent(TelaCadastro.this, TelaFiltroConsultaOuReclamacao.class);
            startActivity(it);
            it.putExtra("nome", (Serializable) tnome);
            }

        }

    }
//}

