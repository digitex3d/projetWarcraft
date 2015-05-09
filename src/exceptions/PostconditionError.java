package exceptions;


@SuppressWarnings("serial")
public class PostconditionError extends ContractError {


	public PostconditionError(String message) {
		super("PostconditionError : " + message);
	}
}
