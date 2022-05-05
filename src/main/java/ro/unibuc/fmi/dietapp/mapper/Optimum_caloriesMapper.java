package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.fmi.dietapp.dto.Optimum_caloriesDto;
import ro.unibuc.fmi.dietapp.model.Optimum_calories;


@Mapper
public interface Optimum_caloriesMapper extends EntityMapper<Optimum_caloriesDto, Optimum_calories> {
}
