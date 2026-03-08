package co.com.botech.util.serviceUtils.CityPolygonUbication.Coordinates;

import java.awt.geom.Path2D;

public class Chia implements PolygonInterface {

	public static Path2D polygon = new Path2D.Double();
	public static String name = "Chía";

	static{
		polygon.moveTo(4.88423777621793, -74.03609523668378);
		polygon.lineTo(4.891952491991091, -74.06288989205983);
		polygon.lineTo(4.883804810632967, -74.07541714838496);
		polygon.lineTo(4.847125784027554, -74.08868846000014);
		polygon.lineTo(4.842596390210204, -74.08143447227512);
		polygon.lineTo(4.836672732410875, -74.07888097990299);
		polygon.lineTo(4.834946538707101, -74.05744231390035);
		polygon.lineTo(4.838072733257931, -74.04878848687366);
		polygon.lineTo(4.859512812546342, -74.03897050989397);
		polygon.lineTo(4.879802985531242, -74.02702523049193);
		polygon.lineTo(4.88423777621793, -74.03609523668378);
		polygon.closePath();

	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Path2D getPolygon() {
		return polygon;
	}

}
