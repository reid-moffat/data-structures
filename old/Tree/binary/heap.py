"""
A heap is a complete binary tree (NOT a BST though) that is used to quickly get the top item and insert items
There are two main uses of heaps: priority queues and heapsort
Since heaps are complete, they are stored in arrays for ease of accessing elements

Priority queues work by assigning a priority to each element in a queue; the highest priority element is dequeued first
instead of the first added item. Heaps works for this because the order of the non-first items don't directly matter
(well, kind of)

There are two main types of heaps: maxheap and minheap. They're the same except a maxheap has all nodes are greater
than their children while minheap has all nodes are less than their children. A maxheap isoften used, so here si how it
works:
-Every node's value is greater than both of its child node's value. Whether the left or right node is bigger is
 irrelevant; this isn't needed for the data structure and saves time
-Node values are stored in an array so its easy to get the last element (we can store the index of the last element and
 directly access it with an array). If a node has index i, then:
  1. Its left child has index 2 * i + 1
  2. Its right child has index 2 * i + 2
  3. Its parent has index (i - 1) // 2
-To extract the largest item, take the first value in the array. Next, replace it with the last element in the array
 (and delete this last element) and repeatedly swap this item with the largest of its children until it is larger than
 both children. This will ensure that we have the maximum item on the top
-To add an item, append the item to the array and repeatedly swap with its parent until it is smaller (or equal to) its
 parent

Time complexity
-Access first or last element: O(1) (if we use .append() with python, or store the last element's index)
-Enqueue: O(log(n))
-Dequeue: O(log(n))
-Find specific element: O(n)
"""


class PriorityQueue:
    def __init__(self):
        self._heap = []

    def enqueue(self, val):
        self._heap.append(val)
        i = len(self._heap) - 1  # Current index of the value
        parent = (i - 1) // 2
        while parent != -1 and val > self._heap[parent]:  # Value is at the top or its parent is larger
            self._heap[i], self._heap[parent] = self._heap[parent], self._heap[i]
            i = parent
            parent = (i - 1) // 2

    def dequeue(self):
        # Base cases: Empty heap or heap with 1 element
        if not self._heap:
            raise Exception("Queue is empty")
        if len(self._heap) == 0:
            high = self._heap[0]
            self._heap.pop()
            return high

        # Save the highest value then replace it with the lowest val
        high = self._heap[0]
        low = self._heap[-1]
        self._heap[0] = low
        self._heap.pop()

        # Shift the lowest value down until it has a greater value than its left and right nodes
        size = len(self._heap)
        i = 0  # Current low index
        left = 2 * i + 1
        right = 2 * i + 2
        while left < size or right < size:  # While the node is not at the bottom
            if left < size and right < size:  # Both children exist
                if low > self._heap[left] and low > self._heap[right]:  # Value is in the right spot
                    break
                elif self._heap[left] > self._heap[right]:  # Swap with the left node
                    self._heap[i], self._heap[left] = self._heap[left], self._heap[i]
                    i = left
                else:  # Swap with the right node
                    self._heap[i], self._heap[right] = self._heap[right], self._heap[i]
                    i = right
                left = 2 * i + 1
                right = 2 * i + 2
            else:  # Since this is a heap, it can't have only a right node. This case has to just be a left node
                if low < self._heap[left]:
                    self._heap[i], self._heap[left] = self._heap[left], self._heap[i]
                break

        return high


if __name__ == "__main__":
    q = PriorityQueue()
    n = 10

    # A more realistic priority queue would have elements with a priority and some sort of data (like a node structure)
    for num in range(n):
        q.enqueue(num)

    print(f"Heap: {q._heap}")
    print("Values: ", end='')
    for num in range(n):
        print(q.dequeue(), end=' ')
