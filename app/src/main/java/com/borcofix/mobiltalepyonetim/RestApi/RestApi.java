package com.borcofix.mobiltalepyonetim.RestApi;

import com.borcofix.mobiltalepyonetim.Models.Kayit;
import com.borcofix.mobiltalepyonetim.Models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded //  POST işleminde kullanılması zorunlu olan anotationdır.
    @POST("/mobilTalepYonetim/sartliekle.php") // Burada "@POST" annotation'ı ile içerisinde Json verileri barındıran web servisimize istek atıyoruz.  Bu istekle de gerekli alanlara göre veritabanımıza ekleme yapıyoruz. Eğer veri çekmek istersek "@GET" kullanmamız gerekir. İçerisine de istek atacağımız Json'un url'inin "/" ile başlayan son kısmını yazıyoruz. Önceki kısmı BaseUrl sınıfımızda yapacağız daha yönetilebilir olması için.
    Call<Result> addUser(@Field("ad") String ad, @Field("soyad") String soyad, @Field("eposta") String eposta, @Field("kullanici_adi") String kullanici_adi, @Field("sifre") String sifre, @Field("sifre_tekrar") String sifre_tekrar); // Burası bize result tipinde bir veri döndürecek. Çünkü ekle.php dosyamızda Json degişkenimizi oluştururken key değerini "Result", value değerini ise "Ekleme başarılıdır" şeklinde yapmıştık. Burada da class'ı mıza uyarlıyoruz. "@Field" annotation'ını kullanarak da web serviste almasını istediğimiz parametreleri yazdık. Mesela: Eğer @GET işlemi yapsaydık ve bir liste döndürmek

    @FormUrlEncoded
    @POST("/mobilTalepYonetim/kullanici_giris.php") // Burada "@POST" annotation'ı ile içerisinde Json verileri barındıran web servisimize istek atıyoruz.  Bu istekle de gerekli alanlara göre veritabanımıza ekleme yapıyoruz. Eğer veri çekmek istersek "@GET" kullanmamız gerekir. İçerisine de istek atacağımız Json'un url'inin "/" ile başlayan son kısmını yazıyoruz. Önceki kısmı BaseUrl sınıfımızda yapacağız daha yönetilebilir olması için.
    Call<Result> loginUser(@Field("kullanici_adi") String kullanici_adi, @Field("sifre") String sifre); // Burada iki addUser de loginUser de Result tipinde sonuç döndürüyor. İkisini farklı yapsaydık daha mı iyi olurdu acaba???

    @FormUrlEncoded
    @POST("/mobilTalepYonetim/kayitekle.php")
    Call<Result> addRecord(@Field("kayit_tipi") String kayit_tipi,@Field("konu") String konu,@Field("aciklama") String aciklama,@Field("foto_baslik") String foto_baslik,@Field("resim") String resim,@Field("adres") String adres,@Field("il_adi") String il_adi,@Field("ilce_adi") String ilce_adi); // Bunlar php dosyamızda _POST metodu ile gönderdiğimiz field lar

    @GET("/mobilTalepYonetim/listele.php")
    Call<List<Kayit>> listele();

    @FormUrlEncoded
    @POST("/mobilTalepYonetim/sil.php") Call<Result> sil(@Field("id") String id);

}


