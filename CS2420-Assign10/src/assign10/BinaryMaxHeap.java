package assign10;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import java.util.List;

public class BinaryMaxHeap<E> implements PriorityQueue<E> {
	
	private E maxHeap[];
	private Comparator<? super E> cmp;
	private int size;//how many elements are in the array
	private int capacity;//how many elements could be in the array, the maximum amount
	
	// This constructor creates an empty BinaryMax Heap and assumes
	//that natural ordering (comparable) will be used for comparisons.	
	@SuppressWarnings({ "unchecked"}) 
	public BinaryMaxHeap() {
		maxHeap = (E []) new Object[10];
		cmp = null;
		size = 0;
		capacity = 10;
		
	}
	
	@SuppressWarnings({ "unchecked"}) 
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		maxHeap = (E[]) new Object[10];
		this.cmp = cmp;
		size = 0;
		capacity = 10;
	}
	
	@SuppressWarnings({ "unchecked"}) 
	public BinaryMaxHeap(List<? extends E> numList) {
		cmp = null;
		maxHeap = (E[]) new Object[numList.size()];
		size = 0;
		capacity = numList.size();
		buildHeap(numList);
	}
	
	@SuppressWarnings({ "unchecked"}) 
	public BinaryMaxHeap(List<? extends E> numList, Comparator<? super E> cmp) {
		this.cmp = cmp;
		maxHeap = (E[]) new Object[numList.size()];
		size = 0;
		capacity = numList.size();
		buildHeap(numList);
	}

	/**
	 * Adds the given item to this priority queue.
	 * O(1) in the average case, O(log N) in the worst case
	 * 
	 * @param item
	 */
	@Override
	public void add(E item) {
		
		//If array is too small for another item, grow it.
				if(size+1 > capacity) {
					growArray();
				}
				//Add item to last index in current array
				maxHeap[size] = item;
				//Percolate item to its correct position
				percolateUp();
				//Increment size
				size++;
	}
	
	private void buildHeap(List<? extends E> numList) {
		for(E item : numList) {
			this.add(item);
		}
	}
	
	private void percolateUp() {
		int currentIndex = size;
		//while currentIndex value is greater than its parents value, 
		//execute switch, and update currentIndex to equal the index it was switched with
		while(currentIndex > 0 && innerCompare(maxHeap[currentIndex], maxHeap[parentIndex(currentIndex)]) > 0) {
			//preserve parent value
			E temp = maxHeap[parentIndex(currentIndex)];
			//copy child value to parent index
			maxHeap[parentIndex(currentIndex)] = maxHeap[currentIndex];
			//copy parent value to child index
			maxHeap[currentIndex] = temp;
			
			//update current index so we know examine at a level higher
			currentIndex = parentIndex(currentIndex);
		}
		
	}
	
	@SuppressWarnings("unchecked")
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
		//if heap is empty, throw error
		if( isEmpty() ) {
			throw new NoSuchElementException("BinaryMaxHeap is Empty");
		}
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
		//if heap is empty, throw error
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
		int childsCompare = childsCompare(parentIndex);
		
		//swap parent until it's in the correct location
		//while parent is less than either of its children
		while( leftCompare < 0 || rightCompare < 0) {
			//if left child is smaller than right child
			if( childsCompare < 0) {
				swapLeft(parentIndex);
				parentIndex = leftChildIndex(parentIndex);
			}else if( childsCompare > 0){
				swapRight(parentIndex);
				parentIndex = rightChildIndex(parentIndex);
			}else {//they are equal
				break;
			}
			childsCompare = childsCompare(parentIndex);
			leftCompare = leftCompare(parentIndex);
			rightCompare = rightCompare(parentIndex);
		}
		return false;
	}
	
	/**
	 * 
	 * 
	 * @param parentIndex
	 * @return				negative if parent is less than left child,
	 * 						positive if parent is greater than left child
	 */
	private int leftCompare(int parentIndex) {
		return innerCompare(maxHeap[parentIndex], maxHeap[leftChildIndex(parentIndex)] );
	}
	
	private int rightCompare(int parentIndex) {
		return innerCompare(maxHeap[parentIndex], maxHeap[rightChildIndex(parentIndex)] );
	}
	
	private int childsCompare(int parentIndex)	{
		return -innerCompare(maxHeap[leftChildIndex(parentIndex)], maxHeap[rightChildIndex(parentIndex)] );
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
	
	@SuppressWarnings("unchecked")
	private int innerCompare(E item1, E item2) {
		if (cmp == null) {
			return ((Comparable<? super E>)item1).compareTo(item2);
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
		size = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] returnArray = new Object[this.size];
		for(int i = 0; i < this.size; i++) {
			returnArray[i] = maxHeap[i];
		}
		return returnArray;
	}
	
// 	public static void main(String[] args) {
// 		BinaryMaxHeap<Integer> BH = new BinaryMaxHeap<Integer>();
// 		System.out.println( Arrays.toString( BH.toArray() ) );
// 		BH.add(13);
// 		System.out.println( Arrays.toString( BH.toArray() ) );
// 		BH.add(14);
// 		System.out.println( Arrays.toString( BH.toArray() ) );
// 		BH.add(-2);
// 		System.out.println( Arrays.toString( BH.toArray() ) );
// 		BH.add(48);
// 		System.out.println( Arrays.toString( BH.toArray() ) );
// 		System.out.println( BH.extractMax() );
// 		System.out.println( Arrays.toString( BH.toArray() ) );
// 		System.out.println( BH.extractMax() );
// 		System.out.println( Arrays.toString( BH.toArray() ) );
// 		System.out.println( BH.extractMax() );
// 		System.out.println( Arrays.toString( BH.toArray() ) );
// 		System.out.println( BH.extractMax() );
// 		System.out.println( Arrays.toString( BH.toArray() ) );	
// 	}


}
