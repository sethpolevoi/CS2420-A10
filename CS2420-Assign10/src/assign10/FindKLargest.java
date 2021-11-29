package assign10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in
 * a list.
 * 
 * @author Erin Parker & Mike Phelps & Seth Polevoi
 * @version ??
 */
public class FindKLargest {

	/**
	 * Determines the k largest items in the given list, using a binary max heap and
	 * the natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k)
			throws IllegalArgumentException {
		// if k is negative or larger than the number of items in the argument list,
		// throw
		// an IllegalArgumentException
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException();
		}
		// Create a BinaryMaxHeap using items
		BinaryMaxHeap<E> heap = new BinaryMaxHeap<E>(items);
		// Create return list
		List<E> list = new ArrayList<E>();

		// Add current largest item to return list k times
		for (int i = 0; i < k; i++) {
			list.add(heap.extractMax());
		}

		return list;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @param cmp   - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp)
			throws IllegalArgumentException {
		// if k is negative or larger than the number of items in the argument list,
		// throw
		// an IllegalArgumentException
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException();
		}
		// Create a BinaryMaxHeap using items and passes in comparator
		BinaryMaxHeap<E> heap = new BinaryMaxHeap<E>(items, cmp);
		// Create return list
		List<E> list = new ArrayList<E>();

		// Add current largest item to return list k times
		for (int i = 0; i < k; i++) {
			list.add(heap.extractMax());
		}

		return list;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine
	 * and the natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k)
			throws IllegalArgumentException {
		// if k is negative or larger than the number of items in the argument list,
		// throw
		// an IllegalArgumentException
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException();
		}

		// Create copy of argument list so that argument list is not changed
		ArrayList<E> listCopy = new ArrayList<E>();
		// Create list which will be returned
		ArrayList<E> returnList = new ArrayList<E>();

		// Copy items into listCopy
		for (int i = 0; i < items.size(); i++) {
			listCopy.add(items.get(i));
		}

		// Sort list copy using java's sort routine with comparable (natural ordering)
		listCopy.sort(null);

		// Because java's sort routine will sort the list in ascending order
		// (and we want to return items in descending order) we will start at
		// the back of the java sorted array and move towards the front (k times)
		int index = listCopy.size() - 1;
		for (int j = 0; j < k; j++) {
			returnList.add(listCopy.get(index));
			index--;
		}

		return returnList;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @param cmp   - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp)
			throws IllegalArgumentException {
		// if k is negative or larger than the number of items in the argument list,
		// throw
		// an IllegalArgumentException
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException();
		}

		// Create copy of argument list so that argument list is not changed
		ArrayList<E> listCopy = new ArrayList<E>();
		// Create list which will be returned
		ArrayList<E> returnList = new ArrayList<E>();

		// Copy items into listCopy
		for (int i = 0; i < items.size(); i++) {
			listCopy.add(items.get(i));
		}

		// Sort list copy using java's sort routine with argument comparator
		listCopy.sort(cmp);

		// Because java's sort routine will sort the list in ascending order
		// (and we want to return items in descending order) we will start at
		// the back of the java sorted array and move towards the front (k times)
		int index = listCopy.size() - 1;
		for (int j = 0; j < k; j++) {
			returnList.add(listCopy.get(index));
			index--;
		}

		return returnList;
	}
}
