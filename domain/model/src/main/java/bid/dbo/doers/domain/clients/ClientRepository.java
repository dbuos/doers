package bid.dbo.doers.domain.clients;

import reactor.core.publisher.Mono;

public interface ClientRepository {
    Mono<Client> save(Client client);
    Mono<Client> findById(String id);
}
