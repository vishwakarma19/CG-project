package frds.mgnt.controller;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseUtil implements Serializable {

    private static final long serialVersionUID = -139780548247708249L;

    static ResponseUtil failed(String message) {
        return new ResponseUtil(false, message);
    }

    static ResponseUtil success() {
        return new ResponseUtil(true);
    }

    public ResponseUtil(boolean success) {
		super();
		this.success = success;
	}

	public ResponseUtil(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	@NonNull
    private final boolean success;

    private String message;
}
