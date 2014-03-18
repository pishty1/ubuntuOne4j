package au.id.villar.ubuntuOne.account;

public class QuotaInfo {

	private long total;
	private long used;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	@Override
	public String toString() {
		return "QuotaInfo{" +
				"total=" + total +
				", used=" + used +
				'}';
	}
}
