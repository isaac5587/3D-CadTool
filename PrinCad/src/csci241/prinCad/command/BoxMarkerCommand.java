package csci241.prinCad.command;

import csci241.prinCad.control.BoxMarkerTool;
import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;

// New file command
public class BoxMarkerCommand extends CommandHandler {

	// Constructor
	public BoxMarkerCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("BoxMarkerCommand File Event");

		getCanvas().setActiveTool(new BoxMarkerTool());

	}
}
