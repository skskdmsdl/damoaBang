package member.model.exception;

//runtimeExcetion 예외처리 강제화X
public class memberException extends RuntimeException {

	public memberException() {
		super();
	}

	public memberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public memberException(String message, Throwable cause) {
		super(message, cause);
	}

	public memberException(String message) {
		super(message);
	}

	public memberException(Throwable cause) {
		super(cause);
	}
	
}
