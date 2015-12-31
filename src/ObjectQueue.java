public class ObjectQueue extends LinkedList implements interfaceQueue
{
	public int size()
	{
		return size;
	}

	public boolean isEmpty()
	{
		return (getHead() == null);
	}

	public Object front()
	{
		return (isEmpty())? null : getHead().getData();
	}

	public Object dequeue()
	{
		size--;
		return removeFront();
	}

	public void enqueue(Object toenqueue) {
		insertBack(toenqueue);
		size++;
	}
}
