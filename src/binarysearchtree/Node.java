package binarysearchtree;

public class Node
{
    Node left;
    int data;
    Node right;
    Node parent;

    //location coordinate
    int parentX, parentY;
    int posX, posY;
    int arrowType;//zero indicate left; right is 1
    public Node()
    {
        left = null;
        right = null;
        data = 0;
    }
}


