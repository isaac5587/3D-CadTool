package csci241.prinCad.model;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import csci240.prinCad.fx3d.Item3dInterface;
import csci240.prinCad.fx3d.Model3dInterface;
import csci241.prinCad.ui.ElevationPropForm;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ModelManager implements Model3dInterface {

	ArrayList<CadItem> _cadItems = new ArrayList<CadItem>();

	final private int _undoSize;

	private Deque<ModelData> _undoList;
	private Deque<ModelData> _redoList;

	// Constructor
	public ModelManager(int undoSize) {
		_undoSize = undoSize;
		_cadItems = new ArrayList<CadItem>();
		_undoList = new ArrayDeque<ModelData>(_undoSize);
		_redoList = new ArrayDeque<ModelData>(_undoSize);
	}

	private String _currentFilePath;

	public String GetFilePath() {
		return _currentFilePath;
	}

	public void SetFilePath(String filePath) {
		_currentFilePath = filePath;
	}

	public void add(CadItem cadItem) {
		saveState();
		_cadItems.add(cadItem); // adds item to the list
	}

	public void clear() {
		saveState();
		_cadItems.clear(); // clears the arrayList
	}

	public void draw(GraphicsContext _gc) {
		for (int i = 0; i < _cadItems.size(); i++) { // loops through the arrayList
			CadItem item = _cadItems.get(i); // gets the item from the index in the arrayList
			item.draw(_gc, Color.BLACK, Color.TURQUOISE); // uses the draw method from the selected item
		}
	}

	public void save(PrintWriter out) {
		for (int j = 0; j < _cadItems.size(); j++) { // loops through the arrayList
			CadItem item = _cadItems.get(j); // gets the item from the index in the arrayList
			String line = item.save();
			out.println(line); // prints out the item type and parameters
		}

	}

	public void load(String line) {
		// for each line read the first word in the line to determine the type
		String mystring = line;
		String arr[] = mystring.split(" ");
		String firstWord = arr[0]; // loops through the arrayList

		// switch statement to identify the cadItem
		switch (firstWord) {
		case "Circle":
			CircleItem circle = CircleItem.load(mystring); // creates the item and loads it into the array
			_cadItems.add(circle);
			break;
		case "Line":
			SingleLineItem _line = SingleLineItem.load(mystring);
			_cadItems.add(_line);
			break;
		case "Rectangle":
			RectangleItem rect = RectangleItem.load(mystring);
			_cadItems.add(rect);
			break;
		default:
		}
	}

	public void select(CadBox boxSelect) {
		for (CadItem item : _cadItems) {
			item.select(boxSelect);
		}
	}

	public void select(CadLine lineSelect) {
		for (CadItem item : _cadItems) {
			item.select(lineSelect);
		}
	}

	public void deleteSelected() {
		saveState();
		ArrayList<CadItem> items = new ArrayList<CadItem>();
		for (CadItem item : _cadItems) {
			if (!item.isSelection())
				items.add(item);

		}
		_cadItems = items;
	}

	public void saveState() {
		saveModelData();
		_redoList.clear(); // clear the redo queue
	}

	private void saveModelData() {
		ModelData modelData = new ModelData(_cadItems); // model data object of cad items
		if (_undoList.size() == _undoSize) { // if the queue size = undo size
			_undoList.removeLast(); // removes last element of the queue
		}
		_undoList.push(modelData); // puts this onto the stack
	}

	public void undo() {
		if (_undoList.isEmpty()) // when there's nothing to undo, do nothing
			return;

		ModelData modelDataCurrent = new ModelData(_cadItems); // put the item that was undo into the redo list
		_redoList.push(modelDataCurrent);

		ModelData modelData = _undoList.pop(); // take the item that was done off the undo list
		_cadItems = modelData.getItems(); // gets the remaining items from the cad item array after undo

	}

	public void redo() {
		if (_redoList.isEmpty()) // when there's nothing to redo, do nothing
			return;

		saveModelData();
		ModelData modelData = _redoList.pop();
		_cadItems = modelData.getItems();

	}

	@Override
	public Iterable<Item3dInterface> get3dItems() {

		ArrayList<Item3dInterface> CadItemCopy = new ArrayList<Item3dInterface>(); // Arraylist of CadItems Arraylist
		CadItemCopy.addAll(_cadItems);

		return CadItemCopy;
	}

	public void setElevation(double z) {

		for (CadItem item : _cadItems) {
			if (item.isSelection())
				item.setElevation(z);

		}

	}
}
