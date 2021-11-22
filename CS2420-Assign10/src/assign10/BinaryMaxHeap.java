package assign10;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E> implements PriorityQueue<E>{
	
	private E[] maxHeap;
	private Comparator<? super E> cmp;
	
	// This constructor creates an empty BinaryMax Heap and assumes
	//that natural ordering (comparable) will be used for comparisons.	
	 
	public BinaryMaxHeap() {
		maxHeap = new E[10];
		cmp = null;
	}
	
	public BinaryMaxHeap(Comparator<? super E>) {
		
	}
	
	public BinaryMaxHeap(List<? extends E>) {
		
	}
	
	public BinaryMaxHeap(List<? extends E>, Comparator<? super E>) {
		
	}

	@Override
	public void add(E item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E peek() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E extractMax() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}


}
