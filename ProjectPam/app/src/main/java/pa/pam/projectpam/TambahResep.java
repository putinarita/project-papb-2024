package pa.pam.projectpam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TambahResep extends AppCompatActivity implements View.OnClickListener {

    private EditText etNama, etBahan, jumlahPorsi, langkah, etAlat, etIngredient;
    private Button publish;
    private FirebaseDatabase db;
    private DatabaseReference resepRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_resep);

        // Inisialisasi Firebase
        db = FirebaseDatabase.getInstance("https://projectakhirpam-f5bb3-default-rtdb.asia-southeast1.firebasedatabase.app/");
        resepRef = db.getReference("resepq"); // Mengakses node "resep" di Firebase Database

        // Inisialisasi EditText
        etNama = findViewById(R.id.etNama);
        etBahan = findViewById(R.id.etIngredient);
        jumlahPorsi = findViewById(R.id.jumlahPorsi);
        langkah = findViewById(R.id.langkah);
        etAlat = findViewById(R.id.etAlat);
        etIngredient = findViewById(R.id.etIngredient);

        // Tombol publish
        publish = findViewById(R.id.publish);
        publish.setOnClickListener(this); // Menetapkan onClickListener untuk tombol publish
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.publish) {
            saveResep(); // Simpan resep ke Firebase
        }
    }

    private void saveResep() {
        // Ambil input pengguna
        String namaResep = etNama.getText().toString().trim();
        String bahan = etBahan.getText().toString().trim();
        String porsi = jumlahPorsi.getText().toString().trim();
        String langkah = this.langkah.getText().toString().trim();
        String alat = etAlat.getText().toString().trim();
        String ingredients = etIngredient.getText().toString().trim();

        // Validasi input
        if (TextUtils.isEmpty(namaResep) || TextUtils.isEmpty(bahan) || TextUtils.isEmpty(porsi) ||
                TextUtils.isEmpty(langkah) || TextUtils.isEmpty(alat) || TextUtils.isEmpty(ingredients)) {
            Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        // Ambil tanggal saat ini
        String tanggal = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        // Buat objek Resep
        String id = resepRef.push().getKey(); // Buat ID unik
        Resep resep = new Resep(id, namaResep, bahan, porsi, langkah, alat, ingredients, tanggal);

        // Simpan ke Firebase
        resepRef.child(id).setValue(resep) // Simpan resep berdasarkan ID
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(TambahResep.this, "Resep berhasil disimpan", Toast.LENGTH_SHORT).show();

                        // Setelah berhasil disimpan, arahkan ke activity SetelahPublish
                        Intent intent = new Intent(TambahResep.this, SetelahPublish.class);
                        startActivity(intent); // Mulai activity baru
                        finish(); // Selesaikan activity saat ini (TambahResep)
                    } else {
                        Toast.makeText(TambahResep.this, "Gagal menyimpan resep", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}