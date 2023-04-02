package es.ifp.playlist;

public class Audio {

    protected int id;
    protected String title;
    protected String url;

    public Audio(int id, String title, String url){
        this.id = id;
        this.title = title;
        this.url = url;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url = url;
    }

}
