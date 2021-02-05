import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

public class BSTree<E>{

	class Node {
		E data;          
		Node left;      
		Node right;
		Node parent;
		Node(E d) {

			this.data = d;
			this.left = null;
			this.right = null;
			this.parent = null;
		}

	}

	private Node root; 
	private Comparator<E> comp;

	/**
	 * Creates an empty tree then with the comparator the values in the tree are compared.
	 * @param theComp
	 */
	BSTree(Comparator<E> theComp){
		comp = theComp;
		root = null;
	}

	public Node getRoot() {
		return root;
	}
	/**
	 * Puts an item in the tree.
	 * @param item in the tree
	 */
	public void addLoop(E item) {
		Node addNode = new Node(item);
		Node curr = root;
		if(curr == null) {
			root = addNode;
		}
		while(curr != null ) {

			if (comp.compare(curr.data, item) > 0 || comp.compare(curr.data, item) == 0 ) {
				if (curr.left == null) {
					curr.left = addNode;
					curr.left.parent = curr;
					return;
				}
				else {
					curr = curr.left;
				}

			}
			else if (comp.compare(curr.data, item) < 0) {
				if (curr.right == null) {
					curr.right = addNode;
					curr.right.parent = curr;
					return;
				}
				else {
					curr = curr.right;
				}
			}
		}
	}
	/**

	 * Will check if the tree is empty.
	 * @return will check if the tree is empty.
	 */

	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;

	}

	/**
	 * Finds the largest value in the tree.
	 * @return the largest value in the tree. 
	 */

	public E maxValueLoop() {
		Node node = findMaxNodeLoop(root);
		if (node == null) {
			throw new NoSuchElementException();
		}
		return findMaxNodeLoop(root).data;

	}

	/**

	 * When at the current node looks for the largest value in the sub tree.
	 * @return largest value in the sub tree at the current node.
	 * @param current

	 */

	private Node findMaxNodeLoop(Node curr) {
		if (curr == null) {
			return null;
		}

		while (curr.right != null) {
			curr = curr.right;
		}
		return curr;
	}

	/**
	 * Looks for the smallest value in the tree.
	 * @return the smallest value in the tree.
	 */
	E minValueLoop() {
		Node node = findMinNodeLoop(root);
		if (node == null) {
			throw new NoSuchElementException();
		}
		return findMinNodeLoop(root).data;

	}

	/**
	 * When at the current node looks for the smallest node in the sub tree.
	 * @return smallest value in the sub tree at the current node.
	 * @param curr
	 */

	private Node findMinNodeLoop(Node curr) {
		if (curr == null) {
			return null;
		}
		while(curr.left != null) {
			curr = curr.left;
		}
		return curr;


	}

	/**
	 * Looks for the given item in the tree.
	 * @return if the given item is in the tree.
	 * @param item
	 */
	public boolean containsLoop(E item) {
		if (findNodeLoop(root, item) != null) {
			return true;
		}
		return false;

	}
	/**
	 * Looks for the node that contains the given time in the sub tree
	 * @return the node that has the given item in the sub tree
	 * @param curr
	 * @param item
	 */

	private Node findNodeLoop(Node curr, E item) {
		while (curr != null) {
			if (comp.compare(curr.data, item) == 0) {
				return curr;
			}
			else if (comp.compare(curr.data, item) > 0 ) {
				curr = curr.left;	
			}
			else if (comp.compare(curr.data, item) < 0 ) {
				curr = curr.right;	
			}

		}
		return null;
	}
	/**
	 * The given item is added to the tree.
	 * @param item
	 */
	public void add(E item) {

		add(root,item);
	}
	/**
	 * While in the current node, the given item is added to the sub-tree. The recursion version of addLoop.
	 * @param curr
	 * @param item
	 */
	public void add(Node curr, E item) {
		Node addNode = new Node(item);
		if(root == null) {
			root = addNode;
		}
		if(curr == null) {
			curr = addNode;
		}
		else if (comp.compare(curr.data, item) < 0) {
			if (curr.right == null) {
				curr.right = addNode;
				curr.right.parent = curr;
				return;
			}
			else {
				add(curr.right, item);
			}
		}
		else if (comp.compare(curr.data, item) > 0 || comp.compare(curr.data, item) == 0 ) {
			if (curr.left == null) {
				curr.left = addNode;
				curr.left.parent = curr;
				return;
			}
			else {
				add(curr.left, item);
			}

		}

	}
	/**
	 * Looks for the largest value in the tree
	 * @return the largest value in the tree
	 */
	public E maxValue() {
		if (findMaxNode(root) == null) {
			throw new NoSuchElementException();
		}
		return findMaxNode(root).data;


	}
	/**
	 * This is the recursive version findMaxNode
	 * @param curr
	 * @return MaxNode using recursion
	 */
	private Node findMaxNode(Node curr) {

		if (curr == null) {
			return null;
		}
		else if (curr.right == null) {
			return curr;
		}
		else {
			return findMaxNode(curr.right);
		}


	}

	/**
	 * Looks for the smallest value in the tree
	 * @return the smallest value in the tree
	 */
	public E minValue() {
		if (findMinNode(root) == null) {
			throw new NoSuchElementException();
		}
		return findMinNode(root).data;

	}
	/**
	 * The Recursive version of findMinNode
	 * @param curr
	 * @return the smallest value in the sub-tree at the current node
	 */
	Node findMinNode(Node curr) {
		if (curr == null) {
			return null;
		}
		else if (curr.left == null) {
			return curr;
		}
		else {
			return findMinNode(curr.left);
		}

	}
	/**
	 * Checks if the given index is in the tree
	 * @param item
	 * @return if the given item is in the tree.
	 */
	public boolean contains(E item) {
		if (findNode(root, item) != null) {
			return true;
		}
		return false;
	}
	/**
	 * This is the recursive version of findNode.
	 * @param curr
	 * @param item
	 * @return the node that has the given item in the sub tree
	 */
	Node findNode(Node curr, E item) {

		if (curr == null) {
			return null;
		}
		else {
			if (comp.compare(curr.data, item) == 0 ) {			
				return curr;
			}
			else if (comp.compare(curr.data, item) < 0 ) {
				return findNode(curr.right, item);	
			}
			else if (comp.compare(curr.data, item) > 0 ) {
				return findNode(curr.left, item);	
			}

		}
		return null;

	}
	/**
	 * The given item is removed from the tree.
	 * @param item
	 * @return if the item is removed.
	 */
	public boolean remove(E item) {
		return false;
	}
	/**
	 * Even if the node is missing one or two children it still removes it.
	 * @param node
	 */
	void removeMissing(Node node) {
		findMinNode(node);

	}
	/**
	 * String representation of the tree while the elements maintain the level-order sequence.
	 * @returns String representation of the tree while the elements maintain the level-order sequence
	 */
	public String toString() {
		Iterator<E> iter = iterator();
		String str ="";
		if (isEmpty()) {
			str = "[" + str.trim() + "]";
			return str;
		}
		while (iter.hasNext()) {
			E curr = iter.next();
			str += curr + " ";
		}
		str = "[" + str.trim() + "]";
		return str;
	}
	/**
	 * An iterator that lists the elements from the tree in level-order is returned.
	 * @return iterator that lists the elements from the tree in level-order 
	 */
	Iterator<E> iterator(){
		return new QueueIterator();
	}
	public class QueueIterator implements Iterator <E>
	{
		Queue <Node> queue = new LinkedList<Node>();
		private Node curr;

		public QueueIterator()
		{
			queue.add(root);
		}
		@Override
		public boolean hasNext() {
			if (!queue.isEmpty()) {
				return true;
			}
			else {
				return false;
			}
		}
		@Override
		public E next() {
			if (hasNext()) {
				curr = queue.poll();
				StringVisitor<E> visitor = new StringVisitor <E>();
				visitor.visit(curr.data);

				if (curr.left != null ) {
					queue.add(curr.left);
				}
				if (curr.right != null ) {
					queue.add(curr.right);
				}
				return curr.data;
			}
			return null;
		}
	}
	/**
	 * An iterator that lists the elements from the tree in pre-order is returned.
	 * @return iterator that lists the elements from the tree in pre-order
	 */
	Iterator<E> preorderIterator(){
		return new StackIterator();
	}
	public class StackIterator implements Iterator <E>
	{
		Stack <Node> stack = new Stack<Node>();
		private Node curr;
		E item;
		public StackIterator()
		{
			stack.push(root);
		}
		@Override
		public boolean hasNext() {
			if (!stack.isEmpty()) {
				return true;
			}
			else {
				return false;
			}
		}
		@Override
		public E next() {
			if (hasNext()) {
				curr = stack.pop();
				StringVisitor<E> visitor = new StringVisitor <E>();
				visitor.visit(curr.data);
				if(curr.right != null ) {
					stack.push(curr.right);
				}
				if (curr.left != null ) {
					stack.push(curr.left);
				}
				return curr.data;
			}
			return null;
		}
	}
	/**
	 * String representation of the tree while the elements maintain the pre-order sequence.
	 * @return String representation of the tree while the elements maintain the pre-order sequence
	 */
	String toStringPre() {
		Iterator<E> iter = preorderIterator();
		String str ="";
		if (isEmpty()) {
			str = "[" + str.trim() + "]";
			return str;
		}
		while (iter.hasNext()) {
			E curr = iter.next();
			str += curr + " ";
		}
		str = "[" + str.trim() + "]";
		return str;

	}

	/**
	 * A pre-order traversal of this tree is performed.
	 * @param visitor
	 */
	public void preorder(Visitor<E> visitor) {
		preorder(visitor,root);
	}

	/**
	 * A pre-order traversal of this tree is performed except with the tree rooted at the given curr.
	 * @param visitor
	 * @param curr
	 */
	private void preorder(Visitor<E> visitor, Node curr) {
		if(curr == null) {
			return;
		}
		visitor.visit(curr.data);
		preorder(visitor,curr.left);
		preorder(visitor,curr.right);
	}
	/**
	 * An in-order traversal of this tree is performed.
	 * @param visitor
	 */
	public void inorder(Visitor<E> visitor) {
		inorder(visitor,root);
	}
	/**
	 * An in-order traversal of this tree is performed except with the tree rooted at the given curr.
	 * @param visitor
	 * @param curr
	 */
	private void inorder(Visitor<E> visitor, Node curr) {
		if(curr == null) {
			return;
		}
		inorder(visitor,curr.left);
		visitor.visit(curr.data);
		inorder(visitor,curr.right);
	}
	/**
	 * A post-order traversal of this tree is performed.
	 * @param visitor
	 */
	public void postorder(Visitor<E> visitor) {
		postorder(visitor,root);
	}
	/**
	 * A post-order traversal of this tree is performed except with the tree rooted at the given curr.
	 * @param visitor
	 * @param curr
	 */
	private void postorder(Visitor<E> visitor, Node curr) {
		if(curr == null) {
			return;
		}
		postorder(visitor,curr.left);
		postorder(visitor,curr.right);
		visitor.visit(curr.data);
	}
	/**
	 * if the given item is equal to this tree this returns true.
	 * @return if the given item is equal to this tree
	 */
	public boolean equals(Object obj) {
		return false;
	}

	/**
	 * if the trees rooted at the given nodes are equal return true.
	 * @param root1
	 * @param root2
	 * @return if the trees rooted at the given nodes are equal
	 */
	private boolean equals(Node root1, Node root2) {
		return false;

	}

	/**
	 * A copy of the tree is returned.
	 */
	public Object clone() {
		return comp;

	}

	/**
	 * A copy of the tree is returned while at the given node.
	 * @param curr
	 * @return
	 */
	private Node copyTree(Node curr) {
		return curr;

	}
}
