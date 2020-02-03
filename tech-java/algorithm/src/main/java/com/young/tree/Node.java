package com.young.tree;

public class Node {
  public int value;
  public Node leftChild;
  public Node rightChild;
  public Node (int value){
    this.value = value;
  }


  public static Node buildTree(){
    Node root = new Node(6);
    Node rLeft = new Node(3);
    Node rRight= new Node(8);
    Node r31Right =new Node(99);
    Node r32Left =new Node(32);
    Node r32Right =new Node(1);
    root.leftChild = rLeft;
    root.rightChild = rRight;
    rLeft.rightChild = r31Right;
    rRight.leftChild = r32Left;
    rRight.rightChild = r32Right;
    return  root;

  }

}
