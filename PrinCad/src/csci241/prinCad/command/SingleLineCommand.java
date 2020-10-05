package csci241.prinCad.command;

import csci241.prinCad.control.SingleLineTool;
import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;

// New file command
public class SingleLineCommand extends CommandHandler {

	// Constructor
	public SingleLineCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle SingleLineCommand Event");

		getCanvas().setActiveTool(new SingleLineTool());
	}
}
