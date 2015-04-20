package exceptions;

@SuppressWarnings("serial")
public class ContractError extends Error {


	public ContractError(String message) {
		super(message);
	}
}
