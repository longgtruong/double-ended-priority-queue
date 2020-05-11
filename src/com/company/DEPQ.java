package com.company;

import java.util.ArrayList;

public class DEPQ {

    private ArrayList<Node> intervalHeap;
    private int nrOfElements = 0;
    private static final int FRONT = 1;

    public DEPQ() {
        intervalHeap = new ArrayList<>();
        /* Add a dummy cell to the elements list to make all our
         * arithmetic 1-indexed.
         */
        intervalHeap.add(new Node(null,null));
    }

    public boolean isEmpty() {
        return nrOfElements == 0;
    }

    public int size() {
        return nrOfElements;
    }

    public int getLow() {
        if (isEmpty()) {
            throw new IllegalArgumentException("EMPTY QUEUE.");
        } else {
            return intervalHeap.get(1).low;
        }
    }

    public int getHigh() {
        if (isEmpty()) {
            throw new IllegalArgumentException("EMPTY QUEUE.");
        } else {
            if (nrOfElements==1) {
                return intervalHeap.get(1).low;
            } else {
                return intervalHeap.get(1).high;
            }
        }
    }

    public void add(int i) {
        if (size() % 2 == 0) {
            intervalHeap.add(new Node(i,null));
        } else {
            Node node = intervalHeap.get(intervalHeap.size()-1);
            if (node.low > i) {
                node.high = node.low;
                node.low = i;
            } else {
                node.high = i;
            }
        }
        nrOfElements++;
        // there is no children
        if (size()<=2) {
            return;
        }
        Node parent = intervalHeap.get((intervalHeap.size()-1)/2);
        if (parent.low>i) {
            minHeapInsert();
        } else if (parent.high<i) {
            maxHeapInsert();
        } else {
            System.out.println();
        }
    }

    private void minHeapInsert() {
        int index = intervalHeap.size() - 1;
        Node node = intervalHeap.get(index);
        while (index>1) {
            int parent = parent(index);
            Node parentNode = intervalHeap.get(parent);
            if (parentNode.low>node.low) {
                int temp = node.low;
                node.low = parentNode.low;
                parentNode.low = temp;
                index = parent(index);
                node=parentNode;
            }

        }
    }

    private void maxHeapInsert() {
        int index = intervalHeap.size() - 1;
        Node node = intervalHeap.get(index);
        while (index>1) {
            int parent = parent(index);
            Node parentNode = intervalHeap.get(parent);
            if (node.high==null) {
                if (node.low>parentNode.high) {
                    int temp = node.low;
                    node.low = parentNode.high;
                    parentNode.high = temp;
                    index=parent;
                    node = parentNode;
                }
            } else {
                if (node.high>parentNode.high) {
                    int temp = node.high;
                    node.high = parentNode.high;
                    parentNode.high = temp;
                    index=parent;
                    node = parentNode;
                }
            }
        }
    }

    public int removeLow() {
        int minValue = getLow();

        /* When the interval heap has only one element,
        * this element should be returned.
        * We leave behind an interval heap without any element.
        */
        if (size()==1) {
            intervalHeap.remove(1);
            nrOfElements--;
            return getLow();
        }


        /** We fill the low element place we just removed by
         * the low element of the last node.
         *
         * When the last node is not the root node,
         * we eliminate the left point p from the last node.
         * If this causes the last node to become vacant,
         * the last node is no longer part of the heap.
         */
        Node lastNode = intervalHeap.get(intervalHeap.size()-1);
        intervalHeap.get(1).low = lastNode.low;


        if (size() % 2 == 1) {
            intervalHeap.remove(intervalHeap.size()-1);
        } else {
            lastNode.low = lastNode.high;
            lastNode.high=null;
        }

        minHeapify(1);
        return minValue;
    }

    public int removeHigh() {
        int maxValue = getHigh();

        /* When the interval heap has only one element,
         * this element should be returned.
         * We leave behind an interval heap without any element.
         */
        if (size()==1) {
            intervalHeap.remove(1);
            nrOfElements--;
            return getHigh();
        }


        /** We fill the high element place we just removed by
         * the high element of the last node.
         *
         * When the last node is not the root node,
         * we eliminate the left point p from the last node.
         * If this causes the last node to become vacant,
         * the last node is no longer part of the heap.
         */
        Node lastNode = intervalHeap.get(intervalHeap.size()-1);

        if (size() % 2 == 1) {
            intervalHeap.get(1).high=lastNode.low;
            intervalHeap.remove(intervalHeap.size()-1);
        } else {
            intervalHeap.get(1).high = lastNode.high;
            lastNode.high=null;
        }

        maxHeapify(1);
        return maxValue;
    }

    private int parent(int node) {
        if (node % 2 == 0) {
            return node / 2;
        }
        return (node - 1) / 2;
    }

    private void minHeapify(int node) {
        int left = left(node);
        int right = right(node);
        int smallest = -1;

        // find the smallest key between current node and its children.
        if (left <= intervalHeap.size() - 1 && intervalHeap.get(left).low < intervalHeap.get(node).low) {
            smallest = left;
        } else {
            smallest = node;
        }
        if (right <= intervalHeap.size() - 1 && intervalHeap.get(right).low < intervalHeap.get(smallest).low) {
            smallest = right;
        }
        // if the smallest key is not the current key then bubble-down it.
        if (smallest != node) {
            int temp = intervalHeap.get(node).low;
            intervalHeap.get(node).low = intervalHeap.get(smallest).low;
            intervalHeap.get(smallest).low=temp;
            minHeapify(smallest);
        }
    }

    private void maxHeapify(int node) {
        int left = left(node);
        int right = right(node);
        int highestIndex=0;

        // find the highest key between current node and its children.
        if (left < intervalHeap.size() - 1 && intervalHeap.get(left).high > intervalHeap.get(node).high) {
            highestIndex = left;
        } else {
            highestIndex = node;
        }
        if (right < intervalHeap.size() - 1 && intervalHeap.get(right).high > intervalHeap.get(highestIndex).high) {
            highestIndex = right;
        }
        // if the highest key is not the current key then bubble-down it.
        if (highestIndex != node) {
            int temp = intervalHeap.get(node).high;
            intervalHeap.get(node).high = intervalHeap.get(highestIndex).high;
            intervalHeap.get(highestIndex).high=temp;
            maxHeapify(highestIndex);
        }
    }

    public void print() {
        for (int i=1;i<intervalHeap.size();i++) {
            System.out.println("["+intervalHeap.get(i).low+", " +intervalHeap.get(i).high+"]");
        }
    }

    public int left(int i) {
        return 2*i;
    }

    public int right (int i) {
        return 2*i+1;
    }


}
