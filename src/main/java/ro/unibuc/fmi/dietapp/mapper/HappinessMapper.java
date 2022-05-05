package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ro.unibuc.fmi.dietapp.dto.HappinessDto;
import ro.unibuc.fmi.dietapp.model.Happiness;
import ro.unibuc.fmi.dietapp.model.User;

@Mapper(imports = {User.class})
public abstract class HappinessMapper implements EntityMapper<HappinessDto, Happiness> {
    @Mappings({
            @Mapping(target = "userId", source = "user.id")
    })
    public abstract HappinessDto toDto(Happiness happiness);

    @Mappings({
            @Mapping(target = "user.id", source = "userId")
    })
    public abstract Happiness toEntity(HappinessDto happinessDto);
}
