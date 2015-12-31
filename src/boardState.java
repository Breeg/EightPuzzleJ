public class boardState implements Comparable <Object>
{
	private int h;
	private int g;
	private int f;
	int[] boardState = new int[9];
	private boardState parent;
	
	public boardState(int[] bState, boardState parent, int h, int g)
	{
		this.boardState = bState;
		this.parent = parent;
		this.h = h;
		this.g = g;
		this.f = h + g;
	}
	
	public int getG()
	{
		return g;
	}

	public int getH()
	{
		return h;
	}
	
	public int getF()
	{
		return f;
	}

	public boardState getParent()
	{
		return parent;
	}

	public void setParent(boardState parent)
	{
		this.parent = parent;
	}

	public int[] getBoardState()
	{
		return boardState;
	}

	public void setBoardState(int[] boardState)
	{
		this.boardState = boardState;
	}

	public int compareTo(Object input)
	{
		int j = 0;
		boardState x = (boardState)(input);
		for(int i = 0; i < 9; i++){
			if(this.getBoardState()[i] != x.getBoardState()[i]){
				j = 1;
			}
		}
		return (j == 0)? 0 : -1;
	}
	
	public boolean equals(boardState input)
	{
		if(this.f != input.getF()){
			return false;
		}
		for(int i = 0; i < this.boardState.length; i++){
			if(this.boardState[i] != input.boardState[i]){
				return false;
			}
		}
		return true;
	}
	
	public String toString()
	{
		String returnStr = "";
		for(int i = 0; i < this.boardState.length; i++){
			returnStr += boardState[i] + " | ";
			if((i + 1) % 3 == 0){
				returnStr += "\n------------ \n";
			}
		}
		return returnStr;
	}
}
