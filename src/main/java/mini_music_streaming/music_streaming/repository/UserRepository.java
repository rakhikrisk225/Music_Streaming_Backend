package mini_music_streaming.music_streaming.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import mini_music_streaming.music_streaming.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> 
{
    Optional<UserEntity>findByEmail(String email);
    boolean existsByEmail(String email);

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
        
}
