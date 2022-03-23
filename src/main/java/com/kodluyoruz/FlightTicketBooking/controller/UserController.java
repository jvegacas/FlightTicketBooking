package com.kodluyoruz.FlightTicketBooking.controller;

import com.kodluyoruz.FlightTicketBooking.model.dto.UserDto;
import com.kodluyoruz.FlightTicketBooking.model.request.CreateUpdateUserRequest;
import com.kodluyoruz.FlightTicketBooking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody CreateUpdateUserRequest request){
        return service.createUser(request);
    }
    @GetMapping("{id}")
    public UserDto getUser(@PathVariable int id) {
        return service.getUserById(id);
    }


    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody CreateUpdateUserRequest request){
        return service.updateUser(id,request);
    }

    @GetMapping
    public List<UserDto> getUserList() {
        return service.getUserList();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable  int id){
        service.deleteUser(id);
    }


}
