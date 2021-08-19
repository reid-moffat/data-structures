"""
Graphs are non-linear data structures consisting of connected nodes

There are two main ways of representing them:
1. Adjacency matrix: Row i column j is 0 if nodes i and j are mot connected; 1 if they are
2. Adjacency list: An array (or dictionary) entry for each node with a linked list that lists the nodes it is connected
   to (unordered)

Adjacency matrices are easier to understand and implement as well as allows quicker accessing, but they waste a lot of
space (it is symmetric and the middle is all zeros if there are no self-loops, making over half, or (m^2 + m)/2 of the
array indices redundant). Generally, more sparse graphs should use adjacency lists (no need to waste most of the m^2
space required), and more dense ones should use adjacency

There are many different types of graphs, for example:
-Weighted graphs: Some edges have weights greater than others (the matrix won't just be 0/1)
-Directed graphs: not necessarily symmetrical

Graphs typically have the following methods:
-Search
-Insert (vertex or edge)
-Delete (for vertices or edges)
-Modify (for weighted/directed graphs)
-Verify the graph: Used to check the type often (like directed, weighted, etc)
-Traversal: Used to visualize the graph or search for a node
Also some helper methods:
-isEmpty() (for the 'number of' methods, it's best to keep a running count as a field for O(1) time)
-Number of vertices
-Number of edges
-Check if edge exists
-Retrieve a vertex that contains a given value

One obvious question to answer is how to traverse a graph? Each vertex can have a theoretically infinite amount of edges
and there is not clear way of determining what the next one is. There are two main ways:
-Breadth-first search goes through all of the nodes based on their distance from the first node, like a level-order
 traversal (all nodes 1 away, then 2 away, etc). Use a queue to traverse the graph and have a 'visited' list to store
 the vertices that have already been visited so we don't traverse the same node multiple times
-Depth-first search keeps moving forward until it hits a dead end or previously-visited vertex, then backtracks
"""
from random import randint


class Graph:

    def __init__(self, size):
        self._graph = [[0] * size for i in range(size)]
        self._size = size
        self._edges = 0

    def __len__(self):
        return self._size

    def add_edge(self, v1, v2):
        if v1 != v2 and self._graph[v1][v2] == 0:
            self._graph[v1][v2] = 1
            self._graph[v2][v1] = 1
            self._edges += 1

    def remove_edge(self, v1, v2):
        if self._graph[v1][v2] == 1:
            self._graph[v1][v2] = 0
            self._graph[v2][v1] = 0
            self._edges -= 1

    def edge_exists(self, v1, v2):
        return self._graph[v1][v2] == 1

    def bfs(self):
        """
        Performs a breadth-first search on a Graph using a random starting node

        :return: total weight from this tree
        :rtype: int
        """
        total_weight = 0
        starting_node = randint(0, len(self._graph) - 1)

        queue = [starting_node]
        visited = [False for node in range(len(self._graph))]
        visited[starting_node] = True

        while queue:
            vertex = queue.pop(0)

            # Process all valid node neighbours
            for neighbour in range(len(self._graph)):
                weight = self._graph[vertex][neighbour]
                if weight and not visited[neighbour]:
                    total_weight += weight
                    queue.append(neighbour)
                    visited[neighbour] = True

        return total_weight


if __name__ == "__main__":
    n = 10
    graph = Graph(n)

    for i in range(15):
        graph.add_edge(randint(0, n-1), randint(0, n-1))

    print(graph.bfs())
