package pa.pam.projectpam;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteResep extends AppCompatActivity {

    private AdapterResep adapter;
    private FirebaseDatabase db;
    private DatabaseReference appDb;

    public void showDialogDelete(Resep resep, Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_delete_resep);
        Button btDelNo = dialog.findViewById(R.id.btDelNo);
        Button btDelYes = dialog.findViewById(R.id.btDelYes);
        db = FirebaseDatabase.getInstance("https://projectakhirpam-f5bb3-default-rtdb.asia-southeast1.firebasedatabase.app/");
        appDb = db.getReference("resepq");
        String resepId = resep.getId();
        btDelNo.setOnClickListener(v -> {dialog.dismiss();});
        btDelYes.setOnClickListener(v -> {
            appDb.child(resepId).removeValue()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Resep berhasil dihapus", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Gagal menghapus resep", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(e -> {
                        Toast.makeText(context, "Gagal Menghapus", Toast.LENGTH_SHORT).show();
                    });
            dialog.dismiss();
            if (context instanceof UpdateResepActivity) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}