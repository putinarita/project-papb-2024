package bn.mobile.recipecategory;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buat object dummy
        List<Category> data = new ArrayList<>();
        data.add(new Category("Nasi", R.drawable.ic_nasi));
        data.add(new Category("Mie", R.drawable.ic_mie));
        data.add(new Category("Sup", R.drawable.ic_sup));
        data.add(new Category("Salad", R.drawable.ic_salad));
        data.add(new Category("Minuman", R.drawable.ic_minuman));
        data.add(new Category("Ayam & Bebek", R.drawable.ic_ayam));
        data.add(new Category("Kue", R.drawable.ic_kue));

        // Masukkan ke dalam adapter
        CategoryAdapter categoryAdapter = new CategoryAdapter(data, this);

        // Tampilkan di RecyclerView
        RecyclerView rvRecipeCategories = this.findViewById(R.id.rvRecipeCategories);
        rvRecipeCategories.setLayoutManager(new LinearLayoutManager(this));
        rvRecipeCategories.setAdapter(categoryAdapter);
    }
}
