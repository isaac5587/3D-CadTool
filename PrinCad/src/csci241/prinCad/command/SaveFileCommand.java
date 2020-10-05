
package csci241.prinCad.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

// New file command
public class SaveFileCommand extends CommandHandler {

	public SaveFileCommand(PrinCanvas canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle Save File Event");

		try {
			Window stage = getCanvas().getScene().getWindow();

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save Model");
			File file = fileChooser.showSaveDialog(stage);

			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw, true);

			getCanvas().saveToFile(out);

			out.flush();
			out.close();
		} catch (Exception ex) {
			Log.error("", ex);
		}
	}

}
