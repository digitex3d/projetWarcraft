package exceptions;


@SuppressWarnings("serial")
public class PreconditionError extends ContractError {


	public PreconditionError(String message) {
		super("PreconditionError : " + message + " not verified.");
	}
}
