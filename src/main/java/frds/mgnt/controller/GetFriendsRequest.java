package frds.mgnt.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author ej
 * @since 9/15/18 10:35 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class GetFriendsRequest implements Serializable {

    private static final long serialVersionUID = -5987345877751148059L;

    @NotEmpty
    @Email
    private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
