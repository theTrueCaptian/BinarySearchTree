package binarysearchtree;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Maeda Hanafi
 * 5/3/10
 * binary search tree operations
 */
public class BST extends Frame implements ActionListener{

    // Retrieved command code
    String command = "";
    Tree tree ;
    RandomNumberGenerator mr = new RandomNumberGenerator(10,100,1000);
    static Frame frame;
    Image node = Toolkit.getDefaultToolkit().getImage("F:\\binarySearchTree\\node.gif");
    
    
    public static void main(String[] args) {
        frame = new BST();
        frame.setResizable(true);
        frame.setSize(1250,900);
        frame.setVisible(true);
        
    }

    public BST(){
        setVisible(true);
        setTitle("Binary Search Tree Operations");
        // Create Menu Bar
        MenuBar bar = new MenuBar();
        setMenuBar(bar);

        // Create Menu Group Labeled "Operations"
        Menu utility = new Menu("Operations");
        bar.add(utility);

        // Create Menu Items
        // Add action Listener
        // Add to "Utilities" Menu Group
        MenuItem addNode = new MenuItem("Add");
        addNode.addActionListener(this);
        utility.add(addNode);

        MenuItem delNode = new MenuItem("Delete");
        delNode.addActionListener(this);
        utility.add(delNode);

        MenuItem searchNode = new MenuItem("Search for a Value");
        searchNode.addActionListener(this);
        utility.add(searchNode);

        MenuItem exit = new MenuItem("Exit");
        exit.addActionListener(this);
        utility.add(exit);
       
        //Create Menu Group Labeled "Traverse"
        Menu traverse = new Menu("Traverse");
        bar.add(traverse);

        //Create Menu Items
        MenuItem pre = new MenuItem("Pre-Order");
        pre.addActionListener(this);
        traverse.add(pre);

        MenuItem in = new MenuItem("In-Order");
        in.addActionListener(this);
        traverse.add(in);

        MenuItem post = new MenuItem("Post-Order");
        post.addActionListener(this);
        traverse.add(post);

        //Create Menu Group Labeled "utility"
        Menu utilities = new Menu("Utility");
        bar.add(utilities);

        //Create Menu Items
        MenuItem createTree = new MenuItem("Create a Tree");
        createTree.addActionListener(this);
        utilities.add(createTree);

         //Create Menu Group Labeled "demo"
        Menu demo = new Menu("Demo");
        bar.add(demo);

        //Create Menu Items
        MenuItem startDemo = new MenuItem("Start Demo");
        startDemo.addActionListener(this);
        demo.add(startDemo);

        // End program when window is closed
        WindowListener l = new WindowAdapter(){
            public void windowClosing(WindowEvent ev){
                System.exit(0);
            }
            public void windowActivated(WindowEvent ev){
                repaint();
            }
            public void windowStateChanged(WindowEvent ev){
                repaint();
            }
        };

        ComponentListener k = new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
            repaint();
        }
        };

        // register listeners
        this.addWindowListener(l);
        this.addComponentListener(k);

        tree = new Tree();
       // tree.Root.posX = frame.getWidth()/2;
       // tree.Root.posY = 50;
    }
    
    public void actionPerformed(ActionEvent ev) {
        command = ev.getActionCommand();
        tree.TraversalResult = "";
        if("Add".equals(command)){
            tree.AddToBST(mr.getRandomNumber());
            tree.counter = 0;
            tree.InOrder(tree.Root);
            repaint();
        }else if("Delete".equals(command)){
            tree.DeleteFromBST(getData());
            tree.counter = 0;
            tree.InOrder(tree.Root);
            System.out.println(tree.TraversalResult);
            repaint();
        }else if("Search for a Value".equals(command)){
            tree.searchValue = getData();
            tree.searchBST(tree.searchValue);
            tree.counter = 0;
            tree.InOrder(tree.Root);
            repaint();
        }else if("Exit".equals(command)){
            System.exit(0);
        }else if("Pre-Order".equals(command)){
            tree.counter = 0;
            tree.PreOrder(tree.Root);
            repaint();
        }else if("In-Order".equals(command)){
            tree.counter = 0;
            tree.InOrder(tree.Root);
            repaint();
        }else if("Post-Order".equals(command)){
            tree.counter = 0;
            tree.PostOrder(tree.Root);
            repaint();
        }else if("Create a Tree".equals(command)){
            tree = new Tree();
            int numNodes = getNumberOFNodes();
            for(int i=0; i<numNodes; i++){
                tree.AddToBST( mr.getRandomNumber());
            }
            tree.counter = 0;
            tree.InOrder(tree.Root);
            repaint();
        }else if("Start Demo".equals(command)){
            tree = new Tree();
            mr = new RandomNumberGenerator(10,100,500);
            int i=0;
            while(i<50){
                int number = mr.getRandomNumber();
                //is unique doent works
                if(tree.isUnique(number)){
                    i++;
                    tree.AddToBST(number);
                }
            }
            tree.counter = 0;
            tree.InOrder(tree.Root);
            repaint();
        }
    }

    

    public int getNumberOFNodes(){
        String string = JOptionPane.showInputDialog(null, "Enter the Number of Nodes:",
                    "Prompt Number Of Nodes", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(string);
    }

    public int getData(){
        String string = JOptionPane.showInputDialog(null, "Enter the Data:",
                    "Prompt Data", JOptionPane.QUESTION_MESSAGE);
        return Integer.parseInt(string);
    }

    public void paint(Graphics g){
        if("Add".equals(command)){
            System.out.println(tree.TraversalResult);
            displayBST(g);
            return;
        }else if("Delete".equals(command)){
            System.out.println(tree.TraversalResult);
            displayBST(g);
            return;
        }else if("Search for a Value".equals(command)){
            System.out.println(tree.TraversalResult);
            displayBST(g);
            return;
        }else if("Pre-Order".equals(command)){
            System.out.println(tree.TraversalResult);
            displayBST(g);
            return;
        }else if("In-Order".equals(command)){
            System.out.println(tree.TraversalResult);
            displayBST(g);
            return;
        }else if("Post-Order".equals(command)){
            System.out.println(tree.TraversalResult);
            displayBST(g);
            return;
        }else if("Create a Tree".equals(command)){
            System.out.println(tree.TraversalResult);
            displayBST(g);
            return;
        }else if("Start Demo".equals(command)){
            System.out.println(tree.TraversalResult);
            displayGUIBST(g);
            return;
        }
    }
    
    public void displayBST(Graphics g){
        g.drawString(tree.TraversalResult, 10, 75);
        Node T = tree.Root;
       
    }

    public void displayGUIBST(Graphics g){
        g.drawString(tree.TraversalResult, 10, 75);
        Node T = tree.Root;

        PreOrder(T, g);
    }

   public void PreOrder(Node T, Graphics g){
        if(T != null){
    
          System.out.println("data:"+T.data+" posRootX:"+T.posX+" ,posRootY:"+
                  T.posY+" parenX:"+T.parentX+" ,parentY:"+T.parentY);
          g.drawImage(node, T.posX, T.posY, this);
          g.drawString(""+T.data, T.posX+15, T.posY+15);
          if(T.arrowType==0){
              g.drawLine(T.parentX, T.parentY+12, T.posX+50, T.posY+12);
          }else{
              g.drawLine(T.parentX+50, T.parentY+12, T.posX, T.posY+12);
          }
          PreOrder (T.left, g);
          PreOrder (T.right, g);

       }
       
    }
}
