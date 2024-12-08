package pa.pam.projectpam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {

    private RecyclerView rvBookmark;
    private List<Bookmark> data;
    private AdapterBookmark adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark); // Layout baru untuk BookmarkActivity

        this.rvBookmark = this.findViewById(R.id.rvBookmark);

        List<Bookmark> data = new ArrayList<>();
        data.add(new Bookmark("Kwetiau Goreng", "Kwetiaw goreng yang gurih manis dengan bahan seadanya", "2-3 porsi", "27/09/24", R.drawable.kwetiaw));
        data.add(new Bookmark("Nasi Goreng", "Nasi goreng yang gurih manis dengan suiran ayam", "2-3 porsi", "05/12/24", R.drawable.nasi_goreng));
        data.add(new Bookmark("Ayam Laos", "Ayam laos dengan bumbu yang gurih ", "3 porsi", "21/07/24", R.drawable.ayam_laos));
        data.add(new Bookmark("Sop Daging", "Sop daging yang dilengkapi dengan beragam sayuran", "2 porsi", "14/07/24", R.drawable.sop_daging));
        data.add(new Bookmark("Tempe Mendoan", "Tempe goreng tepung yang gurih dan kriuk", "4-5 porsi", "27/06/2024", R.drawable.tempe_mendoan));
        data.add(new Bookmark("Telur Tomat", "Olahan telur dan tomat yang segar dan hangat cocok untuk cuaca dingin", "2-3 porsi", "13/06/24", R.drawable.telur_tomat));
        this.data = data;

        // Setup adapter dan RecyclerView
        this.adapter = new AdapterBookmark(this, data);
        this.rvBookmark.setAdapter(this.adapter);
        this.rvBookmark.setLayoutManager(
                new LinearLayoutManager(this)
        );
    }
}

