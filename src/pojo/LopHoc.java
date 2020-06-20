package pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * pojo
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 20-Jun-20 - 3:35 PM
 * @Description
 */
@Entity
public class LopHoc {
    private String maLopHoc;
    private String mssv;
    private String maTkb;
    private Diem diem;
    private SinhVien sinhVien;
    private Tkb tkb;

    @Id
    @Column(name = "maLopHoc")
    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
    }

    @Basic
    @Column(name = "mssv")
    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    @Basic
    @Column(name = "maTkb")
    public String getMaTkb() {
        return maTkb;
    }

    public void setMaTkb(String maTkb) {
        this.maTkb = maTkb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LopHoc lopHoc = (LopHoc) o;
        return Objects.equals(maLopHoc, lopHoc.maLopHoc) &&
                Objects.equals(mssv, lopHoc.mssv) &&
                Objects.equals(maTkb, lopHoc.maTkb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLopHoc, mssv, maTkb);
    }

    @OneToOne(mappedBy = "lopHoc")
    public Diem getDiem() {
        return diem;
    }

    public void setDiem(Diem diem) {
        this.diem = diem;
    }

    @ManyToOne
    @JoinColumn(name = "mssv", referencedColumnName = "mssv", nullable = false, insertable = false, updatable = false)
    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    @ManyToOne
    @JoinColumn(name = "maTkb", referencedColumnName = "maTkb", nullable = false, insertable = false, updatable = false)
    public Tkb getTkb() {
        return tkb;
    }

    public void setTkb(Tkb tkb) {
        this.tkb = tkb;
    }
}
