package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ro.unibuc.fmi.dietapp.dto.UserDto;
import ro.unibuc.fmi.dietapp.model.Country;
import ro.unibuc.fmi.dietapp.model.User;


@Mapper(imports = {Country.class})
public abstract class UserMapper implements EntityMapper<UserDto, User> {
    @Mappings({
            @Mapping(target = "countryId", source = "country.id")
    })
    public abstract UserDto toDto(User user);

    @Mappings({
            @Mapping(target = "country.id", source = "countryId")
    })
    public abstract User toEntity(UserDto userDto);
}
