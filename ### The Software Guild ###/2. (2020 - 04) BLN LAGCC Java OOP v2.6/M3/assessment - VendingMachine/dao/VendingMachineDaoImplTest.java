package dao;

public class VendingMachineDaoImplTest {
    @Override
public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.firstName);
    hash = 89 * hash + Objects.hashCode(this.lastName);
    hash = 89 * hash + Objects.hashCode(this.studentId);
    hash = 89 * hash + Objects.hashCode(this.cohort);
    return hash;
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
    final Student other = (Student) obj;
    if (!Objects.equals(this.firstName, other.firstName)) {
        return false;
    }
    if (!Objects.equals(this.lastName, other.lastName)) {
        return false;
    }
    if (!Objects.equals(this.studentId, other.studentId)) {
        return false;
    }
    if (!Objects.equals(this.cohort, other.cohort)) {
        return false;
    }
    return true;
}
}