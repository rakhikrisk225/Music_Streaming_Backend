package mini_music_streaming.music_streaming.entity;


import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

    private String createdby;

    @CreationTimestamp
    @Column(name="created_date",updatable = false)
    private LocalDateTime created_date;

    @CreationTimestamp
    @Column(name = "updated_on",updatable = false)
    private LocalDateTime updated_on;

     public Long getId() 
     {
        return Id;
    }

    public void setId(Long id) 
    {
        Id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public long getContact() 
    {
        return contact;
    }

    public void setContact(Long contact) 
    {
        this.contact = contact;
    }
    public String getUserstatus() 
    {
        return userstatus;
    }

    public void setUserstatus(String userstatus) 
    {
        this.userstatus = userstatus;
    }

    public String getCreatedby() 
    {
        return createdby;
    }

    public void setCreatedby(String createdby) 
    {
        this.createdby = createdby;
    }

    public LocalDateTime getCreated_date() 
    {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) 
    {
        this.created_date = created_date;
    }

    public LocalDateTime getUpdated_on() 
    {
        return updated_on;
    }

    public void setUpdated_on(LocalDateTime updated_on) 
    {
        this.updated_on = updated_on;
    }


    //One to Many Mapping with user to playlistEntity.

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference("user_playlist")
    private List<PlaylistEntity>playlist;

    public List<PlaylistEntity> getPlaylist() 
    {
        return playlist;
    }

    public void setPlaylist(List<PlaylistEntity> playlist) 
    {
        this.playlist = playlist;
    }
    
}