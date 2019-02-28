package com.borcofix.mobiltalepyonetim;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.borcofix.mobiltalepyonetim.Adapters.KayitAdapter;
import com.borcofix.mobiltalepyonetim.Models.Kayit;
import com.borcofix.mobiltalepyonetim.Models.Result;
import com.borcofix.mobiltalepyonetim.RestApi.ManagerAll;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class List1 extends AppCompatActivity {

    List<Kayit> kayit;
    ListView listView;
    KayitAdapter adp;

    Bitmap bitmap;
    ImageView ivRecordPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list1);

        tanimla();
        resimGoster();
        istek();

    }

    public void tanimla() {
        listView = (ListView) findViewById(R.id.lvL1Entry);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                       /*
                DialogClass dialogClass = new DialogClass(MainActivity.this, MainActivity.this);
                dialogClass.goster(kullanicis.get(position).getId());
                Log.i("deneme", "dasdasdsa");
                */
                 goster(kayit.get(position).getId());

            }
        });


    }

    public void resimGoster() {
        Intent ıntent = new Intent(); // İntent'lerin kullanım alanı sadece activity'ler arası geçiş değildir. Arama, mail ve mesaj gönderme gibi işlemlerde de kullanılabilir. Galeri'nin açılması içinde kullanılabilir.
        ıntent.setType("image/*");
        ıntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(ıntent, 777);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //Buradaki "data", bizim seçili olan resmimiz.
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777 && resultCode == RESULT_OK && data != null) { // "request işlemi başarılıysa" demek kısaca
            Uri path = data.getData(); //Path (yol) alma işlemini yaptık
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                ivRecordPicture.setImageBitmap(bitmap);
                ivRecordPicture.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void yenile(){

        istek();
    }

    public void istek() {

        kayit = new ArrayList<>();
        Call<List<Kayit>> x = ManagerAll.getInstance().goster();
        x.enqueue(new Callback<List<Kayit>>() {
            @Override
            public void onResponse(Call<List<Kayit>> call, Response<List<Kayit>> response) {

                Log.i("Sonucccc:",""+response.body().toString());
                if (response.isSuccessful()) {
                    kayit = response.body();
                    adp = new KayitAdapter(kayit, getApplicationContext(),List1.this);
                    listView.setAdapter(adp);
                }
            }

            @Override
            public void onFailure(Call<List<Kayit>> call, Throwable t) {
                Log.i("HATAAAA:",t.getMessage());
            }
        });
    }

    public void goster(final String id) {
        LayoutInflater ınflater = getLayoutInflater();
        View view = ınflater.inflate(R.layout.dialoglayout, null); //Burada layout'umuzun gösterileceği bir view nesnesi tanımlıyoruz.

        Button iptal = (Button) view.findViewById(R.id.iptalButon); // Burada butonlarımızı tanımlarken normalde view yazmadan sadece findViewById ile yapıyorduk ama burada butonlarımız tıklamaya bağlı bir view içinde gösterileceği için view kullanmamız gerekti.
        Button sil = (Button) view.findViewById(R.id.silButon);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this); // Burada AlertDialog ana sınıf, Builder ise ana sınıfın içindeki bir başka sınıf, bu sınıftan bir nesne ürettik bizde burada.
        alert.setView(view);
        alert.setCancelable(false); // Ekranın herhangibiryerine tıklandığında iptal edilmesin.
        final AlertDialog alertDialog = alert.create();
        iptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel(); // İptal butonuna tıklandığında kapatılacak.
            }
        });

        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<Result> sil = ManagerAll.getInstance().deleteFromDb(id);
                sil.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {

                        yenile();
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
                alertDialog.cancel();

            }
        });


        alertDialog.show();
    }


}

