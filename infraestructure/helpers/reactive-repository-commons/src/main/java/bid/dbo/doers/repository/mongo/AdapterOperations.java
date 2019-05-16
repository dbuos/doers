package bid.dbo.doers.repository.mongo;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.util.function.Function;

import static org.springframework.data.domain.Example.of;

public abstract class AdapterOperations<E, D, I, R extends ReactiveCrudRepository<D, I> & ReactiveQueryByExampleExecutor<D>> {

    protected R repository;
    protected ObjectMapper mapper;
    private Class<D> dataClass;
    private Function<D, E> toEntityFn;

    @SuppressWarnings("unchecked")
    public AdapterOperations(R repository, ObjectMapper mapper, Function<D, E> toEntityFn) {
        this.repository = repository;
        this.mapper = mapper;
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.dataClass = (Class<D>) genericSuperclass.getActualTypeArguments()[1];
        this.toEntityFn = toEntityFn;
    }

    public Mono<E> save(E entity) {
        return Mono.just(entity)
            .map(this::toData)
            .flatMap(this::saveData)
            .thenReturn(entity);
    }

    protected Mono<E> doQuery(Mono<D> query) {
        return query.map(this::toEntity);
    }

    public Mono<E> findById(I id) {
        return doQuery(repository.findById(id));
    }

    public Flux<E> findByExample(E entity){
        return doQueryMany(repository.findAll(of(toData(entity))));
    }

    protected Flux<E> doQueryMany(Flux<D> query) {
        return query.map(this::toEntity);
    }


    protected D toData(E entity) {
        return mapper.map(entity, dataClass);
    }

    protected E toEntity(D data){
        return toEntityFn.apply(data);
    }

    protected Mono<D> saveData(D data) {
        return repository.save(data);
    }

}
