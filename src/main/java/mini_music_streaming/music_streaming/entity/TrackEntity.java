package mini_music_streaming.music_streaming.entity;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="track")
public class TrackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String song;

    private String songtype;

    private Long duration;

    private String singer;

    private String movie;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getSongtype() {
        return songtype;
    }

    public void setSongtype(String songtype) {
        this.songtype = songtype;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
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

    public void setMovie(String movie) 
    {
        this.movie = movie;
    }

    //Many to Many Mapping with PlaylistEntity.
    @ManyToMany(mappedBy = "tracks")
    @JsonIgnore
    private List<PlaylistEntity>playlist= new ArrayList<>();


    public List<PlaylistEntity> getPlaylist() 
    {
        return playlist;
    }

    public void setPlaylist(List<PlaylistEntity> playlist) 
    {
        this.playlist = playlist;
    }


}    
