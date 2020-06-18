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
 * @Date 17-Jun-20 - 10:52 PM
 * @Description
 */
@Entity
public class Lop {
    private String maLop;
    private Collection<LopHoc> lopHocs;
    private Collection<SinhVien> sinhViens;

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
    public Collection<LopHoc> getLopHocs() {
        return lopHocs;
    }

    public void setLopHocs(Collection<LopHoc> lopHocs) {
        this.lopHocs = lopHocs;
    }

    @OneToMany(mappedBy = "lop")
    public Collection<SinhVien> getSinhViens() {
        return sinhViens;
    }

    public void setSinhViens(Collection<SinhVien> sinhViens) {
        this.sinhViens = sinhViens;
    }
}
