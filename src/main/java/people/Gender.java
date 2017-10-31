package people;

public enum Gender {
    Male(0), Female(1);

    private int genCd;

    private Gender(int genCd) {
        this.genCd = genCd;
    }

    public int getIndex() {
        return this.genCd;
    }

    public Gender parse(String s) {
        s = s.toUpperCase();
        if (s.equalsIgnoreCase("MALE")) {
            return Gender.Male;
        } else if (s.equalsIgnoreCase("FEMALE")) {
            return Gender.Female;
        } else {
            throw new IllegalArgumentException("Input error - bad gender");
        }
    }
}
