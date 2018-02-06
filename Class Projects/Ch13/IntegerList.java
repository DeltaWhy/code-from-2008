//Michael Limiero 
// ****************************************************************
// IntegerList.java
//
// Define an IntegerList class with methods to create, fill,
// sort, and search in a list of integers.
//          
// ****************************************************************

import java.util.Scanner;

public class IntegerList{

    int[] list; //values in the list
    int end;

    //-------------------------------------------------------
    //create a list of the given size
    //-------------------------------------------------------
    public IntegerList(int size)
    {
	list = new int[size + 3];
    end = size;
    }


    //-------------------------------------------------------
    //fill array with integers between 1 and 100, inclusive
    //-------------------------------------------------------
    public void randomize()
    {
	for (int i=0; i<end; i++)
	    list[i] = (int)(Math.random() * 100) + 1;
    }


    //-------------------------------------------------------
    //print array elements with indices
    //-------------------------------------------------------
    public void print()
    {
	for (int i=0; i<end; i++)
	    System.out.println(i + ":\t" + list[i]);
    }


    //-------------------------------------------------------
    //return the index of the first occurrence of target in the list.
    //return -1 if target does not appear in the list
    //-------------------------------------------------------
    public int search(int target)
    {
	int location = -1;
	for (int i=0; i<end && location == -1; i++)
	    if (list[i] == target)
            location = i;
	return location;
    }


    //-------------------------------------------------------
    //sort the list into ascending order using the selection sort algorithm
    //-------------------------------------------------------
    public void selectionSort()
    {
	int minIndex;
	for (int i=0; i < end-1; i++)
	    {
		//find smallest element in list starting at location i
		minIndex = i;
		for (int j = i+1; j < end; j++)
		    if (list[j] < list[minIndex])
			    minIndex = j;

		//swap list[i] with smallest element
		int temp = list[i];
		list[i] = list[minIndex];
		list[minIndex] = temp;
	    }
    }
    
    public void sortDecreasing()
    {
        int maxIndex;
        for (int i=0; i < end-1; i++)
	    {
            //find largest element in list starting at location i
            maxIndex = i;
            for (int j = i+1; j < end; j++)
                if (list[j] > list[maxIndex])
                    maxIndex = j;
    
            if (i != maxIndex) //a bit more efficient?
            {
                //swap list[i] with largest element
                int temp = list[i];
                list[i] = list[maxIndex];
                list[maxIndex] = temp;
            }
	    }
    }
    
    public void replaceFirst(int oldVal, int newVal)
    {
        int pos = search(oldVal);
        if (pos < 0) return; // (x < 0) is faster than (x == -1)
        list[pos] = newVal;
    }
    
    public void replaceAll(int oldVal, int newVal)
    {
        for (int i=0; i<end; i++)
            if (list[i] == oldVal)
                list[i] = newVal;
    }
    
    private void increaseSize()
    {
        int[] old = list;
        list = new int[old.length + 3];
        for (int i = 0; i < end; i++) list[i] = old[i];
    }
    
    public void addElement(int newVal)
    {
        if (end == list.length) increaseSize();
        list[end] = newVal;
        end++;
    }
    
    public void removeFirst(int newVal)
    {
        int pos = search(newVal);
        //move everything down
        for (int i = pos; i < end; i++)
            list[i] = list[i+1];
        end--;
    }
    
    public void removeAll(int newVal)
    {
        for (int i = end-1; i >=0; i--) //loop backwards
        {
            if (list[i] == newVal)
            {
                for (int j = i; j < end; j++)
                    list[j] = list[j+1]; //shift down
                end--;
            }
        }
    }
    
    public void addInOrder(int newVal)
    {
        if (end == list.length) increaseSize();
        int pos = 0;
        while (list[pos] < newVal) pos++;
        //pos is where the new value goes
        for (int i = end; i > pos; i--) //loop backwards
            list[i] = list[i-1]; //shift up
        list[pos] = newVal;
        end++;
    }
}
