package pa.pam.projectfragment;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Pastikan BottomNavigationView sudah ada di layout XML dengan id "navigation"
        bottomNavigation = findViewById(R.id.navigation);

        // Menetapkan listener untuk item navigation
        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                // Menggunakan if-else untuk menggantikan switch-case
                if (item.getItemId() == R.id.beranda) {
                    selectedFragment = new HomeFragment(); // pastikan HomeFragment tersedia
                } else if (item.getItemId() == R.id.tambah) {
                    selectedFragment = new TambahFragment(); // pastikan TambahFragment tersedia
                } else if (item.getItemId() == R.id.bookmark) {
                    selectedFragment = new BookmarkFragment(); // pastikan BookmarkFragment tersedia
                }

                // Mengganti fragment yang terpilih
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment) // pastikan fragment_container ada
                            .commit();
                }

                return true; // Menunjukkan bahwa item telah dipilih
            }
        });

        // Menampilkan fragment awal saat aplikasi diluncurkan
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment()) // fragment default adalah HomeFragment
                    .commit();
            bottomNavigation.setSelectedItemId(R.id.beranda); // Menandai item "beranda" sebagai yang terpilih
        }
    }
}