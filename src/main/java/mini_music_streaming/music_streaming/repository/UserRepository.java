package mini_music_streaming.music_streaming.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mini_music_streaming.music_streaming.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> 
{

    public List<UserEntity>findByName(String name);

    public List<UserEntity>findByContact(Long contact);
     
    
    @Query(value = "select * from user",nativeQuery = true)
    public List<UserEntity>getAllUser();

    @Query(value = "select * from user where name =:name",nativeQuery = true)
    public List<UserEntity>getUserByName(@Param("name") String name);

    @Query(value="select * from user LIMIT 10",nativeQuery = true)
    public List<UserEntity>get10user();

    @Modifying
    @Transactional
    @Query(value = "insert into user (name, contact, userstatus) VALUES(:name, :contact, :userstatus)",nativeQuery = true)
    void insertUser(@Param("name") String name,
                    @Param("contact") Long contact,
                    @Param("userstatus") String userstatus);

    
    /**@Query(value = """
        SELECT
            u.id AS user_id,
            u.name AS user_name,
            p.id AS playlist_id,
            p.playlist AS playlist_name,
            t.id AS track_id,
            t.song,
            t.singer,
            t.movie,
            t.duration
            FROM user u
            INNER JOIN playlist p
            ON u.id = p.user_id
            INNER JOIN playlist_track pt
            ON p.id = pt.playlist_id
            INNER JOIN track t
            ON pt.track_id = t.id
            WHERE u.id = ?1
            """,
            nativeQuery = true)
            List<Object[]> getUserPlaylistTrackDetails(Long userId);*/


    /**@Query(value ="SELECT u.id, u.name, p.playlist, t.song " +"FROM user u " +"JOIN playlist p ON u.id = p.user_id " +
        " JOIN track t ON p.track_id = t.id " +
        "WHERE u.id = :userId",
        nativeQuery = true)
        List<Object[]> getUserPlaylistTrackDetails(@Param("userId") Long userId);*/

        @Query(value = """
        SELECT
        u.id, u.name, p.playlist, t.song, t.singer, t.songtype FROM user u
        JOIN playlist p ON u.id = p.user_id JOIN playlist_track pt ON p.id = pt.playlist_id JOIN track t ON pt.track_id = t.id 
        WHERE u.id = :userId """, nativeQuery = true)
        List<Object[]> getUserPlaylistTrackDetails(
        @Param("userId") Long userId);
}
