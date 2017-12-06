package edu.csu.cs414.model;

public class ChadMove {
     
   int fromRow, fromCol;  // Position of piece to be moved.
   int toRow, toCol;      // Square it is to move to.
   ChadMove(int r1, int c1, int r2, int c2) {
        // Constructor.  Just set the values of the instance variables.
      fromRow = r1;
      fromCol = c1;
      toRow = r2;
      toCol = c2;
   }
   boolean isJump() {
       
      return (fromRow - toRow == 2 || fromRow - toRow == -2);
   }
}  // end class CheckersMove.
