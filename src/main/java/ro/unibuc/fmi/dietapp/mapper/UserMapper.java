package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ro.unibuc.fmi.dietapp.dto.UserDto;
import ro.unibuc.fmi.dietapp.model.City;
import ro.unibuc.fmi.dietapp.model.User;


@Mapper(imports = {City.class})
public abstract class UserMapper implements EntityMapper<UserDto, User> {
    @Mappings({
            @Mapping(target = "cityId", source = "city.id")
    })
    public abstract UserDto toDto(User user);

    @Mappings({
            @Mapping(target = "city.id", source = "cityId")
    })
    public abstract User toEntity(UserDto userDto);
}
