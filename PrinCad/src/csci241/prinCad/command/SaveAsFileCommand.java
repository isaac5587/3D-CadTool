package csci241.prinCad.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import csci241.prinCad.model.ModelManager;
import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

// Save as file command
public class SaveAsFileCommand extends CommandHandler {

	public SaveAsFileCommand(PrinCanvas canvas) {
		super(canvas);
	}

	// Handle action event
	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle Save As File Event");

		// ModelManager model = _canvas.getModel();
		ModelManager model = new ModelManager(5);

		Window stage = _canvas.getScene().getWindow();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Model");

		String filePath = model.GetFilePath();
		if (filePath != null) {
			File f = new File(filePath);
			fileChooser.setInitialDirectory(f.getParentFile());
			fileChooser.setInitialFileName(f.getPath());
		}
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showSaveDialog(stage);

		try {
			// Backup existing file
			filePath = file.getAbsolutePath();
			if (file.exists()) {
				File src = new File(filePath);
				File dst = new File(filePath += ".bak");
				if (dst.exists()) {
					dst.delete();
				}
				src.renameTo(dst);
			}

			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw, true);

			model.save(out);

			out.flush();
			out.close();

			model.SetFilePath(filePath);
		} catch (Exception ex) {
			Log.error("Save As File ", ex);
		}

	}

}
