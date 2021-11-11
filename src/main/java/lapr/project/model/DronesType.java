package lapr.project.model;

/**
 * @author Carlos Moutinho <1140858@isep.ipp.pt>
 */
public class DronesType {

    private final Long id;
    private final long weight;
    private final long averageSpeed;
    private final long frontArea;
    private final long maxBattery;
    private final long powerUnit;

    public DronesType(Long id, long weight, long averageSpeed, long frontArea, long maxBattery, long powerUnit) {
        this.id = id;
        this.weight = weight;
        this.averageSpeed = averageSpeed;
        this.frontArea = frontArea;
        this.maxBattery = maxBattery;
        this.powerUnit = powerUnit;
    }

    public Long getId() {
        return this.id;
    }

    public long getWeight() {
        return this.weight;
    }

    public long getAverageSpeed() {
        return this.averageSpeed;
    }

    public long getFrontArea() {
        return this.frontArea;
    }

    public long getMaxBattery() {
        return this.maxBattery;
    }

    public long getPowerUnit() {
        return this.powerUnit;
    }

    public DronesType copyDroneType() {
        return new DronesType(this.id, this.weight, this.averageSpeed, this.frontArea, this.maxBattery, this.powerUnit);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
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

        final DronesType other = (DronesType) obj;

        return this.id.equals(other.getId());
    }

    @Override
    public String toString() {
        return "DronesType{" + "id=" + id + ", weight=" + weight + ", averageSpeed=" + averageSpeed + ", frontArea=" + frontArea + ", maxBattery=" + maxBattery + ", powerUnit=" + powerUnit + '}';
    }

}
