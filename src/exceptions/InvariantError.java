package exceptions;


@SuppressWarnings("serial")
public class InvariantError extends ContractError {


	public InvariantError(String message) {
		super("InvariantError : " + message);
	}
}
