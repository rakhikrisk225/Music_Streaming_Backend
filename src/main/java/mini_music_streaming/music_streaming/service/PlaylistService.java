package mini_music_streaming.music_streaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini_music_streaming.music_streaming.entity.PlaylistEntity;
import mini_music_streaming.music_streaming.repository.PlaylistRepository;

@Service
public class PlaylistService 
{
    @Autowired
    private PlaylistRepository playlistRepository;

    
    public PlaylistEntity createPlaylist(PlaylistEntity playlist)
    {
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
