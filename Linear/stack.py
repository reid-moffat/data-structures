"""
A stack is a collection of objects according to the last-in-first-out principle

Stacks are often implemented with lists in python for simplicity, but LinkedLists acn also be used. They provide a way
of listing items in the order they were inserted (for elements that weren't removed) and popping the last added element

Uses of stacks:
-For data history such as an undo function, web history, clipboard, etc
-Parenthesis matching
-Reversing data (such as a word or linked list)
-Expression evaluation

Note: Encapsulation MUST be followed with a stack. If we can access and directly mutate the underlying data structure
used for storage, it no longer becomes a stack (just an array/linked list/etc)
"""


class Stack:
    def __init__(self):
        self.__stack = []

    # For the len() operator i.e len(stack) works
    def __len__(self):
        return len(self.__stack)

    def empty(self):
        return len(self.__stack) == 0

    def size(self):
        return len(self.__stack)

    def top(self):
        return self.__stack[-1] if len(self.__stack) > 0 else None

    def push(self, e):
        self.__stack.append(e)

    def pop(self):
        return self.__stack.pop() if len(self.__stack) > 0 else None


if __name__ == "__main__":
    letters = Stack()

    for i in "Hello, world!":
        letters.push(i)

    for i in range(letters.size()):
        print(letters.pop(), end='')
