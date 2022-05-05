package ro.unibuc.fmi.dietapp.mapper;

import org.mapstruct.Mapper;
import ro.unibuc.fmi.dietapp.dto.AccountDto;
import ro.unibuc.fmi.dietapp.model.Account;


@Mapper
public interface AccountMapper extends EntityMapper<AccountDto, Account> {
}
