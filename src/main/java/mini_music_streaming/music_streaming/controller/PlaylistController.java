package mini_music_streaming.music_streaming.controller;

import mini_music_streaming.music_streaming.dto.PlaylistDTO;
import mini_music_streaming.music_streaming.entity.PlaylistEntity;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
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

    @GetMapping("/{id}")
    public PlaylistDTO getPlaylist(@PathVariable Long id) 
    {
        return playlistService.getPlaylist(id);
    }

    @PostMapping
    public PlaylistEntity createPlaylist(@Valid @RequestBody PlaylistEntity playlist)
    {
        return playlistService.createPlaylist(playlist);
    }

    @PutMapping("/{playlistId}/user/{userId}")
    public PlaylistEntity assignUserToPlaylist(@PathVariable Long playlistId,@PathVariable Long userId)
    {
        return playlistService.assignUserToPlaylist(playlistId, userId);
    }

    @PutMapping("/{playlistId}/track/{trackId}")
    public PlaylistEntity assignTrackToPlaylist(@PathVariable Long playlistId,@PathVariable Integer trackId)
    {
        return playlistService.assignTrackToPlaylist(playlistId, trackId);
    }
 
    @PutMapping("/{Id}")
    public PlaylistEntity updatePlaylist(@PathVariable Long Id,@RequestBody PlaylistEntity updatedplaylist)
    {
        return playlistService.updatePlaylist(Id, updatedplaylist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlaylist(
        @PathVariable Long id)
    {
        playlistService.deletePlaylist(id);

        return ResponseEntity.ok(
        "Playlist Deleted Successfully");
    }
}
