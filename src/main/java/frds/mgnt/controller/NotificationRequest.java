package frds.mgnt.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author ej
 * @since 9/16/18 11:19 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class NotificationRequest implements Serializable {

    private static final long serialVersionUID = 6431476238684636331L;

    @NotEmpty
    @Email
    private String sender;

    @NotEmpty
    private String text;
}
