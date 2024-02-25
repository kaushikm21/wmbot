package raptorise.document.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.statemachine.StateMachine;
import static org.junit.jupiter.api.Assertions.assertEquals;
import raptorise.document.DocumentApplication; // Import this if specifying classes
import raptorise.document.model.Document;
import raptorise.document.repository.DocumentRepository;
import raptorise.document.state.DocumentEvents;
import raptorise.document.state.DocumentStates;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;


@SpringBootTest // If your test is correctly located, no need to specify classes
public class DocumentStateMachineTests{

    @Autowired
    private DocumentService documentService;

    @Autowired
    private StateMachine<DocumentStates, DocumentEvents> stateMachine;

    @MockBean
    private DocumentRepository documentRepository;


    @Test
    public void testAssignDocumentChangesState() {
        // Mock document data
        Document mockDocument = new Document();
        mockDocument.setId(1L);
        mockDocument.setCurrentUserId(2L); // Assuming this is the "to" user ID
        mockDocument.setPreviousUserId(1L); // Assuming this is the "from" user ID

        // Mock the repository behavior
        when(documentRepository.findById(1L)).thenReturn(Optional.of(mockDocument));

        // Start the state machine
        stateMachine.start();
        System.out.println("Using StateMachine instance in DocumentStateMachineTests: " + System.identityHashCode(stateMachine));

        // Call the service method to test
        documentService.assignDocument(mockDocument); // documentId, fromUserId, toUserId

        // Verify the state transition
        assertEquals(DocumentStates.ASSIGNED, stateMachine.getState().getId(), "Document should be in the ASSIGNED state");
        // Verify interactions with the mock repository
        verify(documentRepository).save(any(Document.class));

        // Additional assertions and verifications as needed
    }

}
