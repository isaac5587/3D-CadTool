package csci241.prinCad.model;

import csci240.prinCad.fx3d.Map3d;
import csci241.prinCad.util.Log;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

public class PolyLineItem extends CadItem {

	public final CadPoint[] _points;
	
	double x,y, _x, _y;

	public PolyLineItem(CadPoint[] points) {
		_points = points;
	}

	// load circle item from string data
	public static PolyLineItem load(String data) {

		PolyLineItem item = null;
		try {
			String[] tokens = data.split(",");
			CadPoint[] points = new CadPoint[tokens.length];
			for (int i = 0; i < tokens.length; i++) {
				String temp = tokens[i].replace("(", "").replace(")", "");
				String[] pts = temp.split(" ");

				double x = Double.parseDouble(pts[0]);
				double y = Double.parseDouble(pts[1]);
				points[i] = new CadPoint(x, y);
			}
			item = new PolyLineItem(points);
		} catch (Exception ex) {
			Log.error("Invalid PolylineItem data string: " + data, ex);
		}
		return item;
	}

	@Override
	public void draw(GraphicsContext gc) {
		CadPoint p1 = _points[0];
		for (int i = 1; i < _points.length; i++) {
			CadPoint p2 = _points[i];
			gc.strokeLine(p1.x, p1.y, p2.x, p2.y);
			_x= p2.x;
			_y = p2.y;
			p1 = p2;
		}
	}

	@Override
	public String save() {
		String s = "";

		for (int i = 0; i < _points.length; i++) {
			CadPoint p = _points[i];
			s += String.format("(%1$f %2$f),", p.x, p.y);
		}

		return s;
	}
	
	public CadBox getRectangle() {
		return new CadBox(
				x,  y,
				_x,  _y);				
	}

	@Override
	public boolean intersects(CadLine line) {
		// TODO Auto-generated method stub
		return line.intersects(this.x, this.y, this._x,this._y);
	}
	
	@Override public CadItem copy() {
		return new PolyLineItem(_points);
	}

	@Override
	public Node get3dObject(Map3d map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
