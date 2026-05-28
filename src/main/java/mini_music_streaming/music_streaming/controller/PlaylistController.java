package mini_music_streaming.music_streaming.controller;

import mini_music_streaming.music_streaming.entity.PlaylistEntity;
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

import mini_music_streaming.music_streaming.service.PlaylistService;

@RestController
@RequestMapping("/playlist")
public class PlaylistController
{
    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public List<PlaylistEntity>getAllPlaylist()
    {
        return playlistService.getAllPlaylist();
    }

    @PostMapping
    public PlaylistEntity createTrack(@RequestBody PlaylistEntity playlist)
    {
        return playlistService.createPlaylist(playlist);
    }

    @PutMapping("/update/{Id}")
    public PlaylistEntity updateplaylist(@PathVariable Long Id,@RequestBody PlaylistEntity updatedplaylist)
    {
        return playlistService.updatePlaylist(Id, updatedplaylist);
    }

    @DeleteMapping("/delete/{Id}")
    public String deletePlaylist(@PathVariable Long Id)
    {
        return playlistService.deletePlaylist(Id);
    }
}
