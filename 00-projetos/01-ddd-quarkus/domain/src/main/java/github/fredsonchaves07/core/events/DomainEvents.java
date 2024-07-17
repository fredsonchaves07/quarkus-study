package github.fredsonchaves07.core.events;

import github.fredsonchaves07.core.entities.AggregateRoot;
import github.fredsonchaves07.core.entities.Identifier;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DomainEvents {

    private static HashMap<String, EventHandler> handlers = new HashMap<>();

    private static Set<AggregateRoot<Identifier>> aggregateRoots = new HashSet<>();

//    public static void markAggregateForDispatch(AggregateRoot<Identifier> aggregate) {
//        AggregateRoot<Identifier> aggregateFound = findMarkedAggregateById(aggregate.id());
//        if (aggregateFound == null) {
//            aggregateRoots.add(aggregate);
//        }
//    }
//
//    private static void dispatchAggregateEvents(AggregateRoot<Identifier> aggregate) {
//        aggregate.domainEvents().stream().forEach(event -> event.dispatch());
//    }
//
//    private static

    public static AggregateRoot<Identifier> findMarkedAggregateById(String id) {
        return aggregateRoots
                .stream()
                .filter(aggregate -> aggregate.id().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
