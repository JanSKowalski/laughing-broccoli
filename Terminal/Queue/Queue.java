public interface Queue<E>{

    public int size();
    public boolean empty();
    public E front() throws EmptyQueueException;
    public E dequeue() throws EmptyQueueException;  
    public void enqueue(E value);


}
