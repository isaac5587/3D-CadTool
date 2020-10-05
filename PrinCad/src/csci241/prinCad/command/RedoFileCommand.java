package csci241.prinCad.command;

import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;

// New file command
public class RedoFileCommand extends CommandHandler {

	// Constructor
	public RedoFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Did Redo Action");
		_canvas.getModel();
		_canvas.redo();
		_canvas.draw();
	}
}
