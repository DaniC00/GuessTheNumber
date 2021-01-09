package com.example.guessthenumber;



import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    ArrayList<Player> listPlayers;

    public CustomAdapter(ArrayList<Player> listPlayers) {
        this.listPlayers = listPlayers;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {

        holder.asignarDatos(listPlayers.get(position));

    }

    @Override
    public int getItemCount() {
        return listPlayers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView intentos;
        ImageView miniatura;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.txtNombreContent);
            intentos = (TextView) itemView.findViewById(R.id.txtIntentosContent);

        }

        public void asignarDatos(Player player) {

            nombre.setText(player.get_name());
            intentos.setText(String.valueOf(player.get_intents()));

        }
    }

}
