package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.fmi.dietapp.dto.FoodCategoryDto;
import ro.unibuc.fmi.dietapp.model.FoodCategory;


@Mapper
public interface FoodCategoryMapper extends EntityMapper<FoodCategoryDto, FoodCategory> {
}
