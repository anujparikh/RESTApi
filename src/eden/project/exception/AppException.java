package eden.project.exception;

public class AppException extends Exception{

	private static final long serialVersionUID = -7906483485294116938L;

	public AppException(String msg) {
		super(msg);
	}
	
	public AppException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
