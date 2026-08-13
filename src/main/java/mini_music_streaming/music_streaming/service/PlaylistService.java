package mini_music_streaming.music_streaming.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini_music_streaming.music_streaming.dto.PlaylistDTO;
import mini_music_streaming.music_streaming.dto.TrackDTO;
import mini_music_streaming.music_streaming.entity.PlaylistEntity;
import mini_music_streaming.music_streaming.entity.TrackEntity;
import mini_music_streaming.music_streaming.entity.UserEntity;
import mini_music_streaming.music_streaming.repository.PlaylistRepository;
import mini_music_streaming.music_streaming.repository.TrackRepository;
import mini_music_streaming.music_streaming.repository.UserRepository;

@Service
public class PlaylistService 
{
    private static final Logger logger =
        LoggerFactory.getLogger(
                PlaylistService.class);

                
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private UserRepository userRepository;

    public PlaylistEntity createPlaylist(
    PlaylistEntity playlist)
    {
        logger.info(
        "Creating playlist {}",
        playlist.getPlaylist());

        PlaylistEntity saved =
        playlistRepository
        .save(playlist);

        logger.info(
        "Playlist created id {}",
        saved.getId());

        return saved;
    }

  public PlaylistEntity assignUserToPlaylist(
        Long playlistId,
        Long userId)
    {
        logger.info(
        "Assign user {} to playlist {}",
        userId,
        playlistId
        );

        PlaylistEntity playlist =
        playlistRepository
        .findById(playlistId)

        .orElseThrow(() -> {

            logger.warn(
                "Playlist not found {}",
                playlistId
            );

            return new RuntimeException(
                "Playlist not found"
            );
        });

        UserEntity user =
        userRepository
        .findById(userId)

        .orElseThrow(() -> {

            logger.warn(
                "User not found {}",
                userId
            );

            return new RuntimeException(
                "User not found"
            );
        });

        playlist.setUser(user);

        PlaylistEntity saved =
        playlistRepository
        .save(playlist);

        logger.info(
        "User {} assigned to playlist {}",
        userId,
        playlistId
        );

        return saved;
    }

    public PlaylistEntity assignTrackToPlaylist(Long playlistId, Integer trackId)
    {
        logger.info(
            "Assign track {} to playlist {}",
                    trackId,
                    playlistId);

        PlaylistEntity playlist =
            playlistRepository
                .findById(playlistId)

                .orElseThrow(() -> 
                {

                    logger.warn(
                    "Playlist not found {}",
                    playlistId);

                    return new RuntimeException(
                    "Playlist not found");

                });

        TrackEntity track =
        trackRepository
        .findById(trackId)

        .orElseThrow(() -> 
        {

            logger.warn(
            "Track not found {}",
            trackId);

            return new RuntimeException(
                "Track not found");

        });

        playlist.getTracks()
            .add(track);

        logger.info(
            "Track added successfully");

        return playlistRepository
            .save(playlist);
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

    public PlaylistDTO getPlaylist(
            Long id) {

        PlaylistEntity playlist =
                playlistRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Playlist not found"));

        PlaylistDTO dto =
                new PlaylistDTO();

        dto.setId(
                playlist.getId());

        dto.setPlaylist(
                playlist.getPlaylist());

        dto.setTracks(

            playlist.getTracks()
            .stream()
            .map(track -> {

                TrackDTO t =
                        new TrackDTO();

                t.setId(
                        track.getId());

                t.setSong(
                        track.getSong());

                t.setSinger(
                        track.getSinger());

                t.setMovie(
                        track.getMovie());


                t.setSongtype(
                        track.getSongtype());

                return t;

            }).collect
            (
                Collectors.toList()
            )
        );

        return dto;
    }
}

