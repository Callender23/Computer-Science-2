
// Jeffrey Callender
// COP 3503, Summer 2019
// je410689

// ====================
// GenericBST: BST.java
// ====================
// Basic binary search tree (BST) implementation that supports insert() and
// delete() operations. This framework is provide for you to modify as part of
// Programming Assignment #2.


import java.io.*;
import java.util.*;

// The Node class extends the Comparable class to AnyType
class Node<AnyType extends Comparable<AnyType>>
{
	// Initilize the members and set them to be type generics
	AnyType data;
	Node<AnyType> left, right;

	Node(AnyType data)
	{
		this.data = data;
	}
}

// Public class generics aslo extends the Comparable class to any type
public class GenericBST<AnyType extends Comparable <AnyType>>
{
	private Node<AnyType> root;

	public void insert(AnyType data)
	{
		root = insert(root, data);
	}

	// insert method that insert elements into BST
	private Node<AnyType> insert(Node<AnyType> root, AnyType data)
	{
		// Check if the root is null. If so returns a generic data type node
		if (root == null)
		{
			return new Node<AnyType>(data);
		}

		// Using the compareTo method we can compare generic type object
		// If compareTo return value greater than zero insert at right child, otherwise at the left
		else if (data.compareTo(root.data) <= 0)
		{
			root.left = insert(root.left, data);
		}

		else if (data.compareTo(root.data) >=0)
		{
			root.right = insert(root.right, data);
		}

		return root;
	}

	public void delete(AnyType data)
	{
		root = delete(root, data);
	}

	// delete method that takes AnyType data parameter
	private Node<AnyType> delete(Node<AnyType> root, AnyType data)
	{

		if (root == null)
		{
			return null;
		}

		// If compareTo return value greater than zero delete right child, otherwise delete left
		else if (data.compareTo(root.data) <= 0)
		{
			root.left = delete(root.left, data);
		}
		else if (data.compareTo(root.data) >= 0)
		{
			root.right = delete(root.right, data);
		}
		else
		{
			// If both the left and right children are null we have no children so kill it
			if (root.left == null && root.right == null)
			{
				return null;
			}
			// If the left children is null then we have one child the right one. Move it to
			// take the place of it's parent
			else if (root.left == null)
			{
				return root.right;
			}
			// If the right child is null then the last child is the left. Move it to its parent place
			else if (root.right == null)
			{
				return root.left;
			}
			else
			{
				// Special case where the node that need to be deleted has two children. We first
				// find the max value in the left subtree and move it up to the node. We then delete
				// that value from the left subtree recursively
				root.data = findMax(root.left);
				root.left = delete(root.left, root.data);
			}
		}

		return root;
	}

	// This method assumes root is non-null, since this is only called by
	// delete() on the left subtree, and only when that subtree is not empty.
	private AnyType findMax(Node<AnyType> root)
	{
		// finds the max value in a BST iteratively
		while (root.right != null)
		{
			root = root.right;
		}

		return root.data;
	}

	public boolean contains(AnyType data)
	{
		return contains(root, data);
	}

	// method that returns boolean type if BST contains values in either left or right subtrees
	private boolean contains(Node<AnyType> root, AnyType data)
	{
		// null check is so return false
		if (root == null)
		{
			return false;
		}

		// If compareTo return value greater than zero return right child, otherwise return left
		else if (data.compareTo(root.data) <= 0)
		{
			return contains(root.left, data);
		}

		else if (data.compareTo(root.data) >= 0)
		{
			return contains(root.right, data);
		}
		else
		{
			// for all other cases return true
			return true;
		}
	}

	// Recursively calls the inorder method to print the inorder Traversal
	public void inorder()
	{
		System.out.print("In-order Traversal:");
		inorder(root);
		System.out.println();
	}

	// Traversal functions (inorder, postorder, preorder)
	private void inorder(Node<AnyType> root)
	{
		if (root == null)
			return;

		inorder(root.left);
		System.out.print(" " + root.data);
		inorder(root.right);
	}

	public void preorder()
	{
		System.out.print("Pre-order Traversal:");
		preorder(root);
		System.out.println();
	}

	private void preorder(Node root)
	{
		if (root == null)
			return;

		System.out.print(" " + root.data);
		preorder(root.left);
		preorder(root.right);
	}

	public void postorder()
	{
		System.out.print("Post-order Traversal:");
		postorder(root);
		System.out.println();
	}

	private void postorder(Node root)
	{
		if (root == null)
			return;

		postorder(root.left);
		postorder(root.right);
		System.out.print(" " + root.data);
	}

	public static double difficultyRating()
	{
		return 1.0;
	}

	public static double hoursSpent()
	{
		return 5;
	}

}
