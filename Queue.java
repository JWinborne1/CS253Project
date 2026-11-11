public interface Queue<E> {
    public void enqueue(E val);

    public E dequeue();

    public E peek();

    public boolean isEmpty();

}
