package com.borcofix.mobiltalepyonetim.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.borcofix.mobiltalepyonetim.DialogClass;
import com.borcofix.mobiltalepyonetim.Models.Kayit;
import com.borcofix.mobiltalepyonetim.R;

import java.util.List;

public class KayitAdapter extends BaseAdapter { //Burada KayıtAdapter sınıfımızı BaseAdapter sınıfımızdan extend ettikten sonra Alt+Enter yaparak implement edilmesi gereken fonksiyonları(getCount, getItem, getItemId, getView gibi) implement ettik

    List<Kayit>  list ;
    Context context;
    Activity activity;



    public KayitAdapter(List<Kayit> list, Context context,Activity activity) { //Burada yukarıda tanımladığımız değişkenlerimizin constructer larını oluşturduk. (SAğ tık, getter and setter, contructor tıklayarak yapabiliyoruz.)
        this.list = list;
        this.context = context;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return list.size();
    } // Count'umuza size'ını verdik

    @Override
    public Object getItem(int position) {
        return list.get(position); //Item'ına da position'ımızı verdik.
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.list11,parent,false);
        TextView id , kayitTipi , konu, aciklama, fotoBaslik, adres, ilAdi, ilceAdi ;
        LinearLayout linearLayout ;
        ImageView resim;
        id = (TextView)convertView.findViewById(R.id.tvL11RecordId);
        kayitTipi =(TextView) convertView.findViewById(R.id.tvL11sRecordType);
        konu = (TextView)convertView.findViewById(R.id.tvL11RecordTopic);
        aciklama = (TextView)convertView.findViewById(R.id.tvL11RecordExplanation);
        fotoBaslik = (TextView)convertView.findViewById(R.id.tvL11ImageTitle);
        adres = (TextView)convertView.findViewById(R.id.tvL11RecordAddress);
        ilAdi = (TextView)convertView.findViewById(R.id.tvL11sCities);
        ilceAdi = (TextView)convertView.findViewById(R.id.tvL11sDistrict);
        resim = (ImageView)convertView.findViewById(R.id.ivRecordPicture);




        id.setText(list.get(position).getId());
        kayitTipi.setText(list.get(position).getKayitTipi());
        konu.setText(list.get(position).getKonu());
        aciklama.setText(list.get(position).getAciklama());
        fotoBaslik.setText(list.get(position).getAciklama());
        adres.setText(list.get(position).getAdres());
        ilAdi.setText(list.get(position).getIlAdi());
        ilceAdi.setText(list.get(position).getIlceAdi());


        return convertView;
    }
}
