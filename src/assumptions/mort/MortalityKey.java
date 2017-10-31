package assumptions.mort;

import people.Gender;

/**
 * Look up key for current mortality assumption hashmap
 */
public class MortalityKey {
    /**
     * age for qx factor
     */
    int age;

    /**
     * gender for qx factor
     */
    Gender gend;

    /**
     * Constructor for hashmap key object for current mortality assumption
     *
     * @param a
     * @param g
     */
    public MortalityKey(int a, Gender g) {
        age = a;
        gend = g;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MortalityKey that = (MortalityKey) o;

        if (age != that.age) return false;
        return gend == that.gend;
    }

    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (gend != null ? gend.hashCode() : 0);
        return result;
    }
}
