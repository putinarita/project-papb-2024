package pa.pam.projectpam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SetelahPublish extends AppCompatActivity implements View.OnClickListener {

    private Button kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setelah_publish);
        this.kembali = findViewById(R.id.kembali);

        this.kembali.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.kembali) {
            // Buat intent untuk pindah ke halaman selanjutnya (NextActivity)
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent); // Mulai activity baru

        }
    }
}