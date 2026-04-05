package dh.backend.accounts.application;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.repository.AccountRepository;
import dh.backend.accounts.infrastructure.web.dto.CvuAndAlias;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCvuAndAlias {

    private final AccountRepository repo;

    public UpdateCvuAndAlias(AccountRepository repo) {
        this.repo = repo;
    }

    public void execute(CvuAndAlias dto, UUID user) {
        Account account = repo.getByUserId(user);
        account.setCvu(dto.cvu());
        account.setAlias(dto.alias());
        repo.update(account);

    }

}
