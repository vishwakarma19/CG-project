package frds.mgnt.service;

public class FriendRuntimeException extends RuntimeException {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2400440907480484973L;

	public FriendRuntimeException(String message) {
        super(message);
    }

    public FriendRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FriendRuntimeException(Throwable cause) {
        super(cause);
    }

    public FriendRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
