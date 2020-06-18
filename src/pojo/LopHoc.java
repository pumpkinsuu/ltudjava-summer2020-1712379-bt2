package pojo;

import javax.persistence.*;
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
public class LopHoc {
    private String maLopHoc;
    private String maLop;
    private String maMon;
    private String mssv;
    private Collection<Diem> diems;
    private Lop lop;
    private Mon mon;
    private SinhVien sinhvienByMssv;

    @Id
    @Column(name = "maLopHoc")
    public String getMaLopHoc() {
        return maLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        this.maLopHoc = maLopHoc;
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

    @Basic
    @Column(name = "MSSV")
    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LopHoc lopHoc = (LopHoc) o;
        return Objects.equals(maLopHoc, lopHoc.maLopHoc) &&
                Objects.equals(maLop, lopHoc.maLop) &&
                Objects.equals(maMon, lopHoc.maMon) &&
                Objects.equals(mssv, lopHoc.mssv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maLopHoc, maLop, maMon, mssv);
    }

    @OneToMany(mappedBy = "lopHoc")
    public Collection<Diem> getDiems() {
        return diems;
    }

    public void setDiems(Collection<Diem> diems) {
        this.diems = diems;
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

    @ManyToOne
    @JoinColumn(name = "MSSV", referencedColumnName = "MSSV", nullable = false, insertable = false, updatable = false)
    public SinhVien getSinhvienByMssv() {
        return sinhvienByMssv;
    }

    public void setSinhvienByMssv(SinhVien sinhvienByMssv) {
        this.sinhvienByMssv = sinhvienByMssv;
    }
}
