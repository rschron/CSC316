

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class proj1Test {

	proj1 proj;
	
	@Before
	public void setUp(){
		 proj = new proj1(); //Create an empty list.
	}
	
	@Test
	public void testAddToFront(){
		
		proj.addToFront("First");
		assertEquals(1, proj.getSize());
		proj.addToFront("Second");
		assertEquals(2, proj.getSize());
		proj.addToFront("Third");
		assertEquals(3, proj.getSize());
	}
	
	public void testFindPosition(){
		
		proj.addToFront("First");
		assertEquals(1, proj.getSize());
		proj.addToFront("Second");
		assertEquals(2, proj.getSize());
		proj.addToFront("Third");
		assertEquals(3, proj.getSize());
		
		int pos = 0;
		
		pos = proj.findPosition("Second");
		assertEquals(2, pos);
		pos = proj.findPosition("First");
		assertEquals(3, pos);
		pos = proj.findPosition("Third");
		assertEquals(1, pos);
		
		pos = proj.findPosition("Fourth");
		assertEquals(-1, pos);
	}
	
	public void testRemove(){
		
		proj.addToFront("First");
		proj.addToFront("Second");
		proj.addToFront("Third");
		proj.addToFront("Fourth");
		proj.addToFront("Fifth");
		
		assertEquals(5, proj.getSize());
		
		proj.remove(2);
		assertEquals(4, proj.getSize());
		assertEquals(1, proj.findPosition("Fifth"));
		assertEquals(2, proj.findPosition("Third"));
		assertEquals(3, proj.findPosition("Second"));
		assertEquals(4, proj.findPosition("First"));
		
		proj.remove(4);
		assertEquals(3, proj.getSize());
		assertEquals(1, proj.findPosition("Fifth"));
		assertEquals(2, proj.findPosition("Third"));
		assertEquals(3, proj.findPosition("Second"));
		
		proj.remove(1);
		assertEquals(1, proj.getSize());
		assertEquals(1, proj.findPosition("Third"));
		assertEquals(2, proj.findPosition("Second"));
	}
}
