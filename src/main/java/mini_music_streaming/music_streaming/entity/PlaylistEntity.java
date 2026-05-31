package mini_music_streaming.music_streaming.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="playlist")
public class PlaylistEntity 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String playlist;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) 
    {
        this.playlist = playlist;
    }
    

   // Many playlists belong to one user
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference("user_playlist")
    private UserEntity user;

    public UserEntity getUser() 
    {
        return user;
    }

    public void setUser(UserEntity user) 
    {
        this.user = user;
    }

    //Many to Many relationship with Tack
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    (
        name="playlist_track",
        joinColumns = @JoinColumn(name="playlist_id"),
        inverseJoinColumns = @JoinColumn(name="track_id")
    )
    private List<TrackEntity>tracks;


    public List<TrackEntity> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackEntity> tracks) {
        this.tracks = tracks;
    }
}
