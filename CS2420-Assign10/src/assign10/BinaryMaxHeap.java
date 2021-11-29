package assign10;

import java.util.Comparator;
import java.util.NoSuchElementException;

import java.util.List;

/**
 * This interface represents the priority queue abstract data type, defining the
 * operations and running times expected of any data structure used to implement
 * a priority queue.
 * 
 * NOTE: The item with the highest priority is the "maximum" item.
 * 
 * @author SETH POLEVOI and MIKE PHELPS
 *
 * @param <E>
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {

	private E maxHeap[];
	private Comparator<? super E> cmp;
	private int size;// how many elements are in the array
	private int capacity;// how many elements could be in the array, the maximum amount

	/**
	 * This constructor creates an empty BinaryMax Heap and assumes that natural
	 * ordering (comparable) will be used for comparisons.
	 */
	@SuppressWarnings({ "unchecked" })
	public BinaryMaxHeap() {
		maxHeap = (E[]) new Object[10];
		cmp = null;
		size = 0;
		capacity = 10;

	}

	/**
	 * This constructor creates an empty BinaryMax Heap uses the provided comparator
	 * for priority sorting
	 * 
	 * @param cmp Comparator to sort between objects E
	 */
	@SuppressWarnings({ "unchecked" })
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		maxHeap = (E[]) new Object[10];
		this.cmp = cmp;
		size = 0;
		capacity = 10;
	}

	/**
	 * This constructor creates a BinaryMax Heap using the provided List as the
	 * initial data entry
	 * 
	 * @param numList list of elements to initialize the binaryMaxHeap in
	 */
	@SuppressWarnings({ "unchecked" })
	public BinaryMaxHeap(List<? extends E> numList) {
		cmp = null;
		maxHeap = (E[]) new Object[numList.size()];
		size = 0;
		capacity = numList.size();
		buildHeap(numList);
	}

	/**
	 * This constructor creates an empty BinaryMax Heap using the provided List as
	 * the initial data entry and the provided comparator for priority sorting
	 * 
	 * @param numList List of elements to initialize the binaryMaxHeap in
	 * @param cmp     Comparator to sort between objects E
	 */
	@SuppressWarnings({ "unchecked" })
	public BinaryMaxHeap(List<? extends E> numList, Comparator<? super E> cmp) {
		this.cmp = cmp;
		maxHeap = (E[]) new Object[numList.size()];
		size = 0;
		capacity = numList.size();
		buildHeap(numList);
	}

	/**
	 * Adds the given item to this priority queue. O(1) in the average case, O(log
	 * N) in the worst case
	 * 
	 * @param item Item to add to the heap
	 */
	@Override
	public void add(E item) {

		// If array is too small for another item, grow it.
		if (size + 1 > capacity) {
			growArray();
		}
		// Add item to last index in current array
		maxHeap[size] = item;
		// Percolate item to its correct position
		percolateUp();
		// Increment size
		size++;
	}

	/**
	 * Add's each value in the given list to the heap
	 * 
	 * @param numList List of elements to initialize the binaryMaxHeap in
	 */
	private void buildHeap(List<? extends E> numList) {
		for (E item : numList) {
			this.add(item);
		}
	}

	private void percolateUp() {
		int currentIndex = size;
		// while currentIndex value is greater than its parents value,
		// execute switch, and update currentIndex to equal the index it was switched
		// with
		while (currentIndex > 0 && innerCompare(maxHeap[currentIndex], maxHeap[parentIndex(currentIndex)]) > 0) {
			// preserve parent value
			E temp = maxHeap[parentIndex(currentIndex)];
			// copy child value to parent index
			maxHeap[parentIndex(currentIndex)] = maxHeap[currentIndex];
			// copy parent value to child index
			maxHeap[currentIndex] = temp;

			// update current index so we know examine at a level higher
			currentIndex = parentIndex(currentIndex);
		}

	}

	/**
	 * Grows the heap storage by twice the capacity and copies over all the old
	 * values to the new larger heap
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		// create a new array twice the capacity as the current array
		capacity = capacity * 2;
		E[] tempMaxHeap = (E[]) new Object[capacity];

		// copy over each element
		for (int index = 0; index < size; index++) {
			tempMaxHeap[index] = maxHeap[index];
		}

		// switch references so larger array is the new heap
		maxHeap = tempMaxHeap;
	}

	/**
	 * Returns the index of the location of a left child of a given parent index
	 * 
	 * @param parentIndex Index of parent value
	 * @return Returns index value of left child or returns the same index as the
	 *         parent, if the parent has no left child
	 */
	private int leftChildIndex(int parentIndex) {
		int index = (parentIndex * 2) + 1;
		// if child doesn't exist, return parent
		if (index > size) {
			return parentIndex;
		}
		return index;
	}

	/**
	 * Returns the index of the location of a right child of a given parent index
	 * 
	 * @param parentIndex Index of parent value
	 * @return Returns index value of right child or returns the same index as the
	 *         parent, if the parent has no right child
	 */
	private int rightChildIndex(int parentIndex) {
		int index = (parentIndex * 2) + 2;
		// if child doesn't exist, return parent
		if (index > size) {
			return parentIndex;
		}
		return index;
	}

	/**
	 * Returns the index of the location of the parent value, given the index
	 * location of one of the children
	 * 
	 * @param childIndex Index of child value
	 * @return Returns index value of the parent of the given child
	 */
	private int parentIndex(int childIndex) {
		return (childIndex - 1) / 2;
	}

	/**
	 * Returns, but does not remove, the maximum item this priority queue. O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E peek() throws NoSuchElementException {
		// if heap is empty, throw error
		if (isEmpty()) {
			throw new NoSuchElementException("BinaryMaxHeap is Empty");
		}
		// return first item in array(largest item)
		return maxHeap[0];
	}

	/**
	 * Returns and removes the maximum item this priority queue. O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	@Override
	public E extractMax() throws NoSuchElementException {
		// if heap is empty, throw error
		if (isEmpty()) {
			throw new NoSuchElementException("BinaryMaxHeap is Empty");
		}
		// store max value to return later
		E maxVal = peek();

		// remove and reorganize the heap
		removeMax();

		return maxVal;
	}

	/**
	 * Deletes the maximum value and reorganizes the heap to remain a priority queue
	 * 
	 */
	private void removeMax() {
		// replace max with last item on last level
		maxHeap[0] = maxHeap[size - 1];

		// remove last item
		size--;

		// parent is currently the top of the heap
		int parentIndex = 0;

		// compare parent with left and right child
		int leftCompare = leftCompare(parentIndex);
		int rightCompare = rightCompare(parentIndex);
		int childsCompare = childsCompare(parentIndex);

		// swap parent until it's in the correct location
		// while parent is less than either of its children
		while (leftCompare < 0 || rightCompare < 0) {
			// if left child is smaller than right child
			if (childsCompare < 0) {
				swapLeft(parentIndex);
				parentIndex = leftChildIndex(parentIndex);
			} else {// if right child is smaller or both childs are equal
				swapRight(parentIndex);
				parentIndex = rightChildIndex(parentIndex);
			}
			childsCompare = childsCompare(parentIndex);
			leftCompare = leftCompare(parentIndex);
			rightCompare = rightCompare(parentIndex);
		}
	}

	/**
	 * Compares the values of a parent and it's left child
	 * 
	 * @param parentIndex Index of the location of the parent
	 * @return negative if parent is less than left child, positive if parent is
	 *         greater than the left child
	 */
	private int leftCompare(int parentIndex) {
		return innerCompare(maxHeap[parentIndex], maxHeap[leftChildIndex(parentIndex)]);
	}

	/**
	 * Compares the values of a parent and it's right child
	 * 
	 * @param parentIndex Index of the location of the parent
	 * @return negative if parent is less than right child, positive if parent is
	 *         greater than the right child
	 */
	private int rightCompare(int parentIndex) {
		return innerCompare(maxHeap[parentIndex], maxHeap[rightChildIndex(parentIndex)]);
	}

	/**
	 * Compares the values of a parent's children
	 * 
	 * @param parentIndex Index of the location of the parent
	 * @return negative if left child is less than right child, positive if the left
	 *         child is greater than the right child
	 */
	private int childsCompare(int parentIndex) {
		return -innerCompare(maxHeap[leftChildIndex(parentIndex)], maxHeap[rightChildIndex(parentIndex)]);
	}

	/**
	 * Swaps the left child with the parent child
	 * 
	 * @param parentIndex Index of the location of the parent
	 */
	private void swapLeft(int parentIndex) {
		E holder = maxHeap[parentIndex];
		maxHeap[parentIndex] = maxHeap[leftChildIndex(parentIndex)];
		maxHeap[leftChildIndex(parentIndex)] = holder;
	}

	/**
	 * Swaps the right child with the parent child
	 * 
	 * @param parentIndex Index of the location of the parent
	 */
	private void swapRight(int parentIndex) {
		E holder = maxHeap[parentIndex];
		maxHeap[parentIndex] = maxHeap[rightChildIndex(parentIndex)];
		maxHeap[rightChildIndex(parentIndex)] = holder;
	}

	@SuppressWarnings("unchecked")
	private int innerCompare(E item1, E item2) {
		if (cmp == null) {
			return ((Comparable<? super E>) item1).compareTo(item2);
		}
		return cmp.compare(item1, item2);
	}

	/**
	 * Returns the number of items in this priority queue. O(1)
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this priority queue is empty, false otherwise. O(1)
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Empties this priority queue of items. O(1)
	 */
	@Override
	public void clear() {
		size = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] returnArray = new Object[this.size];
		for (int i = 0; i < this.size; i++) {
			returnArray[i] = maxHeap[i];
		}
		return returnArray;
	}

}
