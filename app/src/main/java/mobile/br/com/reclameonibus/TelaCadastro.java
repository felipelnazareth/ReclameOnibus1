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
    private EditText tConfirmaEmail;
    private EditText tSenha;
    private EditText tConfirmaSenha;
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
                android.R.layout.simple_spinner_dropdown_item);
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
        tConfirmaEmail = (EditText) findViewById(R.id.tConfirmaEmail);
        tConfirmaEmail.addTextChangedListener(textWatcher);
        tSenha = (EditText) findViewById(R.id.tSenha);
        tSenha.addTextChangedListener(textWatcher);
        tConfirmaSenha = (EditText) findViewById(R.id.tConfirmaSenha);
        tConfirmaSenha.addTextChangedListener(textWatcher);

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
        String confirmaEmail = tConfirmaEmail.getText().toString().trim();
        String confirmaSenha = tConfirmaSenha.getText().toString().trim();

        return (!ehVazio(user, tel, email, pass, confirmaEmail, confirmaSenha) && checkCampos(tel, email) &&
                !emailExistente(email) && confirmaEmailSenha(confirmaEmail, email, confirmaSenha, pass));

    }

    private boolean ehVazio(String user, String tel, String email, String pass, String confirmaEmail, String confirmaSenha) {
        boolean ret = false;
        if (TextUtils.isEmpty(user)) {
            tnome.requestFocus(); //seta o foco para o campo user
            tnome.setError("Digite seu nome");
            ret = true;
        }
        if (TextUtils.isEmpty(tel)) {
            tTelefone.requestFocus(); //seta o foco para o campo password
            tTelefone.setError("Digite seu telefone");
            ret = true;
        }
        if (TextUtils.isEmpty(email)) {
            tEmail.requestFocus(); //seta o foco para o campo password
            tEmail.setError("Digite sua senha");
            ret = true;
        }
        if (TextUtils.isEmpty(confirmaEmail)) {
            tConfirmaEmail.requestFocus();
            tConfirmaEmail.setError("Confirme seu email");
            ret = true;
        }
        if (TextUtils.isEmpty(pass)) {
            tSenha.requestFocus(); //seta o foco para o campo password
            tSenha.setError("Digite sua senha");
            ret = true;
        }
        if (TextUtils.isEmpty(confirmaSenha)) {
            tConfirmaSenha.requestFocus();
            tConfirmaSenha.setError("Confirme sua senha");
            ret = true;
        }

        return ret;
    }

    private boolean checkCampos(String tel, String email) {

        boolean ret = true;
        if ((!checkTelephone(tel))) {
            tTelefone.requestFocus();
            tTelefone.setError("Telefone inválido.");
            ret = false;
        }
        if ((!checkEmail(email))) {
            tEmail.requestFocus();
            tEmail.setError("Email inválido.");
            ret = false;
        }
        return ret;
    }

    private boolean confirmaEmailSenha(String confirmaEmail, String email, String confirmaSenha, String senha) {

        boolean ret = true;
        if (!confirmaEmail.equals(email)) {
            tConfirmaEmail.requestFocus();
            tConfirmaEmail.setError("Emails diferentes");
            ret = false;
        }
        if (!confirmaSenha.equals(senha)) {
            tConfirmaSenha.requestFocus();
            tConfirmaSenha.setError("Senhas diferentes");
            ret = false;
        }

        return ret;
    }

    private boolean emailExistente(String email) {

        DBUsuario db = new DBUsuario(TelaCadastro.this);

        boolean ret = false;
        if (db.existsEmail(email)) {
            tEmail.requestFocus();
            tEmail.setError("Email já cadastrado. Escolha outro.");
            ret = true;
        }
        return ret;
    }


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tela_cadastro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Opcoes no action bar
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
            if (validarCampos()) {

            String resultado;
            GetSetUsuarios getSetUsuarios = helper.buscaParaInserir();
            DBUsuario rec = new DBUsuario(TelaCadastro.this);
            resultado = rec.insereUsuarios(getSetUsuarios);
            Toast.makeText(getBaseContext(), resultado, Toast.LENGTH_SHORT).show();

            Intent it = new Intent(TelaCadastro.this, TelaFiltroConsultaOuReclamacao.class);
                it.putExtra("nome", tnome.getText().toString());
            startActivity(it);
            }

        }

    }
}

