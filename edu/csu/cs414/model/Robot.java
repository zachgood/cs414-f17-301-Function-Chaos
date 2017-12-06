package edu.csu.cs414.model;


import edu.csu.cs414.model.CheckerState;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.csu.cs414.model.Board;
import edu.csu.cs414.model.Util;





public class Robot extends Thread{

	
	private static int DEPTH = 3; 
	private static final int MAX = 99999;
	private static final int MIN = -99999;
	private CheckerState state;
	private static ArrayList NextStates; 
	private JPanel chessBoard;
	public static boolean eaten = false;
	public Robot(){
		start(); 
	}
	
	private void alphaBetaSearch(CheckerState state){
		state.depth = 0;
		NextStates = null;

		int value = maxValue(state,MIN,MAX);

		if(NextStates==null){ 
			JOptionPane.showMessageDialog(null, "You win!");
			Board.win = true;
			return ;
		}

		CheckerState s = null;
		CheckerState nextState = null;
		for(Iterator it = NextStates.iterator();it.hasNext();){
			s = (CheckerState)it.next();
			if(s.value==value){
				nextState = s;
				break;
			}
		}

		if(nextState==null){
			JOptionPane.showMessageDialog(null, "You win!");
			Util.win = true;
			return ;
		}

		ArrayList steps = findPath(new CheckerState(nextState.Oriwhite,nextState.OriBlack),nextState);
		for(Iterator it = steps.iterator();it.hasNext();){
			CheckerState st = (CheckerState)it.next();
			Board.white = st.white;
			Board.black = st.black;
			chessBoard.repaint();
			util.play();
			checkOver(st); 
			try{
				Thread.sleep(300); 
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		Board.turn = true;
		//	System.out.println("####################################################");

	}
	private int maxValue(CheckerState state,int a,int b){

		if(state.depth==DEPTH) 
			return state.getValue();

		state.value = MIN;
		ArrayList list = state.nextStatesOfRed();

		if(state.depth==0)
			NextStates = list;

		CheckerState s = null;
		int minValue = MIN;
		for(Iterator it = list.iterator();it.hasNext();){
			s = (CheckerState)it.next();
			s.depth = state.depth + 1; 

			int temp = minValue(s,a,b);
			if(minValue < temp)
				minValue = temp;

			state.value = state.value>minValue ? state.value : minValue;

			if(state.value>=b)
				return state.value;

			a = a>state.value ? a : state.value;
		}
		return state.value;
	}
	
	private int minValue(CheckerState state,int a,int b){
		return state.value;
		
	}
	
}
