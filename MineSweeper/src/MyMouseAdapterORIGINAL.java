
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapterORIGINAL extends MouseAdapter {
	private Random generator = new Random();
	private static final int TOTAL_ROWS = 11;
	private static final int TOTAL_COLUMNS = 10;
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						if ((gridX == 0) || (gridY == 0)) {
							//On the left column and on the top row
							
							if (gridX == 0 && gridY == 0) {
								//On the top-left grid
								for (int i = 1; i < TOTAL_ROWS - 1; i++){
									myPanel.colorArray[i][i] = generateColor();
								}
								myPanel.repaint();
								
							} else if (gridX == 0 && gridY == TOTAL_ROWS-1) {
									// On the bottom-left grid 
									for (int i = -1; i < 2; i++) {
										for (int j = -1; j < 2; j++) {
											myPanel.colorArray[((TOTAL_ROWS-1)/2) + i][((TOTAL_COLUMNS)/2)+ j] = generateColor();
										}
									}
									myPanel.repaint();
								
							} else if (gridX == 0 && gridY != 0 && gridY != TOTAL_ROWS - 1) {
								// On the left column, except on the top-left or bottom-left
								for (int i = 1; i < TOTAL_COLUMNS; i++) {				
									myPanel.colorArray[i][gridY] = generateColor();
								}
								myPanel.repaint();
						
							} else if (gridY == 0 && gridX != 0 && gridY != TOTAL_ROWS - 1) {
								// On the top row, except on the top-left 
								for (int i = 1; i < TOTAL_ROWS; i++) {
									myPanel.colorArray[gridX][i] = generateColor();
								}
								myPanel.repaint();
							}
							
						} else {
							//On the grid other than on the left column and on the top row:
							Color newColor = generateColor();
							
							myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
						}
					}
				}
			}	
			
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:     //Left mouse button
			//Do nothing
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	
	public Color generateColor () {
		
		Color newColor = null;
		switch (generator.nextInt(5)) {
		case 0:
			newColor = Color.YELLOW;
			break;
		case 1:
			newColor = Color.MAGENTA;
			break;
		case 2:
			newColor = Color.BLACK;
			break;
		case 3:
			newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
			break;
		case 4:
			newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
			break;
		}
		return newColor;
	}
}

















