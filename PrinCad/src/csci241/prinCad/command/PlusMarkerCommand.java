package csci241.prinCad.command;

import csci241.prinCad.control.PlusMarkerTool;
import csci241.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

// New file command
public class PlusMarkerCommand extends CommandHandler {

	// Constructor
	public PlusMarkerCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		// Log.info("Handle PlusMarkerCommand File Event");

		getCanvas().setActiveTool(new PlusMarkerTool());
	}
}
