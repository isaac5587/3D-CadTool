package csci241.prinCad.model;



import java.util.ArrayList;

public class ModelData {

	// Collection of cad items
	final private ArrayList<CadItem> _cadItems;
	
	public ArrayList<CadItem> getItems() {
		return _cadItems;
	}
	
	// Constructor
	public ModelData(ArrayList<CadItem> items) {
		_cadItems = new ArrayList<CadItem>();
		for (CadItem item : items) {
			_cadItems.add(item.copy());
		}
	}
}

