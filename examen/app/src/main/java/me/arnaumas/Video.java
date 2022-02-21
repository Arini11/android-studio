package me.arnaumas;

public class Video {
    private String titol;
    private String miniatura;
    private String id;

    public Video(){}

    public Video(String titol, String miniatura, String id) {
        this.titol = titol;
        this.miniatura = miniatura;
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getMiniatura() {
        return miniatura;
    }

    public void setMiniatura(String miniatura) {
        this.miniatura = miniatura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Video{" +
                "titol='" + titol + '\'' +
                ", miniatura='" + miniatura + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

