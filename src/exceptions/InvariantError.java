package exceptions;


public class InvariantError extends ContractError {


	public InvariantError(String message) {
		super("InvariantError : " + message);
	}
}
