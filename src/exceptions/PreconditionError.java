package exceptions;


public class PreconditionError extends ContractError {


	public PreconditionError(String message) {
		super("PreconditionError : " + message);
	}
}
