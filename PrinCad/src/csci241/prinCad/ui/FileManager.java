package csci241.prinCad.ui;

import csci241.prinCad.command.NewFileCommand;
import csci241.prinCad.command.OpenFileCommand;
import csci241.prinCad.command.SaveAsFileCommand;
import csci241.prinCad.command.SaveFileCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileManager {
	private NewFileCommand _newFileCommand;
	private OpenFileCommand _openFileCommand;
	private SaveFileCommand _saveFileCommand;
	private SaveAsFileCommand _saveAsFileCommand;

	// Constructor
	public FileManager(PrinCanvas canvas) {
		_newFileCommand = new NewFileCommand(canvas);
		_openFileCommand = new OpenFileCommand(canvas);
		_saveFileCommand = new SaveFileCommand(canvas);
		_saveAsFileCommand = new SaveAsFileCommand(canvas);

	}

	public Menu buildMenu(PrinCanvas canvas) {

		// create menu items
		MenuItem miNew = new MenuItem("New");
		miNew.setOnAction(e -> _newFileCommand.action(canvas, e));

		MenuItem miOpen = new MenuItem("Open");
		miOpen.setOnAction(e -> _openFileCommand.action(canvas, e));

		MenuItem miSave = new MenuItem("Save");
		miSave.setOnAction(e -> _saveFileCommand.action(canvas, e));

		MenuItem miSaveAs = new MenuItem("Save As");
		miSaveAs.setOnAction(e -> _saveAsFileCommand.action(canvas, e));

		// create a menu
		Menu fileMenu = new Menu("File");
		ObservableList<MenuItem> fileMenuItems = fileMenu.getItems();

		// add menu items to menu
		fileMenuItems.add(miNew);
		fileMenuItems.add(miOpen);
		fileMenuItems.add(miSave);
		fileMenuItems.add(miSaveAs);

		return fileMenu;
	}

	public void addButtonsToBar(PrinCanvas canvas, ObservableList<Node> nodes) {

		// Create buttons
		Button nfb = new Button();
		nfb.setMinWidth(80);
		nfb.setText("New File");
		nfb.setOnAction(e -> _newFileCommand.action(canvas, e));

		Button ofb = new Button();
		ofb.setMinWidth(80);
		ofb.setText("Open File");
		ofb.setOnAction(e -> _openFileCommand.action(canvas, e));

		Button sfb = new Button();
		sfb.setMinWidth(80);
		sfb.setText("Save File");
		sfb.setOnAction(e -> _saveFileCommand.action(canvas, e));

		Button safb = new Button();
		safb.setMinWidth(80);
		safb.setText("Save As File");
		safb.setOnAction(e -> _saveAsFileCommand.action(canvas, e));

		nodes.add(nfb);
		nodes.add(ofb);
		nodes.add(sfb);
		nodes.add(safb);
	}

}
