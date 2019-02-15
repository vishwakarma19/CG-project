package frds.mgnt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Entity
@Table(name = "profiles")
public class ProfileEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1930294112255207734L;

    @Column(nullable = false, unique = true, length = 200)
    private String emailAddress;

    @Column(nullable = false, length = 150)
    private String name;

    public ProfileEntity(Integer id, String emailAddress, String name) {
        super(id);
        this.emailAddress = emailAddress;
        this.name = name;
    }
    
    
}
