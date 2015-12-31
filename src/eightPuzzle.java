import java.util.Scanner;
	
public class eightPuzzle
{
	//Takes input until a valid goal state is entered; if entered, calls solve to solve the path.
	public eightPuzzle()
	{
		//User input of a goalState
		Scanner input = new Scanner(System.in);
		boolean validGoalState = false;
		
		while(!validGoalState)
		{
			int[] goalState = new int[9];
			int invalidCounter = 0;
			System.out.print("Enter a goal state(numbers 0-8 separated by a space) : ");
			for(int i = 0; i < goalState.length; i++){
				goalState[i] = input.nextInt();
				if(goalState[i] > 8){
					invalidCounter++;
					break;
				} else {
					//Checking if no other values are zero and whether the goalState is totally valid
					for(int j = 0; j < i; j++){
						if(goalState[j] == goalState[i]){
							invalidCounter++;
							break;
						}
					}
				}
			}
			if(invalidCounter > 0){
				validGoalState = false;
				System.out.println("Please enter unique digits which are 8 or less. Start from scratch. \n");
			}
			else{
				//Valid goal state - now check if goal state is reachable
				if(checkReachable(goalState)){
					validGoalState = true;
					boardState finalBoardState = new boardState(goalState, null, 0, 0);
					solve(finalBoardState);
				}
				else{
					validGoalState = false;
					System.out.println("Goal State is not reachable. Try entering a different number from scratch. \n");
				}
				//checkReachable(goalState);
			}
		}
	}
	
	/**
	 * Checks if a certain goalState is reachable in the board or not by counting the number of inversions.
	 * @param goalState a goal state
	 * @return true/false
	*/
	
	public boolean checkReachable(int[] goalState)
	{
		int inversions = 0;
		for(int i = 0; i < goalState.length ; i++){
			for(int j = 0; j < i; j++){
				if(goalState[i] != 0 && goalState[j] != 0 && goalState[i] < goalState[j]){
					inversions++;
                }
			}
		}
		return (inversions % 2 == 0);
	}
	
	/**
	 *  Solves the boardState
	 *  @param goalState the goal state to solve for
	*/
	
	public void solve(boardState goalState)
	{
		int g = 0;
		int[] startState = {0, 1, 2, 3, 4, 5, 6, 7, 8};

		pObjectQueue openNodes = new pObjectQueue();
		LinkedList closedNodes = new LinkedList();

		//Wrting intial boardState to OpenNodes
		boardState startBoardState = new boardState(startState, null, Manhattan(startState, goalState.getBoardState()),g);
		openNodes.enqueue(startBoardState);
		
		boardState current;
		boardState parent;
		LinkedListNode currentChildren;
		LinkedListNode tempChildrenNode;
		LinkedListNode closedNode;
		LinkedList children;
		
		boolean found = false;
		while(!found)
		{
			current = (boardState) openNodes.dequeue();
			parent = current;
			if(current.compareTo(goalState) == 0){
				closedNodes.insertBack(current);
				found = true;
				printPath(closedNodes);
			}
			else{
				children = makeChildren(current.getBoardState());
				g++;
				currentChildren = children.getHead();
				//Insert current into closedNodes
				closedNodes.insertBack(current);
				tempChildrenNode = currentChildren;
				
				while(tempChildrenNode != null){
					closedNode = closedNodes.getHead();
					if(!find(closedNode,tempChildrenNode)){
						boardState newBoard = new boardState((int[])tempChildrenNode.getData(),parent,
								Manhattan((int[]) tempChildrenNode.getData(),goalState.getBoardState()),g);
						openNodes.priorityEnqueue(newBoard);
					}
					tempChildrenNode = tempChildrenNode.getNext();
				}
			}
		}
	}
		
	/**
	 * Returns the Manhattan distance between two boardStates.
	 * @param firstState initial board state
	 * @param secondState second board state
	 * @return manhattan the manhattan distance between the two board states
	*/
	
	public int Manhattan(int[] firstState, int[] secondState)
	{
		int manhattan = 0;
		int shift = 0;
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		for(int i = 0; i < firstState.length; i++){
			if(firstState[i] != 0 && firstState[i] != secondState[i]){
				for(int j = 0; j < secondState.length; j++){
					if(firstState[i] == secondState[j] && secondState[j] != 0){
						x1 = i / 3;
						y1 = i % 3;
						x2 = j / 3;
						y2 = j % 3;
						shift = (Math.abs(x1-x2) + Math.abs(y1-y2));
						manhattan += shift;
					}
                }
            }
        }
		return manhattan;
	}
	
	public void printPath(LinkedList closedNodes)
	{
		if(closedNodes.getHead().getNext() == null){
			System.out.print(closedNodes.getHead().getData());
		}
		else{
			System.out.println(closedNodes.getHead().getData());
			closedNodes.removeFront();
			printPath(closedNodes);
		}
	}
	
	public boolean find(LinkedListNode haystack, Object needle)
	{
		int j = 0;
		int x[] = new int[9];
		boardState parse;
		LinkedListNode parseNode = (LinkedListNode) needle;
		x = (int[]) parseNode.getData();
		LinkedListNode temp = haystack;
		
		while(temp != null){
			parse = (boardState) temp.getData();
			for(int i = 0; i < 9; i++){
				if(parse.getBoardState()[i] == x[i]){
					j=0;
				} else{
					j=1; 
					break;
				}
			}
			temp=temp.getNext();
		}
		return (j == 0);
	}

	public LinkedList makeChildren(int[] boardState)
	{
		int up[] = new int[9], down[] = new int [9], left[] = new int [9], right[] = new int[9];
		LinkedList children = new LinkedList();
		up = boardState.clone();
		down = boardState.clone();
		left = boardState.clone();
		right = boardState.clone();

		for(int i = 0; i < boardState.length; i++)
		{
			if(boardState[i] == 0){
				if(i/3 == 0){
					if(i%3 == 0){
						right[i]=boardState[i+1];
						right[i+1] = boardState[i];
						children.insertBack(right);
						down[i] = boardState[i+3];
						down[i+3] = boardState[i];
						children.insertBack(down);
						return children;
					}
					else if(i%3 == 1){
						right[i] = boardState[i+1];
						right[i+1] = boardState[i];

						down[i] = boardState[i+3];
						down[i+3] = boardState[i];

						left[i] = boardState[i-1];
						left[i-1] = boardState[i];
						children.insertBack(right);
						children.insertBack(down);
						children.insertBack(left);
						return children;
					}
					else if(i%3 == 2){
						left[i] = boardState[i-1];
						left[i-1] = boardState[i];
						down[i] = boardState[i+3];
						down[i+3] = boardState[i];

						children.insertBack(down);
						children.insertBack(left);
						return children;
					}
				}
				if(i/3 == 1){
					if(i%3 == 0){
						right[i] = boardState[i+1];
						right[i+1] = boardState[i];
						down[i] = boardState[i+3];
						down[i+3] = boardState[i];
						up[i] = boardState[i-3];
						up[i-3] = boardState[i];

						children.insertBack(right);
						children.insertBack(down);
						children.insertBack(up);
						return children;
					}
					else if(i%3 == 1){
						up[i] = boardState[i-3];
						up[i-3] = boardState[i];
						right[i] = boardState[i+1];
						right[i+1] = boardState[i];
						down[i] = boardState[i+3];
						down[i+3] = boardState[i];
						left[i] = boardState[i-1];
						left[i-1] = boardState[i];

						children.insertBack(right);
						children.insertBack(down);
						children.insertBack(left);
						children.insertBack(up);
						return children;
					}
					else if(i%3 == 2){
						left[i] = boardState[i-1];
						left[i-1] = boardState[i];
						down[i] = boardState[i+3];
						down[i+3] = boardState[i];
						up[i] = boardState[i-3];
						up[i-3] = boardState[i];

						children.insertBack(down);
						children.insertBack(left);
						children.insertBack(up);
						return children;

					}
				}
				if(i/3 == 2){
					if(i%3 == 0){
						right[i] = boardState[i+1];
						right[i+1] = boardState[i];
						up[i] = boardState[i-3];
						up[i-3] = boardState[i];

						children.insertBack(right);
						children.insertBack(up);
						return children;
					}
					else if(i%3 == 1){
						right[i] = boardState[i+1];
						right[i+1] = boardState[i];
						up[i] = boardState[i-3];
						up[i-3] = boardState[i];
						left[i] = boardState[i-1];
						left[i-1] = boardState[i];

						children.insertBack(right);
						children.insertBack(left);
						children.insertBack(up);
						return children;
					}
					else if(i%3 == 2){
						left[i] = boardState[i-1];
						left[i-1] = boardState[i];
						up[i] = boardState[i-3];
						up[i-3] = boardState[i];

						children.insertBack(left);
						children.insertBack(up);
						return children;
					}
				}
			}
		}
		return children;
	}
}