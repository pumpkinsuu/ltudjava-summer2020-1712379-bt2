package util;

import dbUtil.*;
import pojo.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * util
 *
 * @Created by pumpk - StudentID : 1712379
 * @Date 18-Jun-20 - 4:34 PM
 * @Description
 */
public class ImportCsv {
    public static boolean importSv(File file)  {
        Lop lop = ImportCsv.getLop(file);
        if (lop == null) return false;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file.getCanonicalFile()), StandardCharsets.UTF_8))) {

            String line;
            SinhVien sv = new SinhVien();

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) continue;

                sv.setMssv(data[1]);
                sv.setHoTen(data[2]);
                sv.setGioiTinh(data[3]);
                sv.setCmnd(data[4]);
                sv.setMaLop(lop.getMaLop());
                sv.setPassword(data[1]);

                SinhVienDAO.add(sv);
            }
        } catch (IOException e) {
            System.err.println("ImportSv: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean importTkb(File file) {
        Lop lop = ImportCsv.getLop(file);
        if (lop == null) return false;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file.getCanonicalFile()), StandardCharsets.UTF_8))) {

            String line;
            Mon mon = new Mon();
            Tkb tkb = new Tkb();
            LopHoc lopHoc = new LopHoc();
            List<SinhVien> sinhVienList = SinhVienDAO.getList();

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 4) continue;

                mon.setMaMon(data[1]);
                mon.setTenMon(data[2]);
                mon.setPhong(data[3]);

                tkb.setMaTkb(lop.getMaLop() + '-' + data[1]);
                tkb.setMaLop(lop.getMaLop());
                tkb.setMaMon(data[1]);

                MonDAO.add(mon);

                if (!TkbDAO.add(tkb))
                    continue;

                for (SinhVien sv : sinhVienList) {
                    if (sv.getMaLop().equals(lop.getMaLop())) {
                        lopHoc.setMaLopHoc(tkb.getMaMon() + '-' + sv.getMssv());
                        lopHoc.setMaTkb(tkb.getMaTkb());
                        lopHoc.setMssv(sv.getMssv());

                        LopHocDAO.add(lopHoc);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("ImportTkb: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean importLopHoc(File file) {
        String fileName = getBaseName(file.getName());
        Tkb tkb = TkbDAO.get(fileName);
        if (tkb == null) return false;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file.getCanonicalFile()), StandardCharsets.UTF_8))) {

            String line;
            String maMon = tkb.getMaMon();
            String maTkb = tkb.getMaTkb();
            LopHoc lopHoc = new LopHoc();

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 2) continue;

                if (SinhVienDAO.get(data[1]) != null) {
                    lopHoc.setMaLopHoc(maMon + '-' + data[1]);
                    lopHoc.setMaTkb(maTkb);
                    lopHoc.setMssv(data[1]);

                    LopHocDAO.add(lopHoc);
                }
            }
        } catch (IOException e) {
            System.err.println("ImportLopHoc: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean importDiem(File file) {
        String fileName = getBaseName(file.getName());
        Tkb tkb = TkbDAO.get(fileName);
        if (tkb == null) return false;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(file.getCanonicalFile()), StandardCharsets.UTF_8))) {

            String line;
            Diem diem = new Diem();

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 7) continue;

                String maLopHoc = tkb.getMaMon() + '-' + data[1];

                diem.setMaLopHoc(maLopHoc);
                diem.setDiemGk(Double.parseDouble(data[3]));
                diem.setDiemCk(Double.parseDouble(data[4]));
                diem.setDiemKhac(Double.parseDouble(data[5]));
                diem.setDiemTong(Double.parseDouble(data[6]));

                DiemDAO.add(diem);
            }
        } catch (IOException e) {
            System.err.println("ImportDiem: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static String getBaseName(String fileName) {
        if (fileName != null) {
            int pos = fileName.lastIndexOf('.');
            if (pos != -1)
                return fileName.substring(0, pos);
        }
        return fileName;
    }

    private static Lop getLop(File file) {
        String fileName = getBaseName(file.getName());
        Lop lop = new Lop();
        lop.setMaLop(fileName);

        LopDAO.add(lop);

        return LopDAO.get(fileName);
    }
}
