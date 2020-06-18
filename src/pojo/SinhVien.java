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
public class SinhVien {
    private String mssv;
    private String hoTen;
    private String gioiTinh;
    private String cmnd;
    private String maLop;
    private String password;
    private Collection<LopHoc> lopHoc;
    private Lop lop;

    @Id
    @Column(name = "mssv")
    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    @Basic
    @Column(name = "hoTen")
    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @Basic
    @Column(name = "gioiTinh")
    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Basic
    @Column(name = "cmnd")
    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
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
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinhVien sinhVien = (SinhVien) o;
        return Objects.equals(mssv, sinhVien.mssv) &&
                Objects.equals(hoTen, sinhVien.hoTen) &&
                Objects.equals(gioiTinh, sinhVien.gioiTinh) &&
                Objects.equals(cmnd, sinhVien.cmnd) &&
                Objects.equals(maLop, sinhVien.maLop) &&
                Objects.equals(password, sinhVien.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mssv, hoTen, gioiTinh, cmnd, maLop, password);
    }

    @OneToMany(mappedBy = "sinhVien")
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
}
