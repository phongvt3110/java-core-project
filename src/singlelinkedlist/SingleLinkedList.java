package singlelinkedlist;

public class SingleLinkedList {
	public static Node push(Node head, int data) {
		Node newNode = new Node(data);
		newNode.nextNode = head;
		head = newNode;
		return head;
	}
	public static Node deleteNode(Node head, int position) {
		if(head == null) return head;
		if(position == 1) {
			head = head.nextNode;
			return head;
		}
		Node prevNode = head;
		int i = 2;
		while(prevNode != null && i != position) {
			prevNode = prevNode.nextNode;
			i++;
		}
		if(prevNode == null || prevNode.nextNode == null) {
			return head;
		}
		prevNode.nextNode = prevNode.nextNode.nextNode;
		return head;
	}
	public static void printList(Node head) {
		Node curNode = head;
		while(curNode != null) {
			System.out.println(curNode.data + "  ");
			curNode = curNode.nextNode;
		}
	}
	
	public static void main(String[] args) {
		Node headNode = null;
		
		headNode = push(headNode, 5);
		headNode = push(headNode, 4);
		headNode = push(headNode, 3);
		headNode = push(headNode, 2);
		headNode = push(headNode, 1);
		
		System.out.println("Created Linked List is:");
		printList(headNode);
		headNode = deleteNode(headNode, 1);
		printList(headNode);
	}
}
