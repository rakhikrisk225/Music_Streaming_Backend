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

    private long Id;

    private String name;

    private long contact;
    
    private String userstatus;

    

    public long getId() {
        return Id;
    }

    public void setId(long id) {
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

    public void setContact(long contact) {
        this.contact = contact;
    }
    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) 
    {
        this.userstatus = userstatus;
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference("user_track")
    private List<TrackEntity> tracks;

    public List<TrackEntity> getTracks() 
    {
        return tracks;
    }

    public void setTracks(List<TrackEntity> tracks) 
    {
        this.tracks = tracks;
    }
}