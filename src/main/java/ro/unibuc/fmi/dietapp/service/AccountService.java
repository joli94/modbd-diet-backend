package ro.unibuc.fmi.dietapp.service;

import org.springframework.stereotype.Service;
import ro.unibuc.fmi.dietapp.exception.EntityNotFoundException;
import ro.unibuc.fmi.dietapp.model.Account;
import ro.unibuc.fmi.dietapp.repository.AccountRepository;


@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("The account with this id doesn't exist in the database!")
        );
    }

    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public Account update(Account account) {
        if (accountRepository.existsById(account.getId())) {
            return accountRepository.save(account);
        } else {
            throw new EntityNotFoundException(String.format("There is no account with id=%s in the database!", account.getId().toString()));
        }
    }
}
