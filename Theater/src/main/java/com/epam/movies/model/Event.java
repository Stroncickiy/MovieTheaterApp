package com.epam.movies.model;

import com.epam.movies.enums.Rating;

import java.time.LocalDateTime;

public class Event {
	private Long Id;
	private String name;
	private Long basePrice;
	private Rating rating;
	private Auditorium auditorium;
	private LocalDateTime start;
	private LocalDateTime end;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Long basePrice) {
		this.basePrice = basePrice;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Event event = (Event) o;

		if (Id != null ? !Id.equals(event.Id) : event.Id != null)
			return false;
		if (name != null ? !name.equals(event.name) : event.name != null)
			return false;
		if (basePrice != null ? !basePrice.equals(event.basePrice) : event.basePrice != null)
			return false;
		if (rating != event.rating)
			return false;
		if (auditorium != null ? !auditorium.equals(event.auditorium) : event.auditorium != null)
			return false;
		if (start != null ? !start.equals(event.start) : event.start != null)
			return false;
		return end != null ? end.equals(event.end) : event.end == null;

	}

	@Override
	public int hashCode() {
		int result = Id != null ? Id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (basePrice != null ? basePrice.hashCode() : 0);
		result = 31 * result + (rating != null ? rating.hashCode() : 0);
		result = 31 * result + (auditorium != null ? auditorium.hashCode() : 0);
		result = 31 * result + (start != null ? start.hashCode() : 0);
		result = 31 * result + (end != null ? end.hashCode() : 0);
		return result;
	}
}
