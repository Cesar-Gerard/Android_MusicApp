package Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spotify.MyMusic;
import com.example.spotify.R;
import com.example.spotify.llista_cansons;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.util.List;

import model.Album;
import model.DateUtils;

public class Album_Adapter extends RecyclerView.Adapter<Album_Adapter.ViewHolder>{

    private List<Album> albums;
    MyMusic context;







    public Album_Adapter(List<Album> albums,MyMusic a) {
        this.albums = albums;
        context=a;
    }

    @NonNull
    @Override
    public Album_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Album_Adapter.ViewHolder holder, int position) {
        Album a = albums.get(position);

        holder.titlealbum.setText(a.getName());
        holder.authoralbum.setText(a.getAuthor());
        holder.datealbum.setText(DateUtils.formatDateToDayMonthYear(a.getDate()));


        if(a.getBitmap()!=null){
            holder.imgalbum.setImageBitmap(a.getBitmap());
        }else{
            holder.imgalbum.setImageResource(R.drawable.not_found);
            ImageLoader iL = ImageLoader.getInstance();
            iL.loadImage(a.getImageUrl(), new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    Bitmap bitmap = BitmapFactory.decodeFile(imageUri);

                    float scale = context.getResources().getDisplayMetrics().density;

                    int widthInPx = (int) (120 * scale);
                    int heightInPx = (int) (140 * scale);


                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, widthInPx, heightInPx, true);


                    holder.imgalbum.setImageBitmap(scaledBitmap);

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {





                    a.setBitmap(loadedImage);
                    holder.imgalbum.setImageBitmap(loadedImage);

                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });

        }


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                a.setSelected(true);
                ((AppCompatActivity)context.getContext()).startSupportActionMode(context.actionModeCallback);

                return true;
            }

        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llista_cansons.entrada=a;

                Navigation.findNavController(v).navigate(R.id.action_global_llista_cansons);

            }


        });





    }




    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgalbum;
        public TextView titlealbum;
        public TextView authoralbum;
        public TextView datealbum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgalbum=itemView.findViewById(R.id.AlbumImage);
            titlealbum=itemView.findViewById(R.id.AlbumName);
            authoralbum=itemView.findViewById(R.id.AuthorName);
            datealbum=itemView.findViewById(R.id.RealeseDate);

        }
    }





}
