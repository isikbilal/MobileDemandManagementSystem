package com.borcofix.mobiltalepyonetim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.borcofix.mobiltalepyonetim.Models.Result;
import com.borcofix.mobiltalepyonetim.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    EditText etSUName;
    EditText etSUSurname;
    EditText etSUeMail;
    EditText etSUUserName;
    EditText etSUPassword;
    EditText etSUPasswordAgain;

    String SUname;
    String SUsurename;
    String SUemail;
    String SUusername;
    String SUpassword;
    String SUpasswordAgain;

    Button btnSUSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        tanimla();
        add();


    }

    public void tanimla() {
        etSUName = findViewById(R.id.etSUName);
        etSUSurname = findViewById(R.id.etSUSurname);
        etSUeMail = findViewById(R.id.etSUeMail);
        etSUUserName = findViewById(R.id.etSUUserName);
        etSUPassword = findViewById(R.id.etSUPassword);
        etSUPasswordAgain = findViewById(R.id.etSUPasswordAgain);

        btnSUSignUp = findViewById(R.id.btnSUSignUp);
    }

    public void add() {
        btnSUSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SUname = etSUName.getText().toString();
                SUsurename = etSUSurname.getText().toString();
                SUemail = etSUeMail.getText().toString();
                SUusername = etSUUserName.getText().toString();
                SUpassword = etSUPassword.getText().toString();
                SUpasswordAgain = etSUPasswordAgain.getText().toString();

                /*
                SUname = "bilal";
                SUsurename = "Işık";
                SUemail = "abc@gmail.com";
                SUusername = "borcofix";
                */

                if (!SUname.equals("") && !SUsurename.equals("") && !SUemail.equals("") && !SUusername.equals("") && !SUpassword.equals("") && !SUpasswordAgain.equals("")) {

                    if(!Patterns.EMAIL_ADDRESS.matcher(SUemail).matches()){

                        etSUeMail.setError("Lütfen geçerli bir e posta giriniz");
                        etSUeMail.requestFocus();
                        return;
                    }
                    if(SUpassword.length() < 5 ){

                        etSUPassword.setError("Şifreniz en az 5 karakter olmalıdır");
                        etSUPassword.requestFocus();
                        return;

                    }

                    istek(SUname, SUsurename, SUemail, SUusername, SUpassword, SUpasswordAgain);

                }

                else {
                    Toast.makeText(getApplicationContext(), "Bütün alanların doldurulması zorunludur", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

    public void deleteFromEdittext() {
        etSUName.setText(""); // Burada da yukarıdaki add() fonksiyonumuz ile ekleme işlemini yaptıktan sonra, yeni değerler girebilmemiz için edittext'lerimizi içlerini boşaltmamız gerekiyor. Edittext'lerimizin içlerini "" ifadesi ile setleyerek, yani ayarlayarak, değiştirerek boşalmasını sağlıyoruz.
        etSUSurname.setText("");
        etSUeMail.setText("");
        etSUUserName.setText("");
        etSUPassword.setText("");
        etSUPasswordAgain.setText("");
    }

    public void istek(String ad, String soyad, String eposta, String kullanici_adi, String sifre, String sifre_tekrar) {
        Call<Result> x = ManagerAll.getInstance().ekle(ad, soyad, eposta, kullanici_adi, sifre, sifre_tekrar);
        x.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {


                Toast.makeText(getApplicationContext(), response.body().getResult(), Toast.LENGTH_LONG).show();// Burada önceden Toast mesajının içeriğini kendimiz el ile yazmıştık. Ama biz Result.java sınıfımızı oluşturmuştuk ve getResult methodumuzu da oluşturmuştuk. Dolayısıyla "response.body().getResult()" ifadesini kullanarak direkt olarak ekle.php sayfamızdaki "x" adlı değişkenimizin değerini döndürüyoruz. Böylece ekleme işleminin başarılı yada başarısız olma durumuna göre dinamik bir Toast mesajı verebiliyoruz.

                if (response.body().getResult().equals("Kayit islemi basarili")) { //Burada da sartliekle.php den sönen Json değeri eğer başarılı ise bulunduğumuz activity den LoginScreen adlı activitymize geçmemizi sağlıyoruz
                    deleteFromEdittext();
                    Intent intent = new Intent(SignUp.this, LoginScreen.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "istek yapılamıyor bir hata oluştu", Toast.LENGTH_LONG).show();
                Log.i("HATA",t.getMessage());
            }
        });
    }

}
