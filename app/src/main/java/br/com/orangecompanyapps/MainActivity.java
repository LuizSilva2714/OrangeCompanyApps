package br.com.orangecompanyapps;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtEmail;
    EditText edtAssunto;
    EditText edtMensagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAssunto = findViewById(R.id.edtAssunto);
        edtEmail = findViewById(R.id.edtEmail);
        edtMensagem = findViewById(R.id.edtMensagem);
        edtNome = findViewById(R.id.edtNome);
       /* mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.drawer_icon,
                R.string.drawer_aberto,
                R.string.drawer_fechado
        );*/
    }

    public void sendEmail(View view) {
        final String nome = edtNome.getText().toString();
        final String email = edtEmail.getText().toString();
        final String assunto = edtAssunto.getText().toString();
        final String mensagem = edtMensagem.getText().toString();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("orangeapps2019@gmail.com",
                            "orange2019");
                    sender.sendMail(assunto, nome + " enviou uma nova mensagem!: \n \n" + mensagem,
                            new String(email), "orangeapps2019@gmail.com");


                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
        Toast.makeText(MainActivity.this, "Mensagem enviada com sucesso!", Toast.LENGTH_LONG).show();
        edtAssunto.setText("");
        edtEmail.setText("");
        edtMensagem.setText("");
        edtNome.setText("");
    }
}
