package raptorise.document.model;

import java.io.Serializable;

public class Document implements Serializable {
    private Long id;
    private Long currentUserId;
    private Long previousUserId;

    // Correctly implement setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }

    public void setPreviousUserId(Long previousUserId) {
        this.previousUserId = previousUserId;
    }

    // Implement getters
    public Long getId() {
        return this.id;
    }

    public Long getCurrentUserId() {
        return this.currentUserId;
    }

    public Long getPreviousUserId() {
        return this.previousUserId;
    }

}
