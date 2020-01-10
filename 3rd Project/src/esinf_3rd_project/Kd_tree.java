/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf_3rd_project;

/**
 *
 * @author Bernardo Carvalho
 * @author Tiago Ribeiro
 */
public class Kd_tree extends BST<Pais>{
    
    // Retorna o nível de um dado país na árvore
    protected int getLevel(Pais pais)  
    { 
        return getLevel(this.root(), pais, 1); 
    }
    
    // Retorna o nível de um país na árvore se esse páis existir na árvore, caso contrário retorna 0
    protected int getLevel(Node node, Pais pais, int level)  
    { 
        if (node == null) 
            return 0; 
   
        if (node.getElement() == pais)
            return level; 
   
        int downlevel = getLevel(node.getLeft(), pais, level + 1); 
        if (downlevel != 0) 
            return downlevel; 
   
        downlevel = getLevel(node.getRight(), pais, level + 1); 
        return downlevel; 
    }
    
    protected Node<Pais> parentNode(Node<Pais> pais){
        return parentNode(root, pais);
    }
    
    protected Node<Pais> parentNode(Node<Pais> currentNode, Node<Pais> pais) {        
        if (pais == this.root() || currentNode == null){
                return null;
        }
        else{
            if(currentNode.getLeft() == pais || currentNode.getRight() == pais)
                return currentNode;
            else {
                if((this.getLevel(currentNode.getElement()) % 2) != 0){
                    if (pais.getElement().getLatitude() < currentNode.getElement().getLatitude()) {
                        return parentNode(currentNode.getLeft(), pais);
                    }
                    else {
                        return parentNode(currentNode.getRight(), pais);
                    }
                }
                else{
                    if (pais.getElement().getLongitude() < currentNode.getElement().getLongitude()) {
                        return parentNode(currentNode.getLeft(), pais);
                    }
                    else {
                        return parentNode(currentNode.getRight(), pais);
                    }
                }
            }
        }
    }
    
    public void insert(Pais element){
        root = insert(element, root);
    }
    private Node<Pais> insert(Pais element, Node<Pais> node){
        if (node == null) {
            return new Node(element, null, null);
        }
        
        if((this.getLevel(node.getElement()) % 2) != 0){
            if (element.getLatitude() < node.getElement().getLatitude()) {
                node.setLeft(insert(element, node.getLeft()));
            } else {
                node.setRight(insert(element, node.getRight()));
            }
        }
        else{
            if (element.getLongitude() < node.getElement().getLongitude()) {
                node.setLeft(insert(element, node.getLeft()));
            } else {
                node.setRight(insert(element, node.getRight()));
            }
        }
        return node;
    }
    
    protected Node<Pais> find(Pais element, Node<Pais> node){
            if(node == null){
                return null;
            }
            if(node.getElement().getLatitude() == element.getLatitude() && node.getElement().getLongitude() == element.getLongitude()){
                return node;
            }
            if((this.getLevel(node.getElement()) % 2) != 0){
            if (element.getLatitude() < node.getElement().getLatitude()) {
                    return find(element, node.getLeft());
                } else {
                    return find(element, node.getRight());
                }
            }
            else{
                if (element.getLongitude() < node.getElement().getLongitude()) {
                    return find(element, node.getLeft());
                } else {
                    return find(element, node.getRight());
                }
            }
    }
    
    public boolean equals(Object otherObj) {

        if (this == otherObj) 
            return true;

        if (otherObj == null || this.getClass() != otherObj.getClass())
            return false;

        Kd_tree second = (Kd_tree) otherObj;
        return equals(root, second.root);
    }

    public boolean equals(Node<Pais> root1, Node<Pais> root2) {
        if (root1 == null && root2 == null) 
           return true;
        else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else  
                return false; 
        }
        else return false;
    }
    
}
