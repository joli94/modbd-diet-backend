package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ro.unibuc.fmi.dietapp.dto.DietDto;
import ro.unibuc.fmi.dietapp.model.Diet;
import ro.unibuc.fmi.dietapp.model.DietGoal;
import ro.unibuc.fmi.dietapp.model.DietType;

@Mapper(imports = {DietGoal.class, DietType.class})
public abstract class DietMapper implements EntityMapper<DietDto, Diet> {
    @Mappings({
            @Mapping(target = "dietGoalId", source = "dietGoal.id"),
            @Mapping(target = "dietTypeId", source = "dietType.id")
    })
    public abstract DietDto toDto(Diet diet);

    @Mappings({
            @Mapping(target = "dietGoal.id", source = "dietGoalId"),
            @Mapping(target = "dietType.id", source = "dietTypeId")
    })
    public abstract Diet toEntity(DietDto dietDto);
}
