package csci241.prinCad.command;

import csci240.prinCad.fx3d.View3dScene;
import javafx.event.ActionEvent;

public class View3dCommand extends CommandHandler{

	public View3dCommand(CanvasCommandInterface canvas) {
		super(canvas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(CanvasCommandInterface canvas, ActionEvent e) {
		      View3dScene.Show(_canvas.getModel());
	}

}
