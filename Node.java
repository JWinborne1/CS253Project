public class Node<E> {
private Node<E> next;
private Node<E> prev;
private E data;

    public Node(E data){
        this.data = data;
    }

    public Node<E> getNext(){
        return this.next;
    }

    public Node<E> getPrev(){
        return this.prev;
    }

    public E getData(){
        return this.data;
    }

    public void setNext(Node<E> n){
        this.next = n;
    }

    public void setPrev(Node<E> n){
        this.prev = n;
    }

    public void setData(E data){
        this.data = data;
    }

}
