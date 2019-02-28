package com.borcofix.mobiltalepyonetim.Models;

public class Kayit{
	private String id;
	private String kayitTipi;
	private String konu;
	private String aciklama;
	private String fotoBaslik;
	private String fotoYol;
	private String adres;
	private String ilAdi;
	private String ilceAdi;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){ return id; }

	public void setKayitTipi(String kayitTipi){
		this.kayitTipi = kayitTipi;
	}

	public String getKayitTipi(){
		return kayitTipi;
	}

	public void setKonu(String konu){
		this.konu = konu;
	}

	public String getKonu(){
		return konu;
	}

	public void setAciklama(String aciklama){
		this.aciklama = aciklama;
	}

	public String getAciklama(){
		return aciklama;
	}

	public void setFotoBaslik(String fotoBaslik){
		this.fotoBaslik = fotoBaslik;
	}

	public String getFotoBaslik(){
		return fotoBaslik;
	}

	public void setFotoYol(String fotoYol){
		this.fotoYol = fotoYol;
	}

	public String getFotoYol(){
		return fotoYol;
	}

	public void setAdres(String adres){
		this.adres = adres;
	}

	public String getAdres(){
		return adres;
	}

	public void setIlAdi(String ilAdi){
		this.ilAdi = ilAdi;
	}

	public String getIlAdi(){
		return ilAdi;
	}

	public void setIlceAdi(String ilceAdi){
		this.ilceAdi = ilceAdi;
	}

	public String getIlceAdi(){
		return ilceAdi;
	}

	@Override
 	public String toString(){
		return 
			"Kayit{" +
					",id = '" + id + '\'' +
					",kayit_tipi = '" + kayitTipi + '\'' +
					",konu = '" + konu + '\'' +
					",aciklama = '" + aciklama + '\'' +
					",foto_baslik = '" + fotoBaslik + '\'' +
					"il_adi = '" + ilAdi + '\'' +
					",ilce_adi = '" + ilceAdi + '\'' +
					",adres = '" + adres + '\'' +

				"}";
		}
}
