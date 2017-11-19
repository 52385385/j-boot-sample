package jpamongo.model.common;

import java.lang.reflect.Field;

public class Pm25inAqi {
	private String aqi;
	private String area;
	private String co;
	private String co_24h;
	private String no2;
	private String no2_24h;
	private String o3;
	private String o3_24h;
	private String o3_8h;
	private String o3_8h_24h;
	private String pm10;
	private String pm10_24h;
	private String pm2_5;
	private String pm2_5_24h;
	private String position_name;
	private String primary_pollutant;
	private String quality;
	private String so2;
	private String so2_24h;
	private String station_code;
	private String time_point;
	public String getAqi() {
		return aqi;
	}
	public void setAqi(String aqi) {
		this.aqi = aqi;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCo() {
		return co;
	}
	public void setCo(String co) {
		this.co = co;
	}
	public String getCo_24h() {
		return co_24h;
	}
	public void setCo_24h(String co_24h) {
		this.co_24h = co_24h;
	}
	public String getNo2() {
		return no2;
	}
	public void setNo2(String no2) {
		this.no2 = no2;
	}
	public String getNo2_24h() {
		return no2_24h;
	}
	public void setNo2_24h(String no2_24h) {
		this.no2_24h = no2_24h;
	}
	public String getO3() {
		return o3;
	}
	public void setO3(String o3) {
		this.o3 = o3;
	}
	public String getO3_24h() {
		return o3_24h;
	}
	public void setO3_24h(String o3_24h) {
		this.o3_24h = o3_24h;
	}
	public String getO3_8h() {
		return o3_8h;
	}
	public void setO3_8h(String o3_8h) {
		this.o3_8h = o3_8h;
	}
	public String getO3_8h_24h() {
		return o3_8h_24h;
	}
	public void setO3_8h_24h(String o3_8h_24h) {
		this.o3_8h_24h = o3_8h_24h;
	}
	public String getPm10() {
		return pm10;
	}
	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}
	public String getPm10_24h() {
		return pm10_24h;
	}
	public void setPm10_24h(String pm10_24h) {
		this.pm10_24h = pm10_24h;
	}
	public String getPm2_5() {
		return pm2_5;
	}
	public void setPm2_5(String pm2_5) {
		this.pm2_5 = pm2_5;
	}
	public String getPm2_5_24h() {
		return pm2_5_24h;
	}
	public void setPm2_5_24h(String pm2_5_24h) {
		this.pm2_5_24h = pm2_5_24h;
	}
	public String getPosition_name() {
		return position_name;
	}
	public void setPosition_name(String position_name) {
		this.position_name = position_name;
	}
	public String getPrimary_pollutant() {
		return primary_pollutant;
	}
	public void setPrimary_pollutant(String primary_pollutant) {
		this.primary_pollutant = primary_pollutant;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getSo2() {
		return so2;
	}
	public void setSo2(String so2) {
		this.so2 = so2;
	}
	public String getSo2_24h() {
		return so2_24h;
	}
	public void setSo2_24h(String so2_24h) {
		this.so2_24h = so2_24h;
	}
	public String getStation_code() {
		return station_code;
	}
	public void setStation_code(String station_code) {
		this.station_code = station_code;
	}
	public String getTime_point() {
		return time_point;
	}
	public void setTime_point(String time_point) {
		this.time_point = time_point;
	}
	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 31 + (this.area == null ? 0 : this.area.hashCode());
		hash = hash * 31 + (this.time_point == null ? 0 : this.time_point.hashCode());
		return hash;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Pm25inAqi)) return false;
		Pm25inAqi o = (Pm25inAqi) obj;
		if (o.getArea() != null 
				&& o.getTime_point() != null 
				&& this.area != null
				&& this.time_point != null
				&& o.getArea().equals(this.area) 
				&& o.getTime_point().equals(this.time_point)) return true;
		return false;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{\n");
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields) {
			try {
				sb.append(String.format("  %s : %s\n", f.getType(), f.get(f).toString()));
			} catch (Exception e) {
				return e.getMessage();
			}
		}
		return sb.append("\n}").toString();
	}
}
