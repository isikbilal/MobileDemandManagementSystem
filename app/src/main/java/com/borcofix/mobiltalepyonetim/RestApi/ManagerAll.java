package com.borcofix.mobiltalepyonetim.RestApi;


import com.borcofix.mobiltalepyonetim.Models.Kayit;
import com.borcofix.mobiltalepyonetim.Models.Result;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() // getInstance methodunu tanımladık
    {
        return ourInstance; // Yukarıda oluşturduğumuz ourInstance nesnesini döndürüyoruz
    }

   public Call<Result> ekle(String ad, String soyad, String eposta, String kullanici_adi, String sifre, String sifre_tekrar)// "ekle" methodunu SignIn class'ımızda çağıracağız daha sonra
   {
       Call<Result> x = getRestApi().addUser(ad, soyad, eposta, kullanici_adi, sifre, sifre_tekrar ); // Burada Result tipindeki x adlı değişkenimize, kalıtım aldığımız bir üst sınıf olan "BaseManager" sınıfında bulunan "getRestApi" adlı methodumuzu çağırdık. (Bununla birlikte bizim BaseManager sınıfımızda olmamasına rağmen RestApi adlı interface'imizdeki addUser methodunu da çağırabildik. Bu nasıl oldu ???)

       return x; // Burada da x değerimizi döndürdük.

       //Böylelikle configurasyon işlemlerimiz bitmiş oluyor. Artık arayüzümüzdeki .xml dosyalarıyla bağlantılı olan SignUp clasımızda devam edeceğiz.
   }

    public Call<Result> giris(String kullanici_adi, String sifre) {

        Call<Result> x = getRestApi().loginUser(kullanici_adi, sifre );

        return x;
    }

    public Call<Result> gonder(String kayit_tipi, String konu, String aciklama, String foto_baslik, String resim, String adres, String il_adi, String ilce_adi) {
        Call<Result> x = getRestApi().addRecord(kayit_tipi, konu, aciklama, foto_baslik, resim, adres, il_adi, ilce_adi);
        return x;
    }
    public  Call<List<Kayit>>  goster() {
        Call<List<Kayit>>  x = getRestApi().listele();
        return x;
    }

    public Call<Result> deleteFromDb(String id)
    {
        Call<Result> y = getRestApi().sil(id);
        return y;
    }
}
