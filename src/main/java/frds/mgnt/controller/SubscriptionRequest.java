package frds.mgnt.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author ej
 * @since 9/16/18 9:13 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class SubscriptionRequest implements Serializable {

    private static final long serialVersionUID = 7664102065873920186L;

    @NotEmpty
    @Email
    private String requestor;

    @NotEmpty
    @Email
    private String target;
}
