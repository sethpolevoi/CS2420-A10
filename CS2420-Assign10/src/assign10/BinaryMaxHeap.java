package assign10;

import java.util.Comparator;
import java.util.NoSuchElementException;

import java.util.List;

public class BinaryMaxHeap<E extends Comparable<E>> implements PriorityQueue<E>{
	
	private E maxHeap[];
	private Comparator<? super E> cmp;
	private int size;//how many elements are in the array
	private int capacity;//how many elements could be in the array, the maximum amount
	
	// This constructor creates an empty BinaryMax Heap and assumes
	//that natural ordering (comparable) will be used for comparisons.	
	 
	public BinaryMaxHeap() {
		maxHeap = (E[]) new Object[10];
		cmp = null;
		
	}
	
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		
	}
	
	public BinaryMaxHeap(List<? extends E> numList) {
		
	}
	
	public BinaryMaxHeap(List<? extends E> numList, Comparator<? super E> cmp) {
		
	}

	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param item
	 */
	@Override
	public void add(E item) {
		
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
	
	private int leftChildIndex(int parentIndex) {
		int index = (parentIndex * 2) + 1;
		if (index > size) {
			return parentIndex;
		}
		return index;
	}
	
	private int rightChildIndex(int parentIndex) {
		int index = (parentIndex * 2) + 2;
		if (index > size) {
			return parentIndex;
		}
		return index;
	}
	
	private int parentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}

	/**
	 * Returns, but does not remove, the maximum item this priority queue.
	 * O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		//return first item in array(largest item)
		return maxHeap[0];
	}

	/**
	 * Returns and removes the maximum item this priority queue.
	 * O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E extractMax() throws NoSuchElementException {
		if( isEmpty() ) {
			throw new NoSuchElementException("BinaryMaxHeap is Empty");
		}
		E maxVal = peek();
		removeMax();
		return maxVal;
	}
	
	private boolean removeMax() {
		//replace max with last item on last level
		maxHeap[0] = maxHeap[size-1];
		
		//remove last item
		size--;
		
		//parent is currently the top of the heap
		int parentIndex = 0;
		
		//compare parent with left and right child
		int leftCompare = leftCompare(parentIndex);
		int rightCompare = rightCompare(parentIndex);
		
		//while parent is less than either of its children
		while( leftCompare < 0 || rightCompare < 0) {
			//if left child is 
			if(leftCompare > rightCompare) {
				swapLeft(parentIndex);
				parentIndex = leftChildIndex(parentIndex);
			}else if(leftCompare < rightCompare){
				swapRight(parentIndex);
				parentIndex = rightChildIndex(parentIndex);
			}else {
				break;
			}
			leftCompare = leftCompare(parentIndex);
			rightCompare = rightCompare(parentIndex);
		}
		return false;
	}
	
	private int leftCompare(int parentIndex) {
		return compareItems(maxHeap[parentIndex], maxHeap[leftChildIndex(parentIndex)] );
	}
	
	private int rightCompare(int parentIndex) {
		return compareItems(maxHeap[parentIndex], maxHeap[rightChildIndex(parentIndex)] );
	}
	
	private void swapLeft(int parentIndex)	{
		E holder = maxHeap[parentIndex];
		maxHeap[parentIndex] = maxHeap[leftChildIndex(parentIndex)];
		maxHeap[leftChildIndex(parentIndex)] = holder;
	}
	
	private void swapRight(int parentIndex)	{
		E holder = maxHeap[parentIndex];
		maxHeap[parentIndex] = maxHeap[rightChildIndex(parentIndex)];
		maxHeap[rightChildIndex(parentIndex)] = holder;
	}
	
	private int compareItems(E item1, E item2) {
		if (cmp == null) {
			return item1.compareTo(item2);
		}
		return cmp.compare(item1, item2);
	}

	/**
	 * Returns the number of items in this priority queue.
	 * O(1)
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this priority queue is empty, false otherwise.
	 * O(1)
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Empties this priority queue of items.
	 * O(1)
	 */
	@Override
	public void clear() {
		maxHeap = (E[]) new Object[10];
		size = 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public static void main(String[] args) {
//		BinaryMaxHeap<Integer> BH = new BinaryMaxHeap<>();
//		Integer o1 = 13;
//		Integer o2 = 14;
//		System.out.println( BH.compareItems(o1,o2) );
//	}


}
