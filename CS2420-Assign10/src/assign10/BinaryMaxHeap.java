package assign10;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E> implements PriorityQueue<E>{
	
	private E[] maxHeap;
	private Comparator<? super E> cmp;
	private int size;//how many elements are in the array
	private int capacity;//how many elements could be in the array, the maximum amount
	
	// This constructor creates an empty BinaryMax Heap and assumes
	//that natural ordering (comparable) will be used for comparisons.	
	 
	public BinaryMaxHeap() {
		maxHeap = new E[10];
		cmp = null;
		
	}
	
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		
	}
	
	public BinaryMaxHeap(List<? extends E> numList) {
		
	}
	
	public BinaryMaxHeap(List<? extends E> numList, Comparator<? super E> cmp) {
		
	}

	@Override
	public void add(E item) {
		// TODO Auto-generated method stub
		if(size+1 > capacity) {
			growArray();
		}
	}
	
	private void growArray() {
		//create a new array twice the capacity as the current array
		capacity = capacity*2;
		E[] tempMaxHeap = (E[]) new Object[capacity];
		
		//copy over each element
		for(int index = 0; index < size; index++) {
			tempMaxHeap[index] = maxHeap[index];
		}
		
		//switch references so larger array is the new heap
		maxHeap = tempMaxHeap;
	}

	@Override
	public E peek() throws NoSuchElementException {
		//return first item in array(largest item)
		return maxHeap[0];
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
