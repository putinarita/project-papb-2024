package pa.pam.projectpam;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class UpdateResepActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNama, etBahan, etPorsi, etLangkah, etAlat, etIngredients;
    private Button btnUpdateResep, btnDeleteResep;
    private FirebaseDatabase db;
    private DeleteResep deleteResep = new DeleteResep();
    private DatabaseReference resepRef;
    private String resepId;
    private Resep resep;

    private AdapterResep adapterResep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_resep);

        // Inisialisasi Firebase
        db = FirebaseDatabase.getInstance("https://projectakhirpam-f5bb3-default-rtdb.asia-southeast1.firebasedatabase.app/");
        resepRef = db.getReference("resepq");

        // Inisialisasi EditText dan Button
        etNama = findViewById(R.id.etUpdateNama);
        etBahan = findViewById(R.id.etUpdateBahan);
        etPorsi = findViewById(R.id.etUpdatePorsi);
        etLangkah = findViewById(R.id.etUpdateLangkah);
        etAlat = findViewById(R.id.etUpdateAlat);
        etIngredients = findViewById(R.id.etUpdateBahan);
        btnUpdateResep = findViewById(R.id.btnUpdateResep);
        btnDeleteResep = findViewById(R.id.btnDeleteResep);

        // Ambil data yang dikirim dari MainActivity
        if (getIntent().hasExtra("resepq")) {
            Resep resep = getIntent().getParcelableExtra("resepq");
            if (resep != null) {
                resepId = resep.getId();
                etNama.setText(resep.getNama());
                etBahan.setText(resep.getBahan());
                etPorsi.setText(resep.getPorsi());
                etLangkah.setText(resep.getLangkah());
                etAlat.setText(resep.getAlat());
                etIngredients.setText(resep.getIngredients());
            }
        }

        // Set onClickListener untuk button update
        btnUpdateResep.setOnClickListener(v -> updateResep());

        resep = getIntent().getParcelableExtra("resepq");
        // Set onClickListener untuk button delete
        btnDeleteResep.setOnClickListener(v ->
                deleteResep.showDialogDelete(resep, UpdateResepActivity.this));
    }

    private void updateResep() {
        String namaResep = etNama.getText().toString().trim();
        String bahan = etBahan.getText().toString().trim();
        String porsi = etPorsi.getText().toString().trim();
        String langkah = etLangkah.getText().toString().trim();
        String alat = etAlat.getText().toString().trim();
        String ingredients = etIngredients.getText().toString().trim();

        // Validasi input
        if (
                TextUtils.isEmpty(namaResep) ||
                        TextUtils.isEmpty(bahan) ||
                        TextUtils.isEmpty(porsi) ||
                        TextUtils.isEmpty(langkah) ||
                        TextUtils.isEmpty(alat) ||
                        TextUtils.isEmpty(ingredients)) {
            Toast.makeText(this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        String tanggal = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        Resep updatedResep = new Resep(resepId, namaResep, bahan, porsi, langkah, alat, ingredients, tanggal);

        resepRef.child(resepId).setValue(updatedResep)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(UpdateResepActivity.this, "Resep berhasil diperbarui", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateResepActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Kembali ke aktivitas sebelumnya
                    } else {
                        Toast.makeText(UpdateResepActivity.this, "Gagal memperbarui resep", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnUpdateResep) {
            updateResep(); // Simpan resep ke Firebase
        }

        if (view.getId() == R.id.btnDeleteResep){
            deleteResep();
        }
    }

    private void deleteResep() {
        resepRef.child(resepId).removeValue()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(UpdateResepActivity.this, "Resep berhasil dihapus", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateResepActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(UpdateResepActivity.this, "Gagal menghapus resep", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, "Gagal Menghapus", Toast.LENGTH_SHORT).show();
                });
    }
}