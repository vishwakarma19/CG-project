package frds.mgnt.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

class BaseEntity implements Serializable {

    private static final long serialVersionUID = -7343044791882882877L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

	public BaseEntity(Integer id) {
		this.id = id;
	}
    
    
}
