package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

/**
 * pojo
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 7:31 PM
 * @Description
 */
@Entity
public class Lop {
    private String maLop;
    private Collection<SinhVien> sinhVien;
    private Collection<Tkb> tkb;

    @Id
    @Column(name = "maLop")
    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lop lop = (Lop) o;
        return Objects.equals(maLop, lop.maLop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLop);
    }

    @OneToMany(mappedBy = "lop")
    public Collection<SinhVien> getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(Collection<SinhVien> sinhVien) {
        this.sinhVien = sinhVien;
    }

    @OneToMany(mappedBy = "lop")
    public Collection<Tkb> getTkb() {
        return tkb;
    }

    public void setTkb(Collection<Tkb> tkb) {
        this.tkb = tkb;
    }
}
