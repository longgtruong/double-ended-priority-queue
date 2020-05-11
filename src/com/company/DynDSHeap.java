package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class DynDSHeap {

    private ArrayList<Integer> heap;
    private int size;
    private ArrayList<Integer> deadSpace = new ArrayList<>();


    /**
     * this is a constructor for DynDSHeap, used for the relacement selection algorithm
     * @param size
     */
    public DynDSHeap(int size) {
        heap = new ArrayList<>();
        this.size = size;
    }
    /**
     * this is a constructor for DynDSHeap, it retrieve the arr params and build a min heap from it
     * @param arr
     */
    public DynDSHeap(ArrayList<Integer> arr) {
        heap=arr;
        this.size=arr.size();
        buildHeap();
    }

    /**
     * @return returns if heap is currently empty
     */
    public boolean isEmpty() {
        return heap.size() == 0;
    }

    /**
     * insert element into the heap
     * @param i
     */
    public void insert(int i) {
            heap.add(i);
            int node = heap.size()-1;
            int parent = parent(node);
            while (parent!=node && heap.get(node)<heap.get(parent)) {
                swap(node,parent);
                node=parent;
                parent=parent(node);
            }
    }

    /**
     * recursively swap node to maintain the min heap
     * @param node
     */
    private void minHeapify(int node) {
        int left = left(node);
        int right = right(node);
        int smallest = -1;

        // find the smallest key between current node and its children.
        if (left <= heap.size() - 1 && heap.get(left) < heap.get(node)) {
            smallest = left;
        } else {
            smallest = node ;
        }
        if (right <= heap.size() - 1 && heap.get(right) < heap.get(smallest)) {
            smallest = right;
        }
        // if the smallest key is not the current key then bubble-down it.
        if (smallest != node) {
            swap(node, smallest);
            minHeapify(smallest);
        }
    }

    /**
     * build heap from initialized array
     */

    private void buildHeap() {
        for (int i=heap.size()/ 2;i>=0;i--) {
            minHeapify(i);
        }
    }

    /**
     * @return  returns the parent node index
     */
    private int parent(int node) {
        if (node % 2 == 1) {
            return node / 2;
        }
        return (node - 1) / 2;
    }

    /**
     * @return  returns left child node index
     */
    private int left (int node) {
        if (node==0 || node ==1) {
            return 2*node+1;
        }
        return 2*node;
    }

    /**
     * @return  returns right child node index
     */
    private int right (int node) {
        if (node==0 || node==1) {
            return 2*node+2;
        }
        return 2*node+1;
    }

    /**
     * swap 2 nodes in the heap
     * @param i
     * @param j
     */
    private void swap(int i,int j) {
        int temp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,temp);
    }

    /**
     * remove the minium value of the heap, put the last node index value into the
     * place we just removed from
     * minHeapify the root node
     * @return  mininum value of the heap
     */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("EMPTY HEAP");
        } else if (heap.size()==1) {
            int min = heap.remove(0);
            return min;
        } else {
                int min = heap.get(0);
                int lastNode = heap.remove(heap.size() - 1);
                heap.set(0, lastNode);
                minHeapify(0);
                return min;
        }
    }

    /**
     * method to print out the heap
     */
    public void print() {
        System.out.print("HEAP: ");
        System.out.println(heap.toString());
    }

    /**
     * method to add the element to dead space
     * @param i
     */
    public void addToDeadSpace(int i) {
        deadSpace.add(i);
    }

    /**
     * method to build heap from dead space
     * clear the dead space
     */
    public void buildHeapFromDeadSpace() {
        heap = new ArrayList<Integer>(deadSpace);
        buildHeap();
        deadSpace=new ArrayList<>();
    }

    /**
     * @return  returns list of elements from dead space
     */
    public ArrayList<Integer> getDeadSpace() {
        return deadSpace;
    }
}
