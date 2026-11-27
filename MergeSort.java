import java.util.Comparator;

public class MergeSort<E> {

    /*
        Sorting function that divides a list into partitions then merges them in sorted order
        @param head Head node of the list
        @return merge The merger between split partitions
     */
    public Node<E> sort(Node<E> head, Comparator<? super E> comp){
        return head = mergeSort(head, comp);

    }

    private Node<E> mergeSort(Node<E> head, Comparator<? super E> comp){
        if (head == null || head.next == null) return head;

        Node<E> mid = getMid(head);
        Node<E> rightHalf = mid.next;
        mid.next = null;

        Node<E> left = mergeSort(head, comp);
        Node<E> right = mergeSort(rightHalf, comp);
        return merge(left, right, comp);
    }

    /*
        Helper method for merge sort that merges two sorted parts together
        @param left Node<E> start of left partition; right Node<E> start of right partition
        @return temp.next Head of the new linked list
     */
    private Node<E> merge(Node<E> left, Node<E> right, Comparator<? super E> comp){
        Node<E> temp = new Node<>(null);
        Node<E> tail = temp;

        // Add left and right onto tail
        while(left != null && right != null){
            if(comp.compare(left.data, right.data) <= 0){ // compareTo return -1 when left is smaller than right, 0 when right > left
                tail.next = left;
                left = left.next;
            }else{
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }

        // Add remainders
        if(left != null){
            tail.next = left;
        }else{
            tail.next = right;
        }

        // head of the new list
        return temp.next;
    }
    
    /*
        Helper method for mergeSort to find the middle node
        @param head The start of the current linked list
        @return B middle node
     */
    private Node<E> getMid(Node<E> head){
        if(head == null || head.next == null) return head;

        Node<E> A = head;
        Node<E> B = head;

        while(A.next != null && A.next.next != null){
            B = B.next;
            A = A.next.next;
        }

        return B;
    }


    
}
