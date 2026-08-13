package mini_music_streaming.music_streaming.dto;

import java.util.List;

public class PlaylistDTO 
{
    private Long id;
    private String playlist;
    private List<TrackDTO> tracks;

    public PlaylistDTO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPlaylist() {
        return playlist;
    }
    public void setPlaylist(String playlist) {
        this.playlist = playlist;
    }
    public List<TrackDTO> getTracks() {
        return tracks;
    }
    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }    
}
