public class Node<E>{
    private E _value;
    private Node<E> _next;

    // constructor
    public Node(E value){
	_value = value;
	_next = null;
    }

    public Node (E value, Node<E> next){
	this(value);
	_next = next;
    }

    // accessor methods
    public E getValue(){
	return _value;
    }

    public Node<E> getNext(){
	return _next;
    }

    // mutator methods
    public E setValue(E s){
	E ans = getValue();
	_value = s;
	return ans;
    }

    public Node<E> setNext(Node<E> n){
	Node<E> ans = getNext();
	_next = n;
	return ans;
    }
    
    
}
