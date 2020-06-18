package pojo;

import javax.persistence.*;
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
public class Tkb {
    private String maTkb;
    private String maLop;
    private String maMon;
    private Collection<LopHoc> lopHoc;
    private Lop lop;
    private Mon mon;

    @Id
    @Column(name = "maTkb")
    public String getMaTkb() {
        return maTkb;
    }

    public void setMaTkb(String maTkb) {
        this.maTkb = maTkb;
    }

    @Basic
    @Column(name = "maLop")
    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    @Basic
    @Column(name = "maMon")
    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tkb tkb = (Tkb) o;
        return Objects.equals(maTkb, tkb.maTkb) &&
                Objects.equals(maLop, tkb.maLop) &&
                Objects.equals(maMon, tkb.maMon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maTkb, maLop, maMon);
    }

    @OneToMany(mappedBy = "tkb")
    public Collection<LopHoc> getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(Collection<LopHoc> lopHoc) {
        this.lopHoc = lopHoc;
    }

    @ManyToOne
    @JoinColumn(name = "maLop", referencedColumnName = "maLop", nullable = false, insertable = false, updatable = false)
    public Lop getLop() {
        return lop;
    }

    public void setLop(Lop lop) {
        this.lop = lop;
    }

    @ManyToOne
    @JoinColumn(name = "maMon", referencedColumnName = "maMon", nullable = false, insertable = false, updatable = false)
    public Mon getMon() {
        return mon;
    }

    public void setMon(Mon mon) {
        this.mon = mon;
    }
}
