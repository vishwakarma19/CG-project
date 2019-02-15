package frds.mgnt.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ej
 * @since 9/16/18 6:03 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class BlockRequest implements Serializable {

    private static final long serialVersionUID = -429115110489920280L;

    @NotNull
    @Email
    private String requestor;

    @NotNull
    @Email
    private String target;
}
