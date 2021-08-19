"""
Hash tables are often the most efficient way to search for items by exploiting the speed of accessing array elements

They allows for searching, inserting and deleting values at practically O(1) time (technically, the worst time
complexity is O(n); but if the table is properly constructed and there aren't a lot of weird cases, we can amortize it
to constant time. Same reason why python .append() is O(1))

There are two main data structures that use hashing: hash tables/maps and hash sets. Tables/maps have a key/value pair;
sets are just elements. They don't allow for duplicate keys or ordering and have three main methods: add an element,
delete an element and check if an element (or key-value pair) exists

The main issue with hash structures is collisions. If we have not too many spots in our array, there will be hash values
that collide and we need to find a solution for this. We also need a good balance of space- an array that is too big
wastes a lot of space; one with too little space takes long to resolve collisions

There are two main ways to fix collisions:
1. Closed hashing (open addressing): If there is a collision, search for another open spot in the table and put it
   there. Linear probing, double hashing, quadratic hashing or other methods can be used. When the table is full, we can
   create a new, larger table by rehashing (re-compute the hash value for each element and re-create the table)
2. Open hashing (separate chaining): One array index can have more than one element, usually stored as a linked list

Open hashing is easier to use and understand, is often quicker for searching, doesn't have to worry about deleted flags
and allows for unlimited storage for any size table; but, it can take up more space though
The load factor is the measure of how full the table is: the number of elements in the table divided by the space. Open
hashing is quicker for a low load factor, but gets extremely slow with a high load factor (open hash tables can have a
load factor of greater than 1). See addressing.png for a comparison
"""
import math


def hash_val(key, m):
    """
    Example of a good hash function. Every hash function
    must follow the requirements of a hash function, and
    good also:
    1. Uses all of the params
    2. Has a uniform distribution
    3. Doesn't take too long to compute

    :key: The element's key (a number)
    :m: Size of the table
    :return: A hash value using the multiplication method
    """
    A = 0.38  # Any constant value 0 < A < 1
    # (key * A) % 1 gets a float 0 < result < 1, multiply by m
    # to get in the table's range then floor for an integer index
    return math.floor(m * (key * A) % 1)


def linear_probe(h, i, m):
    """
    Finds a possible next position using linear probing

    :param h: Computed hash value that has resulted in a collision
    :param i: Offset
    :param m: Size of the table
    :return: The next index to be checked if it is open
    """
    return (h + i) % m


def quadratic_probe(h, i, m):
    """
    Finds a possible next position using quadratic probing
    Note that there are many different ways of doing this, such as
    with a formula in the form ai + bi^2. This is just a simple example

    Quadratic hashing reduces primary clustering: linear probing may
    lead to large chunks of values together, reducing efficiency

    Quadratic hashing may lead to secondary clustering, although this
    isn't as much of an issue. The bigger problem is skipping indices
    (for example, we might only get to even indices in some cases); we
    need a load factor of at most 1/2 and a prime sized table to ensure
    that an empty cell will always be found

    :param h: Computed hash value that has resulted in a collision
    :param i: Offset
    :param m: Size of the table
    :return: The next index to be checked if it is open
    """
    return (h + i*i) % m


def double_hash(hash1, hash2, i, m):
    """
    Finds a possible next position using double hashing

    Double hashing is one of the most efficient choices since it uses
    two independent hash functions to find the hash value. This
    results in not many collisions and not primary or secondary
    clustering

    The second hash function has to be relatively prime to the # of
    buckets (for example, if m is even, return a hash value that is
    odd (return x if x is odd, x+1 if x is even))

    :param hash1: First hash value
    :param hash2: Second hash value
    :param i: Offset
    :param m: Size of the table
    :return: The next index to be checked if it is open
    """
    return (hash1 + i*hash2) % m
