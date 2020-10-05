package csci241.prinCad.model;

import csci241.prinCad.util.Log;

public abstract class MarkerItem extends CadItem {
	
	public static final double MarkerSize = 5;

	protected CadPoint _point;
	
	public MarkerItem(CadPoint point) {
		_point = point;
	}
	
	@Override
	public String save() {
		return String.format("%1$f %2$f", _point.x, _point.y);
	}
	
	protected static CadPoint ToPoint(String data) {
		CadPoint point = null;
		try {
			String[] tokens = data.split(" ");
			double x = Double.parseDouble(tokens[0]);
			double y = Double.parseDouble(tokens[1]);
			point = new CadPoint(x, y);
		}
		catch (Exception ex) {
			Log.error("Invalid MarkerItem data string: " + data, ex);
		}
		return point;
	}
}
