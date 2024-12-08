package pa.pam.projectpam;

import android.os.Parcel;
import android.os.Parcelable;

public class Resep implements Parcelable {
    private String id;
    private String nama;
    private String bahan;
    private String porsi;
    private String langkah;
    private String alat;
    private String ingredients;
    private String tanggal;

    // Constructor kosong untuk Firebase
    public Resep() {}

    // Constructor
    public Resep(String id, String nama, String bahan, String porsi, String langkah, String alat, String ingredients, String tanggal) {
        this.id = id;
        this.nama = nama;
        this.bahan = bahan;
        this.porsi = porsi;
        this.langkah = langkah;
        this.alat = alat;
        this.ingredients = ingredients;
        this.tanggal = tanggal;
    }

    // Getter dan Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getBahan() { return bahan; }
    public void setBahan(String bahan) { this.bahan = bahan; }

    public String getPorsi() { return porsi; }
    public void setPorsi(String porsi) { this.porsi = porsi; }

    public String getLangkah() { return langkah; }
    public void setLangkah(String langkah) { this.langkah = langkah; }

    public String getAlat() { return alat; }
    public void setAlat(String alat) { this.alat = alat; }

    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }

    public String getTanggal() { return tanggal; }
    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    // Parcelable implementation
    protected Resep(Parcel in) {
        id = in.readString();
        nama = in.readString();
        bahan = in.readString();
        porsi = in.readString();
        langkah = in.readString();
        alat = in.readString();
        ingredients = in.readString();
        tanggal = in.readString();
    }

    public static final Creator<Resep> CREATOR = new Creator<Resep>() {
        @Override
        public Resep createFromParcel(Parcel in) {
            return new Resep(in);
        }

        @Override public Resep[] newArray(int size) {
            return new Resep[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(bahan);
        dest.writeString(porsi);
        dest.writeString(langkah);
        dest.writeString(alat);
        dest.writeString(ingredients);
        dest.writeString(tanggal);
    }
}