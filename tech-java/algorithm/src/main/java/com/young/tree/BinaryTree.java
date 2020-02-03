package com.young.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BinaryTree {
  public static void main(String[] args){
    floorSearch(Collections.singletonList(Node.buildTree()));
  }

  private static void floorSearch(List<Node> preList){
    if(preList.isEmpty()||preList.stream().allMatch(Objects::isNull)){
      return;
    }
    List<Node> currentList = new ArrayList<>();
    for(Node node : preList){
      if(node==null){
        currentList.add(null);
        currentList.add(null);
        System.out.print("null,");
      }else {
        currentList.add(node.leftChild);
        currentList.add(node.rightChild);
        System.out.print(node.value+",");
      }
    }
    floorSearch(currentList);
  }

}
