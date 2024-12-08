package pa.pam.projectpam;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterBookmark extends RecyclerView.Adapter<AdapterBookmark.BookmarkVH> {
    private final Context ctx;
    private final List<Bookmark> data;

    public AdapterBookmark(Context ctx, List<Bookmark> data) {
        this.ctx = ctx;
        this.data = data;
    }

    public class BookmarkVH extends RecyclerView.ViewHolder{

        private final TextView tvNama;
        private final TextView tvDeskripsi;
        private final TextView tvPorsi;
        private final TextView tvTanggal;
        private final ImageView ivGambar;
        private final ImageView btDel;

        public BookmarkVH(@NonNull View itemView) {
            super(itemView);
            this.tvNama = itemView.findViewById(R.id.tvNama);
            this.tvDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
            this.tvPorsi = itemView.findViewById(R.id.tvPorsi);
            this.tvTanggal = itemView.findViewById(R.id.tvTanggal);
            this.ivGambar = itemView.findViewById(R.id.ivGambar);
            this.btDel = itemView.findViewById(R.id.btnDeleteResep);
        }
    }

    @NonNull
    @Override
    public BookmarkVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(this.ctx)
                .inflate(R.layout.rowview_bookmark, parent, false);
        return new BookmarkVH(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkVH holder, @SuppressLint("RecyclerView") int position) {
        Bookmark bookmark = data.get(position);
        holder.tvNama.setText(bookmark.nama);
        holder.tvPorsi.setText(bookmark.porsi);
        holder.tvDeskripsi.setText(bookmark.deskripsi);
        holder.tvTanggal.setText(bookmark.tanggal);
        holder.ivGambar.setImageResource(bookmark.gambar);

        holder.btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menyimpan nama resep untuk ditampilkan di toast
                String namaResep = data.get(position).nama;

                // Menghapus item dari data
                data.remove(position);

                // Menampilkan toast konfirmasi
                Toast.makeText(ctx, "Resep '" + namaResep + "' telah dihapus", Toast.LENGTH_SHORT).show();

                // Memperbarui tampilan RecyclerView
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, data.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}