package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.fmi.dietapp.dto.RegistrationDto;
import ro.unibuc.fmi.dietapp.model.Registration;


@Mapper
public interface RegistrationMapper extends EntityMapper<RegistrationDto, Registration> {
}
