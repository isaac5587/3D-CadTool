package csci241.prinCad.command;

import csci241.prinCad.control.PolyLineMarkerTool;
import csci241.prinCad.ui.PrinCanvas;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;

public class PolyLineCommand extends CommandHandler {

	public PolyLineCommand(CanvasCommandInterface canvas) {
		super(canvas);

	}

	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle Polyline Event");

		_canvas.setActiveTool(new PolyLineMarkerTool());
	}
}
