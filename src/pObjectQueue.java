public class pObjectQueue extends ObjectQueue
{	
	public void priorityEnqueue(Comparable<Object> item)
	{
		boardState toenqueue = (boardState)item;
		int f = toenqueue.getF();
		boardState head_d = null;
	
		if(getHead() != null){
			head_d = (boardState)getHead().getData();
		}
		
		if(getHead() == null){
			enqueue(item);
		}
		else if(getHead().getNext() == null){
			boardState head =(boardState)getHead().getData();
			if(f < head.getF()){
				insertFront(item);
			}
			else{
				insertBack(item);
			}
		}
		//If Head not empty, but needs replacing
		else if((head_d.getF()) > f){
			insertFront(item);
		}
		else{
			LinkedListNode current = getHead();
			LinkedListNode insert = new LinkedListNode(item,null);
			boardState node;
			
			while(current.getNext() != null){
				node = (boardState) current.getNext().getData();
				if(f <= node.getF()){
					LinkedListNode temp = current.getNext(); 
					current.setNext(insert);
					insert.setNext(temp);
				}
				current = current.getNext();
			}
			insertBack(item);
		}
	}
}