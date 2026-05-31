package mini_music_streaming.music_streaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini_music_streaming.music_streaming.entity.PlaylistEntity;
import mini_music_streaming.music_streaming.entity.TrackEntity;
import mini_music_streaming.music_streaming.repository.PlaylistRepository;
import mini_music_streaming.music_streaming.repository.TrackRepository;

@Service
public class TrackService 
{
    @Autowired
    private TrackRepository trackRepository;  

    @Autowired
    private PlaylistRepository playlistRepository;
    
    public TrackEntity createTrack(TrackEntity track)
    {
        TrackEntity savedTrack = trackRepository.save(track);

        if(track.getPlaylist() != null)
        {
            for(PlaylistEntity p : track.getPlaylist())
            {
                PlaylistEntity playlist =playlistRepository.findById(p.getId()).orElseThrow();

                playlist.getTracks().add(savedTrack);

                playlistRepository.save(playlist);
            }
    
        }   
             return savedTrack;
    }

    public List<TrackEntity> getAllTracks()
    {
        return trackRepository.findAll();
    }

    public TrackEntity getTrackById(Integer Id)
    {
        return trackRepository.findById(Id).orElse(null);
    }

    public String deleteTrack(Integer Id)
    {
        trackRepository.deleteById(Id);
        return "Track Deleted Successfully";
    } 

    public TrackEntity updateTrack(Integer Id, TrackEntity updatedsong)
    {
        TrackEntity existingSong = trackRepository.findById(Id).orElse(null);

        if (existingSong != null) {

            existingSong.setSong(updatedsong.getSong());

            return trackRepository.save(existingSong);
        }

        return null;
    }

    public List<TrackEntity> findBySong(String song)
    {
        return trackRepository.findBySong(song);
    }


}
