package mini_music_streaming.music_streaming.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mini_music_streaming.music_streaming.dto.UserDTO;
import mini_music_streaming.music_streaming.entity.UserEntity;
import mini_music_streaming.music_streaming.service.UserService;

@RestController
@RequestMapping("/user")
@Tag(name = "User APIs")
public class UserController
{
    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users")
    @GetMapping
    public List<UserEntity> getAllUser()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) 
    {
        return userService.getUser(id);
    }

    @Operation(
    summary = "Create User",
    description = "Creates a new user"
    )
    @PostMapping
    public UserEntity createUser(@Valid
                                @RequestBody UserEntity user)
    {
        return userService.createUser(user);
    }

    @PutMapping("/update/{Id}")
    public UserEntity updateUser(@PathVariable Long Id,@RequestBody UserEntity updatedUser)
    {
        return userService.updateUser(Id, updatedUser);
    }

    @DeleteMapping("/delete/{Id}")
    public String deleteUser(@PathVariable Long Id)
    {
        return userService.deleteUser(Id);
    }

    @GetMapping("/name/{name}")
    public List<UserEntity> getUserByName(@PathVariable String name)
    {
        return userService.getUserByName(name);
    }

    @GetMapping("/contact/{contact}")
    public List<UserEntity> getUserByContact(@PathVariable Long contact)
    {
        return userService.getUserByContact(contact);
    }

    @GetMapping("/top10")
    public List<UserEntity> getUser()
    {
        return userService.get10User();
    }

    @PostMapping("/insert/{name}/{contact}/{status}")
    public String insertUser(@PathVariable String name, @PathVariable long contact, @PathVariable("status") String userstatus)
    {
        userService.insertUser(name, contact, userstatus);

        return "User Inserted Successfully";
    }
}