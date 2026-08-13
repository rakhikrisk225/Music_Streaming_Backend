package mini_music_streaming.music_streaming.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mini_music_streaming.music_streaming.dto.UserDTO;
import mini_music_streaming.music_streaming.entity.PlaylistEntity;
import mini_music_streaming.music_streaming.entity.UserEntity;
import mini_music_streaming.music_streaming.exception.ResourceNotFoundException;
import mini_music_streaming.music_streaming.exception.UserAlreadyExistsException;
import mini_music_streaming.music_streaming.repository.UserRepository;

@Service
public class UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity createUser(UserEntity user)
    {
        logger.info(
            "Create user request for email {}",
            user.getEmail()
        );

        if(userRepository
            .findByEmail(user.getEmail())
            .isPresent())
        {
            logger.warn(
                "Duplicate email attempted {}",
                user.getEmail()
            );

            throw new UserAlreadyExistsException(
                    "Email already exists");
        }

        logger.debug("Encoding password");

        user.setPassword(
            passwordEncoder.encode(
                    user.getPassword()));

        if(user.getRole() == null)
        {
            logger.debug(
                    "Assigning default role");

            user.setRole("USER");
        }

        if(user.getPlaylist()!=null)
        {
            logger.debug(
                "Mapping playlists");

            for(PlaylistEntity playlist
                : user.getPlaylist())
            {
                playlist.setUser(user);
            }
        }

        UserEntity savedUser =
            userRepository.save(user);

        logger.info(
            "User created with id {}",
            savedUser.getId());

        return savedUser;
    }

    public List<UserEntity> getAllUsers()
    {
        return userRepository.findAll();
    }

   public UserEntity getUserById(Long id)
    {
        logger.info(
            "Fetching user id {}",
            id
        );

        return userRepository
            .findById(id)
            .orElseThrow(() -> {

                logger.error(
                    "User not found {}",
                    id
                );

                return new ResourceNotFoundException(
                        "User not found"
                );
        });
    }

    public String deleteUser(Long id)
    {
        logger.warn(
            "Deleting user {}",
            id
        );

        userRepository.deleteById(id);

        logger.info(
            "User deleted {}",
            id
        );

        return "User Deleted Successfully";
    }

    public UserEntity updateUser(
        Long id,
        UserEntity updatedUser)
    {
        logger.info(
        "Updating user {}",
        id
        );

        UserEntity existingUser =
            userRepository
                    .findById(id)
                    .orElse(null);

        if(existingUser!=null)
        {
            existingUser.setName(
                updatedUser.getName());

            existingUser.setContact(
                updatedUser.getContact());

            logger.info(
                "User updated {}",
                id
            );

            return userRepository
                .save(existingUser);
        }

        logger.warn(
            "Update failed. User not found {}",
            id
        );

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

    public UserDTO getUser(Long id) 
    {

        UserEntity user = userRepository.findById(id).orElseThrow(() ->new RuntimeException("User not found"));

        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        return dto;
    }
}
