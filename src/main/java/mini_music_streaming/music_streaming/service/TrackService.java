package mini_music_streaming.music_streaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini_music_streaming.music_streaming.dto.TrackDTO;
import mini_music_streaming.music_streaming.entity.TrackEntity;
import mini_music_streaming.music_streaming.repository.TrackRepository;

@Service
public class TrackService 
{
    @Autowired
    private TrackRepository trackRepository;  

    
    public TrackEntity createTrack(TrackEntity track) 
    {
        return trackRepository.save(track);
    }
    public List<TrackEntity> getAllTracks()
    {
        return trackRepository.findAll();
    }

    /**public TrackEntity getTrackById(Integer Id)
    {
        return trackRepository.findById(Id).orElse(null);
    }*/

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

    public TrackDTO getTrackById(Integer id)
    {
        TrackEntity track =trackRepository.findById(id).orElseThrow(() ->new RuntimeException("Track not found"));

        TrackDTO dto =new TrackDTO();

        dto.setId(track.getId());
        dto.setSong(track.getSong());
        dto.setSinger(track.getSinger());
        dto.setMovie(track.getMovie());
        dto.setSongtype(track.getSongtype());

        return dto;
    }
}


