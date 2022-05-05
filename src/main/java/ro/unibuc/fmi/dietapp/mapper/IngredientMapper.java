package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.fmi.dietapp.dto.IngredientDto;
import ro.unibuc.fmi.dietapp.model.Ingredient;

@Mapper
public interface IngredientMapper extends EntityMapper<IngredientDto, Ingredient> {
}
