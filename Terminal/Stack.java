interface Stack<E>{
    public void push(E item);
    public E pop();   // Empty Stack Exception
    public E peek();  // Empty Stack Exception
    public boolean empty();
    
}
