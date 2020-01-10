
package esinf_3rd_project;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bernardo Carvalho
 * @author Tiago Ribeiro
 */
public class Utils {
    public static <E extends Comparable<E>> Iterable<E> sortByBST(List<E> listUnsorted){
        BST<E> bst = new BST<>();
        for (E el : listUnsorted) {
            bst.insert(el);
        }
        List<E> lista_sorted = new ArrayList<>();
        for (E el : bst.inOrder()) {
            lista_sorted.add(el);
        }
        return lista_sorted;
    }    
}
