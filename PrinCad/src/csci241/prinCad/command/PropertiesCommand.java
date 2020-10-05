package csci241.prinCad.command;

import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;

// New file command
public class PropertiesCommand extends CommandHandler {

	// Constructor
	public PropertiesCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle Properties Event");
		_canvas.handlePropertiesCommand();
		
	}
}
