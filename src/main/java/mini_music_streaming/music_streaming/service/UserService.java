package mini_music_streaming.music_streaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mini_music_streaming.music_streaming.entity.PlaylistEntity;
import mini_music_streaming.music_streaming.entity.UserEntity;
import mini_music_streaming.music_streaming.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public UserEntity createUser(UserEntity user)
    {
        if(user.getPlaylist() != null)
            {
                for(PlaylistEntity playlist : user.getPlaylist())
                {
                    playlist.setUser(user);
                }
            }
        return userRepository.save(user);
    }

    public List<UserEntity> getAllUsers()
    {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long Id)
    {
        return userRepository.findById(Id).orElse(null);
    }

    public String deleteUser(Long Id)
    {
        userRepository.deleteById(Id);
        return "User Deleted Successfully";
    } 

    public UserEntity updateUser(Long Id, UserEntity updatedUser)
    {
        UserEntity existingUser = userRepository.findById(Id).orElse(null);

        if (existingUser != null) 
        {
            // update fields
            existingUser.setName(updatedUser.getName());
            existingUser.setContact(updatedUser.getContact());

            // save updated user
            return userRepository.save(existingUser);
        }

        return null;
    }

    public List<UserEntity> getUserByName(String name)
    {
        return userRepository.findByName(name);
    }

    public List<UserEntity> getUserByContact(Long contact)
    {
        return userRepository.findByContact(contact);
    }

    public List<UserEntity> get10User()
    {
        return userRepository.get10user();
    }

    public void insertUser(String name, long contact, String userstatus)
    {
        userRepository.insertUser(name, contact, userstatus);
    }
}
