package mini_music_streaming.music_streaming.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mini_music_streaming.music_streaming.dto.LoginRequestDTO;
import mini_music_streaming.music_streaming.dto.UserRegisterDTO;
import mini_music_streaming.music_streaming.entity.UserEntity;
import mini_music_streaming.music_streaming.exception.UserAlreadyExistsException;
import mini_music_streaming.music_streaming.repository.UserRepository;
import mini_music_streaming.music_streaming.security.JwtUtil;

@Service
public class AuthService
{
    private static final Logger logger =
        LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(LoginRequestDTO request)
    {
        logger.info(
        "Login attempt for {}",
        request.getEmail());

        UserEntity user =
        userRepository
        .findByEmail(request.getEmail())
        .orElseThrow(() -> {

        logger.warn(
            "Invalid login email {}",
            request.getEmail());

        return new RuntimeException(
            "Invalid Email or Password");
        });

        if(!passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()))
        {
            logger.warn(
            "Wrong password for {}",
            request.getEmail());

            throw new RuntimeException(
            "Invalid Email or Password");
        }

        logger.info(
            "Login successful {}",
            user.getEmail());

        return jwtUtil.generateToken(
            user.getEmail(),
            user.getRole());
    }

    public String register(UserRegisterDTO dto)
    {
        logger.info(
            "Register request for {}",
            dto.getEmail());

        if(userRepository.existsByEmail(
                dto.getEmail()))
        {
            logger.warn(
                "Duplicate registration {}",
                dto.getEmail());

            throw new UserAlreadyExistsException(
                "Email already exists");
        }

        UserEntity user =
            new UserEntity();

        user.setName(
            dto.getName());

        user.setContact(
            dto.getContact());

        user.setEmail(
            dto.getEmail());

        user.setPassword(
            passwordEncoder.encode(
                dto.getPassword()));

        user.setRole(
            dto.getRole());

        userRepository.save(
            user);

        logger.info(
            "User registered {}",
            user.getEmail());

        return
            "User Registered Successfully";
    }
}