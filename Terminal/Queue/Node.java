public class Node<E>{
    
    private E _value;
    private Node<E> _next;

    public Node(E value, Node<E> next){
	_value = value;
	_next = next;
    }

    public E getValue(){
	return _value;
    }


    public Node<E> getNext(){
	return _next;
    }

    public E setValue(E value){
	E ans = getValue();
	_value = value;
	return ans;
    }

    public Node<E> setNext(Node<E> next){
	Node<E> ans = getNext();
	_next = next;
	return ans;
    }





}
