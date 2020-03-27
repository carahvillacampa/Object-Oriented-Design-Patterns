package hw2;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyLinked<T> {
    static class Node<T> {
    	public double item;
        public Node<T> next;
        public Node<T> prev;
        
        public Node() { 
        	next=prev=null;
        }
    }

    int N;
    Node<T> first;
    static boolean condition= false;
    Hashtable htable= new Hashtable();
    
    public MyLinked () {
        first = null;
        N = 0;
        assert checkInvariants ();
    }

    private boolean checkInvariants() {
        assert((N != 0) || (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                return false;
            }
            x = x.next;
        }
        assert(x == null);
        return true;
    }
    /*
     * checks if the linked list is empty
     * @return a boolean
     */
    public boolean isEmpty () { return first == null; }
    
    /*
     * @return size of the linked list
     */
    public int size () { return N; }
    
    /*
     * adds a new node to the linked list
     * @param data of type double
     * 
     */
    public void add (double item) {
        Node<T> newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }

    /*
     * deletes the Node at index k
     * @param int that specifies the position of the item in the linked list
     * 
     */
    public void delete (int k) {
    	assert checkInvariants ();
        if (k < 0 || k >= N) throw new IllegalArgumentException ();
        
        //delete in the beginning 
        if (k == 0) {
        	first = first.next;
        	condition= true;
        	N--;
        	return;
        }
        
        int index = 1;
        Node current = first;
        while(index < k){
        	current = current.next;
        	index++;
        	//assert();
        }
        current.next = current.next.next;
        condition= true;
        N--;
        
    }

    /*
     * reverse the list "in place"... without creating any new nodes
     * 
     */
    public void reverse () {
        //assert checkInvariants ();
    	if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        LinkedList list1= new LinkedList ();
       
        Node currPointer= first;
        Node  prev= null;
        Node curr= null;
        
        while(curr != null) {
        	curr= currPointer;
        	currPointer= curr.next;
        	//reversing the link
        	
        	curr.next= prev;
        	prev=curr;
        	first= curr;
        	//assert(next == curr.next && prev.next== curr);
      
        }

    }
    
    /*
     * Value of reverseDoubly should equal the reverse
     */
    public void reverseDoubly() {
    	Node temp = null;
    	Node curr=first;
    	
    	while(curr != null) {
    		temp= curr.prev;
    		curr.prev= curr.next;
    		curr.next= temp;
    		curr= curr.prev;
    	}
    	
    	if(temp != null) {
    		first= temp.prev;
    	}
    }

    /*
     * removes the Node in the Linked list by value
     * @param item of type double to be removed
     */
    public void remove (double item) {
    	
    	assert checkInvariants ();
    	if (first.item == item) {
        	first = first.next;
        	N--;
        	return;
        }
        for (Node current = first; current != null; current = current.next) {
        	if (current.next.item == item) {
        		current.next = current.next.next; //removing
        		N--;
        	}
        } 
    }
}

































