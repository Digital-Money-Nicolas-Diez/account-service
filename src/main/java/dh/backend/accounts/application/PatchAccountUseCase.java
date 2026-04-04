package dh.backend.accounts.application;

import dh.backend.accounts.domain.entity.Account;
import dh.backend.accounts.domain.entity.AccountFactory;
import dh.backend.accounts.domain.repository.AccountRepository;
import dh.backend.accounts.infrastructure.web.dto.CvuAndAlias;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatchAccountUseCase {

    private final AccountRepository repo;
    public PatchAccountUseCase(AccountRepository repo) {
        this.repo = repo;
    }

    public void execute(CvuAndAlias dto, UUID user) {
        Account account = AccountFactory.create(user, dto.alias(), dto.cvu());
        this.repo.update(account);
    }

}
