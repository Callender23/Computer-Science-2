
// jeffrey callender
// je410689
// summer 2019

import java.io.*;
import java.util.*;

class Node<AnyType>
{
    // generic values for both private variable value and height
    private AnyType value;
    private int height;

    // an array list that will point to the next node
    ArrayList<Node<AnyType>> nextNode = new ArrayList<>();

    // method that is initilizing all possible reference to null
    Node(int height)
    {
        this.height = height;
        for(int i = 0; i < height; i++)
            this.nextNode.add(null);

    }
    Node(AnyType data, int height)
    {
        this.height = height;
        this.value = data;
        for(int i = 0; i < height; i++)
            this.nextNode.add(null);
    }

    // returns the value stored at the node
    public AnyType value()
    {
        return this.value;
    }

    // return the height of stored node
    public int height()
    {
        return this.height;
    }

    // returns the reference to the next node at a certain level.
    public Node<AnyType> next(int level)
    {
        if(level < 0 || level > this.height - 1)
            return null;

        return this.nextNode.get(level);
    }

    // suggested helper methods

    // this sets all the nodes reference to a given level
    public void setNext (int level, Node<AnyType> node)
    {
        this.nextNode.set(level,node);
    }

    // causes the node to grow by one level. height will aslo increase as a result
    public void grow()
    {
        this.nextNode.add(height,null);
        this.height += 1;
    }

    // this grow the node by a level of one with a probablity of 50%
    public void maybeGrow()
    {
        if(Math.random() < 0.5)
        {
            this.nextNode.add(this.height,null);
            this.height += 1;
        }
    }

    // this will remove the references of the top node height. will be use when deleting nodes
    public void trim(int height)
    {
        for(int i = height; i < this.height; i++)
            this.nextNode.set(i,null);
        this.height = height;

    }
}

public class SkipList <AnyType extends Comparable<AnyType>>
{
    // we make the first node we have in the list to be empty
    private int size, height;
    private Node<AnyType> head;

    // this constuctor makes a new skiplist. the height of the node is then initliazed to 1 or 0
    SkipList()
    {
        // create new skip list with height of 0 or 1
        this.height = 1;
        this.head = new Node <AnyType>(height);
    }
    SkipList(int height)
    {
        // setting height to height of parameter
        // initilize the head node to have the height of height parameter
        this.height = height;
        this.head = new Node <AnyType>(height);
    }

    // just returns the number of node in the skip list
    public int size()
    {
        return this.size;
    }

    // return the height of the current skip list
    public int height()
    {
        return this.height;
    }

    // return the head of the skip list
    public Node<AnyType> head()
    {
        return this.head;
    }

    // method insert data into our skip list
    public void insert(AnyType data)
    {
        // this hashmap will hold all the node that are needed to be relinked once again.
        HashMap<Integer, Node<AnyType>>  nodeLinkerBacker = new HashMap <>();

        // create a reference node for our head
        Node<AnyType> referenceNode = this.head;

        // since we are inserting we need to expand the SkipList
        this.size += 1;

        int newMaxHeight = getMaxHeight(this.size);

        // if our maxHeight is greater than the current height then we grow the skip list
        if(newMaxHeight > this.height)
        {
            this.height = newMaxHeight;
            growSkipList();
        }
        // the level of each node
        int level = this.height - 1;

        // loops condition that makes sure that we dont have any empty or gone through all the level
        while(referenceNode != null && level >= 0)
        {
            // check to see of level is not empty and if inserted value is smaller than currnet
            // this put the node into the hash and then goes to the next level
            if(referenceNode.next(level) == null || (referenceNode.next(level).value()).compareTo(data) >= 0)
            {
                nodeLinkerBacker.put(level,referenceNode);
                level--;
            }

            // if the value is greater than the data then we keep moving in the skip list
            else if((referenceNode.next(level).value()).compareTo(data) < 0)
                referenceNode = referenceNode.next(level);
        }

        // we are going to generate a new random height here with our current height
        int randomHeight = generateRandomHeight(this.height);

        // we have to create another node here to have the stuff in our link list
        Node <AnyType> anotherNode = new  Node <AnyType> (data, randomHeight);

        // loop goes through the node reference and  link node to the previous reference
        for(int i = randomHeight - 1; i >= 0; i--)
        {
            Node <AnyType> aLittleOldNode = nodeLinkerBacker.get(i).next(i);
            anotherNode.setNext(i, aLittleOldNode);
            nodeLinkerBacker.get(i).setNext(i,anotherNode);
        }

    }
    public void insert(AnyType data, int height)
    {
        // going to hold our node that need to be relinked again to original nodes
        HashMap<Integer, Node<AnyType>> nodeLinkerBacker = new HashMap <>();

        int secondLevel = height -1;

        // reference node and incrementing the height since we are adding data to the list
        Node <AnyType> referenceNode = this.head;
        this.size += 1;
        int newMaxHeight = getMaxHeight(this.size);

        // if the new max height is greater than our current height then we grow the SkipList
        if(newMaxHeight > this.height)
        {
            this.height = newMaxHeight;
            this.growSkipList();
        }

        int level = this.height - 1;
        while(referenceNode != null && level >= 0)
        {
            // check to see of level is not empty and if inserted value is smaller than currnet
            // this put the node into the hash and then goes to the next level
            if(referenceNode.next(level) == null || (referenceNode.next(level).value()).compareTo(data) >= 0)
            {
                nodeLinkerBacker.put(level, referenceNode);
                level -= 1;
            }

            // if the values is greater than data then keep going in the list
            else if((referenceNode.next(level).value()).compareTo(data) < 0)
                referenceNode = referenceNode.next(level);
        }

        // the new node that will insert data into the skip list
        Node <AnyType> newNode = new Node <AnyType>(data, height);

        for(int i = secondLevel; i >= 0; i--)
        {
            Node <AnyType> theseOldNode = nodeLinkerBacker.get(i).next(i);
            newNode.setNext(i, theseOldNode);
            // now its time to link these nodes back to the original state
            nodeLinkerBacker.get(i).setNext(i,newNode);
        }
    }
    public void delete(AnyType data)
    {
        int level = this.height - 1;
        Node<AnyType> referenceNode = this.head;

        // this will keep track of the nodes to be link back to their original postion
        HashMap<Integer, Node<AnyType>> nodeLinkerBacker = new HashMap <>();

        // goes throught the same traveral as the insert and deletes the  nodes that are found
        while(referenceNode != null && level >= 0)
        {
            if(referenceNode.next(level) == null || (referenceNode.next(level).value()).compareTo(data) > 0)
            {
                level -= 1;
            }
            else if((referenceNode.next(level).value()).compareTo(data) < 0)
            {
                referenceNode = referenceNode.next(level);
            }
            else
            {
                nodeLinkerBacker.put(level, referenceNode);
                level -= 1;
            }
        }
        // reference node to the node that are being deleted here.
        referenceNode = referenceNode.next(level + 1);

        if(referenceNode != null && (referenceNode.next(level).value()).compareTo(data) == 0)
        {
            int secondLevel = referenceNode.height();
            int newHeight;

            for(int i = secondLevel - 1; i >= 0; i--)
            {
                nodeLinkerBacker.get(i).setNext(i, referenceNode.next(i));
            }
            this.size -= 1;

            if(this.size <= 1)
            {
                newHeight = 1;
            }
            else
            {
                newHeight = getMaxHeight(this.size);
            }
            if(newHeight < this.height)
            {
                this.height = newHeight;
                this.trimSkipList();
            }

        }
    }

    // this return true if the list contain the data
    public boolean contains(AnyType data)
    {
        int level = this.height - 1;
        Node <AnyType> tempBoi = this.head;

        // if the next level is empty then the data does not exist
        while(tempBoi != null && level >= 0)
        {
            if((tempBoi.next(level).value()).compareTo(data) > 0 || tempBoi.next(level) == null)
                level -= 1;
            else if((tempBoi.next(level).value()).compareTo(data) < 0)
                tempBoi = tempBoi.next(level);
            else
                return true;
        }
        return false;
    }

    public Node<AnyType> get(AnyType data)
    {
        // this is the level of the max height of the skip list
        int level = this.height - 1;
        Node <AnyType> nodeReference = this.head;
        while(nodeReference != null && level >= 0)
        {
            // goes through same check to see if empty or lower than the current node
            if(nodeReference.next(level) == null || (nodeReference.next(level).value()).compareTo(data) > 0)
                level -= 1;

            // if the node is bigger we simplu set the referenceNode to the next node in the list
            else if((nodeReference.next(level).value()).compareTo(data) < 0)
                nodeReference = nodeReference.next(level);
            else
                return nodeReference;
        }
        return null;
    }
    public static double difficultyRating()
    {
        // would give even higher but i am limited by the number here.
        return 5.0;
    }
    public static double hoursSpent()
    {
        return 55;
    }

    // helper functions from the pdf

    private static int getMaxHeight(int n)
    {
        // method that returns the max height of a skip list with n nodes
        return (int)Math.ceil((Math.log(n) / Math.log(2)));
    }

    // this will return with a probablity of 50 then a probablity of 25 then 12.5 and so on..
    private static int generateRandomHeight (int maxHeight)
    {
        // this is the height that will be possibly the max height of the list
        int celingHeight;

        for(celingHeight = 1; celingHeight < maxHeight; celingHeight++)
        {
            double probablityHeight = Math.random();
            if(probablityHeight < 0.5)
                break;
        }
        return celingHeight;
            // look at the way in class in which he showed us
    }

    // call to recrusive helper method of the same name
    private void trimSkipList()
    {
        trimSkipList(this.head);
    }

    private void growSkipList()
    {
        // level of each of the nodes and the next level of the nodes
        int level = this.height - 1;
        int secondLevel = level - 1;

        this.head.grow();

        // we have our next reference node and our current reference to our nodes to keep track
        Node<AnyType> nextNode = this.head;
        Node<AnyType> currentNode = nextNode.next(secondLevel);

        // while the nodes are not empty  we check if the node need to grow
        while(currentNode != null)
        {
            currentNode.maybeGrow();
            if(currentNode.height() == level + 1)
            {
                nextNode.setNext(level, currentNode);
                nextNode = nextNode.next(level);
            }

            // if our node has not grown then we procced to the next node
            currentNode = currentNode.next(secondLevel);
        }
    }
    private void trimSkipList(Node <AnyType> referenceNode)
    {
        // if the node is empty exit the this function
        if(referenceNode == null)
            return;

        // if not we go through the next reference node and then trim the skiplist
        trimSkipList(referenceNode.next(this.height - 1));
        referenceNode.trim(this.height);
    }
}
