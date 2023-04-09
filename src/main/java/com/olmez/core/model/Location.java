package com.olmez.core.model;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.olmez.core.utility.DistanceUnits;
import com.olmez.core.utility.MathUtils;
import com.olmez.core.utility.StringUtility;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "name")
public class Location extends BaseObject {

	private String name;
	private String postCode;
	private Integer streetNumber;
	private String streetName;
	private String city;
	private String province;
	private String country;
	private String googleAddress;
	private Double latitude;
	private Double longitude;
	private String timeZone;
	private LocalDate weatherUpdatedOn = LocalDate.now().minusWeeks(1);
	@Lob
	private String description = "";

	public Location(String name) {
		this.name = StringUtility.isEmpty(name) ? "noname" : name;
	}

	public Location(String name, Double latitude, Double longitude) {
		this(name);
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public boolean hasLatitudeLongitude() {
		return (latitude != null) && (longitude != null);
	}

	public Double distanceToLocation(Double givenLatitude, Double givenLongitude) {
		return hasLatitudeLongitude()
				? MathUtils.distance(latitude, longitude, givenLatitude, givenLongitude, DistanceUnits.KM)
				: null;
	}

	@Override
	public String toString() {
		return (!StringUtility.isEmpty(name)) ? name : toGoogleAddress();
	}

	public String toGoogleAddress() {
		StringBuilder address = new StringBuilder();
		address.append(getName());
		if (streetNumber != null) {
			address.append(streetNumber);
		}
		if (streetName != null) {
			address.append(" ");
			address.append(streetName);
		}
		if (city != null) {
			address.append(", ");
			address.append(city);
		}
		if (province != null) {
			address.append(", ");
			address.append(province);
		}

		if (postCode != null) {
			address.append(" ");
			address.append(postCode);
		}
		if (country != null) {
			address.append(", ");
			address.append(country);
		}
		return address.toString();

	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

}
