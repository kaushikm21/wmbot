package raptorise.document.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.event.OnEventNotAcceptedEvent;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import raptorise.document.state.DocumentEvents;
import raptorise.document.state.DocumentStates;

@Configuration
public class StateMachineEventListenerConfig {

    @Bean
    public StateMachineListener<DocumentStates, DocumentEvents> stateMachineListener() {
        return new StateMachineListenerAdapter<DocumentStates, DocumentEvents>() {
            @Override
            public void stateChanged(State<DocumentStates, DocumentEvents> from, State<DocumentStates, DocumentEvents> to) {
                System.out.println("State changed from " + (from != null ? from.getId() : "none") + " to " + to.getId());
            }


            public void eventNotAccepted(OnEventNotAcceptedEvent event) {
                System.out.println("Event not accepted: " + event.getEvent());
            }
        };
    }
}
