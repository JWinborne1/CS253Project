

public class LinkedList<E>{
    Node<E> head = null;
    Node<E> tail = null;

    /*
        Adds the given value to the beginning of the list
        @param val An object of any type that holds meaningful value
     */
    public void addFront(E val){
        Node<E> newNode = new Node<E>(val);     // Creates new node
        if(head == null){                       // Start list
            head = newNode;     
        } else if(tail == null){                // Second in list
            newNode.next = head;
            tail = head;
            head = newNode;
        }else{                                  // Add new nodes to the list from head
            newNode.next = head;
            head = newNode;
        }
        
        

    }

    /*
        Adds the given value to the back of the list
        @param val An object of any type that holds meaningful value
     */
    public void addBack(E val){
        Node<E> newNode = new Node<E>(val);     // Creates new node
        if(head == null){                       // Start list
            head = newNode;     
        } 
        else if(tail == null){                  // Second in list
            tail = newNode;
            head.next = tail;
        }
        else{                                   // Add new nodes to list from tail
            tail.next = newNode;
            tail = tail.next;
        }
    }

    /*
        Removes the value at the front of the list and returns it
        @return Object of type E from the node at the front of the list
     */
    public E deleteFirst(){
        if(head == null) throw new RuntimeException("Empty!");  // Empty check
        E val = head.data;                                      // Save head data
        head = head.next;                                       // Remove front of list
        return val;                                                     
    }

    /*
        Checks if there are no nodes in the list
        @return true if no nodes in list
     */
    public boolean isEmpty(){
        if(head != null) return false;
        return true;
    }

    /*
        Gives the length of the list as an integer
        @return int
     */
    public int getSize(){
        if(head == null) return 0;
        int size = 0;
        Node<E> curr = head;
        while(curr.next != null){      // Move through list until tail and count nodes
            size++;
            curr = curr.next;
        }
        return size;
    }

    /*
        Gives the first value in the list
        @return Object type E
     */
    public E getFirst(){
        if(head == null) throw new RuntimeException("Empty");
        return head.data;
    }

    /*
        Gives the last value in the list
        @return Object type E
     */
    public E getLast(){
        if(tail != null) return tail.data; // Check if there is a tail
        return head.data;                  // No tail means only 1 node, return value at head
    }
    
    private class Node<T> {
        private Node<T> next;
        private T data;

            public Node(T data){
                this.data = data;
            }
    }
}
