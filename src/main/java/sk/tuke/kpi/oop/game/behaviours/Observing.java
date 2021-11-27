package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.messages.Topic;

import java.util.Objects;
import java.util.function.Predicate;

public class Observing <A extends Actor, T> implements Behaviour<A> {
    private final Topic<T> obserTopic;
    private final Predicate<T> observPred;
    private final Behaviour<A> observDelegate;

    public Observing(Topic<T> topic, Predicate<T> predicate, Behaviour<A> behaviour) {
        this.obserTopic = topic;
        this.observPred = predicate;
        this.observDelegate = behaviour;
    }

    @Override
    public void setUp(A actor) {
        if (actor != null) {
            Objects.requireNonNull(actor.getScene()).getMessageBus().subscribe(obserTopic, topicActor -> {
                    if (observPred.test(topicActor)) observDelegate.setUp(actor);
            });
        }
    }
}
