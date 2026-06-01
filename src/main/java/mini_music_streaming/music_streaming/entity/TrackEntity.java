package mini_music_streaming.music_streaming.entity;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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

    private String createdby;

    @CreationTimestamp
    @Column(name="created_date",updatable = false)
    private LocalDateTime created_date;

    

    @CreationTimestamp
    @Column(name = "updated_on",updatable = false)
    private LocalDateTime updated_on;

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

    public String getCreatedby() 
    {
        return createdby;
    }

    public void setCreatedby(String createdby) 
    {
        this.createdby = createdby;
    }

    public LocalDateTime getCreated_date() 
    {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) 
    {
        this.created_date = created_date;
    }

    public LocalDateTime getUpdated_on() 
    {
        return updated_on;
    }

    public void setUpdated_on(LocalDateTime updated_on) 
    {
        this.updated_on = updated_on;
    }

}