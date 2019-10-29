package edu.smith.cs.csc212.lists;

import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.BadIndexError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * A Singly-Linked List is a list that has only knowledge of its very first
 * element. Elements after that are chained, ending with a null node.
 * 
 * @author jfoley
 *
 * @param <T> - the type of the item stored in this list.
 */
public class SinglyLinkedList<T> extends ListADT<T> {
	/**
	 * The start of this list. Node is defined at the bottom of this file.
	 */
	Node<T> start;

	@Override
	//good
	public T removeFront() {
		checkNotEmpty();
		T firstValue=this.start.value;
		this.start=this.start.next;
		return firstValue;
	}

	@Override
	public T removeBack() {
		SinglyLinkedList.Node<T> deleted;
		checkNotEmpty();
		if (size()==1) {
			T firstValue=this.start.value;
			this.start=null;
			return firstValue;
		}
		else {
		Node <T> last = null;
		Node <T> secondtolast = null;

		for (Node<T>current=this.start; current.next!=null;current=current.next) {
			secondtolast=current;
			last=current.next;
			deleted=last;
			//current=current.next;
			//secondtolast.next.next=null;
		}
		deleted=last;
		//deleted=secondtolast.next;
		secondtolast.next=null;
		
		return deleted.value;}
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		Node<T> deleted=null;
		if (index<0||index>size()) {
			throw new BadIndexError(index);} 
		if (index==0) {
			return removeFront();
		}
		
		else {
			
			int at=0;
			for (Node<T>n=this.start;n!=null;n=n.next) {
			if (at==index-1) {
				deleted=n.next;
				n.next=deleted.next;
				return deleted.value;
			}
			at++;
			}
			return deleted.value;
			}
		
	}

	@Override
	public void addFront(T item) {
		this.start=new Node<T>(item,this.start);
	}

	@Override
	/////good
	public void addBack(T item) {
		if(this.start==null) {
			addFront(item);
			return;
		}
		Node<T>last=null;
		for (Node<T>current=this.start; current!=null;current=current.next) {
			last=current;
		}
		assert(last.next==null);
		last.next=new Node<T>(item,null);
	}

	@Override
	public void addIndex(int index, T item) {
		//this method does not actually add anything..
		if (index>size()||index<0){
			throw new BadIndexError(index);
		}
		if(index==0) {
			//add to front
			this.start=new Node<T>(item,this.start);
			//return;
		}
		if(index==size()) {
			addBack(item);
			return;
		}
		if(index<size()&&index>0) {
			int at=0;
			for (Node<T>n=this.start; n!=null;n=n.next) {
				//at++;
				if (at==index-1) {
					Node <T>inserted =new Node<T>(item,n.next);
					n.next=inserted;
					return;
				}
				at++;
			
			}
		}
		}
	

	@Override
	public T getFront() {
		checkNotEmpty();
		T firstValue=this.start.value;
		return firstValue;
	}

	@Override
	public T getBack() {
		checkNotEmpty();
		Node <T> last = null;
		
		for (Node<T>current=this.start; current!=null;current=current.next) {
			last=current;	
		}
			return last.value;
	}
		

	@Override
	//good////
	public T getIndex(int index) {
		checkNotEmpty();
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at++ == index) {
				return n.value;
			}
		}
		throw new BadIndexError(index);
	}

	@Override
	public void setIndex(int index, T value) {
		//checkNotEmpty();
		if (start==null) {
			this.start=new Node<T>(value,this.start);
		}
		if (start!=null) {
		T original;
		original=this.getIndex(index);
		original=value;
		
		int at = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			if (at++ == index) {
				n.value=value;
			}
		}
		}
	}

	@Override
	//good
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.next) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return this.start == null;
	}

	/**
	 * The node on any linked list should not be exposed. Static means we don't need
	 * a "this" of SinglyLinkedList to make a node.
	 * 
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes after me?
		 */
		public Node<T> next;
		/**
		 * What value is stored in this node?
		 */
		public T value;

		/**
		 * Create a node with no friends.
		 * 
		 * @param value - the value to put in it.
		 * @param next - the successor to this node.
		 */
		public Node(T value, Node<T> next) {
			this.value = value;
			this.next = next;
		}
	}

}
