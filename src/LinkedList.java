public class LinkedList
{
	protected int size;
	protected LinkedListNode head;
	
	public LinkedList(LinkedListNode head)
	{
		this.head = head;
	}	
	
	public LinkedList() {
		this.head = null;
	}

	public LinkedListNode getHead()
	{
		return head;
	}

	public void setHead(LinkedListNode head)
	{
		this.head = head;
	} 

	public void insertFront(Object data)
	{
		if(head == null){
			head = new LinkedListNode(data, null);
		}
		else{
			LinkedListNode temp = new LinkedListNode(data,null);
			temp.setNext(head);
			head = temp;
		}
	}
	
	public void insertBack(Object data)
	{
		if(head == null){
			head = new LinkedListNode(data,null);
		}
		else{
			LinkedListNode temp = new LinkedListNode(data,null);
			LinkedListNode current = head;
			while(current.getNext() != null){
				current = current.getNext();
			}
			current.setNext(temp);
		}
	}
	
	public Object removeFront()
	{
		if(head == null){
			System.out.println("Cannot remove an empty list");
			return null;
		}
		else{
			Object temp = head.getData();
			head = head.getNext();
			return temp;
		}
	}
	
	public Object removeBack()
	{
		if(head == null){
			System.out.println("Cannot remove an empty list");
			return null;
		}
		else if(head.getNext() == null){
			Object temp = head.getData();
			head = null;
			return temp;
		}
		else{
			LinkedListNode current = head;
			while(current.getNext().getNext() != null){
				current = current.getNext();
			}
			Object temp = current.getNext().getData();
			current.setNext(null);
			return temp;
		}	
	}
}
