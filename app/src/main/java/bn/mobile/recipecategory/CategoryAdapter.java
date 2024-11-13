package bn.mobile.recipecategory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter {

    private final List<Category> data;
    private final Context context;

    public CategoryAdapter(List<Category> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.rowview, parent, false);
        return new CategoryVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = this.data.get(position);
        CategoryVH vh = (CategoryVH) holder;
        vh.tvCategory.setText(category.getName());
        vh.ivCategory.setImageResource(category.getImageResourceId());

        vh.itemView.setOnClickListener(v -> Toast.makeText(context, "Ayo pilih resep " + category.getName() + " yang ingin kamu masak!", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class CategoryVH extends ViewHolder {

        private final TextView tvCategory;
        private final ImageView ivCategory;

        public CategoryVH(View itemView) {
            super(itemView);
            this.tvCategory = itemView.findViewById(R.id.tvCategory);
            this.ivCategory = itemView.findViewById(R.id.ivCategory);
        }
    }
}
