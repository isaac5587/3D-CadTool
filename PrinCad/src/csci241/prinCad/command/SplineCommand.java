package csci241.prinCad.command;

import csci241.prinCad.control.SplineTool;
import csci241.prinCad.util.Log;
import javafx.event.ActionEvent;

public class SplineCommand extends CommandHandler {

	public SplineCommand(CanvasCommandInterface canvas) {
		super(canvas);

	}

	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		Log.info("Handle Spline Event");

		_canvas.setActiveTool(new SplineTool());
	}

}
