package com.epam.moovies.model;

public class Seat {
	private Long number;
	private boolean vip;
	private Long auditoryId;

	public Long getAuditoryId() {
		return auditoryId;
	}

	public void setAuditoryId(Long auditoryId) {
		this.auditoryId = auditoryId;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((auditoryId == null) ? 0 : auditoryId.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + (vip ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (auditoryId == null) {
			if (other.auditoryId != null)
				return false;
		} else if (!auditoryId.equals(other.auditoryId))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (vip != other.vip)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + number + (isVip() ? "v" : "");
	}

}
