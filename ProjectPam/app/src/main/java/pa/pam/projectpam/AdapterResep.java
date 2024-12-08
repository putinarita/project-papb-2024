package pa.pam.projectpam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterResep extends RecyclerView.Adapter<AdapterResep.ResepVH> {
    private final Context ctx;
    private final List<Resep> dataset;
    private final OnEditClickListener editListener;

    public interface OnEditClickListener {
        void onEditClick(Resep resep, String pilihan);
    }

    public AdapterResep(Context ctx, List<Resep> dataset, OnEditClickListener editListener) {
        this.ctx = ctx;
        this.dataset = dataset;
        this.editListener = editListener;
    }

    public class ResepVH extends RecyclerView.ViewHolder {
        private final TextView tvNama;
        private final TextView tvPorsi;
        private final TextView tvTanggal;
        private final ImageView btEdit;
        private final ImageView btDelete;


        public ResepVH(@NonNull View itemView) {
            super(itemView);
            this.tvNama = itemView.findViewById(R.id.tvNama);
            this.tvPorsi = itemView.findViewById(R.id.tvPorsi);
            this.tvTanggal = itemView.findViewById(R.id.tvTanggal);
            this.btEdit = itemView.findViewById(R.id.btEdit);
            this.btDelete = itemView.findViewById(R.id.btDelete);

            btEdit.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    editListener.onEditClick(dataset.get(position), "Edit");
                }
            });

            btDelete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    editListener.onEditClick(dataset.get(position), "Delete");
                }
            });
        }
    }

    @NonNull
    @Override
    public ResepVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(this.ctx)
                .inflate(R.layout.rowview, parent, false);
        return new ResepVH(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResepVH holder, int position) {
        Resep r = this.dataset.get(position);
        holder.tvNama.setText(r.getNama());
        holder.tvPorsi.setText(r.getPorsi());
        holder.tvTanggal.setText(r.getTanggal());
    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }
}
