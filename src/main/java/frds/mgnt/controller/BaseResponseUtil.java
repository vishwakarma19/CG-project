package frds.mgnt.controller;

import java.io.Serializable;

import org.springframework.lang.NonNull;

class BaseResponseUtil implements Serializable {

    private static final long serialVersionUID = -139780548247708249L;

    static BaseResponseUtil failed(String message) {
        return new BaseResponseUtil(false, message);
    }

    static BaseResponseUtil success() {
        return new BaseResponseUtil(true);
    }

    public BaseResponseUtil(@NonNull boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public BaseResponseUtil(@NonNull boolean success) {
		super();
		this.success = success;
	}

	@NonNull
    private final boolean success;

    private String message;
}
