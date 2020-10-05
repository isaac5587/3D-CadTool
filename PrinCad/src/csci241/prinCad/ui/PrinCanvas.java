package csci241.prinCad.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.PrintWriter;

import csci241.prinCad.command.CanvasCommandInterface;
import csci241.prinCad.control.BoxSelectionTool;
import csci241.prinCad.control.CadTool;
import csci241.prinCad.control.CanvasToolInterface;
import csci241.prinCad.control.LineSelectionTool;
import csci241.prinCad.model.CadItem;
import csci241.prinCad.model.ModelManager;
import javafx.scene.Cursor;

// Drawing canvas for the Prin CAD tools application
public class PrinCanvas extends Canvas implements CanvasToolInterface, CanvasCommandInterface {
	// Reference to graphics context
	private GraphicsContext _gc;

	public GraphicsContext getGC() {
		return _gc;
	}

	// Mouse movement properties

	public CadTool ls = new LineSelectionTool();
	public CadTool rs = new BoxSelectionTool();
	private int lastsizeindex = 100;
	private ModelManager _model = new ModelManager(lastsizeindex);

	public ModelManager getModel() {
		return _model;
	}

	private CadTool _selectionTool;

	// Active tool
	private CadTool _activeTool;

	public void setActiveTool(CadTool activeTool) {
		_activeTool = activeTool;
	}

	// Data constructor
	public PrinCanvas(double width, double height) {
		// invoke (call) parent class constructor
		super(width, height);

		// Get graphics context and fill with background color
		_gc = getGraphicsContext2D();
		_gc.setFill(MainForm.appSettings.getCanvasBackgroundColor());
		_gc.fillRect(0, 0, MainForm.appSettings.getCanvasWidth(), MainForm.appSettings.getCanvasHeight());

		// Subscribe to mouse events
		setOnMousePressed(e -> onMousePressed(e));
		setOnMouseDragged(e -> onMouseDrag(e));
		setOnMouseReleased(e -> onMouseRelease(e));

		_selectionTool = new LineSelectionTool();
		_activeTool = _selectionTool;
	}

	public void toggleSelectionType() {
		if (_selectionTool instanceof BoxSelectionTool) {
			_selectionTool = new LineSelectionTool();
		} else {
			_selectionTool = new BoxSelectionTool();
		}
		_activeTool = _selectionTool;
	}

	// Set back to selection mode
	public void reset() {
		_activeTool = _selectionTool;
	}

	public void reset(CadItem cadItem) {
		_model.add(cadItem);
		_activeTool = _selectionTool;
	}

	public void draw() {
		_gc.fillRect(0, 0, getWidth(), getHeight());
		_gc.setStroke(Color.BLACK);
		_gc.setLineWidth(0);
		_model.draw(_gc);
	}

	public void saveToFile(PrintWriter out) {
		_model.save(out);
	}

	public void loadFromFile(String line) {
		_model.load(line);

	}

	public void clearCanvas() {
		_model.clear();
	}

	public void delete() {
		_model.deleteSelected();
	}

	public void saveState() {
		_model.saveState();
	}

	public void undo() {
		_model.undo();
	}

	public void redo() {
		_model.redo();
	}
	public void handlePropertiesCommand() {
		double z = ElevationPropForm.Show(0.0);
		_model.setElevation(z);
	}


	

	// Handle mouse pressed (button down)
	private void onMousePressed(MouseEvent e) {

		_activeTool.mousePressed(this, e);
	}

	// Handle mouse drag (only called when mouse button IS depressed)
	private void onMouseDrag(MouseEvent e) {

		_activeTool.mouseDragged(this, e);
	}

	// Handle mouse release (button up)
	private void onMouseRelease(MouseEvent e) {

		_activeTool.mouseRelease(this, e);
	}
}
