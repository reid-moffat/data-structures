"""
A tree is a non-linear data structure where a data item can be connected to several different data items.
There are many different types, all with different structures, sizes and complexity; but they all share some standard
parts and are often quite efficient

Terminology:
-Node: A structure that contains zero or more child nodes (this doesn't have to be just one variable; it could hold
 multiple variables or even another data structure)
-Leaf: A node without any child nodes
-Edge: The connection between two nodes or a node and a leaf (visually a line; computationally a pointer)
-Path: A collection of connected edges and their corresponding nodes that goes from one node to another node or a leaf
 strictly top to bottom (no changing direction in between)
-Node height: The number of edges on the longest path between the node and a leaf (note: leaves have a height of 0)
-Root node: The topmost node i.e the node with no parent nodes (it has the strictly largest height)
-Depth: Number of edges in a straight line between this node and the root node
-Level: Depth + 1 i.e the root node is at level 1
-Subtree: A node from a tree and all nodes below it
-Valid tree: A tree where each node has a single unique path from the root (each non-root node has exactly one parent)
-Empty tree: A tree with no nodes

There are a multitude of different uses for trees. Some examples:
-Sorted binary search trees allow for O(log(n)) search, insert AND delete (useful in many cases)
-Family trees (two parents would be represented with one node in this case)
-Simplify mathematical expressions
-Decision trees
"""


# A very simple node. Used for a binary tree and stores one value
class Node:
    def __init__(self, val=None):
        self.val = val  # Encapsulation isn't always needed with nodes
        self.left = None
        self.right = None


class TernaryNode:
    def __init__(self, val=None):
        self.val = val
        self.child1 = None
        self.child2 = None
        self.child3 = None


# A node with M children can use a list
class MNode:
    def __init__(self, val=None):
        self.val = val
        self.children = []
