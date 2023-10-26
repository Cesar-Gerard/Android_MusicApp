package model;

import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Album {


    int id;
    String name;
    String ImageUrl;

    String author;
    Date date;


    List<Song> consons_Album;


    public static List<Album> list_albums=null;

    public Album(int id, String name, String imageUrl, String author, Date date) {
        this.id = id;
        this.name = name;
        ImageUrl = imageUrl;
        this.author = author;
        this.date = date;
    }

    public Album(int id, String name, String imageUrl, String author, Date date, List<Song> consons_Album) {
        this.id = id;
        this.name = name;
        ImageUrl = imageUrl;
        this.author = author;
        this.date = date;
        this.consons_Album = consons_Album;
    }

    //#region Getters i Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.ImageUrl = null;
    }

    public List<Song> getConsons_Album() {
        return consons_Album;
    }

    public void setConsons_Album(List<Song> consons_Album) {
        this.consons_Album = consons_Album;
    }


    //#endregion




    public static List<Album> createList(){

        if(list_albums==null){

            list_albums = new ArrayList<>();
            list_albums.add(new Album(0,"21", "https://i1.sndcdn.com/artworks-000168814903-4vrfjc-t500x500.jpg","Adele",DateUtils.parseDayMonthYear("21/07/2003")));
            list_albums.add(new Album(1,"Greatest Hits", "https://i1.sndcdn.com/artworks-0594d6b29566c9686580e8d3560d253d1bc868d3-0-t500x500.jpg","Queen",DateUtils.parseDayMonthYear("08/09/2014")));
            list_albums.add(new Album(2,"Dancing Queen", "https://i1.sndcdn.com/artworks-HklzvIa2mO1dlBcF-OXKJqw-t500x500.jpg","ABBA",DateUtils.parseDayMonthYear("21/07/2003")));
            list_albums.add(new Album(3,"Coinicidir", "https://i1.sndcdn.com/artworks-suT8zKas8erE-0-t500x500.jpg","Macaco",DateUtils.parseDayMonthYear("21/07/2003")));
            list_albums.add(new Album(4,"Semells Like Teen Spirit", "https://i1.sndcdn.com/artworks-gojRubnOqDOn-0-t500x500.jpg","Nirvana",DateUtils.parseDayMonthYear("21/07/2003")));
            list_albums.add(new Album(5,"Life is Strange Soundtrack", "https://i1.sndcdn.com/artworks-000130535674-a4utz2-t500x500.jpg","Dolkins",DateUtils.parseDayMonthYear("21/07/2003")));


            return list_albums;
        }else{
            return list_albums;
        }


    }


    public static int getNewId(){

        Album last = list_albums.get(list_albums.size()-1);

        return last.getId()+1;

    }





    public static boolean Album_Realesed(String entrada){

        for(Album n : list_albums){

            if(n.getName().equals(entrada)){
                return true;
            }

        }

        return false;


    }



}
