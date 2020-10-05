package csci241.prinCad.command;

import csci241.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;

public abstract class CommandHandler {

	// Owning canvas
	PrinCanvas _canvas;

	public final CanvasCommandInterface getCanvas() {
		return _canvas;
	}

	// Constructor
	public CommandHandler(CanvasCommandInterface canvas) {
		_canvas = (PrinCanvas) canvas;
	}

	// Handle action event
	public abstract void action(CanvasCommandInterface canvas, ActionEvent e);

}
