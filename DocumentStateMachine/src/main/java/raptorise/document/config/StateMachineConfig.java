package raptorise.document.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.listener.StateMachineListener;
import raptorise.document.model.Document;
import raptorise.document.repository.DocumentRepository;
import raptorise.document.state.DocumentStates;
import raptorise.document.state.DocumentEvents;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<DocumentStates, DocumentEvents> {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private StateMachineListener<DocumentStates, DocumentEvents> stateMachineListener;

    public StateMachineConfig(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<DocumentStates, DocumentEvents> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(stateMachineListener); // Register the listener
        System.out.println("Registered the listener with autoStartup as true");

    }

        @Override
        public void configure(StateMachineStateConfigurer<DocumentStates, DocumentEvents> states) throws Exception {

            System.out.println("Configuring states...");
            states.withStates()
                    .initial(DocumentStates.UPLOADED)
                    .states(EnumSet.allOf(DocumentStates.class));
            System.out.println("States configured: " + EnumSet.allOf(DocumentStates.class));
        }

    @Override
    public void configure(StateMachineTransitionConfigurer<DocumentStates, DocumentEvents> transitions) throws Exception {
        System.out.println("Configuring transitions...");
        transitions.withExternal()
                .source(DocumentStates.UPLOADED).target(DocumentStates.ASSIGNED).event(DocumentEvents.ASSIGN)
                .action(assignAction())
                .and()
                .withExternal()
                .source(DocumentStates.ASSIGNED).target(DocumentStates.ACCEPTED).event(DocumentEvents.ACCEPT);
        System.out.println("Transitions configured.");
    }

    @Bean
    public Action<DocumentStates, DocumentEvents> assignAction() {
        return context -> {
            System.out.println("extended state ="+context.getMessageHeader("documentId"));
            Long documentId = (Long) context.getMessageHeader("documentId");
            System.out.println("documentId extracted from context: " + documentId);
            if (documentId != null) {
                Document document = documentRepository.findById(documentId)
                        .orElseThrow(() -> new RuntimeException("Document not found for ID: " + documentId));
                documentRepository.save(document);
            } else {
                System.out.println("Document ID is null, action cannot proceed.");
            }
        };
    }


   /* @Override
    public void configure(StateMachineConfigurationConfigurer<DocumentStates, DocumentEvents> config) throws Exception {
        System.out.println("Configuring state machine...");
        config.withConfiguration().autoStartup(true);
        System.out.println("State machine configured to auto start.");
    }*/
}






