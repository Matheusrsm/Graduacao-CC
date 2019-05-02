package linkedList;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SinglyLinkedListImplTest {
	
	private SinglyLinkedListImpl<Integer> list;
	
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws IOException {
        outContent.close();
    }

	@Test
	public void testAddHeadEqualsNull() {
		list = new SinglyLinkedListImpl<>();
		list.add(1);
		String expected = "Adding: 1" + System.lineSeparator();
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	public void testAddHeadNotEqualsNull() {
		list = new SinglyLinkedListImpl<>();
		list.add(1);
		list.add(2);
		String expected = "Adding: 1" + System.lineSeparator() + "Adding: 2" + System.lineSeparator();
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	public void testAddAfter() {
		list = new SinglyLinkedListImpl<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.addAfter(4, 3);
		String expected = "Adding: 1" + System.lineSeparator() + "Adding: 2" + System.lineSeparator() + 
				"Adding: 3" + System.lineSeparator() + "Traversing to all nodes.." + System.lineSeparator();
		assertEquals(expected, outContent.toString());
	}

	@Test
	public void testDeleteFront() {
		list = new SinglyLinkedListImpl<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.addAfter(4, 3);
		list.deleteFront();
		String expected = "Adding: 1" + System.lineSeparator() + "Adding: 2" + System.lineSeparator() + 
				"Adding: 3" + System.lineSeparator() + "Traversing to all nodes.." + System.lineSeparator() +
				"Deleted: 1" + System.lineSeparator();
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	public void testDeleteAfter() {
		list = new SinglyLinkedListImpl<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.addAfter(4, 3);
		list.deleteAfter(2);
		String expected = "Adding: 1" + System.lineSeparator() + "Adding: 2" + System.lineSeparator() + 
				"Adding: 3" + System.lineSeparator() + "Traversing to all nodes.." + System.lineSeparator() +
				"Traversing to all nodes.." + System.lineSeparator() + "Deleted: 3" + System.lineSeparator();
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	public void testTraverse() {
		list = new SinglyLinkedListImpl<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.addAfter(4, 3);
		list.deleteFront();
		list.deleteAfter(2);
		list.traverse();
		String expected = "Adding: 1" + System.lineSeparator() + "Adding: 2" + System.lineSeparator() + 
				"Adding: 3" + System.lineSeparator() + "Traversing to all nodes.." + System.lineSeparator() +
				"Deleted: 1" + System.lineSeparator() + "Traversing to all nodes.." + System.lineSeparator() +
				"Deleted: 3" + System.lineSeparator() + "2" + System.lineSeparator() + "4" +  System.lineSeparator();
		assertEquals(expected, outContent.toString());
	}
	
	@Test
	public void testMain() {
		list = new SinglyLinkedListImpl<>();
		SinglyLinkedListImpl.main(null);
		String expected = "Adding: 3\n" + 
				"Adding: 32\n" + 
				"Adding: 54\n" + 
				"Adding: 89\n" + 
				"Traversing to all nodes..\n" + 
				"Deleted: 3\n" + 
				"Traversing to all nodes..\n" + 
				"Deleted: 89\n" + 
				"32\n" + 
				"54\n" + 
				"76\n";
		assertEquals(expected, outContent.toString());
	}
}