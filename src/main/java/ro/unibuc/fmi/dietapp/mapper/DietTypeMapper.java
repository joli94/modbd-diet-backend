package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.fmi.dietapp.dto.DietTypeDto;
import ro.unibuc.fmi.dietapp.model.DietType;

@Mapper
public interface DietTypeMapper extends EntityMapper<DietTypeDto, DietType> {
}
