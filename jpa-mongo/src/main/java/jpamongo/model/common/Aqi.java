package jpamongo.model.common;

import java.util.ArrayList;
import java.util.List;

public class Aqi {
	private Pm25inAqi general;
	private List<Pm25inAqi> details = new ArrayList<>();
	public Pm25inAqi getGeneral() {
		return general;
	}
	public void setGeneral(Pm25inAqi general) {
		this.general = general;
	}
	public List<Pm25inAqi> getDetails() {
		return details;
	}
	public void setDetails(List<Pm25inAqi> details) {
		this.details = details;
	}
	public Aqi(List<Pm25inAqi> plain) {
		plain.forEach(p -> {
			if (p.getPosition_name() == null) {
				this.general = p;
			} else {
				details.add(p);
			}
		});
	}
	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 31 + (this.general == null ? 0 : this.general.hashCode());
		return hash;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!Aqi.class.isAssignableFrom(obj.getClass())) return false;
		final Aqi aqi = (Aqi) obj;
		if (aqi.getGeneral() != null
				&& this.general != null
				&& aqi.getGeneral().equals(this.general)) return true;
		return false;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{\n");
		sb.append(String.format("  general : %s\n  details: [\n", this.general.toString()));
		details.forEach(d -> {
			sb.append(String.format("    %s\n", d.toString()));
		});
		sb.append("\n  ]\n}");
		return sb.toString();
	}
}
