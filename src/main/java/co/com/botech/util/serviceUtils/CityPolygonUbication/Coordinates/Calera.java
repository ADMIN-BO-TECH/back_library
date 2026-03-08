package co.com.botech.util.serviceUtils.CityPolygonUbication.Coordinates;

import java.awt.geom.Path2D;

public class Calera implements PolygonInterface {
	public static Path2D polygon = new Path2D.Double();
	public static String name = "Calera";

	static {
		polygon.moveTo(4.725163063041492, -73.96201314606017);
		polygon.lineTo(4.728890774056978, -73.9704107862782);
		polygon.lineTo(4.7282694902805815, -73.9726110413566);
		polygon.lineTo(4.72289718968608, -73.97609477856535);
		polygon.lineTo(4.7189136201964175, -73.97924847751145);
		polygon.lineTo(4.712883402703781, -73.9749579801079);
		polygon.lineTo(4.710982959677722, -73.97198763575157);
		polygon.lineTo(4.71226210459389, -73.96432341389328);
		polygon.lineTo(4.725163063041492, -73.96201314606017);
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
