package csci241.prinCad.command;

import csci241.prinCad.control.EllipseTool;
import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;

// New file command
public class EllipseCommand extends CommandHandler {

	// Constructor
	public EllipseCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle Ellipse Event");

		getCanvas().setActiveTool(new EllipseTool());
	}
}
