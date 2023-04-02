package es.ifp.playlist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseSQL extends SQLiteOpenHelper {

    protected SQLiteDatabase db;

    public DataBaseSQL(Context context) {
        super(context,"audio",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE media (id integer primary key autoincrement not null, title text, url text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS media");
    }

    public void insertAudio(String title, String url){
        db = this.getReadableDatabase();
        db.execSQL("INSERT INTO media (title, url) VALUES('"+title+"','"+url+"')");
    }

    public Audio getAudio(int id){
        Audio audio = null;
        //String contenido = "";
        Cursor res = null;
        db = this.getReadableDatabase();
        res = db.rawQuery("SELECT * FROM media WHERE id="+id+" ", null);
        res.moveToFirst();
        while (res.isAfterLast() ==  false){
            //contenido = res.getInt(res.getColumnIndex("id")) + ".-" + res.getString(res.getColumnIndex("title") + ".-" + res.getString(res.getColumnIndex("url"));
            audio = new Audio(res.getInt(res.getColumnIndex("id")), res.getString(res.getColumnIndex("title")), res.getString(res.getColumnIndex("url")));
            res.moveToNext();
        }
        return audio;
    }
    public ArrayList<String> getAllAudios(){
        ArrayList<String> filas = new ArrayList<String>();
        String contenido = "";
        Cursor res = null;
        db = this.getReadableDatabase();
        res = db.rawQuery("SELECT * FROM media ORDER BY id ASC", null);
        res.moveToFirst();
        while (res.isAfterLast() ==  false){
            contenido = res.getInt(res.getColumnIndex("id")) + ".-" + res.getString(res.getColumnIndex("title"));
            filas.add(contenido);
            res.moveToNext();
        }
        return filas;
    }
}
