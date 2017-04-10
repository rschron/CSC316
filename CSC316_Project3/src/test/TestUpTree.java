package test;
import static org.junit.Assert.*;

import org.junit.Test;

import source.UpTree;

public class TestUpTree {
	
	UpTree up = new UpTree();
	
	@Test
	public void testOperations(){
		
		/*
		 * Initialize 10 trees.
		 */
		for(int i = 0; i <= 10; i++){
			up.makeSet(i);
		}
		
		/*
		 * Check that all trees are roots.
		 */
		for(int i = 0; i <= 10; i++){
			assertTrue(up.isRoot(i));
		}
		
		up.union(0, 1);
		assertEquals(0, up.find(1));
		assertEquals(0, up.find(0));
		assertTrue(up.isRoot(0));
		assertFalse(up.isRoot(1));
		
		up.union(3, 5);
		assertEquals(3, up.find(5));
		assertEquals(3, up.find(3));
		assertTrue(up.isRoot(3));
		assertFalse(up.isRoot(5));
		
		up.union(10, 7);
		assertEquals(10, up.find(7));
		assertEquals(10, up.find(10));
		assertTrue(up.isRoot(10));
		assertFalse(up.isRoot(7));
		
		up.union(0, 2);
		assertEquals(2, up.find(2));
		assertEquals(2, up.find(0));
		assertEquals(2, up.find(1));
		assertTrue(up.isRoot(2));
		assertFalse(up.isRoot(0));
		
		up.union(6, 8);
		assertEquals(6, up.find(8));
		assertTrue(up.isRoot(6));
		assertFalse(up.isRoot(8));
		
		up.union(6, 9);
		assertEquals(9, up.find(9));
		assertTrue(up.isRoot(9));
		assertEquals(9, up.find(6));
		assertEquals(9, up.find(8));
		
		up.union(2, 3);
		assertTrue(up.isRoot(3));
		assertFalse(up.isRoot(2));
		assertEquals(-5, up.getSize(3));
		
		up.union(3, 10);
		up.union(10, 9);
		assertTrue(up.isRoot(9));
		assertFalse(up.isRoot(10));
		assertFalse(up.isRoot(3));
		assertFalse(up.isRoot(2));
		assertEquals(-10, up.getSize(9));
		assertEquals(-10, up.getSize(3));
	}
}
