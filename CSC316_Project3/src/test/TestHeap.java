package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import source.Heap;
import source.Heap.Record;


public class TestHeap {
	Heap h = new Heap();
	
	Record r1 = h.makeRecord(0, 0, 1);
	Record r2 = h.makeRecord(0, 0, 2);
	Record r3 = h.makeRecord(0, 0, 3);
	Record r4 = h.makeRecord(0, 0, 4);
	Record r5 = h.makeRecord(0, 0, 5);
	Record r6 = h.makeRecord(0, 0, 6);
	Record r7 = h.makeRecord(0, 0, 7);
	Record r8 = h.makeRecord(0, 0, 8);
	Record r9 = h.makeRecord(0, 0, 9);
	Record r10 = h.makeRecord(0, 0, 10);
	
	Record vals[] = {r1, r4, r6, r2, r10, r3, r5, r7, r9, r8};
	
	
	@Test
	public void testOperations(){
		
		double[] control = {1, 2, 3, 4, 8, 6, 5, 7, 9, 10};
		
		double[] actual = new double[10];
		
		for(int i = 0; i < vals.length; i++){
			h.insert(vals[i]);
		}

		for(int i = 0; i < actual.length; i++){
			actual[i] = h.getHeapElement(i);
		}
		
		for(int i = 0; i < actual.length; i++){
			assertEquals(control[i], actual[i], 0);
		}
		
		
		int[] arr1 = {2, 4, 3, 7, 8, 6, 5, 10, 9};
		int[] arr2 = {3, 4, 5, 7, 8, 6, 9, 10};
		int[] arr3 = {4, 7, 5, 10, 8, 6, 9};
		int[] arr4 = {5, 7, 6, 10, 8, 9};
		int[] arr5 = {6, 7, 9, 10, 8};
		int[] arr6 = {7, 8, 9, 10};
		int[] arr7 = {8, 10, 9};
		int[] arr8 = {9, 10};
		int[] arr9 = {10};
		
		h.deleteMin();
		for(int i = 0; i < arr1.length; i++){
			assertEquals(arr1[i], h.getHeapElement(i), 0);
		}
		h.deleteMin();
		for(int i = 0; i < arr2.length; i++){
			assertEquals(arr2[i], h.getHeapElement(i), 0);
		}
		h.deleteMin();
		for(int i = 0; i < arr3.length; i++){
			assertEquals(arr3[i], h.getHeapElement(i), 0);
		}
		h.deleteMin();
		for(int i = 0; i < arr4.length; i++){
			assertEquals(arr4[i], h.getHeapElement(i), 0);
		}
		h.deleteMin();
		for(int i = 0; i < arr5.length; i++){
			assertEquals(arr5[i], h.getHeapElement(i), 0);
		}
		h.deleteMin();
		for(int i = 0; i < arr6.length; i++){
			assertEquals(arr6[i], h.getHeapElement(i), 0);
		}
		h.deleteMin();
		for(int i = 0; i < arr7.length; i++){
			assertEquals(arr7[i], h.getHeapElement(i), 0);
		}
		h.deleteMin();
		for(int i = 0; i < arr8.length; i++){
			assertEquals(arr8[i], h.getHeapElement(i), 0);
		}
		h.deleteMin();
		for(int i = 0; i < arr9.length; i++){
			assertEquals(arr9[i], h.getHeapElement(i), 0);
		}
		h.deleteMin();
		assertEquals(0, h.getHeapElement(0), 0);
	}
		
		
}
	
