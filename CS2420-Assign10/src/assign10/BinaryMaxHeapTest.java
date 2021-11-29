package assign10;

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
	BinaryMaxHeap<String> BHStringCmp = new BinaryMaxHeap<String>(new OrderByA() );

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
		System.out.println( randVals );
		System.out.println( Arrays.toString( BHStringCmp.toArray() ) );
		
		for (int index = 0; index < 10; index++) {
			String maxRemoved = BHStringCmp.extractMax();
			System.out.println("MaxVal: " + randVals.get(9-index) + "\tMaxRemoved: " + maxRemoved);
			assertEquals(randVals.get(9-index), maxRemoved);
			System.out.println( Arrays.toString( BHStringCmp.toArray() ) );
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
			assertEquals(randVals.get(9999-index), maxRemoved);
//			System.out.println( Arrays.toString( BHStringCmp.toArray() ) );
		}
	}

	// -----------------------extractMax Tests End----------------------------

	// -----------------------clear Tests Begin----------------------------
	@Test
	void testClearCommon() {
		
	}
	// -----------------------clear Tests End----------------------------
	
	// -----------------------Comparator classes----------------------------
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
				return o1.compareTo(o2);
			}
		}
	}
}
