package mini_music_streaming.music_streaming.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="playlist")
public class PlaylistEntity 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    private String playlist;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPlaylist() {
        return playlist;
    }

    public void setPlaylist(String playlist) 
    {
        this.playlist = playlist;
    }

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
