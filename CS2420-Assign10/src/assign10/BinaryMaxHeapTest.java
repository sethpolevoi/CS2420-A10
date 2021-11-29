package assign10;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryMaxHeapTest {

	BinaryMaxHeap<Integer> BHIntEmpty = new BinaryMaxHeap<Integer>();
	BinaryMaxHeap<Integer> BHInt = new BinaryMaxHeap<Integer>();
	BinaryMaxHeap<Integer> BHIntBig = new BinaryMaxHeap<Integer>();

	BinaryMaxHeap<String> BHStringEmpty = new BinaryMaxHeap<String>();
	BinaryMaxHeap<String> BHStringCmp = new BinaryMaxHeap<String>(new OrderByA());

	// this code is stolen from the internet
	private static String generateWord(int wordSize) {
		// just lowercase
		int bottomLim = 97;
		int topLim = 122;

		Random random = new Random();

		String randWord = random.ints(bottomLim, topLim + 1).limit(wordSize)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return randWord;
	}

	// this code is stolen from the internet
	private static String generateWord2(int wordSize) {
		// just lowercase
		int bottomLim = 97;
		int topLim = 104;

		Random random = new Random();

		String randWord = random.ints(bottomLim, topLim + 1).limit(wordSize)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return randWord;
	}

	@BeforeEach
	void setUp() throws Exception {
		BHInt.add(13);
		BHInt.add(14);
		BHInt.add(-2);
		BHInt.add(48);
		BHInt.add(1);
		BHInt.add(15);

		BHIntBig.add(13);
		BHIntBig.add(14);
		BHIntBig.add(-2);
		BHIntBig.add(48);
		BHIntBig.add(1);
		BHIntBig.add(15);
		BHIntBig.add(10);
		BHIntBig.add(50);
		BHIntBig.add(-7);
		BHIntBig.add(49);
		BHIntBig.add(60);
		BHIntBig.add(22);
	}

	@Test
	void testAddEmpty() {
		assertTrue(true);
	}

	// -----------------------extractMax Tests Begin----------------------------

	@Test
	void testExtractMaxWithSomeDuplicates() {
		Random rand = new Random();
		ArrayList<Integer> randVals = new ArrayList<>(1000);
		for (int index = 0; index < 10; index++) {
			int val = rand.nextInt(5);
			randVals.add(val);
			BHIntEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());

		for (int index = 0; index < 10; index++) {
			int maxRemoved = BHIntEmpty.extractMax();
			assertEquals(randVals.get(index), maxRemoved);
		}
	}

	@Test
	void testExtractMaxWithManyDuplicates() {
		Random rand = new Random();
		ArrayList<Integer> randVals = new ArrayList<>(1000);
		for (int index = 0; index < 100; index++) {
			int val = rand.nextInt(10);
			randVals.add(val);
			BHIntEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());

		for (int index = 0; index < 100; index++) {
			int maxRemoved = BHIntEmpty.extractMax();
			assertEquals(randVals.get(index), maxRemoved);
		}
	}

	@Test
	void testExtractMaxBig() {
		Random rand = new Random();
		ArrayList<Integer> randVals = new ArrayList<>(1000);
		for (int index = 0; index < 10000; index++) {
			int val = rand.nextInt(10000);
			randVals.add(val);
			BHIntEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());

		for (int index = 0; index < 10000; index++) {
			int maxRemoved = BHIntEmpty.extractMax();
			assertEquals(randVals.get(index), maxRemoved);
		}
	}

	@Test
	void testExtractMaxNegativesSmall() {
		Random rand = new Random();
		ArrayList<Integer> randVals = new ArrayList<>(1000);
		for (int index = 0; index < 10; index++) {
			int val = rand.nextInt(10) - 5000;
			randVals.add(val);
			BHIntEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());

		for (int index = 0; index < 10; index++) {
			int maxRemoved = BHIntEmpty.extractMax();
			assertEquals(randVals.get(index), maxRemoved);
		}
	}

	@Test
	void testExtractMaxNegativesBig() {
		Random rand = new Random();
		ArrayList<Integer> randVals = new ArrayList<>(1000);
		for (int index = 0; index < 10000; index++) {
			int val = rand.nextInt(10000) - 5000;
			randVals.add(val);
			BHIntEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());

		for (int index = 0; index < 10000; index++) {
			int maxRemoved = BHIntEmpty.extractMax();
			assertEquals(randVals.get(index), maxRemoved);
		}
	}

	@Test
	void testExtractMaxOne() {
		Random rand = new Random();
		ArrayList<Integer> randVals = new ArrayList<>(1000);
		for (int index = 0; index < 1; index++) {
			int val = rand.nextInt(100);
			randVals.add(val);
			BHIntEmpty.add(val);
		}

		for (int index = 0; index < 1; index++) {
			int maxRemoved = BHIntEmpty.extractMax();
			assertEquals(randVals.get(index), maxRemoved);
		}
	}

	@Test
	void testExtractMaxEmpty() {
		assertThrows(NoSuchElementException.class, () -> {
			BHIntEmpty.extractMax();
		});
	}

	@Test
	void testExtractMaxNewEmpty() {
		Random rand = new Random();
		ArrayList<Integer> randVals = new ArrayList<>(1000);
		for (int index = 0; index < 10000; index++) {
			int val = rand.nextInt(10000);
			randVals.add(val);
			BHIntEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());

		for (int index = 0; index < 10000; index++) {
			int maxRemoved = BHIntEmpty.extractMax();
			assertEquals(randVals.get(index), maxRemoved);
		}
		assertThrows(NoSuchElementException.class, () -> {
			BHIntEmpty.extractMax();
		});
	}

	@Test
	void testExtractMaxAfterClear1() {
		Random rand = new Random();
		ArrayList<Integer> randVals = new ArrayList<>(1000);
		for (int index = 0; index < 10000; index++) {
			int val = rand.nextInt(10000);
			randVals.add(val);
			BHIntEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());

		for (int index = 0; index < 100; index++) {
			int maxRemoved = BHIntEmpty.extractMax();
			assertEquals(randVals.get(index), maxRemoved);
		}
		BHIntEmpty.clear();
		assertThrows(NoSuchElementException.class, () -> {
			BHIntEmpty.extractMax();
		});
	}

	@Test
	void testExtractMaxAfterClear2() {
		BHIntBig.clear();
		assertThrows(NoSuchElementException.class, () -> {
			BHIntBig.extractMax();
		});
	}

	@Test
	void testExtractMaxAfterAdds() {
		Random rand = new Random();
		ArrayList<Integer> randVals = new ArrayList<>(10000);
		for (int index = 0; index < 10000; index++) {
			int val = rand.nextInt(10000);
			randVals.add(val);
			BHIntEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());

		for (int index = 0; index < 5000; index++) {
			int maxRemoved = BHIntEmpty.extractMax();
			assertEquals(randVals.get(index), maxRemoved);
		}

		for (int k = 0; k < 5000; k++) {
			randVals.remove(0);
		}

		for (int index = 0; index < 5000; index++) {
			int val = rand.nextInt(10000);
			randVals.add(val);
			BHIntEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());

		for (int index = 0; index < 10000; index++) {
			int maxRemoved = BHIntEmpty.extractMax();
			assertEquals(randVals.get(index), maxRemoved);
		}
	}

	@Test
	void testExtractMaxStringsSmall() {
		ArrayList<String> randVals = new ArrayList<>(10000);
		for (int index = 0; index < 10; index++) {
			String val = generateWord(5);
			randVals.add(val);
			BHStringEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());
//		System.out.println( randVals );
//		System.out.println( Arrays.toString( BHStringEmpty.toArray() ) );

		for (int index = 0; index < 10; index++) {
			String maxRemoved = BHStringEmpty.extractMax();
//			System.out.println("MaxVal: " + randVals.get(index) + " MaxRemoved: " + maxRemoved);
			assertEquals(randVals.get(index), maxRemoved);
//			System.out.println( Arrays.toString( BHStringEmpty.toArray() ) );
		}
	}

	@Test
	void testExtractMaxStringsBig() {
		ArrayList<String> randVals = new ArrayList<>(10000);
		for (int index = 0; index < 10000; index++) {
			String val = generateWord(10);
			randVals.add(val);
			BHStringEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());
//		System.out.println( randVals );
//		System.out.println( Arrays.toString( BHStringEmpty.toArray() ) );

		for (int index = 0; index < 10000; index++) {
			String maxRemoved = BHStringEmpty.extractMax();
//			System.out.println("MaxVal: " + randVals.get(index) + " MaxRemoved: " + maxRemoved);
			assertEquals(randVals.get(index), maxRemoved);
		}
	}

	@Test
	void testExtractMaxComparatorStringsSmall() {
		ArrayList<String> randVals = new ArrayList<>();
		for (int index = 0; index < 10; index++) {
			String val = generateWord2(10);
			randVals.add(val);
			BHStringCmp.add(val);
		}

		Collections.sort(randVals, new OrderByA());
//		System.out.println(randVals);
//		System.out.println(Arrays.toString(BHStringCmp.toArray()));

		for (int index = 0; index < 10; index++) {
			String maxRemoved = BHStringCmp.extractMax();
//			System.out.println("MaxVal: " + randVals.get(9 - index) + "\tMaxRemoved: " + maxRemoved);
			assertEquals(randVals.get(9 - index), maxRemoved);
//			System.out.println(Arrays.toString(BHStringCmp.toArray()));
		}
	}

	@Test
	void testExtractMaxComparatorStringsBig() {
		ArrayList<String> randVals = new ArrayList<>(10000);
		for (int index = 0; index < 10000; index++) {
			String val = generateWord2(25);
			randVals.add(val);
			BHStringCmp.add(val);
		}

		Collections.sort(randVals, new OrderByA());
//		System.out.println( randVals );
//		System.out.println( Arrays.toString( BHStringCmp.toArray() ) );

		for (int index = 0; index < 10000; index++) {
			String maxRemoved = BHStringCmp.extractMax();
//			System.out.println("MaxVal: " + randVals.get(9-index) + "\tMaxRemoved: " + maxRemoved);
			assertEquals(randVals.get(9999 - index), maxRemoved);
//			System.out.println( Arrays.toString( BHStringCmp.toArray() ) );
		}
	}

	// -----------------------extractMax Tests End----------------------------
	
	// -----------------------add Tests Begin---------------------------------
	
	@Test
	void testAddOneToEmpty() {
		assertTrue(BHIntEmpty.isEmpty()); 
		BHIntEmpty.add(7);
		assertFalse(BHIntEmpty.isEmpty()); 
		assertEquals(BHIntEmpty.extractMax(), 7);
	}
	
	@Test
	void testAddManyPositiveToEmpty() {
		assertTrue(BHIntEmpty.isEmpty()); 
		BHIntEmpty.add(7);
		BHIntEmpty.add(8);
		BHIntEmpty.add(3);
		BHIntEmpty.add(1);
		BHIntEmpty.add(100);
		BHIntEmpty.add(2);
		assertFalse(BHIntEmpty.isEmpty()); 
		assertEquals(BHIntEmpty.size(), 6);
		assertEquals(BHIntEmpty.extractMax(), 100);
	}
	
	@Test
	void testAddManyNegativeToEmpty() {
		assertTrue(BHIntEmpty.isEmpty()); 
		BHIntEmpty.add(-7);
		BHIntEmpty.add(-8);
		BHIntEmpty.add(-3);
		BHIntEmpty.add(-1);
		BHIntEmpty.add(-100);
		BHIntEmpty.add(-2);
		assertFalse(BHIntEmpty.isEmpty()); 
		assertEquals(BHIntEmpty.size(), 6);
		assertEquals(BHIntEmpty.extractMax(), -1);
	}
	
	@Test
	void testAddNewHighToPrePopulatedQueue() {
		assertEquals(BHIntBig.peek(), 60);
		BHIntBig.add(1000);
		assertEquals(BHIntBig.peek(), 1000);
	}
	
	@Test
	void testAddMaxMaintanedAfterAddingLowerNumber() {
		assertEquals(BHIntBig.peek(), 60);
		BHIntBig.add(11);
		assertEquals(BHIntBig.peek(), 60);
	}
	
	@Test
	void testManyAddCorrectOrder(){
		BHIntEmpty.add(9);
		BHIntEmpty.add(88);
		BHIntEmpty.add(1);
		BHIntEmpty.add(2);
		BHIntEmpty.add(4);
		BHIntEmpty.add(-9);
		BHIntEmpty.add(66);
		
		assertEquals(BHIntEmpty.extractMax(), 88);
		assertEquals(BHIntEmpty.extractMax(), 66);
		assertEquals(BHIntEmpty.extractMax(), 9);
		assertEquals(BHIntEmpty.extractMax(), 4);
		assertEquals(BHIntEmpty.extractMax(), 2);
		assertEquals(BHIntEmpty.extractMax(), 1);
		assertEquals(BHIntEmpty.extractMax(), -9);
	}
	
	@Test
	void testAddOneStringToEmpty() {
		assertTrue(BHStringEmpty.isEmpty()); 
		BHStringEmpty.add("Mike");
		assertFalse(BHStringEmpty.isEmpty()); 
		assertEquals(BHStringEmpty.extractMax(), "Mike");
	}
	
	@Test
	void testAddManyStringsToEmpty() {
		assertTrue(BHStringEmpty.isEmpty()); 
		BHStringEmpty.add("Seth");
		BHStringEmpty.add("Mike");
		BHStringEmpty.add("Noah");
		BHStringEmpty.add("Arthur");
		BHStringEmpty.add("Caleb");
		
		assertFalse(BHStringEmpty.isEmpty()); 
		assertEquals(BHStringEmpty.size(), 5);
		assertEquals(BHStringEmpty.extractMax(), "Seth");
	}
	
	@Test
	void testNewHighStringBecomesMax() {
		BHStringEmpty.add("Seth");
		BHStringEmpty.add("Mike");
		BHStringEmpty.add("Noah");
		BHStringEmpty.add("Arthur");
		BHStringEmpty.add("Caleb");
		
		assertEquals(BHStringEmpty.peek(), "Seth");
		BHStringEmpty.add("ZZZ");
		assertEquals(BHStringEmpty.peek(), "ZZZ");
	}
	
	@Test
	void testAddMaxMaintanedAfterAddingLowerString() {
		BHStringEmpty.add("Seth");
		BHStringEmpty.add("Mike");
		BHStringEmpty.add("Noah");
		BHStringEmpty.add("Arthur");
		BHStringEmpty.add("Caleb");
		
		assertEquals(BHStringEmpty.peek(), "Seth");
		BHStringEmpty.add("Adam");
		assertEquals(BHStringEmpty.peek(), "Seth");
	}
	
	// -----------------------add Tests End--------------------------------


	// -----------------------clear Tests Begin----------------------------
	@Test
	void testClearCommon() {
		assertFalse(0 == BHInt.size());
		BHInt.clear();
		assertTrue(0 == BHInt.size());
	}
	
	@Test
	void testClearEmpty() {
		assertTrue(0 == BHIntEmpty.size());
		BHIntEmpty.clear();
		assertTrue(0 == BHIntEmpty.size());
	}
	
	@Test
	void testClearAddClear() {
		assertFalse(0 == BHInt.size());
		BHInt.clear();
		assertTrue(0 == BHInt.size());
		
		Random rand = new Random();
		ArrayList<Integer> randVals = new ArrayList<>(1000);
		for (int index = 0; index < 10000; index++) {
			int val = rand.nextInt(10000);
			randVals.add(val);
			BHInt.add(val);
		}
		
		assertFalse(0 == BHInt.size());
		BHInt.clear();
		assertTrue(0 == BHInt.size());
	}
	// -----------------------clear Tests End----------------------------
	
	
	
	// -----------------------size Tests End----------------------------
	@Test
	void testSizeEmpty() {
		assertTrue(0 == BHIntEmpty.size());
	}
	
	@Test
	void testSizeAfterAddSmall() {
		assertTrue(6 == BHInt.size());
	}
	
	@Test
	void testSizeAfterAddBigSequentially() {
		assertTrue(0 == BHIntEmpty.size());
		
		Random rand = new Random();
		for (int k = 0; k < 10000; k++) {
			int val = rand.nextInt(10000);
			BHIntEmpty.add(val);
			
			assertEquals(k+1, BHIntEmpty.size()	);
		}
	}
	
	@Test
	void testSizeAfterExtractBigSequentially() {
		assertTrue(12 == BHIntBig.size());
		
		Random rand = new Random();
		int testSize = BHIntBig.size();
		
		for (int k = 0; k < 10000; k++) {
			int val = rand.nextInt(10000);
			if(rand.nextInt(8) == 1) {
				BHIntBig.extractMax();
				testSize--;
			}else {
				BHIntBig.add(val);
				testSize++;
			}
			assertEquals(testSize, BHIntBig.size()	);
		}
	}
	// -----------------------size Tests End----------------------------
	
	
	
	// -----------------------peek Tests Begin----------------------------
	@Test
	void testPeekCommon() {
		assertEquals(60, BHIntBig.peek());
	}
	
	@Test
	void testPeekBig() {		
		Random rand = new Random();
		ArrayList<Integer> randVals = new ArrayList<>(1000);
		for (int index = 0; index < 10000; index++) {
			int val = rand.nextInt(10000);
			randVals.add(val);
			BHIntEmpty.add(val);
		}

		Collections.sort(randVals, Collections.reverseOrder());

		for (int index = 0; index < 10000; index++) {
			int maxRemoved = BHIntEmpty.extractMax();
			assertEquals(randVals.get(index), maxRemoved);
		}
	}
	
	@Test
	void testPeekTwiceInRow() {
		int peek1 = BHIntBig.peek();
		BHIntBig.peek();
		BHIntBig.peek();
		BHIntBig.peek();
		int peek2 = BHIntBig.peek();
		assertEquals(peek1, peek2);
	}
	
	@Test
	void testPeekAfterAddSmall() {
		int peek1 = BHIntBig.peek();
		BHIntBig.peek();
		BHIntBig.add(-99);
		int peek2 = BHIntBig.peek();
		assertEquals(peek1, peek2);
	}
	@Test
	void testPeekAfterAddMax() {
		int peek1 = BHIntBig.peek();
		BHIntBig.peek();
		BHIntBig.add(999);
		int peek2 = BHIntBig.peek();
		assertFalse(peek1 == peek2);
		assertEquals(999, peek2);
	}
	
	@Test
	void testPeekAfterExtractMax() {
		int peek1 = BHIntBig.peek();
		assertEquals(BHIntBig.peek(), BHIntBig.extractMax());
		assertEquals(BHIntBig.peek(), BHIntBig.extractMax());
		assertEquals(BHIntBig.peek(), BHIntBig.extractMax());
		assertEquals(BHIntBig.peek(), BHIntBig.extractMax());
		assertFalse( peek1 == BHIntBig.peek() );
	}
	
	@Test
	void testPeekAfterClear() {
		assertEquals( 60, BHIntBig.peek());
		BHIntBig.clear();
		assertThrows(NoSuchElementException.class, () -> {
			BHIntBig.peek();
		});
		
	}
	
	@Test
	void testPeekOnEmpty() {
		assertThrows(NoSuchElementException.class, () -> {
			BHIntEmpty.peek();
		});
	}	
	// -----------------------peek Tests End----------------------------
	
	
	// -----------------------isEmpty Tests Begin----------------------------
	@Test
	void testIsEmptyNoAdds() {
		assertTrue( BHIntEmpty.isEmpty() );
	}
	
	@Test
	void testIsEmptyAfterAdds1() {
		assertFalse( BHInt.isEmpty() );
		assertFalse( BHIntBig.isEmpty() );
	}
	
	@Test
	void testIsEmptyAfterAdds2() {
		assertTrue( BHIntEmpty.isEmpty() );
		BHIntEmpty.add(2);
		assertFalse( BHInt.isEmpty() );
	}
	
	@Test
	void testIsEmptyAfterClear() {
		assertFalse( BHIntBig.isEmpty() );
		BHIntBig.clear();
		assertTrue( BHIntBig.isEmpty() );
	}
	
	@Test
	void testIsEmptyAfterClearThenAdd() {
		assertFalse( BHIntBig.isEmpty() );
		BHIntBig.clear();
		assertTrue( BHIntBig.isEmpty() );
		BHIntBig.add(3);
		assertFalse( BHIntBig.isEmpty() );
		BHIntBig.add(3);
		assertFalse( BHIntBig.isEmpty() );
	}
	
	@Test
	void testIsEmptyExtractMaxFalse() {
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
	}
	
	@Test
	void testIsEmptyExtractMaxTrue() {
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
		assertFalse( BHInt.isEmpty() );
		BHInt.extractMax();
		assertTrue( BHInt.isEmpty() );
	}
	
	@Test
	void testIsEmptyTwiceInRow() {
		assertTrue( BHIntEmpty.isEmpty() );
		assertTrue( BHIntEmpty.isEmpty() );
		assertFalse( BHIntBig.isEmpty() );
		assertFalse( BHIntBig.isEmpty() );
	}
	// -----------------------isEmpty Tests End----------------------------
	
	// -----------------------toArray Tests Begin-------------------------
	
	@Test
	void testToArraySingleItemHeap() {
		BHIntEmpty.add(-7);
		String expected = "[-7]";
		assertEquals(Arrays.toString(BHIntEmpty.toArray()), expected);
	}
	
	@Test
	void testToArraManyItemHeap() {
		BHIntEmpty.add(7);
		BHIntEmpty.add(6);
		BHIntEmpty.add(9);
		BHIntEmpty.add(11);
		BHIntEmpty.add(35);
		String expected = "[35, 11, 7, 6, 9]";
		assertEquals(Arrays.toString(BHIntEmpty.toArray()), expected);
	}
	
	@Test
	void testToArraManyItemHeapStrings() {
		BHStringEmpty.add("Seth");
		BHStringEmpty.add("Mike");
		BHStringEmpty.add("Apple");
		String expected = "[Seth, Mike, Apple]";
		assertEquals(Arrays.toString(BHStringEmpty.toArray()), expected);
	}
	
	@Test
	void testToArrayEmptyHeap() {
		String expected = "[]";
		assertEquals(Arrays.toString(BHIntEmpty.toArray()), expected);
	}
	
	@Test
	void testToArrayAfterAddingNewSmallest() {
		BHIntEmpty.add(7);
		BHIntEmpty.add(6);
		BHIntEmpty.add(9);
		BHIntEmpty.add(11);
		BHIntEmpty.add(35);
		String expected = "[35, 11, 7, 6, 9]";
		assertEquals(Arrays.toString(BHIntEmpty.toArray()), expected);
		String expected2 = "[35, 11, 7, 6, 9, -30]";
		BHIntEmpty.add(-30);
		assertEquals(Arrays.toString(BHIntEmpty.toArray()), expected2);
	}
	
	@Test
	void testToArrayAfterAddingNewHighest() {
		BHIntEmpty.add(7);
		BHIntEmpty.add(6);
		BHIntEmpty.add(9);
		BHIntEmpty.add(11);
		BHIntEmpty.add(35);
		BHIntEmpty.add(-30);
		String expected = "[35, 11, 7, 6, 9, -30]";
		assertEquals(Arrays.toString(BHIntEmpty.toArray()), expected);
		String expected2 = "[100, 11, 35, 6, 9, -30, 7]";
		BHIntEmpty.add(100);
		assertEquals(Arrays.toString(BHIntEmpty.toArray()), expected2);
	}
	
	// -----------------------toArray Tests End-----------------------------
	

	// -----------------------Comparator classes----------------------------
	protected class OrderByA implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			int aCount1 = 0;
			int aCount2 = 0;

			for (int i = 0; i < o1.length(); i++) {
				if (o1.charAt(i) == 'a' || o1.charAt(i) == 'A') {
					aCount1++;
				}
			}

			for (int i = 0; i < o2.length(); i++) {
				if (o2.charAt(i) == 'a' || o2.charAt(i) == 'A') {
					aCount2++;
				}
			}

			if (aCount1 > aCount2) {
				return 1;
			} else if (aCount1 < aCount2) {
				return -1;
			} else {
				return o1.compareTo(o2);
			}
		}
	}
}
