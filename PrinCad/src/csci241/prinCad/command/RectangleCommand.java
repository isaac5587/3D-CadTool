package csci241.prinCad.command;

import csci241.prinCad.control.RectangleTool;
import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;

// New file command
public class RectangleCommand extends CommandHandler {

	// Constructor
	public RectangleCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle RectangleCommand Event");

		getCanvas().setActiveTool(new RectangleTool());
	}
}
