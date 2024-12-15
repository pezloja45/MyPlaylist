package com.pezloja.myplaylist;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorCancion extends RecyclerView.Adapter<AdaptadorCancion.HolderCanciones> {
    List<Cancion> dataSet;
    Context contexto;

    public AdaptadorCancion(List<Cancion> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public AdaptadorCancion.HolderCanciones onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        contexto = parent.getContext();
        View view = LayoutInflater.from(contexto).inflate(R.layout.lista, parent, false);
        return new HolderCanciones(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCancion.HolderCanciones holder, int position) {
        Cancion c = dataSet.get(position);
        holder.str_titulo.setText(c.getTitulo());
        holder.str_autor.setText(c.getAutor());
        holder.imagen.setImageResource(c.getImagen());

        holder.sk_musica.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int posicion, boolean fromUser) {
                if (fromUser) { // 'b' es true si el cambio de progreso fue iniciado por el usuario
                    GestionCanciones.mover(posicion);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        holder.btn_play.setOnClickListener(view -> {
            GestionCanciones.startCancion(c, contexto, holder.sk_musica);
        });
        holder.btn_pause.setOnClickListener(view -> {
            GestionCanciones.pauseCancion();
        });
        holder.btn_stop.setOnClickListener(view -> {
            GestionCanciones.stopCancion(holder.sk_musica);
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class HolderCanciones extends RecyclerView.ViewHolder {
        TextView str_titulo, str_autor;
        ImageView imagen, btn_play, btn_pause, btn_stop;
        SeekBar sk_musica;

        public HolderCanciones(@NonNull View itemView) {
            super(itemView);
            str_titulo = itemView.findViewById(R.id.str_titulo);
            str_autor = itemView.findViewById(R.id.str_autor);
            imagen = itemView.findViewById(R.id.imagen);
            btn_play = itemView.findViewById(R.id.btn_play);
            btn_pause = itemView.findViewById(R.id.btn_pause);
            btn_stop = itemView.findViewById(R.id.btn_stop);
            sk_musica = itemView.findViewById(R.id.sk_musica);
        }
    }
}