public class NodeStack<E> implements Stack<E>{

    // private instance variables.
    private Node<E> _head;

    // constructor
    public NodeStack(){
	_head = null;
    }
    // O(1)
    public boolean empty(){
	return _head == null;
    }
    // O(1)
    public void push(E value){
	_head = new Node<E>(value, _head);
    }
    // O(1)
    public E peek(){
	if (empty())
	    throw new EmptyStackException("Don't peek at an empty stack");
	return _head.getValue();
    }
    // O(1)
    public E pop(){
	if (empty())
	    throw new EmptyStackException("Don't pop at an empty stack");
	E temp = _head.getValue();
	_head = _head.getNext();
	return temp;
    }



    public static void main(String [] args){
	Stack<Integer> s = new NodeStack<Integer>();
	for (int i = 1; i < 10; i++)
	    s.push(i);

	while (! s.empty())
	    System.out.println(s.pop());

	s.peek();
    }





}
