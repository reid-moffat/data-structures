package ca.reidmoffat.linearstructures;

public class LinkedList {
}

/*
"""
A linked list is a linear data structure that consists of Nodes linked together with pointers

Pros of Linked lists compared to arrays:
1. It is a truly dynamic list- no need to copy over elements when the capacity of an array is full. There is no extra
   memory wasted with empty spaces
2. Insertion and deletion is O(1) if you know the location. Even if you don't know the location, n/2 searches is usually
   quicker than array solutions; no need for 'flags' or 'expanding capacity'
3. Nodes are scattered through memory. Instead of needing a 4000-byte consecutive block, we only need 1000 4-byte
   blocks for example. This can be helpful for very large lists if the memory becomes fragmented
4. Many more complex data structure can be implemented quite easily with LinkedLists, such as with open hashing

Cons of linked lists compared to arrays:
1. Accessing elements is O(n) (n/2 searches on average) since we need to traverse through the Nodes
2. An array takes up the minimum required memory space; nodes require a pointer making each element more costly
3. Arrays are extremely simple to use for basic tasks (no pointer issues)
4. Node's memory needs to be freed once deleted (not an issue in python, but can lead to issues in other languages)
5. You can't traverse backwards without a doubly linked list, which takes extra memory

When should linked lists be used over arrays?
-When the size is constantly changing
-When inserting to the middle of the list is important
(Not too often; arrays are usually the better option)

Variations of a linked list:
-Doubly linked list (allows for reverse iteration, but takes more memory)
-Circular linked list (last node points to the first node)
-Doubly circular linked list
"""
import random


# Other methods can be used to make a node, but this is the easiest
class Node:
    def __init__(self, val=None):
        self.val = val
        self.next = None


# There a multitude of different methods a linked list can have depending
# on the intended use; this one is a general, basic list
class LinkedList:
    def __init__(self):
        self.__head = None

    # For the 'in' operator i.e 'if 10 in linkedList' works
    def __contains__(self, item):
        curr = self.__head
        while curr:
            if curr.val == item:
                return True
            curr = curr.next
        return False

    def get_first(self) -> Node:
        return self.__head

    def print(self):
        curr = self.__head
        while curr:
            print(f"{curr.val} ", end='')
            curr = curr.next

    def append(self, node: Node):
        if not self.__head:
            self.__head = node
            return
        curr = self.__head
        while curr.next:
            curr = curr.next
        curr.next = node

    def add_first(self, node: Node):
        node.next = self.__head
        self.__head = node


if __name__ == "__main__":
    linked_list = LinkedList()

    for i in range(50):
        linked_list.append(Node(random.randint(0, 100)))

    print(f"Is 50 in this Linked List? {50 in linked_list}")
    print("Linked List nodes in order: ")
    linked_list.print()
 */
