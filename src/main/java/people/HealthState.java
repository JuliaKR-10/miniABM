package people;

public enum HealthState {
    Good(0), Fair(1), Poor(2);

    private int healthCd;

    private HealthState(int healthCd) {
        this.healthCd = healthCd;
    }

    public int getIndex() {
        return this.healthCd;
    }
}
