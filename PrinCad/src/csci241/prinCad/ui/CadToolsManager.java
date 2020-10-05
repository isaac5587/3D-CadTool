package csci241.prinCad.ui;

import csci241.prinCad.command.BoxMarkerCommand;
import csci241.prinCad.command.CircleCommand;
import csci241.prinCad.command.CrissCrossMarkerCommand;
import csci241.prinCad.command.EllipseCommand;
import csci241.prinCad.command.PlusMarkerCommand;
import csci241.prinCad.command.PolyLineCommand;
import csci241.prinCad.command.RectangleCommand;
import csci241.prinCad.command.SingleLineCommand;
import csci241.prinCad.command.SplineCommand;
import csci241.prinCad.control.SplineTool;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class CadToolsManager {

	// private Markers _Markers;
	private PlusMarkerCommand _plusMarker;
	private BoxMarkerCommand _boxMarker;
	private CrissCrossMarkerCommand _ExMarker;
	private SingleLineCommand _singleLine;
	private CircleCommand _circle;
	private RectangleCommand _rectangle;
	private PolyLineCommand _polyLine;
	private EllipseCommand _ellipse;
	private SplineCommand _spline;

	public CadToolsManager(PrinCanvas canvas) {
		// _Markers = new Markers(canvas);
		_plusMarker = new PlusMarkerCommand(canvas);
		_boxMarker = new BoxMarkerCommand(canvas);
		_ExMarker = new CrissCrossMarkerCommand(canvas);
		_singleLine = new SingleLineCommand(canvas);
		_rectangle = new RectangleCommand(canvas);
		_circle = new CircleCommand(canvas);
		_polyLine = new PolyLineCommand(canvas);
		_ellipse = new EllipseCommand(canvas);
		_spline = new SplineCommand(canvas);


	}

	// Build menu
	public Menu buildMenu(PrinCanvas canvas) {

		// create menu items
		MenuItem miPlus = new MenuItem("Plus (+)");
		miPlus.setOnAction(e -> _plusMarker.action(canvas, e));

		MenuItem miBox = new MenuItem("Box ([])");
		miBox.setOnAction(e -> _boxMarker.action(canvas, e));

		MenuItem miEX = new MenuItem("Ex Marker (X)");
		miEX.setOnAction(e -> _ExMarker.action(canvas, e));

		MenuItem miSingleLine = new MenuItem("Single Line (|) ");
		miEX.setOnAction(e -> _singleLine.action(canvas, e));

		MenuItem miCircle = new MenuItem("Circle (o)");
		miEX.setOnAction(e -> _circle.action(canvas, e));

		MenuItem miRectangle = new MenuItem("Rectangle ([  ]) ");
		miEX.setOnAction(e -> _rectangle.action(canvas, e));

		MenuItem miEllipse = new MenuItem("Ellipse (O)");
		miEllipse.setOnAction(e -> _ellipse.action(canvas, e));

		MenuItem miPolyLine = new MenuItem("PolyLine (/ /)");
		miPolyLine.setOnAction(e -> _polyLine.action(canvas, e));
		
		MenuItem miSpline = new MenuItem("Spline (U)");
		miSpline.setOnAction(e -> _polyLine.action(canvas, e));

		Menu markerMenu = new Menu("Markers");
		ObservableList<MenuItem> markerMenuItems = markerMenu.getItems();

		// add individual marker menu items to menu
		markerMenuItems.add(miPlus);
		markerMenuItems.add(miBox);
		markerMenuItems.add(miEX);
		markerMenuItems.add(miSingleLine);
		markerMenuItems.add(miCircle);
		markerMenuItems.add(miEllipse);
		markerMenuItems.add(miRectangle);
		markerMenuItems.add(miPolyLine);
		markerMenuItems.add(miSpline);

		// create a menu
		Menu cadToolMenu = new Menu("CAD Tools");
		ObservableList<MenuItem> cadToolMenuItems = cadToolMenu.getItems();

		// add menu items to menu
		cadToolMenuItems.add(markerMenu);

		return cadToolMenu;
	}

	public void addButtonsToScreen(PrinCanvas canvas, ObservableList<Node> nodes) {

		Button pmb = new Button();
		pmb.setMinWidth(80);
		pmb.setText("Plus Marker");
		pmb.setOnAction(e -> _plusMarker.action(canvas, e));
		nodes.add(pmb);

		Button bmb = new Button();
		bmb.setMinWidth(80);
		bmb.setText("Box Marker");
		bmb.setOnAction(e -> _boxMarker.action(canvas, e));
		nodes.add(bmb);

		Button ccmb = new Button();
		ccmb.setMinWidth(80);
		ccmb.setText("Ex Marker");
		ccmb.setOnAction(e -> _ExMarker.action(canvas, e));
		nodes.add(ccmb);

		Button slmb = new Button();
		slmb.setMinWidth(80);
		slmb.setText("Single Line");
		slmb.setOnAction(e -> _singleLine.action(canvas, e));
		nodes.add(slmb);

		Button cmb = new Button();
		cmb.setMinWidth(80);
		cmb.setText("Circle");
		cmb.setOnAction(e -> _circle.action(canvas, e));
		nodes.add(cmb);

		Button rmb = new Button();
		rmb.setMinWidth(80);
		rmb.setText("Rectangle");
		rmb.setOnAction(e -> _rectangle.action(canvas, e));
		nodes.add(rmb);

		Button emb = new Button();
		emb.setMinWidth(80);
		emb.setText("Ellipse");
		emb.setOnAction(e -> _ellipse.action(canvas, e));
		nodes.add(emb);

		Button plmb = new Button();
		plmb.setMinWidth(80);
		plmb.setText("PolyLine");
		plmb.setOnAction(e -> _polyLine.action(canvas, e));
		nodes.add(plmb);
		
		Button smb = new Button();
		smb.setMinWidth(80);
		smb.setText("Spline");
		smb.setOnAction(e -> _spline.action(canvas, e));
		nodes.add(smb);

	}
}
