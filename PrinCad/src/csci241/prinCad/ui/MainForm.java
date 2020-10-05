package csci241.prinCad.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.print.DocFlavor.URL;
import javax.xml.soap.Node;

import csci241.prinCad.util.Log;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainForm extends Application {

	// Manager of file commands
	private FileManager _fileManager;
	private EditManager _EditManager;
	private CadToolsManager _CadToolsManager;

	public static AppSettings appSettings = new AppSettings();

	public static void main(String[] args) throws IOException {
		appSettings.loadFile();
		// Launch the javaFX application
		// Saves settings from the file

		Log.info("PrinCad begin execution"); // after restoring app settings

		try {
			// Launch the javaFX application
			launch(args);
			// Save app settings
			appSettings.save();
		} catch (Exception ex) {
			Log.error("PrinCad crash with exception ", ex);
			throw ex;
		}

		Log.info("PrinCad end execution");
	}

	// Override the start
	@Override
	public void start(Stage primaryStage) throws IOException {

		// Create drawing canvas
		PrinCanvas canvas = new PrinCanvas(appSettings.getCanvasWidth(), appSettings.getCanvasHeight());

		// Create file manager
		_fileManager = new FileManager(canvas);

		// Create Edit manager
		_EditManager = new EditManager(canvas);

		// Create New CadToolsManager
		_CadToolsManager = new CadToolsManager(canvas);

		// Get graphics context and fill with background colorbarney834

		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(appSettings.getCanvasBackgroundColor());
		gc.fillRect(0, 0, appSettings.getCanvasWidth(), appSettings.getCanvasHeight());

		// Create the typical monolithic border layout
		// Attach canvas to center of layout
		BorderPane pane = new BorderPane(canvas);

		Color sceneBackgroundColor = appSettings.getSceneBackgroundColor();
		pane.setStyle(FormatStyleColor(sceneBackgroundColor));

		MenuBar mb = new MenuBar();
		pane.setTop(mb);

		ObservableList<Menu> menus = mb.getMenus();

		// add file menu to menu bar
		Menu fileMenu = _fileManager.buildMenu(canvas);
		menus.add(fileMenu);

		// add edit menu to menu bar
		Menu editMenu = _EditManager.buildMenu(canvas);
		menus.add(editMenu);

		Menu CadToolsManagerMenu = _CadToolsManager.buildMenu(canvas);
		menus.add(CadToolsManagerMenu);

		VBox vbox = new VBox(5);
		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.TOP_CENTER);
		pane.setRight(vbox);

		VBox ybox = new VBox(10);
		ybox.setPadding(new Insets(20));
		ybox.setAlignment(Pos.TOP_LEFT);
		pane.setLeft(ybox);

		// Add buttons to bar
		ObservableList<javafx.scene.Node> nodes = vbox.getChildren();
		_fileManager.addButtonsToBar(canvas, nodes);

		ObservableList<javafx.scene.Node> nextNodes = vbox.getChildren();
		_EditManager.addButtonsToBar(canvas, nextNodes);

		ObservableList<javafx.scene.Node> lastNodes = ybox.getChildren();
		_CadToolsManager.addButtonsToScreen(canvas, lastNodes);

		// Create a scene, attach layout pane to scene,
		// set the initial size and background color
		Scene scene = new Scene(pane, appSettings.getSceneWidth(), appSettings.getSceneHeight(), sceneBackgroundColor);

		// Apply application styles
		File file = new File("AppStyles.css");
		if (!file.exists()) {
			Log.info(file.toString() + " does not exist");
		} else {
			java.net.URL url = file.toURI().toURL();
			scene.getStylesheets().add(url.toExternalForm());
		}

		// Attach scene to stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("CSCI 241 PrinCad Project");
		primaryStage.show();
	}

	// Build menu

	private String FormatStyleColor(Color color) {
		String rx = String.format("%02X", Math.round(color.getRed() * 255.0));
		String gx = String.format("%02X", Math.round(color.getGreen() * 255.0));
		String bx = String.format("%02X", Math.round(color.getBlue() * 255.0));
		String fx = "-fx-background-color: #" + rx + gx + bx + ";";
		return fx;
	}

}
