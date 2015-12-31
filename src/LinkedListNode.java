public class LinkedListNode
{
	private Object data;
	private LinkedListNode next;
	
	public LinkedListNode(Object data, LinkedListNode next)
	{
		super();
		this.data = data;
		this.next = next;
	}
	
	public Object getData()
	{
		return data;
	}
	
	public void setData(Object data)
	{
		this.data = data;
	}

	public LinkedListNode getNext()
	{
		return next;
	}
	
	public void setNext(LinkedListNode next)
	{
		this.next = next;
	}
}
