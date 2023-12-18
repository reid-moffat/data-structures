package ca.reidmoffat.trees;

public class AVLTree {
}
/*
"""
An issue with binary trees is the insertion order can make the tree unbalanced, making the complexity O(n) for all
operations

We can solve this issue with a self-balancing tree: the two most common being:
1: AVL tree- faster for looking up values and may take less space than red-black trees (depending on the situation)
2: Red-Black trees- faster for insertion and deletion. It is more general purpose and used in Java, C, Linux, etc

AVL trees work by storing the balance factor fo each node:
-Starting with a standard binary search tree
-Storing the balance factor for each node. This is the right subtree's height minus it left subtree's height (make sure
 to also update it each time there is an insertion/deletion)
-A balance factor of 0 or +-1 is ok because its approximately balanced. If not, we need to 'rotate' the tree
-There are four cases of imbalance. We can consider the grandparent node since there is a maximum balance factor of +-2,
 meaning we can rotate around the nodes

Red-black trees work by storing a bit for each node, specifying whether each node is 'red' or 'black':
-Starting with a standard binary search tree
-Each node is either red or black:
 1. The root node and all null leaves (a leaf's left and right (which are None)) are black
 2. If a node is red, all of its children are black
 3. For every node, every path from the node to descendant leaves contains the same number of black nodes (we don’t
    count the root, but this won’t change if it is balanced or not anyway)
Note that the tree is only roughly balanced. There could be an imbalance by a factor of 2 at most, making search less
efficient.

The example below is from CISC 235 assignment 2. Note this is a tree map (nodes have a key:value pair), but much of the
logic like rotations are similar (see https://i.imgur.com/SEsSRit.png for a visual rotation guide)
"""


# Node with a key and value
class MapNode:

    def __init__(self, key, val):
        self.key = key
        self.values = [val]
        self.left = None
        self.right = None
        self.height = 1


class AVLTreeMap:

    def __init__(self):
        self.root = None

    def get(self, root, key):
        """
        Gets the value(s) mapped to in this tree by a specified key

        :param root: MapNode of an AVLTreeMap
        :param key: a key
        :return: the values mapped to by this key
                 (as a list) if the key is in this
                 AVL tree map; otherwise None
        """
        return self.__get(root, key)

    def __get(self, root, key):
        if not root:
            return None
        elif key == root.key:
            return root.values
        elif key < root.key:
            return self.__get(root.left, key)
        return self.__get(root.right, key)

    def put(self, root, key, val):
        """
        Adds a key:value pair to this AVL tree
        If the key already exists, the value will be added
        to the list of values associated with this key

        :param root: tree root
        :param key: a key
        :param val: a value mapped to by this key
        :return: a tree with the key/val pair inserted
        """
        # Case 1: Empty tree
        if not root:
            return MapNode(key, val)
        # Case 2: Insert on the left
        elif key < root.key:
            if root.left:
                root.left = self.put(root.left, key, val)
            else:
                root.left = MapNode(key, val)
                return root
        # Case 3: Insert on the right
        elif key > root.key:
            if root.right:
                root.right = self.put(root.right, key, val)
            else:
                root.right = MapNode(key, val)
                return root
        # Case 4: Add to the root's values (key == root.key)
        else:
            root.values.append(val)
            return root

        # Update the height of this root
        root.height = max(self.getHeight(root.left),
                          self.getHeight(root.right)) + 1

        # Find the balance of this root
        balance = self.getBalance(root)

        if balance > 1:
            # Unbalanced case 1: Left - Left
            if key < root.left.key:
                return self.__rightRotation(root)
            # Unbalanced case 2: Left - Right
            # Note that key != root.left.key because no node
            # is inserted; if there was an imbalance it would have
            # been present last insertion and fixed
            else:
                root.left = self.__leftRotation(root.left)
                return self.__rightRotation(root)

        elif balance < -1:
            # Unbalanced case 3: Right - Right
            if key > root.right.key:
                return self.__leftRotation(root)
            # Unbalanced case 4: Right - Left
            # key != root.right.key using the same logic as case 2
            else:
                root.right = self.__rightRotation(root.right)
                return self.__leftRotation(root)
        return root

    def __rightRotation(self, root_node):
        # Perform a right rotation on the root node
        pivot = root_node.left
        temp = pivot.right  # pivot.right will be overridden
        pivot.right = root_node
        root_node.left = temp

        # Update the heights of the changed nodes
        root_node.height = max(self.getHeight(root_node.left),
                               self.getHeight(root_node.right)) + 1

        pivot.height = max(self.getHeight(pivot.left),
                           self.getHeight(pivot.right)) + 1

        # pivot is now in the root's place
        return pivot

    def __leftRotation(self, root_node):
        # Perform a left rotation on the root node
        pivot = root_node.right
        temp = pivot.left  # pivot.left will be overridden
        pivot.left = root_node
        root_node.right = temp

        # Update the heights of the changed nodes
        root_node.height = max(self.getHeight(root_node.left),
                               self.getHeight(root_node.right)) + 1

        pivot.height = max(self.getHeight(pivot.left),
                           self.getHeight(pivot.right)) + 1

        # pivot is now in the root's place
        return pivot

    def getHeight(self, root):
        """
        Return the high of a given node

        :param root: a node
        :return: the node's height
        """
        return root.height if root else 0

    def getBalance(self, root):
        """
        Returns the balance of a node

        :param root: a node
        :return: the node's balance
        """
        return self.getHeight(root.left) - self.getHeight(root.right) if root else 0


print_increment = 4  # Number of spaces between each level when printing the AVL tree


def print2D(root, space=0):
    """
    Prints an AVL tree map in a 2-D representation
    """
    # Terminate this recursive branch if root is None
    if not root:
        return

    # Increase distance between levels
    space += print_increment

    # Process right child first
    print2D(root.right, space)

    # Print current node after a newline
    print()
    for spaces in range(print_increment, space):
        print(end=" ")
    print("%s [" % root.key, end="")
    for val_index in range(len(root.values)):
        print("%s" % root.values[val_index], end="")
        if val_index < len(root.values) - 1:
            print(", ", end="")
        else:
            print("]")

    # Process left child
    print2D(root.left, space)


if __name__ == "__main__":
    # Test a AVL tree map with 9 nodes
    tree_map = AVLTreeMap()
    tree = None

    tree = tree_map.put(tree, 15, "bob")
    tree = tree_map.put(tree, 20, "anna")
    tree = tree_map.put(tree, 24, "tom")
    tree = tree_map.put(tree, 10, "david")
    tree = tree_map.put(tree, 13, "david")
    tree = tree_map.put(tree, 7, "ben")
    tree = tree_map.put(tree, 30, "karen")
    tree = tree_map.put(tree, 36, "erin")
    tree = tree_map.put(tree, 25, "david")

    print2D(tree)

 */
