public class EmptyStackException extends RuntimeException {

    public EmptyStackException(){
	this("");
    }

    public EmptyStackException(String message){
	super(message);
    }
}
