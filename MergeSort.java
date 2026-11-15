public class MergeSort<E extends Comparable<E>> {

    private static class Node<E> {
        private Node<E> next;
        private E data;

        public Node(E data){
                this.data = data;
            }
    }

    /*
        Sorting function that divides a list into partitions then merges them in sorted order
        @param head Head node of the list
        @return merge The merger between split partitions
     */
    public Node<E> sort(Node<E> head){
        if(head == null || head.next == null) return head; // Base

        // Find mid node
        Node<E> mid = getMid(head);
        
        // Recurse on each half
        Node<E> left = sort(head);
        Node<E> right = sort(mid.next);

        // Merge sorted halves
        return merge(left, right);

    }

    /*
        Helper method for merge sort that merges two sorted parts together
        @param left Node<E> start of left partition; right Node<E> start of right partition
        @return temp.next Head of the new linked list
     */
    private Node<E> merge(Node<E> left, Node<E> right){
        Node<E> temp = new Node<>(null);
        Node<E> tail = temp;

        // Add left and right onto tail
        while(left != null && right != null){
            if(left.data.compareTo(right.data) < 0){ // compareTo return -1 when left is smaller than right, 0 when right > left
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
        if(head == null) return head;

        Node<E> A = head;
        Node<E> B = head.next;

        while(A != null && A.next != null){
            B = B.next;
            A = A.next.next;
        }

        return B;
    }


    
}
