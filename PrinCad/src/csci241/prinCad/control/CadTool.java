package csci241.prinCad.control;

import javafx.scene.input.MouseEvent;

public interface CadTool {

	public void mousePressed(CanvasToolInterface canvas, MouseEvent e);

	public void mouseDragged(CanvasToolInterface canvas, MouseEvent e);

	public void mouseRelease(CanvasToolInterface canvas, MouseEvent e);

}
