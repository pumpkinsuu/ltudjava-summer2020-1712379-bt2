package pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

/**
 * pojo
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 20-Jun-20 - 3:34 PM
 * @Description
 */
@Entity
public class Lop {
    private String maLop;
    private List<SinhVien> sinhvien;
    private List<Tkb> tkb;

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
    public List<SinhVien> getSinhVien() {
        return sinhvien;
    }

    public void setSinhVien(List<SinhVien> sinhvien) {
        this.sinhvien = sinhvien;
    }

    @OneToMany(mappedBy = "lop")
    public List<Tkb> getTkb() {
        return tkb;
    }

    public void setTkb(List<Tkb> tkb) {
        this.tkb = tkb;
    }
}
