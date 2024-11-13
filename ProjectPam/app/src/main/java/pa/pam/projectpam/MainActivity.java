package pa.pam.projectpam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tambah;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.tambah = findViewById(R.id.tambah);
        this.tambah.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tambah) {
            // Buat intent untuk pindah ke halaman selanjutnya (NextActivity)
            Intent intent = new Intent(this, TambahResep.class);
            startActivity(intent); // Mulai activity baru
        }
    }
}

