package csci241.prinCad.ui;

import csci241.prinCad.command.CanvasCommandInterface;
import csci241.prinCad.command.CommandHandler;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;

// New file command
public class Markers extends CommandHandler {

	// Constructor
	public Markers(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Markers File Event");
	}
}
