
package csci241.prinCad.command;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;

// New file command
public class OpenFileCommand extends CommandHandler {

	// Constructor
	public OpenFileCommand(PrinCanvas canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle Open File Event");
		_canvas.getModel();

		try {
			// file chooser
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			Window stage = null;
			File file = fileChooser.showOpenDialog(stage);

			FileReader fw = new FileReader(file);
			BufferedReader br = new BufferedReader(fw);
			String line = br.readLine();

			while (line != null) {
				_canvas.loadFromFile(line);
				line = br.readLine();
			}

			_canvas.draw();

		} catch (Exception ex) {
			Log.error("", ex);
		}
	}
}
