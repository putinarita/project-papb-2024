package pa.pam.projectpam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TambahResep extends AppCompatActivity implements View.OnClickListener {

    private EditText etNama;
    private EditText jumlahPorsi;
    private EditText langkah;
    private EditText etIngredient;
    private EditText etAlat;
    private Button publish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_resep);

        this.etNama = findViewById(R.id.etNama);
        this.jumlahPorsi = findViewById(R.id.jumlahPorsi);
        this.langkah = findViewById(R.id.langkah);
        this.etIngredient = findViewById(R.id.etIngredient);
        this.etAlat = findViewById(R.id.etAlat);
        this.publish = findViewById(R.id.publish);

        this.publish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.publish) {
            // Buat intent untuk pindah ke halaman selanjutnya (NextActivity)
            Intent intent = new Intent(this, SetelahPublish.class);
            startActivity(intent); // Mulai activity baru

        }
    }
}