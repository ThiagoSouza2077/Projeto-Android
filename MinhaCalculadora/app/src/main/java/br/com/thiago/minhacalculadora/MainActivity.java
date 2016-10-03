package br.com.thiago.minhacalculadora;

import android.graphics.ImageFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Button botaoLimpar = (Button) findViewById(R.id.botaoC);
        botaoLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Limpar(v);
            }
        });*/

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayoutCalculadora);
        int cont = gridLayout.getChildCount();
        ButtonClickMandler buttonClickMandler = new ButtonClickMandler();
        for (int i=0; i<cont; i++){
            View v = gridLayout.getChildAt(i);
            if (v instanceof Button){
                v.setOnClickListener(buttonClickMandler);
            }
        }

    }

    private class ButtonClickMandler implements View.OnClickListener{
        @Override
        public void onClick(View v){
            //Toast.makeText(getApplication(),""+((Button)v).getText(),Toast.LENGTH_SHORT).show();

            TextView textView = (TextView) findViewById(R.id.textViewOutputScreen);

            if (v instanceof Button){
                Button botaoApertado = (Button) v;

                if (v.getId()== R.id.botaoC){
                    textView.setText("0");
                }
                else if (v.getId()== R.id.botaoIgual){
                    try {
                        double resultadoCalculo = Calculo.Calcular(textView.getText().toString());
                        textView.setText(Double.toString(resultadoCalculo));
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                        textView.setText("0");
                    }

                }

                else if (textView.getText().length() > 0 && Calculo.Operador(textView.getText().charAt(textView.getText().length()-1))&&
                        Calculo.Operador(botaoApertado.getText().charAt(0))){
                    String mensagemErro = "Proibido usar dois operadores seguidos";
                    Toast.makeText(getApplicationContext(),mensagemErro,Toast.LENGTH_SHORT).show();
                }else {
                    if (textView.getText().equals("0") &&
                            !Calculo.Operador(botaoApertado.getText().charAt(0))) {
                        textView.setText("");
                    }
                    textView.setText(textView.getText().toString() + botaoApertado.getText());
                }
            }
        }
    }


}
