package csci241.prinCad.command;

import csci241.prinCad.model.ModelManager;
import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;

// New file command
public class UndoFileCommand extends CommandHandler {

	// Constructor
	public UndoFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info(" Did an Undo Action");
		_canvas.getModel();
		_canvas.undo();
		_canvas.draw();

	}
}
