package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		while(!isEmpty()) {
			remove(getRoot().getData());
		}
		
		for (int i = 0; i < array.length; i++) {
			this.insert(array[i], this.getRoot());
		}
		return this.order();
	}
		
	@SuppressWarnings("unchecked")
	private void insert(T element, BSTNode<T> node) {
		if (element != null) {

			// Disponivel
			if (node.isEmpty()) {
				node.setData(element);
				node.setLeft(new BSTNode.Builder<T>().parent(node).build());
				node.setRight(new BSTNode.Builder<T>().parent(node).build());
				
				if (node.getParent() == null) {
					node.setParent(new BSTNode<>());
					System.out.println("HEY");
				}
				//System.out.println(element + " alocado\n");

				// Nao Disponivel
			} else {
				// Elemento menor que Atual node
				if (this.comparator.compare(node.getParent().getData(), element) > 0) {
					//System.out.println(element + " esquerda");
					insert(element, (BSTNode<T>) node.getLeft());

					// Elemento maior que Atual node
				} else if (this.comparator.compare(node.getParent().getData(), element) > 0) {
					//System.out.println(element + " direita");
					insert(element, (BSTNode<T>) node.getRight());
				}
			}
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> aux = root;
		return search(element, aux);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> result = new BSTNode<>();
	
		if (element != null && !node.isEmpty()) {
			
			if (node.getData().equals(element)) {
				result = node;
			// this.comparator.compare(node.getData(), element
			} else if (node.getData().compareTo(element) < 0) {
				// System.out.println(node.getData() + " Menor que " + element);
				result = search(element, (BSTNode<T>) node.getRight());
	
			} else {
				// System.out.println(node.getData() + " Maior que " + element);
				result = search(element, (BSTNode<T>) node.getLeft());
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		if (this.isEmpty()) return array;
		reverseOrder(array, root, 0);
		return array;
	}

	private int reverseOrder(T[] array, BSTNode<T> node, int index) {
		if (!node.getRight().isEmpty() && node.getRight() instanceof BSTNode) {
			index = reverseOrder(array, (BSTNode<T>) node.getRight(), index);
		}
		
		array[index++] = node.getData();
		
		if (!node.getLeft().isEmpty() && node.getLeft() instanceof BSTNode) {
			index = reverseOrder(array, (BSTNode<T>) node.getLeft(), index);
		}
		
		return index;
	}
	
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
