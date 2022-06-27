package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.messages.Topic;

import java.util.Objects;
import java.util.function.Predicate;

public class Observing<A extends Actor, T> implements Behaviour<A> {
    private final Topic<T> observeTopic;
    private final Predicate<T> observePared;
    private final Behaviour<A> observeDelegate;

    public Observing(Topic<T> topic, Predicate<T> predicate, Behaviour<A> behaviour) {
        this.observeTopic = topic;
        this.observePared = predicate;
        this.observeDelegate = behaviour;
    }

    @Override
    public void setUp(A actor) {
        if (actor != null) {
            actor.getScene().getMessageBus().subscribe(observeTopic, topicActor -> {
                if (observePared.test(topicActor)) {
                    observeDelegate.setUp(actor);
                }
            });
        }
    }
}
