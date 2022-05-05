package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ro.unibuc.fmi.dietapp.dto.CityDto;
import ro.unibuc.fmi.dietapp.model.City;
import ro.unibuc.fmi.dietapp.model.Country;


@Mapper(imports = {Country.class})
public abstract class CityMapper implements EntityMapper<CityDto, City> {
    @Mappings({
            @Mapping(target = "countryId", source = "country.id")
    })
    public abstract CityDto toDto(City city);

    @Mappings({
            @Mapping(target = "country.id", source = "countryId")
    })
    public abstract City toEntity(CityDto cityDto);
}
