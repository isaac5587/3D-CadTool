package csci241.prinCad.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

// New file command
public class NewFileCommand extends CommandHandler {

	// Constructor
	public NewFileCommand(CanvasCommandInterface canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle New File Event");

		_canvas.getModel();
		_canvas.clearCanvas();
		_canvas.draw();

	}
}
