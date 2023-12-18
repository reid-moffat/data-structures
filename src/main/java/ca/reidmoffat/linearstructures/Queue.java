package ca.reidmoffat.linearstructures;

public class Queue {
}
/*
"""
A queue is a collection of objects according to the first-in-first-out principle

Uses of queues:
-Many applications of loading, such as video buffering or website loading
-JavaScript async operations (once a promise is resolved, it is added to a queue)
-Lists of tasks to do
-A first-come-first-serve common resource shared between multiple people

Variations of queues:
-Priority queue (very useful in a lot of situations; priority is put before insertion order)
-Double-ended queue (deque); often used for queues with a maximum size (whe na new item is added, the oldest item is
 removed to save space

Note: Encapsulation MUST be followed with a stack. If we can access and directly mutate the underlying data structure
used for storage, it no longer becomes a stack (just an array/linked list/etc)
"""
import random


class Queue:
    def __init__(self):
        self.__queue = []

    # For the len() operator i.e len(queue) works
    def __len__(self):
        return len(self.__queue)

    def empty(self):
        return len(self.__queue) == 0

    def size(self):
        return len(self.__queue)

    def first(self):
        return self.__queue[0] if not self.empty() else None

    def enqueue(self, e):
        self.__queue.append(e)

    def dequeue(self):
        return self.__queue.pop(0) if not self.empty() else None


if __name__ == "__main__":
    queue = Queue()

    for i in range(50):
        queue.enqueue(random.randint(-50, i))

    print(f"Queue length: {len(queue)}")
    print(f"Queue elements in order: ", end='')

    for i in range(50):
        print(f"{queue.dequeue()} ", end='')

 */
