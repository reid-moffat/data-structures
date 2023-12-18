package ca.reidmoffat.trees;

public class BinarySearchTree {
}

/*
"""
A binary search tree is a sorted binary tree that allows for log complexity* searching. Unlike binary search on an
array, inserting and deleting nodes only takes log time*

*Assuming the tree is relatively balanced such as an AVL tree or red-black tree. As an extreme example, the binary tree
below is essentially a linked list:
1
 \
  2
   \
    3
     \
      4
       \
        5

BSTs are binary trees where all nodes in node X's left subtree have a value less than X and all nodes in X's right
subtree have a value greater than X. By default, they don't have duplicates (but we can add in an attribute to each node
as the count, for example). An inorder traversal will process nodes from lowest to highest
"""
import random


class Node:
    def __init__(self, val=None):
        self.val = val
        self.left = None
        self.right = None


class BST:
    def __init__(self):
        self.root = None

    def insert(self, val):
        if not self.root:
            self.root = Node(val)
            return True
        curr = self.root
        while True:
            if val < curr.val:
                if not curr.left:
                    curr.left = Node(val)
                    return True
                curr = curr.left
            elif val > curr.val:
                if not curr.right:
                    curr.right = Node(val)
                    return True
                curr = curr.right
            else:
                return False  # No duplicates

    def delete(self, val):

        def newNode(node):  # Node that will take the deleted node's spot
            # At least one child missing: Replace the node with the other child
            if not node.left:
                return node.right
            if not node.right:
                return node.left

            # Two children: replace this node with the inorder successor
            curr_node = node.right
            curr_parent = node
            while True:
                if not curr_node.left:
                    node.val = curr_node.val
                    curr_parent.left = curr_node.right  # Delete this node
                    return node
                curr_parent = curr_node
                curr_node = curr_node.left

        if not self.root:
            return False
        parent = None
        curr = self.root
        if curr.val == val:  # Handles the root case
            self.root = newNode(curr)
            return True
        while curr:  # Find the node
            if curr.val > val:
                parent = curr
                curr = curr.left
            elif curr.val < val:
                parent = curr
                curr = curr.right
            else:  # Replace it with the new node
                if parent.left == curr:
                    parent.left = newNode(curr)
                else:
                    parent.right = newNode(curr)
                return True
        return False

    def search(self, val):
        curr = self.root
        while curr:
            if curr.val < val:
                curr = curr.left
            elif curr.val > val:
                curr = curr.right
            else:
                return True
        return False

    def minValue(self):
        if self.root is None:
            return None
        curr = self.root
        while True:
            if not curr.left:
                return curr.val
            curr = curr.left

    def maxValue(self):
        if self.root is None:
            return None
        curr = self.root
        while True:
            if not curr.right:
                return curr.val
            curr = curr.right

    def print2D(self):
        """
        Wrapper method for __print2D

        Prints this binary tree in a 2-D representation
        """
        self.__print2D(self.root)

    def __print2D(self, root, space=0):
        if not root:
            return
        space += 4

        # Process right child first
        self.__print2D(root.right, space)

        # Print current node after a newline
        print()
        for spaces in range(4, space):
            print(end=" ")
        print(root.val)

        # Process left child
        self.__print2D(root.left, space)


if __name__ == "__main__":
    bst = BST()
    bst.insert(50)

    nodes = [50]
    for i in range(50):
        value = random.randint(1, 100)
        nodes.append(value)
        bst.insert(value)

    bst.print2D()

    maximum = bst.maxValue()
    assert maximum == max(nodes)
    print(f"\nMaximum value: {maximum}")

    minimum = bst.minValue()
    assert minimum == min(nodes)
    print(f"Minimum value: {minimum}")

    for i in nodes:
        bst.delete(i)

    bst.print2D()
    assert bst.root is None

 */