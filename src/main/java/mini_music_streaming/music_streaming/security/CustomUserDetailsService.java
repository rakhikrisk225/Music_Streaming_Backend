package mini_music_streaming.music_streaming.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mini_music_streaming.music_streaming.entity.UserEntity;
import mini_music_streaming.music_streaming.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
    {
        UserEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> 
                    new UsernameNotFoundException("User not found"));

        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(
                    new SimpleGrantedAuthority("ROLE_"+ user.getRole())
                )
        );
    }
}
