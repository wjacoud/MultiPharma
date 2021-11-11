package lapr.project.model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author leona
 */
public class Address implements Comparable<Address> {

    private static final String ZIPCODE_PATTERN = "[0-9]{4}-[0-9]{3}";
    private static final double LATITUDE_LIMIT = 90;
    private static final double LONGITUDE_LIMIT = 180;

    // @GeneratedValue GenerationType.IDENTITY)
    private final Long pk;
    private final String country;
    private final String district;
    private final String location;
    private final String zipCode;
    private final Double latitude;
    private final Double longitude;
    private final Double elevation;

    /* To read form DB */
    public Address(final Long pk, final String country, final String district, final String location,
            final String zipCode, final double latitude, final double longitude) {
        this.pk = pk;
        this.country = country;
        this.district = district;
        this.location = location;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = 0.0;
    }

    /* Full Constructor without PK */
    public Address(final String country, final String district, final String location,
            final String zipCode, final double latitude, final double longitude, final double elevation) {

        checkAttributes(country, district, location, zipCode, latitude, longitude);
        this.pk = null;
        this.country = country;
        this.district = district;
        this.location = location;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
    }

    public Long getPk() {
        return this.pk;
    }

    public String getCountry() {
        return this.country;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getLocation() {
        return this.location;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getElevation() {
        return this.elevation;
    }

    @Override
    public int compareTo(Address a) {
        if (this == a) {
            return 0;
        }

        return Double.compare(this.latitude, a.getLatitude())
                + Double.compare(this.longitude, a.getLongitude());
    }

    @Override
    public int hashCode() {
        return this.latitude.hashCode() + this.longitude.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Address other = (Address) obj;

        if (!Objects.equals(this.country, other.country)) {
            return false;
        }

        if (!Objects.equals(this.district, other.district)) {
            return false;
        }

        if (!Objects.equals(this.location, other.location)) {
            return false;
        }

        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }

        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }

        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }

//        if (!Objects.equals(this.elevation, other.elevation)) {
//            return false;
//        }

        return true;
    }

    @Override
    public String toString() {
        return String.format("Address #%d%nzipCode = %s%nlatitude = %s%nlongitude = %s%n", pk, zipCode, latitude, longitude);
    }

    private static void checkAttributes(final String country, final String district, final String location,
            final String zipCode, final double latitude, final double longitude) {
        if (!Pattern.matches(ZIPCODE_PATTERN, zipCode)
                || country == null
                || district == null
                || location == null
                || latitude < -LATITUDE_LIMIT
                || latitude > LATITUDE_LIMIT
                || longitude < -LONGITUDE_LIMIT
                || longitude > LONGITUDE_LIMIT) {
            throw new IllegalArgumentException("Error: Address with Invalid arguments!");
        }
    }
}
