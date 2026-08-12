package mini_music_streaming.music_streaming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mini_music_streaming.music_streaming.dto.LoginRequestDTO;
import mini_music_streaming.music_streaming.dto.UserRegisterDTO;
import mini_music_streaming.music_streaming.dto.AuthResponseDTO;
import mini_music_streaming.music_streaming.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String  register(@RequestBody UserRegisterDTO dto)
    {
        return authService.register(dto);
    }
    
    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO request)
    {
        String token = authService.login(request);

        AuthResponseDTO response=new AuthResponseDTO();

        response.setToken(token);
        return response;
    }
}
