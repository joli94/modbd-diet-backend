package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.fmi.dietapp.dto.PaymentDto;
import ro.unibuc.fmi.dietapp.model.Payment;

@Mapper
public interface PaymentMapper extends EntityMapper<PaymentDto, Payment> {
}
