"""
A binary tree is a tree where each node has a maximum of two children
Binary trees are the simplest to understand and are used very often due to their speed. However, a basic binary tree
isn't too efficient and requires algorithms to optimize

Full vs complete:
-Full binary tree: All nodes have either zero or two children
-Complete binary tree: All levels are full except possibly the last level (where all nodes are as left as possible)
-A binary tree can be full and complete, only full, only complete or neither

Full and complete tree:
    10
   /  \
  2    3
 / \  / \
7  6 1   2

Full but not complete tree (leaves doesn't have to be at the bottom):
      10
     /  \
    2    3
   / \
  7  6
 / \
2   9

Complete but not full tree:
    10
   /  \
  2    3
 / \  /
7  6 1

Neither full nor complete tree:
    10
   /
  2
 / \
7   6

Binary tree operations are commonly:
1. Search: With a sorted binary tree, sorting is extremely efficient O(log(n))
2. Add node: O(1), O(log(n)) or O(n) depending on how the tree is set and and how you want to insert it
3. Remove node: Same as add for complexity
4. Traverse: There are three main ways of doing this. See 'traversals.png'; they all process the current node and
   recursively traverse the left and right subtree, but the order is different
    a) Preorder is curr -> left -> right
    b) In-order is left -> curr -> right
    c) Post-order is left -> right -> root
    (There is also level-order, which goes from left to right on each level)
5. Pruning: Remove a subtree
6. Destruction: Delete the entire tree
When we traverse a binary tree, we must start with the root node. All traversals
"""


class Node:
    def __init__(self, val=None):
        self.val = val
        self.left = None
        self.right = None


def preOrder(root):
    if root:
        print(f"{root.val} ", end='')  # This can be anything for the node (add up the value, add to array, etc)
        preOrder(root.left)
        preOrder(root.right)


def inOrder(root):
    if root:
        inOrder(root.left)
        print(f"{root.val} ", end='')
        inOrder(root.right)


def postOrder(root):
    if root:
        postOrder(root.left)
        postOrder(root.right)
        print(f"{root.val} ", end='')


def levelOrder(root):
    nodes = [root]
    while nodes:
        curr = nodes.pop(0)
        print(f"{curr.val} ", end='')
        if curr.left:
            nodes.append(curr.left)
        if curr.right:
            nodes.append(curr.right)


if __name__ == "__main__":
    #     1
    #    / \
    #   2   3
    #  / \ / \
    # 4  5 6  7
    n1 = Node(1)
    n2 = Node(2)
    n3 = Node(3)
    n4 = Node(4)
    n5 = Node(5)
    n6 = Node(6)
    n7 = Node(7)

    n1.left = n2
    n1.right = n3
    n2.left = n4
    n2.right = n5
    n3.left = n6
    n3.right = n7

    preOrder(n1)    # 1 2 4 5 3 6 7
    print()
    inOrder(n1)     # 4 2 5 1 6 3 7
    print()
    postOrder(n1)   # 4 5 2 6 7 3 1
    print()
    levelOrder(n1)  # 1 2 3 4 5 6 7
