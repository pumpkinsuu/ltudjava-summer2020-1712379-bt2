package pojo;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * pojo
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 17-Jun-20 - 7:14 PM
 * @Description
 */
@Entity
public class Mon {
    private String maMon;
    private String tenMon;
    private String phong;
    private Collection<LopHoc> lopHocs;

    @Id
    @Column(name = "maMon")
    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    @Basic
    @Column(name = "tenMon")
    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    @Basic
    @Column(name = "phong")
    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mon mon = (Mon) o;
        return Objects.equals(maMon, mon.maMon) &&
                Objects.equals(tenMon, mon.tenMon) &&
                Objects.equals(phong, mon.phong);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maMon, tenMon, phong);
    }

    @OneToMany(mappedBy = "mon")
    public Collection<LopHoc> getLopHocs() {
        return lopHocs;
    }

    public void setLopHocs(Collection<LopHoc> lopHocs) {
        this.lopHocs = lopHocs;
    }
}
