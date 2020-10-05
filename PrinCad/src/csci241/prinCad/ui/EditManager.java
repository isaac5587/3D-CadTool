package csci241.prinCad.ui;

import csci241.prinCad.command.DeleteCommand;
import csci241.prinCad.command.PropertiesCommand;
import csci241.prinCad.command.RedoFileCommand;
import csci241.prinCad.command.ToggleSelectionCommand;
import csci241.prinCad.command.UndoFileCommand;
import csci241.prinCad.command.View3dCommand;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class EditManager {
	private UndoFileCommand _UndoFileCommand;
	private RedoFileCommand _RedoFileCommand;
	private DeleteCommand _DeleteFileCommand;
	private ToggleSelectionCommand _ToggleCommand;
	private PropertiesCommand _Properties;
	private View3dCommand _View3d;

	// Constructor
	public EditManager(PrinCanvas canvas) {
		_UndoFileCommand = new UndoFileCommand(canvas);
		_RedoFileCommand = new RedoFileCommand(canvas);
		_DeleteFileCommand = new DeleteCommand(canvas);
		_ToggleCommand = new ToggleSelectionCommand(canvas);
		_Properties = new PropertiesCommand(canvas);
		_View3d = new View3dCommand(canvas);

	}

	public Menu buildMenu(PrinCanvas canvas) {

		// create menu items
		MenuItem miUndo = new MenuItem("Undo");
		miUndo.setOnAction(e -> _UndoFileCommand.action(canvas, e));

		MenuItem miRedo = new MenuItem("Redo");
		miRedo.setOnAction(e -> _RedoFileCommand.action(canvas, e));

		MenuItem miDelete = new MenuItem("Delete");
		miDelete.setOnAction(e -> _DeleteFileCommand.action(canvas, e));

		MenuItem miToggle = new MenuItem("Toggle Selection");
		miToggle.setOnAction(e -> _ToggleCommand.action(canvas, e));

		MenuItem miProperties = new MenuItem("Properties");
		miProperties.setOnAction(e -> _Properties.action(canvas, e));

		MenuItem miView3d = new MenuItem("View in 3D");
		miProperties.setOnAction(e -> _View3d.action(canvas, e));

		// create a menu
		Menu fileMenu = new Menu("Edit");
		ObservableList<MenuItem> fileMenuItems = fileMenu.getItems();

		// add menu items to menu
		fileMenuItems.add(miUndo);
		fileMenuItems.add(miRedo);
		fileMenuItems.add(miDelete);
		fileMenuItems.add(miToggle);
		fileMenuItems.add(miProperties);
		fileMenuItems.add(miView3d);

		return fileMenu;
	}

	public void addButtonsToBar(PrinCanvas canvas, ObservableList<Node> nodes) {

		// Create buttons
		Button ufb = new Button();
		ufb.setMinWidth(80);
		ufb.setText("Undo");
		ufb.setOnAction(e -> _UndoFileCommand.action(canvas, e));

		Button rfb = new Button();
		rfb.setMinWidth(80);
		rfb.setText("Redo");
		rfb.setOnAction(e -> _RedoFileCommand.action(canvas, e));

		Button dfb = new Button();
		dfb.setMinWidth(80);
		dfb.setText("Delete");
		dfb.setOnAction(e -> _DeleteFileCommand.action(canvas, e));

		Button tcfb = new Button();
		tcfb.setMinWidth(80);
		tcfb.setText("^Selection");
		tcfb.setOnAction(e -> _ToggleCommand.action(canvas, e));

		Button pfb = new Button();
		pfb.setMinWidth(80);
		pfb.setText("Properties");
		pfb.setOnAction(e -> _Properties.action(canvas, e));

		Button v3db = new Button();
		v3db.setMinWidth(80);
		v3db.setText("View in 3D");
		v3db.setOnAction(e -> _View3d.action(canvas, e));

		nodes.add(ufb);
		nodes.add(rfb);
		nodes.add(dfb);
		nodes.add(tcfb);
		nodes.add(pfb);
		nodes.add(v3db);
	}

}