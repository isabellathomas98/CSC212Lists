package edu.smith.cs.csc212.lists;






//import edu.smith.cs.csc212.lists.SinglyLinkedList.Node;
import me.jjfoley.adt.ListADT;
import me.jjfoley.adt.errors.BadIndexError;
import me.jjfoley.adt.errors.TODOErr;

/**
 * A Doubly-Linked List is a list based on nodes that know of their successor and predecessor.
 * @author jfoley
 *
 * @param <T>
 */
public class DoublyLinkedList<T> extends ListADT<T> {
	/**
	 * This is a reference to the first node in this list.
	 */
	private Node<T> start;
	/**
	 * This is a reference to the last node in this list.
	 */
	private Node<T> end;
	
	/**
	 * A doubly-linked list starts empty.
	 */
	public DoublyLinkedList() {
		this.start = null;
		this.end = null;
	}
	

	@Override
	public T removeFront() {
		checkNotEmpty();
		T firstValue=start.value;
		start=start.after;
		return firstValue;
	}

	@Override
	public T removeBack() {
		checkNotEmpty();
		if(size()==1) {
			T firstValue=start.value;
			start=null;
			return firstValue;
		}
		T lastValue=end.value;
		end=end.before;
		end.after=null;
		return lastValue;
		
	}

	@Override
	public T removeIndex(int index) {
		checkNotEmpty();
		Node<T> deleted=null;
		if (index<0||index>size()-1) {
			throw new BadIndexError(index);
		}
		if (index==0) {
			return removeFront();
		}
		if (index==size()-1) {
			return removeBack();
		}
		//else if (index>0 && index>size()){
			int at=0;
			
			for (Node<T>n=start;n!=null;n=n.after) {
			if (at==index) {
				deleted=n;
				n.after=deleted.after;
				return deleted.value;
				
			}
			
			at++;
			}
			
		//}
		
		return deleted.value;
	}

	@Override
	public void addFront(T item) {
		if (start == null) {
			start = end = new Node<T>(item);
		} else {
			Node<T> second = start;
			start = new Node<T>(item);
			start.after = second;
			second.before = start;
		}
	}

	@Override
	public void addBack(T item) {
		if (end == null) {
			end = start = new Node<T>(item);
		} else {
			Node<T> secondLast = end;
			end = new Node<T>(item);
			end.before = secondLast;
			secondLast.after = end;
		}
	}

	@Override
	public void addIndex(int index, T item) {
		
		if (index<0||index>size()) {
			throw new BadIndexError(index);
		}
		if(index==0) {
			addFront(item);
		}
		if (index==size()) {
			addBack(item);
		}
		if(start==null) {
			addFront(item);
		}
		if (index>0&&index<size()) {
			int at=0;
			for (Node<T>n=start; n!=null;n=end.after) {
				
				if (at==index-1) {
					Node <T>inserted =new Node<T>(item);
					n.before=inserted;
					n=n.before;
					
					
					//return;
				}
				at++;
			
			}
		}
	}

	@Override
	public T getFront() {
		checkNotEmpty();
		
		return start.value;
	}
		
	

	@Override
	public T getBack() {
		checkNotEmpty();
		return end.value;
	}
	
	@Override
	public T getIndex(int index) {
		checkNotEmpty();
		if (index<0||index>=size()) {
			throw new BadIndexError(index);
		}
		int at=0;
		for(Node <T>current=start;current!=null;current=current.after) {
			if(at++ ==index) {
				return current.value;
			}
		}
		throw new BadIndexError(index);
	}
	
	public void setIndex(int index, T value) {
		//throw new TODOErr();
		if (start==null) {
			this.start=new Node<T>(value);
		}
		if (start!=null) {
		T original;
		original=this.getIndex(index);
		original=value;
		
		int at = 0;
		for (Node<T> n = start; n != null; n = n.after) {
			if (at++ == index) {
				n.value=value;
			}
		}
		}
	}

	@Override
	public int size() {
		int count = 0;
		for (Node<T> n = this.start; n != null; n = n.after) {
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		return (start == null);
		//return end == null;
		//throw new TODOErr();
	}
	
	/**
	 * The node on any linked list should not be exposed.
	 * Static means we don't need a "this" of DoublyLinkedList to make a node.
	 * @param <T> the type of the values stored.
	 */
	private static class Node<T> {
		/**
		 * What node comes before me?
		 */
		public Node<T> before;
		/**
		 * What node comes after me?
		 */
		public Node<T> after;
		/**
		 * What value is stored in this node?
		 */
		public T value;
		/**
		 * Create a node with no friends.
		 * @param value - the value to put in it.
		 */
		public Node(T value) {
			this.value = value;
			this.before = null;
			this.after = null;
		}
	}
}
