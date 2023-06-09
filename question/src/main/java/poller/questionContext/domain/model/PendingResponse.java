package poller.questionContext.domain.model;

import javax.persistence.*;

/**
 * PendingResponse class.
 */
@Entity
@Table(name = "pendingResponse")
public class PendingResponse {
    /**The PendingResponse ID.*/
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private  long id;

    /**The User ID.*/
    private  long idUser;

    /**The Question ID.*/
    private  long idQuestion;

    /**The Status Enum.*/
    private  Enum<Status> status;

    /**PendingResponse ID getter.
     * @return PendingResponse ID*/
    public long getId() {
        return id;
    }

    /**PendingResponse ID setter.
     * @param id the PendingResponse ID*/
    public void setId(final long id) {
        this.id = id;
    }

    /**User ID getter.
     * @return User ID*/
    public long getIdUser() {
        return idUser;
    }

    /**User ID setter.
     * @param idUser the User ID*/
    public void setIdUser(final long idUser) {
        this.idUser = idUser;
    }

    /**Question ID getter.
     * @return Question ID*/
    public long getIdQuestion() {
        return idQuestion;
    }

    /**Question ID setter.
     * @param idQuestion the Question ID*/
    public void setIdQuestion(final long idQuestion) {
        this.idQuestion = idQuestion;
    }

    /**Status getter.
     * @return status*/
    public Enum<Status> getStatus() {
        return status;
    }

    /**Status setter.
     * @param status the status*/
    public void setStatus(
            final Enum<Status> status) {

        this.status = status;
    }
}
