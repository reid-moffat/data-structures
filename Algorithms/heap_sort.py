"""
HeapSort uses heaps to find the maximum element each time and place it in the sorted section of the array (like
selection sort, but heaps are O(log(n)) to shift an element instead of O(n) and we use the largest, not smallest,
element each time)

Steps:
1. Build a max heap of the array O(n)
2. Repeat n times: Remove the largest element and re-heap i.e swap the max and last element then move it down O(log(n))

Total time complexity: O(n + n*log(n)) = O(nlog(n))
Note: Heapsort isn't used that much since it isn't as fast ad other sorting algorithms, but it's good for visualizing
"""
import random
import time


def heapSort(arr):

    def heapify(root, stop):
        largest = root  # Initialize largest as root
        left = 2 * root + 1  # left node index
        right = 2 * root + 2  # right node index

        # If left child of root exists and is greater than root
        if left < stop and arr[largest] < arr[left]:
            largest = left

        # If right child of root exists and is greater than root
        if right < stop and arr[largest] < arr[right]:
            largest = right

        # Change root, if needed
        if largest != root:
            arr[root], arr[largest] = arr[largest], arr[root]  # swap

            # Heapify the root
            heapify(largest, stop)

    # Build a max heap
    end = len(arr)
    for i in range(end // 2 - 1, -1, -1):  # end // 2 - 1 is the last non-leaf node
        heapify(i, end)

    # One by one move the max element to the end of the array
    for i in range(end - 1, 0, -1):
        arr[i], arr[0] = arr[0], arr[i]  # Swap the last element and max element (of the unsorted portion)
        heapify(0, i)  # Move the last element to its correct place (and the largest element to the top)


if __name__ == "__main__":
    n = 10000
    array = [random.randint(1, 1000) for i in range(n)]

    start_time = time.time()
    heapSort(array)
    end_time = time.time()
    print(f"Execution time: {1000*(end_time - start_time):.2f} ms")

    assert all([array[i] <= array[i+1] for i in range(n-1)])
