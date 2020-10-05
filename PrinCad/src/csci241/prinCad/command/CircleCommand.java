package csci241.prinCad.command;

import csci241.prinCad.control.CircleTool;
import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;

// New file command
public class CircleCommand extends CommandHandler {

	// Constructor
	public CircleCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle CircleCommand Event");

		getCanvas().setActiveTool(new CircleTool());
	}
}
