package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ro.unibuc.fmi.dietapp.dto.BillingDto;
import ro.unibuc.fmi.dietapp.model.Billing;

@Mapper(uses = {UserMapper.class, DietMapper.class, PaymentMapper.class})
public interface BillingMapper extends EntityMapper<BillingDto, Billing> {
    @Mappings({
            @Mapping(target = "userDto", source = "user"),
            @Mapping(target = "dietDto", source = "diet"),
            @Mapping(target = "paymentDto", source = "payment")
    })
    BillingDto toDto(Billing billing);

    @Mappings({
            @Mapping(target = "user", source = "userDto"),
            @Mapping(target = "diet", source = "dietDto"),
            @Mapping(target = "payment", source = "paymentDto")
    })
    Billing toEntity(BillingDto billingDto);
}
