package frds.mgnt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "profiles")
public class ProfileEntity implements Serializable {

    /**
	 * Serial version
	 */
	private static final long serialVersionUID = 6366069554122270859L;

	@Column(nullable = false, unique = true, length = 200)
    private String emailAddress;

    @Column(nullable = false, length = 150)
    private String name;

    public ProfileEntity(Integer id, String emailAddress, String name) {
       // super(id);
        this.emailAddress = emailAddress;
        this.name = name;
    }
}
