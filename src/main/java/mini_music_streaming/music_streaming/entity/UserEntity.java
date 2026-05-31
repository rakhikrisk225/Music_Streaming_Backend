package mini_music_streaming.music_streaming.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity 
{

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)

    private Long Id;

    private String name;

    private Long contact;
    
    private String userstatus;

    

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }
    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) 
    {
        this.userstatus = userstatus;
    }


    //One to Many Mapping with user to playlistEntity. 
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference("user_playlist")
    private List<PlaylistEntity>playlist;



    public List<PlaylistEntity> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<PlaylistEntity> playlist) {
        this.playlist = playlist;
    }
    
}