package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.fmi.dietapp.dto.CountryDto;
import ro.unibuc.fmi.dietapp.model.Country;

@Mapper
public interface CountryMapper extends EntityMapper<CountryDto, Country> {
}
