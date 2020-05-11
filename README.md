# double-ended-priority-queue

A double ended priority queue supports operations of both max heap (a max priority queue) and min heap (a min priority queue). The following operations are expected from double ended priority queue.

- isEmpty() -Checks if DEPQ is empty and returns true if empty 
- size() -Returns the total number of elements present in the DEPQ 
- getLow() -Returns the element having least priority 
- getHigh() -Returns the element having highest priority 
- add(x) -Inserts the element x in the DEPQ
- removeLow() -Removes an element with minimum priority and returns this element 
- removeHigh() -Removes an element with maximum priority and returns this element

To successfully build the double ended priority queue, we decided to use the build the DEPQ based on an interval heap data structure.

An interval heap is a complete binary tree in which each node, except possibly the last one (the nodes of the complete binary tree are ordered using a level order traversal), contains two elements. Let the priorities of the two elements (in the sequel, we do not differentiate between an element and its priority) in node P be a and b, where a <= b. We say that the node P represents the closed interval [a,b]. a is the left end point of the interval of P, and b is its right end point. The interval [c,d] is contained in the interval [a,b] iff a <= c <= d <= b. In an interval heap, the intervals represented by the left and right children (if they exist)of each node P are contained in the interval represented by P. When the last node contains a single element with priority c, then a <= c <= b, where [a,b] is the interval of the parent (if any) of the last node.

