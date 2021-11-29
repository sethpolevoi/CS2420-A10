package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.lang.Math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class FindKLargestTest {
	
	@BeforeEach
	void setUp() throws Exception {
		

	}
//----------------------Test Find KLargestHeap ----------------------
	@Test
	void testHeapNaturalOrderingPosNumbers() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(3);
		
		List<Integer> testList = FindKLargest.findKLargestHeap(list, 3);
		
		assertEquals(testList.get(0), 11);
		assertEquals(testList.get(1), 9);
		assertEquals(testList.get(2), 7);
	}
	
	@Test
	void testHeapNaturalOrderingNegNumbers() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(-7);
		list.add(-9);
		list.add(-11);
		list.add(-3);
		
		List<Integer> testList = FindKLargest.findKLargestHeap(list, 3);
		
		assertEquals(testList.get(0), -3);
		assertEquals(testList.get(1), -7);
		assertEquals(testList.get(2), -9);
	}
	
	@Test
	void testHeapNaturalOrderingNegAndPosNumbers() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(-7);
		list.add(9);
		list.add(-11);
		list.add(3);
		
		List<Integer> testList = FindKLargest.findKLargestHeap(list, 3);
		
		assertEquals(testList.get(0), 9);
		assertEquals(testList.get(1), 3);
		assertEquals(testList.get(2), -7);
	}
	
	@Test
	void testHeapNaturalOrderingKEquals0() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(3);
		
		List<Integer> testList = FindKLargest.findKLargestHeap(list, 0);
		
		assertEquals(testList.toString(), "[]");
	}
	
	@Test
	void testHeapNaturalOrderingEmptyList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		List<Integer> testList = FindKLargest.findKLargestHeap(list, 0);
		
		assertEquals(testList.toString(), "[]");
	}
	
	@Test
	void testHeapNaturalOrderingInvalidKPositive() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(3);
		
		assertThrows(IllegalArgumentException.class, () -> {
			FindKLargest.findKLargestHeap(list, 10);
		});
	}
	
	@Test
	void testHeapNaturalOrderingInvalidKNegative() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(3);
		
		assertThrows(IllegalArgumentException.class, () -> {
			FindKLargest.findKLargestHeap(list, -1);
		});
	}
	
	
//-
	@Test
	void testHeapWithComparatorPosNumbers() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(3);
		
		OrderByIntAbsoluteValue cmp = new OrderByIntAbsoluteValue();
		List<Integer> testList = FindKLargest.findKLargestHeap(list, 3, cmp);
		
		assertEquals(testList.get(0), 11);
		assertEquals(testList.get(1), 9);
		assertEquals(testList.get(2), 7);
	}
	
	@Test
	void testHeapWithComparatoringNegNumbers() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(-7);
		list.add(-9);
		list.add(-11);
		list.add(-3);
		
		OrderByIntAbsoluteValue cmp = new OrderByIntAbsoluteValue();
		List<Integer> testList = FindKLargest.findKLargestHeap(list, 3, cmp);
		
		assertEquals(testList.get(0), -11);
		assertEquals(testList.get(1), -9);
		assertEquals(testList.get(2), -7);
	}
	

//----------------------Stop Tests Find KLargestHeap --------------------
	

//----------------------Start Tests Find KLargestSort -------------------
	@Test
	void testSortNaturalOrderingPosNumbers() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(3);
		
		List<Integer> testList = FindKLargest.findKLargestSort(list, 3);
		
		assertEquals(testList.get(0), 11);
		assertEquals(testList.get(1), 9);
		assertEquals(testList.get(2), 7);
	}
	
	@Test
	void testSortNaturalOrderingNegNumbers() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(-7);
		list.add(-9);
		list.add(-11);
		list.add(-3);
		
		List<Integer> testList = FindKLargest.findKLargestSort(list, 3);
		
		assertEquals(testList.get(0), -3);
		assertEquals(testList.get(1), -7);
		assertEquals(testList.get(2), -9);
	}
	
	@Test
	void testSortNaturalOrderingNegAndPosNumbers() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(-7);
		list.add(9);
		list.add(-11);
		list.add(3);
		
		List<Integer> testList = FindKLargest.findKLargestSort(list, 3);
		
		assertEquals(testList.get(0), 9);
		assertEquals(testList.get(1), 3);
		assertEquals(testList.get(2), -7);
	}
	
	@Test
	void testSortNaturalOrderingKEquals0() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(3);
		
		List<Integer> testList = FindKLargest.findKLargestSort(list, 0);
		
		assertEquals(testList.toString(), "[]");
	}
	
	@Test
	void testSortNaturalOrderingEmptyList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		List<Integer> testList = FindKLargest.findKLargestSort(list, 0);
		
		assertEquals(testList.toString(), "[]");
	}
	
	@Test
	void testSortNaturalOrderingInvalidKPositive() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(3);
		
		assertThrows(IllegalArgumentException.class, () -> {
			FindKLargest.findKLargestSort(list, 10);
		});
	}
	
	@Test
	void testSortNaturalOrderingInvalidKNegative() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(7);
		list.add(9);
		list.add(11);
		list.add(3);
		
		assertThrows(IllegalArgumentException.class, () -> {
			FindKLargest.findKLargestSort(list, -1);
		});
	}
	
	
	
//----------------------Stop Tests Find KLargestSort --------------------	
	
	protected class OrderByA implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			int aCount1 = 0;
			int aCount2 = 0;
			
			for(int i = 0; i < o1.length(); i++) {
				if(o1.charAt(i) == 'a' || o1.charAt(i) == 'A') {
					aCount1++;
				}
			}
			
			for(int i = 0; i < o2.length(); i++) {
				if(o2.charAt(i) == 'a' || o2.charAt(i) == 'A') {
					aCount2++;
				}
			}
				
			if(aCount1 > aCount2) {
				return 1;
			}
			else if( aCount1 < aCount2) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	
	protected class OrderByIntAbsoluteValue implements Comparator<Integer> {
				
		@Override
		public int compare(Integer o1, Integer o2) {
			int val1 = (int) o1;
			int val2 = (int) o2;
			if(Math.abs(val1) > Math.abs(val2)) {
				return 1;
			}
			
			else if(Math.abs(val1) < Math.abs(val2)) {
				return -1;
			}
			
			else return 0;
			
		}
	}
	
}
