package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

/**
 * Class that gathers all the constants used in the project.
 * @author Romain Darous
 *
 */
public abstract class Const {
	
	private Const() {
		
	}

	/*
	 * *****************
	 * FRAME PARAMETERS.
	 * *****************
	 */
	
	/**
	 * Background color of the application.
	 */
	public final static Color BACKGROUND_COLOR = new Color(229,229,229);
	
	/**
	 * Width of the application.
	 */
	public final static int WIDTH = 1440;
	
	/**
	 * Height of the application.
	 */
	public final static int HEIGHT = 810;
	
	/**
	 * Dimensions of the application.
	 */
	public final static Dimension VIEW_DIMENSIONS = new Dimension(WIDTH,HEIGHT);
	
	/*
	 * ********************
	 * EDITCARD PARAMETERS.
	 * ********************
	 */
	
	/**
	 * Background color of the edit button menu.
	 */
	public final static Color EDIT_SOLVED_MENU_COLOR = new Color(181,181,181);
	
	/*
	 * ***********
	 * MAZE MODES.
	 * ***********
	 */
	
	/**
	 * No specified maze mode.
	 */
	public final static int NONE = 0;
	
	/**
	 * Maze mode : Creating a maze.
	 */
	public final static int CREATING = 1;
	
	/**
	 * Maze mode : Loading a maze.
	 */
	public final static int LOADING = 2;
	
	/**
	 * Maze mode : Editing a maze.
	 */
	public final static int EDITING = 3;
	
	/**
	 * Maze mode : Solving a maze.
	 */
	public final static int SOLVING = 4;
	
	/**
	 * Maze mode : Modifying a maze.
	 */
	public final static int MODIFYING = 5;
	
	/**
	 * Maze mode : Saving a maze.
	 */
	public final static int SAVING = 6;
	
	/**
	 * Maze mode : Drawing a maze.
	 */
	public final static int DRAWING = 7;
	
	/**
	 * Maze mode : Maze error.
	 */
	public final static int INIT_ERROR = 8;
	
	/*
	 * *****************************
	 * UPDATED/MAINTAINED VARIABLES.
	 * *****************************
	 */
	
	/**
	 * The maze mode or the page haven't changed.
	 */
	public final static int MAINTAINED = 0;
	
	/**
	 * The maze mode or the page have changed.
	 */
	public final static int UPDATED = 1;
	
	/*
	 * ***********
	 * MAZE PAGES.
	 * ***********
	 */
	
	/**
	 * Leaving page, which is basically nothing.
	 */
	public final static int LEAVE_PAGE = 0;
	
	/**
	 * The home page of the application.
	 */
	public final static int HOME_PAGE = 1;
	
	/**
	 * The edit page of the application.
	 */
	public final static int EDIT_PAGE = 2;
	
	/**
	 * The solved page of the application.
	 */
	public final static int SOLVE_PAGE = 3;
	
	/**
	 * The creating maze page.
	 */
	public final static int ENABLE_CREATE_PAGE = 4;
	
	/**
	 * The disabling creating maze page.
	 */
	public final static int DISABLE_CREATE_PAGE = 5;
	
	/*
	 * **************
	 * BUTTON LABELS.
	 * **************
	 */
	
	/**
	 * "Q U I T T E R"
	 */
	public final static String LEAVE_LABEL = "Q U I T T E R";
	
	/**
	 * "C H A R G E R"
	 */
	public final static String LOAD_LABEL = "C H A R G E R";
	
	/**
	 * "N O U V E A U"
	 */
	public final static String NEW_LABEL = "N O U V E A U";
	
	/**
	 * "R E S O U D R E"
	 */
	public final static String SOLVE_LABEL = "R E S O U D R E";
	
	/**
	 * "ANNULER"
	 */
	public static final String CANCEL_LABEL = "ANNULER";
	
	/**
	 * "OK"
	 */
	public final static String OK_LABEL = "OK";
	
	/**
	 * "SAUVEGARDER"
	 */
	public final static String SAVE_LABEL = "SAUVEGARDER";
	
	/**
	 * "M O D I F I E R"
	 */
	public final static String MODIFY_LABEL = "M O D I F I E R";
	
	/**
	 * "Nombre de lignes : "
	 */
	public final static String HEIGHT_LABEL = "Nombre de lignes : ";
	
	/**
	 * "Nombre de colonnes : "
	 */
	public final static String WIDTH_LABEL = "Nombre de colonnes : ";
	
	
	/*
	 * ****************************************
	 * BACKGROUND/FOREGROUND COLORS of BUTTONS.
	 * ****************************************
	 */
	
	/**
	 * Foreground color of buttons.
	 */
	public static final Color BUTTON_FOREGROUND = Color.WHITE;
	
	//Background colors of the leave/cancel buttons.
	
	/**
	 * Default background color of the leave/cancel buttons.
	 */
	public final static Color LEAVE_CANCEL_BUTTON_DEFAULT_COLOR = new Color(136, 22, 0);
	
	/**
	 * Over background color of the leave/cancel buttons.
	 */
	public final static Color LEAVE_CANCEL_BUTTON_OVER_COLOR = new Color(72, 12, 0);
	
	/**
	 * Pressed background color of the leave/cancel buttons.
	 */
	public final static Color LEAVE_CANCEL_BUTTON_PRESSED_COLOR = new Color(30, 5, 0);

	//Background colors of the load button.
	
	/**
	 * Default background color of the load buttons.
	 */
	public final static Color LOAD_BUTTON_DEFAULT_COLOR = new Color(54, 130, 127);
	
	/**
	 * Over background color of the load buttons.
	 */
	public final static Color LOAD_BUTTON_OVER_COLOR = new Color(252, 163, 17);
	
	/**
	 * Pressed background color of the load buttons.
	 */
	public final static Color LOAD_BUTTON_PRESSED_COLOR = new Color(224, 152, 40);
	
	//Background colors of the new button.
	
	/**
	 * Default background color of the new buttons.
	 */
	public final static Color NEW_BUTTON_DEFAULT_COLOR = new Color(20, 33, 61);
	
	/**
	 * Over background color of the new buttons.
	 */
	public final static Color NEW_BUTTON_OVER_COLOR = LOAD_BUTTON_OVER_COLOR;
	
	/**
	 * Pressed background color of the new buttons.
	 */
	public final static Color NEW_BUTTON_PRESSED_COLOR = LOAD_BUTTON_PRESSED_COLOR;
	
	//Background colors of the solve button.
	
	/**
	 * Default background color of the solve buttons.
	 */
	public final static Color SOLVE_BUTTON_DEFAULT_COLOR = new Color(157, 105, 90);
	
	/**
	 * Over background color of the solve buttons.
	 */
	public final static Color SOLVE_BUTTON_OVER_COLOR = LOAD_BUTTON_OVER_COLOR;
	
	/**
	 * Pressed background color of the solve buttons.
	 */
	public final static Color SOLVE_BUTTON_PRESSED_COLOR = LOAD_BUTTON_PRESSED_COLOR;
	
	//Background colors of the ok/save button.
	
	/**
	 * Default background color of the ok/save buttons.
	 */
	public final static Color OK_SAVE_BUTTON_DEFAULT_COLOR = new Color(56, 128, 83);
	
	/**
	 * Over background color of the ok/save buttons.
	 */
	public final static Color OK_SAVE_BUTTON_OVER_COLOR = LOAD_BUTTON_OVER_COLOR;
	
	/**
	 * Pressed background color of the ok/save buttons.
	 */
	public final static Color OK_SAVE_BUTTON_PRESSED_COLOR = LOAD_BUTTON_PRESSED_COLOR;
	
	//Background colors of the modify button.
	
	/**
	 * Default background color of the modify button.
	 */
	public final static Color MODIFY_BUTTON_DEFAULT_COLOR = SOLVE_BUTTON_DEFAULT_COLOR;
	
	/**
	 * Over background color of the modify button.
	 */
	public final static Color MODIFY_BUTTON_OVER_COLOR = LOAD_BUTTON_OVER_COLOR;
	
	/**
	 * Pressed background color of the modify button.
	 */
	public final static Color MODIFY_BUTTON_PRESSED_COLOR = LOAD_BUTTON_PRESSED_COLOR;

	/*
	 * ******************
	 * BUTTON DIMENSIONS.
	 * ******************
	 */
	
	/**
	 * Height of all buttons of the edit button menu.
	 */
	public final static int EDIT_HEIGHT = 110;
	
	/**
	 * Home page button dimensions.
	 */
	public static final Dimension HOME_BUTTON_DIMENSIONS = new Dimension (400,110);
	
	/**
	 * Ok/Cancel dimensions of the home page.
	 */
	public static final Dimension OK_CANCEL_HOME_BUTTON_DIMENSIONS = new Dimension (165 , 45);
	
	/**
	 * Dimensions of the New, Load and Leave buttons of the solved page.
	 */
	public static final Dimension SOLVED_BUTTON_DIMENSIONS = new Dimension (200,45);
	
	/**
	 * Dimensions of the cancel/edit buttons of the edit page.
	 */
	public static final Dimension CANCEL_EDIT_BUTTON_DIMENSIONS = new Dimension (220, EDIT_HEIGHT);
	
	/**
	 * Dimensions of the save button of the edit page.
	 */
	public static final Dimension SAVE_EDIT_BUTTON_DIMENSIONS = new Dimension (350, EDIT_HEIGHT);
	
	/**
	 * Dimensions of the solve button.
	 */
	public static final Dimension SOLVE_BUTTON_DIMENSIONS = new Dimension(600, EDIT_HEIGHT);
	
	/**
	 * Dimensions of the modify button.
	 */
	public static final Dimension MODIFY_BUTTON_DIMENSIONS = new Dimension(315, EDIT_HEIGHT);
	
	/*
	 * *************
	 * BUTTON FONTS.
	 * *************
	 */
	
	/**
	 * Home button font.
	 */
	public static final Font HOME_BUTTON_FONT = new Font("Arial Black", Font.BOLD, 40);
	
	/**
	 * Dimension panel font.
	 */
	public static final Font HOME_DIMENSION_FONT = new Font("Arial Black", Font.BOLD, 24 );

	/**
	 * Font of the New, Load and Leave buttons of the solved page.
	 */
	public static final Font SOLVED_MENU_BUTTON_FONT = new Font("Arial Black", Font.BOLD, 20);
	
	/**
	 * Solve button font.
	 */
	public static final Font SOLVE_BUTTON_FONT = new Font("Arial Black", Font.BOLD, 60);
	
	/**
	 * Modify button font.
	 */
	public static final Font MODIFY_BUTTON_FONT = new Font("Arial Black", Font.BOLD, 33);
	
	/**
	 * Ok/Cancel button font of the home page.
	 */
	public static final Font OK_CANCEL_HOME_BUTTON_FONT = new Font("Arial Black", Font.BOLD, 20);
	
	/**
	 * Cancel button font of the edit page.
	 */
	public static final Font CANCEL_EDIT_BUTTON_FONT = new Font("Arial Black", Font.BOLD, 30);
	
	/**
	 * Save button of the edit page.
	 */
	public static final Font SAVE_EDIT_BUTTON_FONT = new Font("Arial Black", Font.BOLD, 35);

	/*
	 * **************************
	 * DIMENSION MENU PARAMETERS.
	 * **************************
	 */
	
	/**
	 * Dimension text field background color.
	 */
	public static final Color DIMENSION_TEXT_FIELD_BACKGROUND_COLOR = Color.WHITE;
	
	/**
	 * Dimensions of the dimension text fields.
	 */
	public static final Dimension DIMENSION_TEXT_FIELD_DIMENSIONS = new Dimension(75, 45);
	
	/*
	 * *************
	 * MENU LAYOUTS.
	 * *************
	 */
	
	/**
	 * Home button menu Layout.
	 */
	public final static FlowLayout HOME_BUTTON_MENU_DEFAULT_LAYOUT = new FlowLayout(FlowLayout.CENTER, 75, 100);
	
	/**
	 * Home create layout.
	 */
	public final static FlowLayout HOME_BUTTON_MENU_CREATE_LAYOUT = new FlowLayout(FlowLayout.CENTER, 25, 100);
	
	/**
	 * Dimension panel layout.
	 */
	public final static FlowLayout DIMENSION_PANEL_LAYOUT = new FlowLayout(FlowLayout.RIGHT);
	
	/**
	 * Edit button menu layout.
	 */
	public final static FlowLayout EDIT_BUTTON_MENU_LAYOUT = new FlowLayout(FlowLayout.LEFT, 60, 20); 
	
	/**
	 * Button menu layout of the solved page.
	 */
	public final static FlowLayout SOLVED_BUTTON_MENU_LAYOUT = new FlowLayout(FlowLayout.LEFT, 20, 20); 
	
	/**
	 * Box panel of the edit page layout.
	 */
	public final static FlowLayout BOX_PANEL_LAYOUT = new FlowLayout(FlowLayout.LEFT, 15, 15);
	
	/*
	 * ************
	 * CARD LABELS.
	 * ************
	 */
	
	/**
	 * "edit"
	 */
	public final static String EDIT_LABEL = "edit";
	
	/**
	 * "home"
	 */
	public final static String HOME_LABEL = "home";
	
	/*
	 * **********************
	 * HOME TITLE PARAMETERS.
	 * **********************
	 */
	
	//Motto parameters
	
	/**
	 * Font of the Motto component.
	 */
	public final static Font MOTTO_FONT = new Font("Arial Black", Font.BOLD, 35);
	
	/**
	 * Color of the Motto component.
	 */
	public final static Color MOTTO_TEXT_COLOR = Color.BLACK; 
	
	/**
	 * "Trouve en un instant le plus court chemin qui r�sout ton labyrinthe !"
	 */
	public final static String MOTTO_LABEL = "Trouve en un instant le plus court chemin qui r�sout ton labyrinthe !";
	
	//Name parameters
	
	/**
	 * Font of the Name component.
	 */
	public final static Font NAME_FONT = new Font("Arial Black", Font.BOLD, 80);
	
	/**
	 * Color of the Name component.
	 */
	public final static Color NAME_TEXT_COLOR = NEW_BUTTON_PRESSED_COLOR;
	
	/**
	 * "Romain Darous"
	 */
	public final static String NAME_LABEL = "Romain Darous";

	//Title parameters
	
	/**
	 * Font of the Title component.
	 */
	public final static Font TITLE_FONT = new Font("Arial Black", Font.BOLD, 100);
	
	/**
	 * Color of the Title component.
	 */
	public final static Color TITLE_TEXT_COLOR = new Color(20, 33, 61);
	
	/**
	 * "T E L E' B Y R I N T H E"
	 */
	public final static String TITLE_LABEL = "T E L E' B Y R I N T H E";

	/*
	 * ***********
	 * BOX COLORS.
	 * ***********
	 */
	
	/**
	 * Color of an empty box.
	 */
	public final static Color EMPTY_COLOR = LOAD_BUTTON_DEFAULT_COLOR;
	
	/**
	 * Color of an wall box.
	 */
	public final static Color WALL_COLOR = SOLVE_BUTTON_DEFAULT_COLOR;
	
	/**
	 * Color of an solved box.
	 */
	public final static Color SOLVE_COLOR = SOLVE_BUTTON_OVER_COLOR;
	
	/**
	 * Color of an arrival box.
	 */
	public final static Color ARRIVAL_COLOR = LEAVE_CANCEL_BUTTON_DEFAULT_COLOR;
	
	/**
	 * Color of an departure box.
	 */
	public final static Color DEPARTURE_COLOR = OK_SAVE_BUTTON_DEFAULT_COLOR;
	
	/*
	 * **********
	 * BOX FONTS.
	 * **********
	 */
	
	/**
	 * Box font size 0.
	 */
	public final static  Font BOX_FONT_0 = new Font("Arial BLack", Font.BOLD, 70);
	
	/**
	 * Box font size 1.
	 */
	public final static Font BOX_FONT_1 = new Font("Arial BLack", Font.BOLD, 50);
	
	/**
	 * Box font size 2.
	 */
	public final static Font BOX_FONT_2 = new Font("Arial BLack", Font.BOLD, 25);
	
	/**
	 * Box font size 3.
	 */
	public final static Font BOX_FONT_3 = new Font("Arial BLack", Font.BOLD, 20);
	
	/**
	 * Box font size 4.
	 */
	public final static Font BOX_FONT_4 = new Font("Arial BLack", Font.BOLD, 15);
	
	/**
	 * Font of the boxes in the box panels.
	 */
	public final static Font BOX_TEXT_FONT = new Font("Arial BLack", Font.BOLD, 35);
	
	/**
	 * Default foreground color of the text of the box panels.
	 */
	public final static Color BOX_FONT_COLOR = Color.WHITE;
	
	/**
	 * Foreground color of the text of the box panels when selected.
	 */
	public final static Color SELECTED_BOX_FONT_COLOR = NEW_BUTTON_PRESSED_COLOR;

	
	/*
	 * ***********
	 * BOX LABELS.
	 * ***********
	 */
	
	/**
	 * "A"
	 */
	public final static String A_LABEL = "A";
	
	/**
	 * "D"
	 */
	public final static String D_LABEL = "D";
	
	/**
	 * "E"
	 */
	public final static String E_LABEL = "E";
	
	/**
	 * "W"
	 */
	public final static String W_LABEL = "W";
	
	/**
	 * "O"
	 */
	public final static String O_LABEL = "O";
	
	/**
	 * "V"
	 */
	public final static String VOID_LABEL = "V";
	
	/**
	 * Allows to check if the selected box has changed.
	 */
	public final static String SELECTION_UPDATED = "Updated";
	
	/**
	 * Allows to check if the selected box hasn't changed.
	 */
	public final static String SELECTION_MAINTAINED = "Maintained";

	/*
	 * ***************
	 * BOX DIMENSIONS.
	 * ***************
	 */
	
	/**
	 * Box width/height level 0
	 */
	public final static int LEVEL_0 = 100 ;
	
	/**
	 * Box width/height level 1
	 */
	public final static int LEVEL_1 = 75;
	
	/**
	 * Box width/height level 2
	 */
	public final static int LEVEL_2 = 50;
	
	/**
	 * Box width/height level 3
	 */
	public final static int LEVEL_3 = 35;
	
	/**
	 * Box width/height level 4
	 */
	public final static int LEVEL_4 = 25;
	
	/**
	 * Box dimensions level 0
	 */
	public final static Dimension BOX_DIMENSIONS_0 = new Dimension(LEVEL_0, LEVEL_0);
	
	/**
	 * Box dimensions level 1
	 */
	public final static Dimension BOX_DIMENSIONS_1 = new Dimension(LEVEL_1, LEVEL_1);
	
	/**
	 * Box dimensions level 2
	 */
	public final static Dimension BOX_DIMENSIONS_2 = new Dimension(LEVEL_2, LEVEL_2);
	
	/**
	 * Box dimensions level 3
	 */
	public final static Dimension BOX_DIMENSIONS_3 = new Dimension(LEVEL_3, LEVEL_3);
	
	/**
	 * Box dimensions level 4
	 */
	public final static Dimension BOX_DIMENSIONS_4 = new Dimension(LEVEL_4, LEVEL_4);

	/**
	 * Max height size of the maze displayed with the dimensions level 0.
	 */
	public final static int MAX_HEIGHT_0 = 6;
	
	/**
	 * Max height size of the maze displayed with the dimensions level 1.
	 */
	public final static int MAX_HEIGHT_1 = 8;
	
	/**
	 * Max height size of the maze displayed with the dimensions level 2.
	 */
	public final static int MAX_HEIGHT_2 = 12;
	
	/**
	 * Max height size of the maze displayed with the dimensions level 3.
	 */
	public final static int MAX_HEIGHT_3 = 16;
	
	/**
	 * Max height size of the maze displayed with the dimensions level 4.
	 */
	public final static int MAX_HEIGHT_4 = 22;
	
	/**
	 * Max width size of the maze displayed with the dimensions level 0.
	 */
	public final static int MAX_WIDTH_0 = 11;
	
	/**
	 * Max width size of the maze displayed with the dimensions level 1.
	 */
	public final static int MAX_WIDTH_1 = 14;
	
	/**
	 * Max width size of the maze displayed with the dimensions level 2.
	 */
	public final static int MAX_WIDTH_2 = 21;
	
	/**
	 * Max width size of the maze displayed with the dimensions level 3.
	 */
	public final static int MAX_WIDTH_3 = 29;
	
	/**
	 * Max width size of the maze displayed with the dimensions level 4.
	 */
	public final static int MAX_WIDTH_4 = 39;

	
	/*
	 * ******
	 * PATHS.
	 * ******
	 */
	
	/**
	 * "./data"
	 */
	public final static String LOAD_MAZE_DEFAULT_PATH = "./data";
	
	/**
	 * "./data/temp/tempMaze.txt"
	 */
	public final static String SAVE_TEMP_MAZE_PATH = "./data/temp/tempMaze.txt";
	
	/*
	 ********************************
	 * READING EXCEPTIONS MANAGEMENT. 
	 * ******************************
	 */
	
	/**
	 * Error message.
	 */
	public final static String ERROR = "Une erreur inconnue s'est produite. Veuillez r�essayer.";
	
	/**
	 * OK message.
	 */
	public final static String OK = "C'est tout bon, foncez !";
	
	/**
	 * Void file message.
	 */
	public final static String VOID_FILE = "Le fichier s�lectionn� est vide ! Fichier s�lectionn� : \n";
	
	/**
	 * One case file message.
	 */
	public final static String ONE_CASE_FILE = "Il faut au moins deux cases ! Une case de d�part et une case d'arriv�e. Fichier s�lectionn� : \n";
	
	/**
	 * No departure case message.
	 */
	public final static String NO_DEPARTURE_CASE = "Pas de case d�part dans le fichier choisi : \n";
	
	/**
	 * No arrival case message.
	 */
	public final static String NO_ARRIVAL_CASE = "Pas de case d'arriv�e dans le fichier choisi : \n";
	
	/**
	 * One case creation message.
	 */
	public final static String ONE_CASE_CREATION = "Il faut au moins deux cases pour cr�er un labyrinthe !";
	
	/**
	 * Too many cases message.
	 */
	public final static String TOO_MANY_CASES = "Votre labyrinthe est trop gros pour �tre affich�.\n Nombre de lignes max : " + String.valueOf(MAX_HEIGHT_4) + ". " + "Nombre de colonnes max : " + String.valueOf(MAX_WIDTH_4);
	
	/**
	 * A dimension input is lacking message.
	 */
	public final static String NO_INPUT = "Un des champs n'a pas �t� rempli.\nEntrez deux nombre entiers pour commencer.";
	
	/**
	 * One of the inputs is not an integer message.
	 */
	public final static String NOT_INTEGER_INPUT = "Un caract�re invalide a �t� saisi.\nIl faut entrer deux nombre entiers pour commencer.";
	
	/**
	 * One of the inputs is a negative number message.
	 */
	public final static String NEGATIVE_INPUT = "Vous avez entr� un nombre strictement n�gatif.\nIl faut entrer deux nombres entiers positifs pour commencer.";
	
	/*
	 * *****************************
	 * SOLVING EXCEPTION MANAGEMENT.
	 * *****************************
	 */
	
	/**
	 * Message displayed when the given maze has no solution.
	 */
	public final static String UNSOLVABLE_MAZE = "Le labyrinthe n'a pas de solution ! Essayez de le modifier !";
	
	/*
	 * ******************
	 * POP UP PARAMETERS.
	 * ******************
	 */
	
	/**
	 * Pop up window dimensions.
	 */
	public final static Dimension POP_UP_DIMENSION = new Dimension((int) (1920*0.75), 110);
	
	/**
	 * Pop up window font.
	 */
	public final static Font POP_UP_FONT = new Font("Arial BLack", Font.BOLD, 19);
	
	/**
	 * Pop up window X coordinate.
	 */
	public final static int POP_UP_X = 240;
	
	/**
	 * Pop up window Y coordinate.
	 */
	public final static int POP_UP_Y = 575;
	

}
