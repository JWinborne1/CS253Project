public interface Queue<E> {
    public void enqueue();

    public E dequeue();

    public boolean isEmpty();

    public boolean isFull();

    public int getSize();
}
