package pa.pam.projectpam;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import android.view.ViewGroup;
import android.view.Window;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
import android.app.Dialog;

public class MainActivity extends AppCompatActivity {

    public static final String FirebaseURL =
            "https://projectakhirpam-f5bb3-default-rtdb.asia-southeast1.firebasedatabase.app/";
    private RecyclerView rvResepList;
    private List<Resep> dataset;
    private DeleteResep deleteResep = new DeleteResep();
    private AdapterResep adapter;
    private FirebaseDatabase db;
    private DatabaseReference appDb;

    private ActivityResultLauncher<Intent> updateResepLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rvResepList = findViewById(R.id.rvResepList);
        this.dataset = new ArrayList<>();
        this.adapter = new AdapterResep(this, this.dataset, this::onClick);
        this.rvResepList.setLayoutManager(new LinearLayoutManager(this));
        this.rvResepList.setAdapter(this.adapter);
        this.db = FirebaseDatabase.getInstance(FirebaseURL);
        this.appDb = this.db.getReference("resepq");

        // Inisialisasi ActivityResultLauncher
        updateResepLauncher = registerForActivityResult(
                new StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        boolean updated = result.getData().getBooleanExtra("updated", false);
                        if (updated) {
                            // Muat ulang data resep setelah update
                            loadResepData();
                        }
                    }
                }
        );

        this.loadResepData();

        // Inisialisasi BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.tambah) {
                // Pindah ke activity tambah resep
                Intent intent = new Intent(this, TambahResep.class);
                startActivity(intent);
                return true;
            }else if (item.getItemId() == R.id.bookmark) {
                // Pindah ke halaman bookmark
                Intent intent = new Intent(this, BookmarkActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void loadResepData() {
        this.appDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataset.clear(); // Bersihkan dataset lama
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Resep resep = dataSnapshot.getValue(Resep.class);
                    if (resep != null) {
                        dataset.add(resep);
                    }
                }
                adapter.notifyDataSetChanged(); // Perbarui RecyclerView
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error saat mengambil data: " + error.getMessage());
            }
        });
    }

    private void onClick(Resep resep, String pilihan) {
        switch (pilihan) {
            case "Edit":
                Intent intent = new Intent(this, UpdateResepActivity.class);
                intent.putExtra("resepq", resep); // Mengirim data resep ke UpdateResepActivity
                updateResepLauncher.launch(intent);
                break;
            case "Delete":
                deleteResep.showDialogDelete(resep, MainActivity.this);
                break;
            default:
                Toast.makeText(this, "Tidak ada pilihan", Toast.LENGTH_SHORT).show();
        }
    }
}