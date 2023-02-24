package ca.reidmoffat.graphs;

public class SpanningTree {
}

/*
"""
A subgraph of a graph is a spanning tree if it contains every vertex of the graph and is a valid tree (only one parent
per node/vertex). Since we likely have to remove edges, there can be more than one spanning tree for a graph (all fully
connected graphs have at least one spanning tree)

Depth-first or breadth-first search can be used to make spanning trees. For weighted graphs, there are algorithms for
finding the spanning tree with the minimum total weight. This can be used for things like the travelling salesman
problem- find the minimum distance to travel between all given cities

Kruskal's algorithm: Always pick the next lowest weight edge that doesn't form a cycle (this is ok even if it isn't
connected to anything)

Prim's algorithm also finds a minimum spanning tree, but does it by going through the nodes that have been connected
already. It chooses the smallest weight edge in the current tree that doesn't cause a cycle. Time complexity: O(v^2) for
an adjacency matrix; O(vlogv + elogv) for adjacency list
"""
from random import randint
from typing import List


def prim_mst(graph: List[List[int]]):
    """
    Returns the total weights of the minimum spanning tree created by Prim's algorithm
    on the specified graph

    :param graph: an adjacency matrix representing a graph
    :return: total weight of the MST
    :rtype: int
    """
    # Prim's algorithm will always result in the same total weight (although not necessarily
    # distinct trees) for the same graph; any starting node can be used
    start = randint(0, len(graph) - 1)
    nodes = range(len(graph))  # A range of all the nodes in this graph used for iterating

    picked = [False for i in nodes]  # Nodes that are already in this MST
    picked[start] = True
    total_weight = 0

    # Edges that can be picked next (does not include edges that will cause a cycle)
    # The format of each key:value pair is edge:weight where:
    #  edge is a tuple with two node indices; the first node is in the subgraph and the second isn't
    #  weight is the weight of this edge, as defined in the graph adjacency matrix
    possible_edges = {(start, edge): graph[start][edge] for edge in nodes if graph[start][edge]}

    # Each loop iteration adds one node. Since there is already one start node, there are n-1 iterations needed
    for i in range(len(graph) - 1):
        # Find the edge with the minimum weight
        new_edge = min(possible_edges, key=possible_edges.get)
        new_node = new_edge[1]  # This node that will be added to the current subgraph

        # Add the weight of this new edge
        total_weight += graph[new_edge[0]][new_node]

        # Mark the new node
        picked[new_node] = True

        # Delete the old edge from the possibilities so it can't be chosen again
        del possible_edges[new_edge]

        # Add all (valid) edges connected to the new node to the dictionary of possible edges
        for neighbour in nodes:
            # If the neighbour node has not been picked and has a value that isn't 0, this means that
            # there is an edge between the new node and this node that won't make a cycle
            if not picked[neighbour] and graph[new_node][neighbour]:
                possible_edges[(new_node, neighbour)] = graph[new_node][neighbour]

    return total_weight

 */
