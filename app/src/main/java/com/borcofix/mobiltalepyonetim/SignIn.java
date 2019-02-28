package com.borcofix.mobiltalepyonetim;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.borcofix.mobiltalepyonetim.Models.Result;
import com.borcofix.mobiltalepyonetim.RestApi.ManagerAll;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {

    EditText etSIUserName;
    EditText etSIPassword;
    String SIuserName;
    String SIpassWord;

    Button btnSISignIn;
    Button btnSISignInCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        tanimla();
        login();

    }

    public void tanimla(){

        btnSISignIn = findViewById(R.id.btnSISignIn);
        etSIUserName = findViewById(R.id.etSIUserName);
        etSIPassword = findViewById(R.id.etSIPassword);
    }

    public void login() {
        btnSISignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SIuserName = etSIUserName.getText().toString();
                SIpassWord = etSIPassword.getText().toString();

                if (!SIuserName.equals("") && !SIpassWord.equals("")) {

                    istekLogin(SIuserName, SIpassWord);

                } else {
                    Toast.makeText(getApplicationContext(), "Bütün alanların doldurulması zorunludur", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void deleteFromEdittext() {

        etSIUserName.setText("");
        etSIPassword.setText("");
    }

    public void istekLogin(String kullanici_adi, String sifre) {
        Call<Result> x = ManagerAll.getInstance().giris(kullanici_adi, sifre);
        x.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                Toast.makeText(getApplicationContext(), response.body().getResult(), Toast.LENGTH_LONG).show();// Burada önceden Toast mesajının içeriğini kendimiz el ile yazmıştık. Ama biz Result.java sınıfımızı oluşturmuştuk ve getResult methodumuzu da oluşturmuştuk. Dolayısıyla "response.body().getResult()" ifadesini kullanarak direkt olarak ekle.php sayfamızdaki "x" adlı değişkenimizin değerini döndürüyoruz. Böylece ekleme işleminin başarılı yada başarısız olma durumuna göre dinamik bir Toast mesajı verebiliyoruz.

                if (response.body().getResult().equals("Giris basarili")) { //Burada da sartliekle.php den sönen Json değeri eğer başarılı ise bulunduğumuz activity den LoginScreen adlı activitymize geçmemizi sağlıyoruz
                    deleteFromEdittext();
                    Intent intent = new Intent(SignIn.this, Homepage.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Kullanıcı adı veya şifre yanlış", Toast.LENGTH_LONG).show();
                Log.i("HATA",t.getMessage());
            }
        });
    }

}
