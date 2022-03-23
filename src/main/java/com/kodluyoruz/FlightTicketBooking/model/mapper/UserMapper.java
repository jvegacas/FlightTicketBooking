package com.kodluyoruz.FlightTicketBooking.model.mapper;

import com.kodluyoruz.FlightTicketBooking.model.dto.UserDto;
import com.kodluyoruz.FlightTicketBooking.model.entity.User;
import com.kodluyoruz.FlightTicketBooking.model.request.CreateUpdateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper USER_MAPPER= Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user);

    List<UserDto> toUserDtoList(List<User> userList);

    User createUser(CreateUpdateUserRequest request);

    void updateUser(@MappingTarget User user, CreateUpdateUserRequest request);

}
