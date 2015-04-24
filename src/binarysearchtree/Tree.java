package binarysearchtree;

import javax.swing.JOptionPane;

public class Tree{
    Node Root = null;
    String TraversalResult  ;
    int length;
    int x  , y ;
    int counter = 0;
    boolean found ;
    int searchValue;

    public Tree(){
        Root = null;
        x = 650;
        y = 100;
        TraversalResult="";
        length = 0;
        counter = 0;
        found = false;
    }

    public void AddToBST(int data){
          length++;
          
          if(Root == null){
               Root = new Node();
               Root.data = data;
               Root.posX = x;
               Root.posY = y;
               Root.parentX = x;
               Root.parentY = y;
               return;
          }

          add(data, Root);

     }

     private void add(int currentData, Node position){
     
          if(currentData<position.data){
               
               if(position.left==null){
                    position.left = new Node();
                    position.left.data = currentData;
                    
                    position.left.parent = position;

                    position.left.arrowType = 0;
                    position.left.parentX = position.posX;
                    position.left.parentY = position.posY;

                    position.left.posX = position.posX-(int)(1250/(Math.pow(2.0,(double)(position.left.posY%25+2)+1)));
                    position.left.posY = position.posY + 25;
                    
                    return;
               }
               
               add(currentData, position.left);
          }else{
               
               if(position.right==null){
                    position.right = new Node();
                    position.right.data = currentData;
                    
                    position.right.parent = position;

                    position.right.arrowType = 1;
                    position.right.parentX = position.posX;
                    position.right.parentY = position.posY;

                    position.right.posX = position.posX+(int)(1250/(Math.pow(2.0,(double)(position.right.posY%25+2)+1)));
                    position.right.posY = position.posY + 25;
                    
                    return;
               }
               
               add(currentData, position.right);
          }
     }
   
    public void DeleteFromBST(int number){
        System.out.println("sup");
        Node current = Root;
        Node parent = Root;

        boolean isLeftChild = true;

        //while loop to search for node to delete
        while (current != null){//travers
            if(number == current.data)
                break;
            if(number < current.data){
                isLeftChild = true;
                parent = current;
                current  = current.left;
            }else{
                isLeftChild = false;
                parent = current;
                current  = current.right;
            }
            
        }
        if(current == null){
            JOptionPane.showMessageDialog(null,
            " Node does not exist",
            " Can NOT delete -- "+number,
            JOptionPane.WARNING_MESSAGE);
            return ;
        }else if(current.left == null && current.right == null){//leaf node
            if(current == Root){
                Root = null;
            }else if(isLeftChild){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }else if(current.right == null){//left child
            if(current == Root) 
                Root = current.left; 
            else if(isLeftChild){

                parent.left = current.left;
            }else {
                parent.right = current.left;
                parent.right.arrowType=1;
            }
            
        }else if(current.left == null){//right child
            if(current == Root){
                Root = current.right;
            }else if(isLeftChild) {
                parent.left = current.right;
                parent.left.arrowType=0;
            }else {
                parent.right = current.right;
            }
            
        }else{
            Node successor = getSuccessor(current); //get successor
            if(current == Root)
                Root = successor;
            else if(isLeftChild)
                parent.left= successor; //set node to delete to successor
            else
                parent.right = successor;
            //attach current's left to successor's left since successor has no left child
            successor.left = current.left;

         }
        length--;
    }
  
   public Node getSuccessor(Node delNode){
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.right;

        while(current != null){
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if(successor != delNode.right){
            successorParent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
    }

     public Node searchBST(int searchData){
          if(Root == null){
               return null;
          }
          found = false;
          return search(searchData, Root);
     }

      private Node search(int searchData, Node node){
          if(node!=null){
              if(node.data == searchData){
                  found = true;
                  System.out.println("found!");
                   return node;
              }
              if(searchData < node.data){
                  System.out.print(node.data+" ");
                   return search(searchData, node.left);
              }else{
                    System.out.print(node.data+" ");
                   return search(searchData, node.right);
              }
          }else
              return null;
     }

    public boolean isUnique(int number){
        Node temp = Root;
        while(temp!=null){
            if(temp.data == number){
                  return false;
            }else{
                if(number < temp.data){
                    temp = temp.left;
                }else{
                    temp = temp.right;
                }
            }
        }
        return true;
    }

    public void InOrder(Node T){
       if(T != null){
          InOrder (T.left);
          if(found && (T.data==searchValue)){
              TraversalResult+="["+T.data+"], ";
              found = false;
          }else{
              TraversalResult+=T.data+" ,";
          }
          counter++;
          if(counter%20==0){
              TraversalResult+="\n";
          }
          
          InOrder (T.right);
       }
    }

    public void PreOrder(Node T){
      if(T != null){
          TraversalResult+=T.data+" ,";
          counter++;
          if(counter%20==0){
              TraversalResult+="\n";
          }
          PreOrder(T.left);
          PreOrder(T.right);
      }
    }

    public void PostOrder(Node T){
      if(T != null){
          PostOrder(T.left);
          PostOrder(T.right);
          TraversalResult+=T.data+" ,";
          counter++;
          if(counter%20==0){
                TraversalResult+="\n";
          }
      }
    }


}


