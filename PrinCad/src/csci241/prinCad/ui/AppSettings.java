package csci241.prinCad.ui;

import java.lang.String;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import csci241.prinCad.util.Log;
import javafx.scene.paint.Color;

public class AppSettings implements SettingsInterface {

	private Properties p = new Properties();

	public Log.LoggingLevel getLoggingLevel() {

		String s = p.getProperty("LoggingLevel");

		Log.LoggingLevel state = Log.LoggingLevel.None;
		if (s == null) {
			return state;
		}
		state = Log.LoggingLevel.valueOf(s); // converting string to enum
		return state;
	}

	public void setLoggingLevel(Log.LoggingLevel e) {
		p.setProperty("LoggingLevel", e.toString());

	}

	public void save() throws IOException {
		OutputStream os = new FileOutputStream("program.properties");
		p.store(os, null);
	}

	public void loadFile() throws IOException {
		File f = new File("program.properties");
		if (f.exists()) {

			InputStream is = new FileInputStream("program.properties");
			p.load(is);
		}

		else {
			p.setProperty("canvasHeight", "225");
			p.setProperty("canvasWidth", "275");
			p.setProperty("SceneHeight", "250");
			p.setProperty("SceneWidth", "300");
			p.setProperty("CanvasBackgroundColor", "Color.PURPLE");
			p.setProperty("SceneBackgroundColor", "Color.YELLOW");
			p.setProperty("LoggingLevel", Log.LoggingLevel.None.toString());
		}
	}

	public void setCanvasHeight(int canvasHeight) {
		p.setProperty("canvasHeight", Integer.toString(canvasHeight));

	}

	public int getCanvasHeight() {
		String s = p.getProperty("canvasHeight");
		int j = 0;
		try {
			j = Integer.parseInt(s);
		} catch (Exception e) {

		}
		return j;
	}

	public void setCanvasWidth(int canvasWidth) {
		p.setProperty("canvasWidth", Integer.toString(canvasWidth));
	}

	public int getCanvasWidth() {
		String t = p.getProperty("canvasWidth");
		int j = 0;
		try {
			j = Integer.parseInt(t);
		} catch (Exception e) {
			System.out.println(e);
		}
		return j;
	}

	public void setSceneHeight(int SceneHeight) {
		p.setProperty("SceneHeight", Integer.toString(SceneHeight));
	}

	public int getSceneHeight() {
		String t = p.getProperty("SceneHeight");
		int j = Integer.parseInt(t);
		return j;
	}

	public void setSceneWidth(int SceneWidth) {
		p.setProperty("SceneWidth", Integer.toString(SceneWidth));
	}

	public int getSceneWidth() {
		String t = p.getProperty("SceneWidth");
		int j = Integer.parseInt(t);
		return j;
	}

	public void setCanvasBackgroundColor(Color CanvasBackgroundColor) {
		p.setProperty("CanvasBackgroundColor", CanvasBackgroundColor.toString());

	}

	public Color getCanvasBackgroundColor() {
		String s = p.getProperty("CanvasBackgroundColor");
		Color j = Color.GREEN;
		try {
			j = Color.web(s);
		} catch (Exception E) {
			System.out.println(E);
		}
		return j;
	}

	public void setSceneBackgroundColor(Color SceneBackgroundColor) {
		p.setProperty("SceneBackgroundColor", SceneBackgroundColor.toString());

	}

	public Color getSceneBackgroundColor() {
		String s = p.getProperty("SceneBackgroundColor");
		Color j = Color.BLUE;
		try {
			j = Color.web(s);
		} catch (Exception E) {
			System.out.println(E);
		}
		return j;
	}

}