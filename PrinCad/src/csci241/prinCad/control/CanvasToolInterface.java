package csci241.prinCad.control;

import csci241.prinCad.model.CadItem;
import csci241.prinCad.model.ModelManager;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;

public interface CanvasToolInterface {
	public GraphicsContext getGC();

	public GraphicsContext getGraphicsContext2D();

	public void setCursor(Cursor cursor);

	public void draw();

	public void reset();

	public void reset(CadItem cadItem);
	
	public ModelManager getModel();
}
