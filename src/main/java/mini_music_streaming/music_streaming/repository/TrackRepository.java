package mini_music_streaming.music_streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mini_music_streaming.music_streaming.entity.TrackEntity;
import java.util.List;


public  interface TrackRepository extends JpaRepository<TrackEntity, Integer>
{
    public List<TrackEntity> findBySong(String song);

    public List<TrackEntity> findBySinger(String singer);

}
