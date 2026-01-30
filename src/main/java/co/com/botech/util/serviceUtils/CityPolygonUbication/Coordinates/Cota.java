package co.com.botech.util.serviceUtils.CityPolygonUbication.Coordinates;

import java.awt.geom.Path2D;

public class Cota implements PolygonInterface {
	public static Path2D polygon = new Path2D.Double();
	public static String name = "Cota";

	static{
		polygon.moveTo(4.82716521789375, -74.09118494736902);
		polygon.lineTo(4.834158575466503, -74.10267666929803);
		polygon.lineTo(4.82853761638404, -74.10588378101372);
		polygon.lineTo(4.818206169292381, -74.11022955451301);
		polygon.lineTo(4.815650144375809, -74.10838353863913);
		polygon.lineTo(4.812028797188091, -74.11255143720403);
		polygon.lineTo(4.799141870972434, -74.12162749801388);
		polygon.lineTo(4.793538536709832, -74.12444501498786);
		polygon.lineTo(4.788287076701209, -74.11468943663407);
		polygon.lineTo(4.79947686259456, -74.09570338486354);
		polygon.lineTo(4.798752236690248, -74.09174445151994);
		polygon.lineTo(4.809218505351907, -74.08124029800778);
		polygon.lineTo(4.822905670065765, -74.08641105413872);
		polygon.lineTo(4.82716521789375, -74.09118494736902);
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
