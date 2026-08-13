package mini_music_streaming.music_streaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mini_music_streaming.music_streaming.dto.TrackDTO;
import mini_music_streaming.music_streaming.entity.TrackEntity;
import mini_music_streaming.music_streaming.service.TrackService;

@RestController
@RequestMapping("/track")
public class TrackController
{
    @Autowired
    private TrackService trackService;


    @GetMapping
    public List<TrackEntity> getAllTrack()
    {
        return trackService.getAllTracks();
    }

    @PostMapping
    public TrackEntity createTrack(@RequestBody TrackEntity track)
    {
        return trackService.createTrack(track);
    }

    @PutMapping("/update/{Id}")
    public TrackEntity updateTrack(@PathVariable Integer Id,@RequestBody TrackEntity updatedTrack)
    {
        return trackService.updateTrack(Id, updatedTrack);
    }

    @DeleteMapping("/delete/{Id}")
    public String deleteTrack(@PathVariable Integer Id)
    {
        return trackService.deleteTrack(Id);
    }

    @GetMapping("/song/{song}")
    public List<TrackEntity> findBySong(@PathVariable String song)
    {
        return trackService.findBySong(song);
    }

    @GetMapping("/{id}")
    public TrackDTO getTrack(@PathVariable Integer id) 
    {
        return trackService.getTrackById(id);
    }
}
