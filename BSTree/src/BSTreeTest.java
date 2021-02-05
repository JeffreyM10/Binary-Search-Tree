/**
 * Unit test cases for the implementation of a Binary Search Tree.
 *
 * @author  Jeffrey Mazariegos
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class BSTreeTest
{
	/**
	 * the tree to use for testing
	 */
	private BSTree<Integer> tree;

	private StringVisitor strVisitor;

	// returns a tree loaded with the given items
	static BSTree<Integer> load(Integer... items)
	{
		IntComparator compare = new IntComparator();
		BSTree<Integer> tree = new BSTree<Integer>(compare);
		for (Integer value : items) {
			tree.add(value);
		}
		return tree;
	}

	/**
	 * Test the addFirst method.
	 */
	@Test
	public void test_add()
	{
		tree = load();
		tree.add(1);
		System.out.println(tree.toString());
		assertEquals( tree.toString(), "[1]" );
		assertEquals(tree.toStringPre(), "[1]" );

		// testing single
		tree = load(4);
		tree.add(3);
		assertEquals(tree.toString(), "[4 3]" );
		assertEquals(tree.toStringPre(), "[4 3]" );

		tree = load(1);
		tree.add(2);
		assertEquals(tree.toString(), "[1 2]" );
		assertEquals(tree.toStringPre(), "[1 2]" );

		// testing multi
		tree = load(15,12,17,11,14,16,19);
		tree.add(20);
		assertEquals(tree.toString(), "[15 12 17 11 14 16 19 20]" );
		assertEquals(tree.toStringPre(), "[15 12 11 14 17 16 19 20]" );
		

		tree = load(15,12,17,11,14,16,19);
		tree.add(9);
		assertEquals(tree.toString(), "[15 12 17 11 14 16 19 9]" );
		assertEquals(tree.toStringPre(), "[15 12 11 9 14 17 16 19]" );

	}

	/**
	 * Test the maxValueLoop method.
	 */
	@Test
	public void test_maxValueLoop()
	{
		// testing empty
		tree = load();
		try { tree.maxValueLoop(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals( tree.toString(), "[]" );
		assertEquals(tree.toStringPre(), "[]" );

		// testing single
		tree = load(1);
		assertTrue( tree.maxValueLoop().equals(1) );
		assertEquals( tree.toString(), "[1]" );
		assertEquals(tree.toStringPre(), "[1]" );

		tree = load(3);
		assertTrue( tree.maxValueLoop().equals(3) );
		assertEquals(tree.toString(), "[3]" );
		assertEquals(tree.toStringPre(), "[3]" );

		// testing multi
		tree = load(15,12,17,11,14,16,19);
		assertTrue( tree.maxValueLoop().equals(19) );
		assertEquals( tree.toString(), "[15 12 17 11 14 16 19]" );
		assertEquals(tree.toStringPre(), "[15 12 11 14 17 16 19]" );

		tree = load(6,4,8,3,5,7,9);
		assertTrue( tree.maxValueLoop().equals(9) );
		assertEquals(tree.toString(), "[6 4 8 3 5 7 9]" );
		assertEquals(tree.toStringPre(), "[6 4 3 5 8 7 9]" );
	}

	@Test
	public void test_addLoop() {
		// testing empty
		tree = load();
		tree.addLoop(1);
		assertEquals( tree.toString(), "[1]" );
		assertEquals(tree.toStringPre(), "[1]" );

		// testing single
		tree = load(4);
		tree.addLoop(3);
		assertEquals(tree.toString(), "[4 3]" );
		assertEquals(tree.toStringPre(), "[4 3]" );

		tree = load(1);
		tree.addLoop(2);
		assertEquals(tree.toString(), "[1 2]" );
		assertEquals(tree.toStringPre(), "[1 2]" );

		// testing multi
		tree = load(15,12,17,11,14,16,19);
		tree.addLoop(20);
		assertEquals(tree.toString(), "[15 12 17 11 14 16 19 20]" );
		assertEquals(tree.toStringPre(), "[15 12 11 14 17 16 19 20]" );

		tree = load(15,12,17,11,14,16,19);
		tree.addLoop(9);
		assertEquals(tree.toString(), "[15 12 17 11 14 16 19 9]" );
		assertEquals(tree.toStringPre(), "[15 12 11 9 14 17 16 19]" );

	}

	@Test
	public void test_isEmpty() {
		// testing empty
		tree = load();
		assertTrue(tree.isEmpty());
		assertEquals(tree.toString(), "[]" );
		assertEquals(tree.toStringPre(), "[]" );

		// testing single
		tree = load(0);
		assertFalse(tree.isEmpty());
		assertEquals(tree.toString(), "[0]" );
		assertEquals(tree.toStringPre(), "[0]" );

		// testing multi
		tree = load(0,0,0,0);
		assertFalse(tree.isEmpty());
		assertEquals(tree.toString(), "[0 0 0 0]" );
		assertEquals(tree.toStringPre(), "[0 0 0 0]" );

		tree = load(0,0,0,0);
		assertFalse(tree.isEmpty());
		assertEquals(tree.toString(), "[0 0 0 0]" );
		assertEquals(tree.toStringPre(), "[0 0 0 0]" );

	}
	@Test
	public void test_minValueLoop() {

		// testing empty
		tree = load();
		try { tree.minValueLoop(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals( tree.toString(), "[]" );
		assertEquals(tree.toStringPre(), "[]" );

		// testing single
		tree = load(0);
		assertTrue( tree.minValueLoop().equals(0) );
		assertEquals( tree.toString(), "[0]" );
		assertEquals(tree.toStringPre(), "[0]" );

		tree = load(3);
		assertTrue( tree.minValueLoop().equals(3) );
		assertEquals(tree.toString(), "[3]" );
		assertEquals(tree.toStringPre(), "[3]" );

		// testing multi
		tree = load(15,12,17,11,14,16,19);
		assertTrue( tree.minValueLoop().equals(11) );
		assertEquals( tree.toString(), "[15 12 17 11 14 16 19]" );
		assertEquals(tree.toStringPre(), "[15 12 11 14 17 16 19]" );

		tree = load(6,4,8,3,5,7,9);
		assertTrue( tree.minValueLoop().equals(3) );
		assertEquals(tree.toString(), "[6 4 8 3 5 7 9]" );
		assertEquals(tree.toStringPre(), "[6 4 3 5 8 7 9]" );


	}
	@Test
	public void test_containsLoop() {

		// testing empty
		tree = load();
		tree.containsLoop(0);
		assertEquals( tree.toString(), "[]" );
		assertEquals(tree.toStringPre(), "[]" );

		// testing single
		tree = load(0);
		assertFalse(tree.containsLoop(1));
		assertEquals(tree.toString(), "[0]" );
		assertEquals(tree.toStringPre(), "[0]" );

		tree = load(1);
		tree.containsLoop(1);
		assertEquals(tree.toString(), "[1]" );
		assertEquals(tree.toStringPre(), "[1]" );

		// testing multi
		tree = load(15,12,17,11,14,16,19);
		tree.containsLoop(17);
		assertEquals(tree.toString(), "[15 12 17 11 14 16 19]" );
		assertEquals(tree.toStringPre(), "[15 12 11 14 17 16 19]" );

		tree = load(6,4,8,3,5,7,9);
		tree.containsLoop(7);
		assertEquals(tree.toString(), "[6 4 8 3 5 7 9]" );
		assertEquals(tree.toStringPre(), "[6 4 3 5 8 7 9]" );

	}

	@Test
	public void test_maxValue() {
		tree = load();
		try { tree.maxValue(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals( tree.toString(), "[]" );
		assertEquals(tree.toStringPre(), "[]" );

		// testing single
		tree = load(1);
		assertTrue( tree.maxValue().equals(1) );
		assertEquals( tree.toString(), "[1]" );
		assertEquals(tree.toStringPre(), "[1]" );

		tree = load(3);
		assertTrue( tree.maxValue().equals(3) );
		assertEquals(tree.toString(), "[3]" );
		assertEquals(tree.toStringPre(), "[3]" );
		// testing multi
		tree = load(15,12,17,11,14,16,19);
		assertTrue( tree.maxValue().equals(19) );
		assertEquals(tree.toString(), "[15 12 17 11 14 16 19]" );
		assertEquals(tree.toStringPre(), "[15 12 11 14 17 16 19]" );

		tree = load(6,4,8,3,5,7,9);
		assertTrue( tree.maxValue().equals(9) );
		assertEquals(tree.toString(), "[6 4 8 3 5 7 9]" );
		assertEquals(tree.toStringPre(), "[6 4 3 5 8 7 9]" );
	}

	@Test
	public void test_minValue() {
		// testing empty
		tree = load();
		try { tree.minValue(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals( tree.toString(), "[]" );
		assertEquals(tree.toStringPre(), "[]" );

		// testing single
		tree = load(0);
		assertTrue( tree.minValue().equals(0) );
		assertEquals( tree.toString(), "[0]" );
		assertEquals(tree.toStringPre(), "[0]" );

		tree = load(3);
		assertTrue( tree.minValue().equals(3) );
		assertEquals(tree.toString(), "[3]" );
		assertEquals(tree.toStringPre(), "[3]" );

		// testing multi
		tree = load(15,12,17,11,14,16,19);
		assertTrue( tree.minValue().equals(11) );
		assertEquals( tree.toString(), "[15 12 17 11 14 16 19]" );
		assertEquals(tree.toStringPre(), "[15 12 11 14 17 16 19]" );

		tree = load(6,4,8,3,5,7,9);
		assertTrue( tree.minValue().equals(3) );
		assertEquals(tree.toString(), "[6 4 8 3 5 7 9]" );
		assertEquals(tree.toStringPre(), "[6 4 3 5 8 7 9]" );
	}

	@Test
	public void test_contains() {
		// testing empty
		tree = load();
		assertFalse(tree.contains(0));
		assertEquals( tree.toString(), "[]" );
		assertEquals(tree.toStringPre(), "[]" );

		// testing single
		tree = load(0);
		assertTrue(tree.contains(0));
		assertEquals(tree.toString(), "[0]" );
		assertEquals(tree.toStringPre(), "[0]" );

		tree = load(1);
		assertTrue(tree.contains(1));
		assertEquals(tree.toString(), "[1]" );
		assertEquals(tree.toStringPre(), "[1]" );

		// testing multi
		tree = load(6,4,8,3,5,7,9);
		assertTrue(tree.contains(6));
		assertEquals(tree.toString(), "[6 4 8 3 5 7 9]" );
		assertEquals(tree.toStringPre(), "[6 4 3 5 8 7 9]" );

		tree = load(6,4,8,3,5,7,9);
		assertTrue(tree.contains(9));
		assertEquals(tree.toString(), "[6 4 8 3 5 7 9]" );
		assertEquals(tree.toStringPre(), "[6 4 3 5 8 7 9]" );
		

		tree = load(6,4,8,3,5,7,9);
		assertTrue(tree.contains(3));
		assertEquals(tree.toString(), "[6 4 8 3 5 7 9]" );
		assertEquals(tree.toStringPre(), "[6 4 3 5 8 7 9]" );
	}
	@Test
	public void test_remove() {
		// testing empty
		tree = load();
		assertFalse(tree.remove(0));
		assertEquals( tree.toString(), "[]" );
		assertEquals(tree.toStringPre(), "[]" );

		// testing single
		tree = load(0);
		assertTrue(tree.remove(0));
		//System.out.print(tree.toString());
		assertEquals(tree.toString(), "[]" );
		assertEquals(tree.toStringPre(), "[]" );

		tree = load(1);
		assertTrue(tree.remove(1));
		assertEquals(tree.toString(), "[]" );
		assertEquals(tree.toStringPre(), "[]" );

		// testing multi
		tree = load(6,4,8,3,5,7,9);
		assertTrue(tree.remove(6));
		assertEquals(tree.toString(), "[4 8 3 5 7 9]" );
		assertEquals(tree.toStringPre(), "[4 3 5 8 7 9]" );

		tree = load(6,4,8,3,5,7,9);
		assertTrue(tree.remove(9));
		assertEquals(tree.toString(), "[6 4 8 3 5 7]" );
		assertEquals(tree.toStringPre(), "[6 4 3 5 8 7]" );

		tree = load(6,4,8,3,5,7,9);
		assertTrue(tree.remove(3));
		assertEquals(tree.toString(), "[6 4 8 5 7 9]" );
		assertEquals(tree.toStringPre(), "[6 4 5 8 7 9]" );
	}
	@Test
	public void test_removeMissing() {
		// testing empty
		tree = load(0);
		tree.removeMissing(null);
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load(0);
		tree.removeMissing(null);
		assertEquals(tree.toString(), "[0 1]" );

		tree = load(1);
		tree.removeMissing(null);
		assertEquals(tree.toString(), "[1 1]" );

		// testing multi
		tree = load(0,1,2,3,4,5,6,7,8,9);
		tree.removeMissing(null);
		assertEquals(tree.toString(), "[0 1 2 3 4 5 6 7 8 9 7]" );

		tree = load(0,1,2,3,4,5,6,7,8,9);
		tree.removeMissing(null);
		assertEquals(tree.toString(), "[0 1 2 3 4 5 6 7 8 9 9]" );
	}

	@Test
	public void test_preorder() {
		//empty
		tree = load();
		strVisitor = new StringVisitor<Integer>();
		tree.preorder( strVisitor );
		assertEquals(strVisitor.getValue(), "[]" );
		

		//single
		tree = load(6);
		strVisitor = new StringVisitor<Integer>();
		tree.preorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[6]" );
		

		//multi
		tree = load(6,4,8,3,5,7,9);
		strVisitor = new StringVisitor<Integer>();
		tree.preorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[6 4 3 5 8 7 9]" );
		

	}

	@Test
	public void test_inorder() {
		//empty
		tree = load();
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		

		//single
		tree = load(6);
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[6]" );
	

		//multi
		tree = load(6,4,8,3,5,7,9);
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[3 4 5 6 7 8 9]" );
		
	}

	@Test
	public void test_postorder() {
		//empty
		tree = load();
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		

		//single
		tree = load(6);
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[6]" );
		

		//multi
		tree = load(6,4,8,3,5,7,9);
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[3 5 4 7 9 8 6]" );
		
	}
	

	@Test
	public void test_equals() {
		//empty
		tree = load();
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		

		//single
		tree = load(6);
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[6]" );
		

		//multi
		tree = load(6,4,8,3,5,7,9);
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[3 5 4 7 9 8 6]" );	
	}
	
	@Test
	public void test_BSTree() {
		Integer[] numbers = {6,4,3,5,8,7,9};
	}	
}