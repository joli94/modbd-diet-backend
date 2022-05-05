package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.fmi.dietapp.dto.DietGoalDto;
import ro.unibuc.fmi.dietapp.model.DietGoal;

@Mapper
public interface DietGoalMapper extends EntityMapper<DietGoalDto, DietGoal> {
}
