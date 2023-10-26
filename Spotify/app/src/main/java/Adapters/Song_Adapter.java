package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotify.R;

import java.util.List;

import model.Song;

public class Song_Adapter extends RecyclerView.Adapter<Song_Adapter.ViewHolder> {

    private List<Song> cansons;
    private Context context;



    public Song_Adapter(List<Song> cansons, Context context) {
        this.cansons = cansons;
        this.context = context;
    }

    @NonNull
    @Override
    public Song_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.canso_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Song_Adapter.ViewHolder holder, int position) {



            Song a = cansons.get(position);

            holder.id.setText(String.valueOf(a.getId()));
            holder.name.setText(a.getName());
            holder.time.setText(a.getTime());

            if (a.isLike() == true) {
                holder.fav.setImageResource(R.drawable.song_favourite);
            } else {
                holder.fav.setImageResource(R.drawable.song_nonfavourite);
            }

            holder.fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (a.isLike() == true) {
                        startHeartBeatAnimation(holder);
                        holder.fav.setImageResource(R.drawable.song_nonfavourite);
                        a.setLike(false);
                    } else {
                        startHeartBeatAnimation(holder);
                        holder.fav.setImageResource(R.drawable.song_favourite);
                        a.setLike(true);
                    }

                }
            });







    }

    @Override
    public int getItemCount() {
        if(cansons!=null){
            return cansons.size();
        }else{
            return 0;
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView fav;
        TextView id;
        TextView name;

        TextView time;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fav=itemView.findViewById(R.id.imfav);
            id = itemView.findViewById(R.id.idSong);
            name = itemView.findViewById(R.id.txvNameSong);
            time = itemView.findViewById(R.id.txvTimeSong);

        }
    }



    private void startHeartBeatAnimation(ViewHolder holder) {
        // Cargar la animación desde el recurso de animación
        Animation pulseAnimation = AnimationUtils.loadAnimation(context, R.anim.heart_pulse);

        // Iniciar la animación en el botón
        holder.fav.startAnimation(pulseAnimation);
    }


}
