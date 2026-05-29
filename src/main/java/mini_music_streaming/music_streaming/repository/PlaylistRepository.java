package mini_music_streaming.music_streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mini_music_streaming.music_streaming.entity.PlaylistEntity;

public  interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> 
{
    
}
