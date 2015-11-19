//CLASSE PARA ARMAZENAR O QUE FOI DIGITADO PELO USUARIO EM UM OBJETO
// PARA EM SEGUIDA INSERIR NO BANCO

//package mobile.br.com.reclameonibus;
//
//import android.widget.AutoCompleteTextView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//
///**
// * Created by Matheus on 19/11/2015.
// */
//public class ReclamacaoFormulario {
//
//    private AutoCompleteTextView txtLinha;
//    private EditText txtOrdem;
//    private TextView displayTime;
//    private TextView displayDate;
//    private EditText tLocal;
//    private Spinner spReclamacao;
//    private Button btFoto;
//
//    private Reclamacao reclamacao;
//
//    public ReclamacaoFormulario(TelaReclamacao activity){
//
//        txtLinha = (AutoCompleteTextView) activity.findViewById(R.id.txtLinha);
//        txtOrdem = (EditText) activity.findViewById(R.id.txtOrdem);
//        displayTime = (TextView) activity.findViewById(R.id.displayTime);
//        displayDate = (TextView) activity.findViewById(R.id.displayDate);
//        tLocal = (EditText) activity.findViewById(R.id.tLocal);
//        spReclamacao = (Spinner) activity.findViewById(R.id.spReclamacao);
//        btFoto = (Button) activity.findViewById(R.id.btFoto);
//
//        reclamacao = new Reclamacao();
//    }
//
//    public Reclamacao buscaParaInserir(){
//        reclamacao.setLinha(txtLinha.getText().toString());
//        reclamacao.setNumero_ordem(txtOrdem.getText().toString());
//        reclamacao.setHora(displayTime.getText().toString());
//        reclamacao.setData(displayDate.getText().toString());
//        reclamacao.setLocal(tLocal.getText().toString());
//        reclamacao.setTipo_reclamacao(spReclamacao.getSelectedItem().toString());
////      reclamacao.setFoto(btFoto.getText().toString());
//
//        return reclamacao;
//    }
//
//}
