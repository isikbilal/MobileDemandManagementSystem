package com.borcofix.mobiltalepyonetim;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.borcofix.mobiltalepyonetim.Models.Result;
import com.borcofix.mobiltalepyonetim.RestApi.ManagerAll;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.borcofix.mobiltalepyonetim.RestApi.BaseUrl.Url;
import static java.sql.Types.NULL;

public class Create extends AppCompatActivity {

    private static String[] iller = new String[] {"İSTANBUL", "ANKARA", "İZMİR", "ADANA", "ADIYAMAN", "AFYONKARAHİSAR", "AĞRI", "AKSARAY", "AMASYA", "ANTALYA", "ARDAHAN", "ARTVİN", "AYDIN", "BALIKESİR", "BARTIN", "BATMAN", "BAYBURT", "BİLECİK", "BİNGÖL", "BİTLİS", "BOLU", "BURDUR", "BURSA", "ÇANAKKALE", "ÇANKIRI", "ÇORUM", "DENİZLİ", "DİYARBAKIR", "DÜZCE", "EDİRNE", "ELAZIĞ", "ERZİNCAN", "ERZURUM", "ESKİŞEHİR", "GAZİANTEP", "GİRESUN", "GÜMÜŞHANE", "HAKKARİ", "HATAY", "IĞDIR", "ISPARTA", "KAHRAMANMARAŞ", "KARABÜK", "KARAMAN", "KARS", "KASTAMONU", "KAYSERİ", "KIRIKKALE", "KIRKLARELİ", "KIRŞEHİR", "KİLİS", "KOCAELİ", "KONYA", "KÜTAHYA", "MALATYA", "MANİSA", "MARDİN", "MERSİN", "MUĞLA", "MUŞ", "NEVŞEHİR", "NİĞDE", "ORDU", "OSMANİYE", "RİZE", "SAKARYA", "SAMSUN", "SİİRT", "SİNOP", "SİVAS", "ŞIRNAK", "TEKİRDAĞ", "TOKAT", "TRABZON", "TUNCELİ", "ŞANLIURFA", "UŞAK", "VAN", "YALOVA", "YOZGAT", "ZONGULDAK"};
    private String[] istanbul  = {"ADALAR", "ARNAVUTKÖY", "ATAŞEHİR", "AVCILAR", "BAĞCILAR", "BAHÇELİEVLER", "BAKIRKÖY", "BAŞAKŞEHİR", "BAYRAMPAŞA", "BEŞİKTAŞ", "BEYLİKDÜZÜ", "BEYOĞLU", "BÜYÜKÇEKMECE", "BEYKOZ", "ÇATALCA", "ÇEKMEKÖY", "ESENLER", "ESENYURT", "EYÜP", "FATİH", "GAZİOSMANPAŞA", "GÜNGÖREN", "KADIKÖY", "KAĞITHANE", "KARTAL", "KÜÇÜKÇEKMECE", "MALTEPE", "PENDİK", "SANCAKTEPE", "SARIYER", "SİLİVRİ", "SULTANBEYLİ", "SULTANGAZİ", "ŞİLE", "ŞİŞLİ", "TUZLA", "ÜSKÜDAR", "ÜMRANİYE", "ZEYTİNBURNU" };
    private String[] ankara = { "AKYURT", "ALTINDAĞ", "AYAŞ", "BALA", "BEYPAZARI", "ÇAMLIDERE", "ÇANKAYA", "ÇUBUK", "ELMADAĞ", "ETİMESGUT", "EVREN", "GÖLBAŞI", "GÜDÜL", "HAYMANA", "KALECİK", "KAZAN", "KEÇİÖREN", "KIZILCAHAMAM", "MAMAK", "NALLIHAN", "POLATLI", "PURSAKLAR", "SİNCAN", "ŞEREFLİKOÇHİSAR", "YENİMAHALLE" };
    private String[] izmir = { "ALİAĞA", "BALÇOVA", "BAYINDIR", "BAYRAKLI", "BERGAMA", "BEYDAĞ", "BORNOVA", "BUCA", "ÇEŞME", "ÇİĞLİ", "DİKİLİ", "FOÇA", "GAZİEMİR", "GÜZELBAHÇE", "KARABAĞLAR", "KARABURUN", "KARŞIYAKA", "KEMALPAŞA", "KINIK", "KİRAZ", "KONAK", "MENDERES", "MENEMEN", "NARLIDERE", "ÖDEMİŞ", "SEFERİHİSAR", "SELÇUK", "TİRE", "TORBALI", "URLA" };
    private String[] adana = {"ALADAĞ","CEYHAN","ÇUKUROVA","FEKE","İMAMOĞLU","KARAİSALI","KARATAŞ","KOZAN","POZANTI","SAİMBEYLİ","SARIÇAM","SEYHAN","TUFANBEYLİ","YUMURTALIK","YÜREĞİR"};
    private String[] adıyaman = {"BESNİ","ÇELİKHAN","GERGER","GÖLBAŞI","KAHTA","SAMSAT","SİNCİK","TUT"};
    private String[] afyon = {"BAŞMAKÇI","BAYAT","BOLVADİN","ÇAY","ÇOBANLAR","DAZKIRI","DİNAR","EMİRDAĞ","EVCİLER","HOCALAR","İHSANİYE","İSCEHİSAR","KIZILÖREN","SANDIKLI","SİNANPAŞA"};
    private String[] agri = {"DİYADİN","DOĞUBAYAZIT","ELEŞKİRT","HAMUR","PATNOS","TAŞLIÇAY","TUTA"};
    private String[] amasya = {"GÖYNÜCEK","GÜMÜŞHACIKÖY","HAMAMÖZÜ","MERZİFON","SULUOVA","TAŞOVA"};
    private String[] antalya = {"AKSEKİ","AKSU","ALANYA","DEMRE","DÖŞEMEALTI","ELMALI","FİNİKE","GAZİPAŞA","İBRADI","KAŞ","KEMER","KEPEZ","KONYAALTI","KORKUTELİ","KUMLUCA","MANAVGAT","MURATPAŞA","SERİK"};
    private String[] artvin = {"ARDANUÇ","ARHAVİ","BORÇKA","HOPA","MURGUL","ŞAVŞAT","YUSUFELİ"};
    private String[] bayburt = {"AYDINTEPE","DEMİRÖZÜ"};
    private String[] bitlis = {"ADİLCEVAZ","AHLAT","GÜROYMAK","HİZAN","MUTKİ","TATVAN","KARATAŞ","KOZAN","POZANTI","SAİMBEYLİ","SARIÇAM","SEYHAN","TUFANBEYLİ","YUMURTALIK","YÜREĞİR"};
    private String[] bolu = {"DÖRTDİVAN","GEREDE","GÖYNÜK","KIBRISCIK","MENGEN","MUDURNU","SEBEN","YENİÇAĞA"};
    private String[] bursa = {"BÜYÜKORHAN","GEMLİK","GÜRSU","HARMANCIK","İNEGÖL","İZNİK","KARACABEY","KELES","KESTEL","MUDANYA","MUSTAFAKEMALPAŞA","NİLÜFER","ORHANELİ","ORHANGAZİ","OSMANGAZİ","YENİŞEHİR","YILDIRIM"};
    private String[] canakkale = {"AYVACIK","BAYRAMİÇ","BİGA","BOZCAADA","ÇAN","ECEABAT","EZİNE","GELİBOLU","İMROZ","LAPSEKİ","YENİCE"};
    private String[] düzce = {"AKÇAKOCA","CUMAYERİ","ÇİLİMLİ","GÜMÜŞOVA","KAYNAŞLI","YIĞILCA"};
    private String[] erzurum = {"AŞKALE","AZİZİYE","ÇAT","HINIS","HORASAN","İSPİR","KARAÇOBAN","KARAYAZI","KÖPRÜKÖY","NARMAN","OLTU","OLUR","PALANDÖKEN","PASİNLER","PAZARYOLU","ŞENKAYA","TEKMAN","TORTUM","UZUNDERE","YAKUTİYE"};
    private String[] giresun = {"ALUCRA","BULANCAK","ÇAMOLUK","ÇANAKÇI","DERELİ","DOĞANKENT","ESPİYE","EYNESİL","GÖRELE","GÜCE","KEŞAP","PİRAZİZ","ŞEBİNKARAHİSAR","TİREBOLU","YAĞLIDERE"};
    private String[] gumushane = {"KELKİT","KÖSE","KÜRTÜN","ŞİRAN","TORUL"};
    private String[] kars = {"AKYAKA","ARPAÇAY","DİGOR","KAĞIZMAN","SARIKAMIŞ","SELİM","SUSUZ"};
    private String[] kocaeli = {"BAŞİSKELE","ÇAYIROVA","DARICA","DERİNCE","DİLOVASI","GEBZE","GÖLCÜK","İZMİT","KANDIRA","KARAMÜRSEL","KARTEPE","KÖRFEZ"};
    private String[] malatya = {"AKÇADAĞ","ARAPGİR","ARGUVAN","BATTALGAZİ","DARENDE","DOĞANŞEHİR","DOĞANYOL","HEKİMHAN","KALE","KULUNCAK","PÜTÜRGE","YAZIHAN","YEŞİLYURT"};
    private String[] kahramanmaras = {"AFŞİN","ANDIRIN","ÇAĞLAYANCERİT","EKİNÖZÜ","ELBİSTAN","GÖKSUN","NURHAK","PAZARCIK","TÜRKOĞLU"};
    private String[] ordu = {"AKKUŞ","AYBASTI","ÇAMAŞ","ÇATALPINAR","ÇAYBAŞI","FATSA","GÖLKÖY","GÜLYALI","İKİZCE","KABADÜZ","KABATAŞ","KORGAN","KUMRU","MESUDİYE","PERŞEMBE","ULUBEY","ÜNYE"};
    private String[] rize = {"ARDEŞEN","ÇAMLIHEMŞİN","ÇAYELİ","DEREPAZARI","FINDIKLI","GÜNEYSU","HEMŞİN","İKİZDERE","İYİDERE","KALKANDERE","PAZAR"};
    private String[] samsun = {"ONDOKUZMAYIS","ALAÇAM","ASARCIK","ATAKUM","AYVACIK","BAFRA","CANİK","ÇARŞAMBA","HAVZA","İLKADIM","KAVAK","LADİK","SALIPAZARI","TEKKEKÖY","TERME","VEZİRKÖPRÜ","YAKAKENT"};
    private String[] sinop = {"AYANCIK","BOYABAT","DİKMEN","DURAĞAN","ERFELEK","GERZE","SARAYDÜZÜ","TÜRKELİ"};
    private String[] sivas = {"AKINCILAR","ALTINYAYLA","DİVRİĞİ","DOĞANŞAR","GEMEREK","GÖLOVA","GÜRÜN","HAFİK","İMRANLI","KANGAL","KOYULHİSAR","SUŞEHRİ","ŞARKIŞLA","ULAŞ","YILDIZELİ","ZARA"};
    private String[] tokat = {"ALMUS","ARTOVA","BAŞÇİFTLİK","ERBAA","PAZAR","REŞADİYE","SULUSARAY","TURHAL","YEŞİLYURT","ZİLE"};
    private String[] trabzon = {"AKÇAABAT","ARAKLI","ARSİN","BEŞİKDÜZÜ","ÇARŞIBAŞI","ÇAYKARA","DERNEKPAZARI","DÜZKÖY","HAYRAT","KÖPRÜBAŞI","MAÇKA","OF","SÜRMENE","TONYA","VAKFIKEBİR","YOMRA"};

    ArrayAdapter<String> dataAdapterForIller;
    ArrayAdapter<String> dataAdapterForIlceler;

    EditText etRecordTopic;
    EditText etRecordExplanation;
    ImageView ivRecordPicture;
    EditText etRecordLocation;
    EditText etRecordAdress;
    Spinner sRecordType;
    Spinner sCities;
    Spinner sDistrict;
    EditText etImageTitle;

    Button btnSend;
    Button btnCancel;
    Button btnImageSelect;
    Button btnImageUpload;

    Bitmap bitmap;

    String CrecordTopic;
    String CrecordExplanation;
    String CrecordLocation;
    String CrecordAdress;
    String CimageTitle;
    String CrecordType;
    String Ccities;
    String Cdistrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);

        tanimla();
        islem();
        spinnerAdapter();
        addRecord();

    }

    public void tanimla(){

        etRecordTopic = findViewById(R.id.etRecordTopic);
        etRecordExplanation = findViewById(R.id.etRecordExplanation);
        ivRecordPicture = findViewById(R.id.ivRecordPicture);
        etRecordAdress = findViewById(R.id.etRecordAdress);
        sRecordType = findViewById(R.id.sRecordType);
        sCities = findViewById(R.id.sCities);
        sDistrict = findViewById(R.id.sDistrict);
        btnSend =  findViewById(R.id.btnSend);
        btnCancel =  findViewById(R.id.btnCancel);
        btnImageSelect =  findViewById(R.id.btnImageSelect);
        etImageTitle =  findViewById(R.id.etImageTitle);
    }

    public void addRecord(){

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gonder();
            }
        });
    }

    public void islem() {
        btnImageSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resimGoster();
            }
        });

        /*
        btnImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gonder();
            }
        });
        */
    }

    public void gonder() {

        CrecordType = sRecordType.getSelectedItem().toString();
        CrecordTopic = etRecordTopic.getText().toString();
        CimageTitle = etImageTitle.getText().toString();
        CrecordExplanation = etRecordExplanation.getText().toString();
        CrecordAdress = etRecordAdress.getText().toString();
        Ccities = sCities.getSelectedItem().toString();
        Cdistrict = sDistrict.getSelectedItem().toString();

        String imageToString = imageToString();
        Log.i("imageToString", imageToString);
        Log.i("imageToString", "" + imageToString.length());
        Call<Result> x = ManagerAll.getInstance().gonder(CrecordType,CrecordTopic,CrecordExplanation,CimageTitle,imageToString,CrecordAdress,Ccities,Cdistrict);
        x.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Toast.makeText(getApplicationContext(), response.body().getResult(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.i("HATA Resim:",t.getMessage());
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

    public String imageToString() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byt = byteArrayOutputStream.toByteArray();
        String imageToString = Base64.encodeToString(byt, Base64.DEFAULT);
        return imageToString;
    }

    public void spinnerAdapter(){

        //Spinner'lar için adapterleri hazırlıyoruz.
        dataAdapterForIller = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, iller);
        dataAdapterForIlceler = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,istanbul);

        //Listelenecek verilerin görünümünü belirliyoruz.
        dataAdapterForIller.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterForIlceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Hazırladğımız Adapter'leri Spinner'lara ekliyoruz.
        sCities.setAdapter(dataAdapterForIller);
        sDistrict.setAdapter(dataAdapterForIlceler);

        // Listelerden bir eleman seçildiğinde yapılacakları tanımlıyoruz.
        sCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //Hangi il seçilmişse onun ilçeleri adapter'e ekleniyor.

                if(parent.getSelectedItem().toString().equals(iller[0]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,istanbul);
                 if(parent.getSelectedItem().toString().equals(iller[1]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,ankara);
                 if(parent.getSelectedItem().toString().equals(iller[2]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,izmir);
                 if(parent.getSelectedItem().toString().equals(iller[3]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,adana);
                 if(parent.getSelectedItem().toString().equals(iller[4]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,adıyaman);
                 if(parent.getSelectedItem().toString().equals(iller[5]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,afyon);
                 if(parent.getSelectedItem().toString().equals(iller[6]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,agri);
                 if(parent.getSelectedItem().toString().equals(iller[8]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,amasya);
                 if(parent.getSelectedItem().toString().equals(iller[9]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,antalya);
                 if(parent.getSelectedItem().toString().equals(iller[11]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,artvin);
                 if(parent.getSelectedItem().toString().equals(iller[16]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,bayburt);
                 if(parent.getSelectedItem().toString().equals(iller[19]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,bitlis);
                 if(parent.getSelectedItem().toString().equals(iller[20]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,bolu);
                 if(parent.getSelectedItem().toString().equals(iller[22]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,bursa);
                 if(parent.getSelectedItem().toString().equals(iller[23]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,canakkale);
                 if(parent.getSelectedItem().toString().equals(iller[28]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,düzce);
                 if(parent.getSelectedItem().toString().equals(iller[32]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,erzurum);
                 if(parent.getSelectedItem().toString().equals(iller[35]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,giresun);
                 if(parent.getSelectedItem().toString().equals(iller[36]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,gumushane);
                 if(parent.getSelectedItem().toString().equals(iller[41]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,kahramanmaras);
                 if(parent.getSelectedItem().toString().equals(iller[44]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,kars);
                 if(parent.getSelectedItem().toString().equals(iller[51]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,kocaeli);
                 if(parent.getSelectedItem().toString().equals(iller[54]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,malatya);
                 if(parent.getSelectedItem().toString().equals(iller[62]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,ordu);
                 if(parent.getSelectedItem().toString().equals(iller[64]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,rize);
                 if(parent.getSelectedItem().toString().equals(iller[66]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,samsun);
                 if(parent.getSelectedItem().toString().equals(iller[68]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,sinop);
                 if(parent.getSelectedItem().toString().equals(iller[69]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,sivas);
                 if(parent.getSelectedItem().toString().equals(iller[72]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,tokat);
                 if(parent.getSelectedItem().toString().equals(iller[73]))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,trabzon);
                 if (parent.getSelectedItem().toString().equals(NULL))
                    dataAdapterForIlceler = new ArrayAdapter<String>(Create.this, android.R.layout.simple_spinner_item,afyon);


                dataAdapterForIlceler.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sDistrict.setAdapter(dataAdapterForIlceler);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }



}
