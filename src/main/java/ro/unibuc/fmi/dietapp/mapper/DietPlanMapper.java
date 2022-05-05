package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.fmi.dietapp.dto.DietPlanDto;
import ro.unibuc.fmi.dietapp.model.DietPlan;

@Mapper
public interface DietPlanMapper extends EntityMapper<DietPlanDto, DietPlan> {
}
