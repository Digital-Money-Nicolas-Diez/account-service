package dh.backend.accounts.infrastructure.mappers;

public interface Mapper<D, E> {
    D toDomain(E entity);
    E fromDomain(D domain);
}