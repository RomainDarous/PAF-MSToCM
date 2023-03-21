package maze;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dijkstra.Dijkstra;
import dijkstra.GraphInterface;
import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import ui.Const;

/**
 * Class of the maze. It allows to initialize a maze from a text File and to save it into another after having solved it.
 * @author Romain Darous
 *
 */
public class Maze implements GraphInterface{
	
	private VertexInterface[][] boxes; //Matrix of vertices (that will be boxes). This is the maze.
	private VertexInterface depart; // Departure vertex of the maze.
	private VertexInterface arrivee; // Arrival vertex of the maze.
	private int mazeHeight; // line number of the maze.
	private int mazeWidth; // column number of the maze.
	private int verNumber; //vertices number. Walls does not count.
	
	/**
	 * Constructor of the Maze class.
	 * It only creates an instance of Maze.
	 */
	public Maze() {
	}
	
	/**
	 * Gets all the boxes of the maze.
	 * @return the boxes of the maze (VertexInterface[][] object).
	 */
	public final VertexInterface[][] getBoxes() {
		return boxes;
	}
	
	/**
	 * Gives the departure vertex of the maze.
	 * @return the departure vertex of the maze.
	 */
	public final VertexInterface getDepart() {
		return depart;
	}
	
	/**
	 * Gives the arrival vertex of the maze.
	 * @return the arrival vertex of the maze.
	 */
	public final VertexInterface getArrivee() {
		return arrivee;
	}
	
	/**
	 * Sets the departure box of the maze.
	 * @param depart the new departure box.
	 */
	public final void setDepart(VertexInterface depart) {
		this.depart = depart;
	}
	
	/**
	 * Sets the arrival box of the maze.
	 * @param arrivee the new departure box.
	 */
	public final void setArrivee(VertexInterface arrivee) {
		this.arrivee = arrivee;
	}
	
	/**
	 * Sets the box table of the maze.
	 * @param boxes the new boxes of the maze.
	 */
	public final void setBoxes (VertexInterface[][] boxes) {
		this.boxes = boxes;
	}
	
	/**
	 * Modifies a box of the maze at given coordinates.
	 * @param m the line of the box to modify.
	 * @param n the column of the box to modify.
	 * @param box the new box to put at these coordinates.
	 */
	public final void setBox(int m, int n, VertexInterface box) {
		boxes[m][n] = box;
	}
	
	/**
	 * Gets a box of the maze at the given coordinates.
	 * @param n the line of the box to get.
	 * @param m the column of the box to get.
	 * @return the box of the maze at the given coordinates.
	 */
	public final VertexInterface getBox(int n, int m) {
		return boxes[n][m];
	}
	
	/**
	 * Gets the maze height.
	 * @return the maze height.
	 */
	public final int getMazeHeight() {
		return mazeHeight;
	}
	
	/**
	 * Gets the maze width.
	 * @return the maze width.
	 */
	public final int getMazeWidth() {
		return mazeWidth;
	}
	
	/**
	 * Gets all the vertices of the maze.
	 * @return all the vertices in an ArrayList object.
	 */
	public final ArrayList<VertexInterface> getAllVertices() {
		// WallBoxes aren't considered as vertices.
		ArrayList<VertexInterface> vertices = new ArrayList<VertexInterface>();
		 
		for (VertexInterface[] maze_row : boxes) {
			for (VertexInterface box : maze_row) {
				if(!box.getLabel().equals("W")) {
					vertices.add(box);
				}
			}
		}
		return vertices;
	}
	
	@Override
	public final ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
		
		ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>(4); //Successor list returned by the method.
		
		MBox box = (MBox) vertex ;
		int[] coords = box.getCoords();
		
		// A wall has no successors.
		if (box.getLabel().equals("W")){
			return successors;
			
		} else {
			int[][] indexVoisins = {{0,1} , {1,0} , {0,-1} , {-1,0}};
			for (int[] index : indexVoisins) {
				
				if ( (coords[0] + index[0]) < mazeHeight && (coords[0] + index[0]) >= 0 && (coords[1] + index[1]) < mazeWidth && (coords[1] + index[1]) >= 0) {
					if(!boxes[ coords[0] + index[0] ][ coords[1] + index[1] ].getLabel().equals("W")) {
						successors.add(boxes[ coords[0] + index[0] ][ coords[1] + index[1] ]);
					}
				}
			}
			return successors;
		}
	}
	
	@Override
	public final int getWeight(VertexInterface src , VertexInterface dst) {
		
		ArrayList<VertexInterface> successorsSrc = this.getSuccessors(src);
		
		// Si la box dst est dans les successeurs de src, le poids vaut 1.
		if (successorsSrc.contains(dst)) {
			return 1;
		} else {
			//Sinon, il vaut 0;
			return 0;
		}

	}
	
	@Override
	public final int getVerticeNumber() {
		return verNumber;
	}

	/**
	 * Initializes the maze from a text file.
	 * @param fileName the path of the text file.
	 * @return an error message if there is one, or an OK message if there isn't.
	 */
	public final String initFromTextFile(String fileName) {
		String line;
		
	    try {
	    	FileReader fr = new FileReader(fileName);
	    	BufferedReader br = new BufferedReader(fr);
	    	
	    	//Variables counting the number of departure and arrival boxes.
	    	int DBoxNb = 0;
	    	int ABoxNb = 0;
	    	
	    	//Reading the text file.
	    	ArrayList<String> file  = new ArrayList<String>();
	    	mazeHeight = 0;
	    	
	    	while((line = br.readLine()) != null) {
	    		file.add(line);
	    		mazeHeight += 1;
	    	}
	    	
	    	if (mazeHeight == 0) throw new MazeReadingException(fileName, Const.VOID_FILE);
	    	
	    	//Checking the incorrect case number per lign exceptions.
	    	mazeWidth = file.get(0).length();
	    	for (int i = 1 ; i < mazeHeight ; i++) {
	    		if (file.get(i).length() != mazeWidth) {
	    			throw new MazeReadingException(fileName, i, "Nombre de cases incorrect à la ligne " + String.valueOf(i+1) + " du fichier choisi : \n");
	    		}
	    	}
	    	
	    	//Checking if the maze is too big to be displayed
	    	if (mazeHeight > Const.MAX_HEIGHT_3 || mazeWidth > Const.MAX_WIDTH_3) {
				throw new MazeReadingException(fileName, mazeHeight, mazeWidth, "Ton labyrinthe a " + String.valueOf(mazeHeight) + " lignes et " + String.valueOf(mazeWidth) + " colonnes. C'est trop pour être affiché.\nNombre de lignes max : " + String.valueOf(Const.MAX_HEIGHT_3) + ". Nombre de colonnes max : " + String.valueOf(Const.MAX_WIDTH_3) + ". Fichier choisi : \n" );
	    	}
	    	
	    	//Creating the box table and adding the appropriate boxes.
	    	boxes = new VertexInterface[mazeHeight][mazeWidth];
			
	    	for (int i = 0 ; i < mazeHeight ; i++) {
	    		line = file.get(i);
	    		
	    		for(int j = 0 ; j < mazeWidth ; j++) {
	    			char label = line.charAt(j);
	    			
	    			if(label == 'W') {
	    				boxes[i][j] = new WBox(i,j);
	    				
	    			} else if(label == 'E') {
	    				boxes[i][j] = new EBox(i,j);
	    				
	    			} else if(label == 'D') {
	    				//Checking departure case errors.
	    				DBoxNb++;
	    				if (DBoxNb > 1) throw new MazeReadingException(fileName, i, j, "Deuxième case départ en position (" + String.valueOf(i+1) + ", " + String.valueOf(j+1) + ") dans le fichier choisi : \n");
	    				
	    				//If not, adding the departure box.
	    				boxes[i][j] = new DBox(i,j);
	    				this.depart = boxes[i][j];
	    				
	    			} else if(label == 'A') {
	    				//Checking arrival case errors.
	    				ABoxNb++;
	    				if (ABoxNb > 1) throw new MazeReadingException(fileName, i, j, "Deuxième case d'arrivée en position (" + String.valueOf(i+1) + ", " + String.valueOf(j+1) + ") dans le fichier choisi : \n");
	    				
	    				//If not, adding the arrival box.
	    				boxes[i][j] = new ABox(i,j);
	    				this.arrivee = boxes[i][j];
	    				
	    			} else {
	    				throw new MazeReadingException(fileName, i, j, "Caractère non valide à l'indice (" + String.valueOf(i+1) + ", " + String.valueOf(j+1) + ") dans le fichier choisi : \n");
	    			}
	    		}
	    	}
	    	br.close();
	    	
	    	//Checking if there is a departure and an arrival case.
	    	if (DBoxNb == 0) throw new MazeReadingException(fileName, Const.NO_DEPARTURE_CASE);
	    	else if (ABoxNb == 0) throw new MazeReadingException(fileName, Const.NO_ARRIVAL_CASE);
	    		    	
	    	this.verNumber = this.getAllVertices().size();
	    	
	    	//If everything went good : 
	    	return Const.VOID_LABEL;
	    	
	    } catch (MazeReadingException e) {
	    	return e.getErrorMessage();
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    	return Const.ERROR;
	    } catch (IOException e) {
	    	e.printStackTrace();
	    	return Const.ERROR;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return Const.ERROR;
	    }
	}
	
	/**
	 * Saves the maze in its current state to the given path.
	 * @param fileName the path where the maze must be saved.
	 */
	public final void saveToTextFile(String fileName) {
		try {
			File file = new File(fileName);
			FileWriter fileWriter = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			PrintWriter writer = new PrintWriter(fileName);
			
			//Writing the current state of the maze in the file.
			String line = new String();
			for (VertexInterface[] boxA : boxes) {
				for (VertexInterface box : boxA) {
					line += box.getLabel();
				}
				
				writer.println(line);
				line = "";
			}
			bw.flush();
			writer.close();
			bw.close();
		} catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * Gets the shortest way between the departure box and the arrival box, by replacing the label of the boxes of the shortest way by 'O'.
	 * @return a boolean : true if the maze can be solved, false if it can't.
	 */
	public final boolean solveMaze() {
		
		PreviousInterface previous = Dijkstra.dijkstra(this, this.getDepart());
		
		// Display of the result.
		VertexInterface arrivee = this.getArrivee();
		VertexInterface pivot = previous.getPrevious(arrivee);
		
		while(pivot != this.getDepart() && pivot != null) {
			pivot.setLabel("O");
			pivot = previous.getPrevious(pivot);
		}
		
		if (pivot == this.getDepart()) return true;
		else return false;		
	}
}
