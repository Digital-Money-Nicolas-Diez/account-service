package dh.backend.accounts.infrastructure.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import dh.backend.accounts.infrastructure.persistence.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {}