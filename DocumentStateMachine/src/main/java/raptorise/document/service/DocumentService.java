package raptorise.document.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;
import raptorise.document.model.Document;
import raptorise.document.state.DocumentEvents;
import raptorise.document.state.DocumentStates;

@Service
public class DocumentService {

    @Autowired
    private StateMachine<DocumentStates, DocumentEvents> stateMachine;

    public void assignDocument(Document document) {
        System.out.println("Using StateMachine instance in DocumentService: " + System.identityHashCode(stateMachine));
        stateMachine.sendEvent(MessageBuilder.withPayload(DocumentEvents.ASSIGN)
                .setHeader("documentId", document.getId()) // Ensure this matches the key used in assignAction
                .build());
    }

    // Additional methods for accept, forward, revert
}

