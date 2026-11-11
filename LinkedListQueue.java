

class LinkedListQueue<E> implements Queue<E>{
    LinkedList<E> list = new LinkedList<E>();

    public void enqueue(E val){
        list.addBack(val);
    }

    public E dequeue(){
        return list.deleteFirst();
    }

    public E peek(){
        return list.getFirst();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public int getSize(){
        return list.getSize();
    }

}