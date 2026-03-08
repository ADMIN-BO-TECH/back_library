package co.com.botech.util.serviceUtils.CityPolygonUbication.Coordinates;

import java.awt.geom.Path2D;

public class FunzaMosquera implements PolygonInterface {
	public static Path2D polygon = new Path2D.Double();
	public static String name = "Funza y Mosquera";

	static{
		polygon.moveTo(4.698227296876624, -74.23842974593892);
		polygon.lineTo(4.693150087593509, -74.23477921703238);
		polygon.lineTo(4.692774700183435, -74.22150615951341);
		polygon.lineTo(4.694870710041144, -74.21740121445474);
		polygon.lineTo(4.699777148700946, -74.2146781898223);
		polygon.lineTo(4.703428078146288, -74.19626390959249);
		polygon.lineTo(4.7091100301601045, -74.19193315096935);
		polygon.lineTo(4.7212642811797, -74.19679852823334);
		polygon.lineTo(4.722439741523843, -74.19500516490075);
		polygon.lineTo(4.73059001190849, -74.1983271202129);
		polygon.lineTo(4.727860564116952, -74.20369548890113);
		polygon.lineTo(4.730266425726171, -74.20561876758937);
		polygon.lineTo(4.725850193028705, -74.21512843814529);
		polygon.lineTo(4.732046258586379, -74.21882414988227);
		polygon.lineTo(4.734287136001939, -74.22626374683647);
		polygon.lineTo(4.724091538850004, -74.23723606021537);
		polygon.lineTo(4.716819061653709, -74.23236874372401);
		polygon.lineTo(4.7127062556930355, -74.2394009061224);
		polygon.lineTo(4.709542357956778, -74.24349309016543);
		polygon.lineTo(4.706556486657391, -74.24099580158496);
		polygon.lineTo(4.700498148123415, -74.24033985915415);
		polygon.lineTo(4.698227296876624, -74.23842974593892);
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
