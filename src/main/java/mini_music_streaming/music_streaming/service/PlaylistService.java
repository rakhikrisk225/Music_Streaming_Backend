package mini_music_streaming.music_streaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini_music_streaming.music_streaming.entity.PlaylistEntity;
import mini_music_streaming.music_streaming.entity.TrackEntity;
import mini_music_streaming.music_streaming.entity.UserEntity;
import mini_music_streaming.music_streaming.repository.PlaylistRepository;
import mini_music_streaming.music_streaming.repository.TrackRepository;
import mini_music_streaming.music_streaming.repository.UserRepository;

@Service
public class PlaylistService 
{
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private UserRepository userRepository;

    public PlaylistEntity createPlaylist(PlaylistEntity playlist)
    {
        return playlistRepository.save(playlist);
    }

    public PlaylistEntity assignUserToPlaylist(Long playlistId, Long userId)
    {
        PlaylistEntity playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found"));

        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        playlist.setUser(user);

        return playlistRepository.save(playlist);
    }

    public PlaylistEntity assignTrackToPlaylist(Long playlistId, Integer trackId)
    {
        PlaylistEntity playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found"));

        TrackEntity track = trackRepository.findById(trackId).orElseThrow(() -> new RuntimeException("Track not found"));

        playlist.getTracks().add(track);

        return playlistRepository.save(playlist);
    }

    public List<PlaylistEntity>getAllPlaylist()
    {
        return playlistRepository.findAll();
    }

    public PlaylistEntity getPlaylistById(Long Id)
    {
        return playlistRepository.findById(Id).orElse(null);
    }

    public String deletePlaylist(Long Id)
    {
        playlistRepository.deleteById(Id);
        return "Playlist Deleted Successfully";
    } 

    public PlaylistEntity updatePlaylist(Long Id, PlaylistEntity updatedPlaylist)
    {
        PlaylistEntity existingPlaylist = playlistRepository.findById(Id).orElse(null);

        if (existingPlaylist != null) 
        {
            existingPlaylist.setPlaylist(updatedPlaylist.getPlaylist());

            return playlistRepository.save(existingPlaylist);
        }

        return null;
    }
}
