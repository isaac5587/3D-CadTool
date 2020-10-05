package csci241.prinCad.command;

import csci241.prinCad.control.BoxSelectionTool;
import csci241.prinCad.control.CadTool;
import csci241.prinCad.control.LineSelectionTool;
import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

// New file command
public class ToggleSelectionCommand extends CommandHandler {

	public CadTool ls = new LineSelectionTool();
	public CadTool rs = new BoxSelectionTool();

	// Constructor
	public ToggleSelectionCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle Toggle Event");

		getCanvas().toggleSelectionType();

	}
}
