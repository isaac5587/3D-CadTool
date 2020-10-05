package csci241.prinCad.command;

import java.io.PrintWriter;

import csci241.prinCad.control.CadTool;
import csci241.prinCad.ui.PrinCanvas;
import javafx.event.ActionEvent;
import javafx.scene.Scene;

public interface CanvasCommandInterface {

	public void setActiveTool(CadTool activeTool);

	public void clearCanvas();

	public void draw();

	public void loadFromFile(String line);

	public void saveToFile(PrintWriter out);

	public void toggleSelectionType();
	
	public void delete();
	
	public void undo();
	
	public void redo();
	
	public void saveState();

	public Scene getScene();
	
	public void handlePropertiesCommand();

}
