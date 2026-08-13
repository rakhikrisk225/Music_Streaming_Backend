package mini_music_streaming.music_streaming.dto;

public class TrackDTO 
{
    private Integer id;
    private String song;
    private String singer;
    private String movie;
    private String songtype;

    
    public TrackDTO() {}


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSong() {
        return song;
    }
    public void setSong(String song) {
        this.song = song;
    }
    public String getSinger() {
        return singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }
    public String getMovie() {
        return movie;
    }
    public void setMovie(String movie) {
        this.movie = movie;
    }
    public String getSongtype() {
        return songtype;
    }
    public void setSongtype(String songtype) {
        this.songtype = songtype;
    }
}
