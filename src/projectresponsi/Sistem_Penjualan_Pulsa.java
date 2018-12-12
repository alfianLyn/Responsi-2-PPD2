/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectresponsi;

import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class Sistem_Penjualan_Pulsa {

    public static String sql;
    private static ResultSet rs;
    String name;
    String no_hp;
    String mail;
    int value;
    int price;
    String stats;
    static ArrayList<Sistem_Penjualan_Pulsa> data = new ArrayList<Sistem_Penjualan_Pulsa>();

    public Sistem_Penjualan_Pulsa(String nm, String no, String em, int nml, int prc, String sts) {
        this.name = nm;
        this.no_hp = no;
        this.mail = em;
        this.value = nml;
        this.price = prc;
        this.stats = sts;
    }

    public static void main(String[] args) {
        UserAuth();

    }

    public static void AdminAct() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/responsippd2", "root", "");
            Statement state = (Statement) conn.createStatement();
            String table = null;

            Scanner input = new Scanner(System.in);
            int bayar = 0;
            String status = null;

            System.out.println("Sistem Penjualan Pulsa");
            for (;;) {
                System.out.println("Choose your choice: \n"
                        + "1. Insert Data\n"
                        + "2. Pembayaran\n"
                        + "3. Log Penjualan\n"
                        + "4. Exit");
                int select = new Scanner(System.in).nextInt();
                if (select >= 4) {
                    break;
                }
                switch (select) {
                    case 1:
                        System.out.println("\n======================");
                        System.out.print("Banyak Data Pelanggan : ");
                        int jumlah = input.nextInt();
                        System.out.println("");
                        if (jumlah <= 0) {
                            System.out.println("Tidak Boleh Kosong");
                        } else {
                            for (int i = 0; i < jumlah; i++) {
                                System.out.println("Masukkan Data Pelanggan Ke-" + (i + 1));
                                System.out.print("Ketikan Nama     : ");
                                String nama = input.next();
                                System.out.print("Ketikan Nomor    : ");
                                String nomor = input.next();
                                System.out.print("Ketikan E-Mail   : ");
                                String email = input.next();
                                while (true) {
                                    System.out.println("====Menu Nominal====");
                                    System.out.println(" 5   = Rp.   5000,- ");
                                    System.out.println(" 10  = Rp.  10000,- ");
                                    System.out.println(" 20  = Rp.  20000,- ");
                                    System.out.println(" 50  = Rp.  50000,- ");
                                    System.out.println(" 100 = Rp. 100000,- ");
                                    System.out.print("Masukkan Nominal : ");
                                    int nominal = input.nextInt();
                                    if (nominal == 5) {
                                        System.out.print("Terbayar : ");
                                        bayar = input.nextInt();
                                        if (bayar >= 5000) {
                                            status = "Lunas";
                                        } else {
                                            status = "Hutang";
                                        }
                                        data.add(new Sistem_Penjualan_Pulsa(nama, nomor, email, nominal, bayar, status));
                                        break;
                                    } else if (nominal == 10) {
                                        System.out.print("Terbayar : ");
                                        bayar = input.nextInt();
                                        if (bayar >= 10000) {
                                            status = "Lunas";
                                        } else {
                                            status = "Hutang";
                                        }
                                        data.add(new Sistem_Penjualan_Pulsa(nama, nomor, email, nominal, bayar, status));
                                        break;
                                    } else if (nominal == 20) {
                                        System.out.print("Terbayar : ");
                                        bayar = input.nextInt();
                                        if (bayar >= 20000) {
                                            status = "Lunas";
                                        } else {
                                            status = "Hutang";
                                        }
                                        data.add(new Sistem_Penjualan_Pulsa(nama, nomor, email, nominal, bayar, status));
                                        break;
                                    } else if (nominal == 50) {
                                        System.out.print("Terbayar : ");
                                        bayar = input.nextInt();
                                        if (bayar >= 50000) {
                                            status = "Lunas";
                                        } else {
                                            status = "Hutang";
                                        }
                                        data.add(new Sistem_Penjualan_Pulsa(nama, nomor, email, nominal, bayar, status));
                                        break;
                                    } else if (nominal == 100) {
                                        System.out.print("Terbayar : ");
                                        bayar = input.nextInt();
                                        if (bayar >= 100000) {
                                            status = "Lunas";
                                        } else {
                                            status = "Hutang";
                                        }
                                        data.add(new Sistem_Penjualan_Pulsa(nama, nomor, email, nominal, bayar, status));
                                        break;
                                    } else {
                                        System.out.println("Nominal Tidak Tersedia");
                                    }
                                    System.out.println("");
                                }
                            }

                            for (int i = 0; i < data.size(); i++) {
                                System.out.println(
                                        "Daftar Pelanggan Ke-" + (i + 1) + "\n"
                                        + "Nama   : " + data.get(i).name + "\n"
                                        + "Nomor  : " + data.get(i).no_hp + "\n"
                                        + "E-Mail : " + data.get(i).mail + "\n"
                                        + "Nominal: " + data.get(i).value + "\n"
                                        + "Bayar  : " + data.get(i).price + "\n"
                                        + "Status : " + data.get(i).stats + "\n"
                                );
                                table = "INSERT INTO nota(Nama, Email, Notlp, Nominal, Pembayaran, Status)VALUES('" + data.get(i).name + "','" + data.get(i).mail + "','" + data.get(i).no_hp + "','" + data.get(i).value + "','" + data.get(i).price + "','" + data.get(i).stats + "')";
                                state.executeUpdate(table);
                            }
                            data.clear();
                        }
                        break;
                    case 2:
                        boolean validation = false;
                        ArrayList<Integer> checker = new ArrayList<>();
                        sql = "SELECT id, nama, email, notlp, nominal, pembayaran FROM nota WHERE status='Hutang' order by id asc ";
                        rs = state.executeQuery(sql);
                        System.out.println(" " + "Nama" + "\t" + "Email" + "\t" + "No Telp" + "\t" + "Nominal");
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String nm = rs.getString("nama");
                            String em = rs.getString("email");
                            String tlp = rs.getString("notlp");
                            int val = rs.getInt("nominal");
                            int byr = rs.getInt("pembayaran");
                            System.out.println(id + "." + nm + "\t" + em + "\t" + tlp + "\t" + val);
                            checker.add(id);
                        }
                        if (checker.size() < 1) {
                            System.out.println("Data Kosong, Silahkan isi data terlebih dahulu.");
                            break;
                        } else {
                            System.out.println("Pilih data");
                            int choose = new Scanner(System.in).nextInt();
                            for (int i = 0; i < checker.size(); i++) {
                                if (choose != checker.get(i)) {
                                    validation = true;
                                }
                            }
                            if (validation == true) {
                                System.out.println("Data yang anda Pilih tidak valid...");
                                break;
                            }
                            System.out.println("Masukkan Nominal Pembayaran");
                            bayar = input.nextInt();
                            sql = "UPDATE nota SET pembayaran=" + bayar + ", status=\"Lunas\" WHERE id=" + choose;
                            state.executeUpdate(sql);
                            System.out.println("Data berhasil diubah...");
                        }
                        break;
                    case 3:
                        ArrayList<Integer> checker2 = new ArrayList<>();
                        sql = "SELECT id, nama, email, notlp, nominal, pembayaran, status FROM nota order by id asc ";
                        rs = state.executeQuery(sql);
                        System.out.println(" " + "Nama" + "\t" + "Email" + "\t" + "No Telp" + "\t" + "Nominal" + "\t" + "Status");
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String nm = rs.getString("nama");
                            String em = rs.getString("email");
                            String tlp = rs.getString("notlp");
                            int val = rs.getInt("nominal");
                            int byr = rs.getInt("pembayaran");
                            String sts = rs.getString("status");
                            System.out.println(id + "." + nm + "\t" + em + "\t" + tlp + "\t" + val + "\t" + sts);
                            checker2.add(id);
                        }
                        if (checker2.size() < 1) {
                            System.out.println("Data Kosong, Silahkan isi data terlebih dahulu.");
                            break;
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void OwnerAct() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/responsippd2", "root", "");
            Statement state = (Statement) conn.createStatement();
            System.out.println("Sistem Penjualan Pulsa");
            for (;;) {
                System.out.println("Choose your choice: \n"
                        + "1. Log Penjualan\n"
                        + "2. Hapus Data\n"
                        + "3. Exit");
                int select = new Scanner(System.in).nextInt();
                if (select >= 3) {
                    break;
                }
                switch (select) {
                    case 1:
                        ArrayList<Integer> checker2 = new ArrayList<>();
                        sql = "SELECT id, nama, email, notlp, nominal, pembayaran, status FROM nota order by id asc ";
                        rs = state.executeQuery(sql);
                        System.out.println(" " + "Nama" + "\t" + "Email" + "\t" + "No Telp" + "\t" + "Nominal" + "\t" + "Status");
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String nm = rs.getString("nama");
                            String em = rs.getString("email");
                            String tlp = rs.getString("notlp");
                            int val = rs.getInt("nominal");
                            int byr = rs.getInt("pembayaran");
                            String sts = rs.getString("status");
                            System.out.println(id + "." + nm + "\t" + em + "\t" + tlp + "\t" + val + "\t" + sts);
                            checker2.add(id);
                        }
                        if (checker2.size() < 1) {
                            System.out.println("Data Kosong, Silahkan isi data terlebih dahulu.");
                            break;
                        }
                        break;
                    case 2:
                        boolean valid = false;
                        ArrayList<Integer> checker1 = new ArrayList<>();
                        sql = "SELECT id, nama, email, notlp, nominal, pembayaran, status FROM nota order by id asc ";
                        rs = state.executeQuery(sql);
                        System.out.println(" " + "Nama" + "\t" + "Email" + "\t" + "No Telp" + "\t" + "Nominal" + "\t" + "Status");
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String nm = rs.getString("nama");
                            String em = rs.getString("email");
                            String tlp = rs.getString("notlp");
                            int val = rs.getInt("nominal");
                            int byr = rs.getInt("pembayaran");
                            String sts = rs.getString("status");
                            System.out.println(id + "." + nm + "\t" + em + "\t" + tlp + "\t" + val + "\t" + sts);
                            checker1.add(id);
                        }
                        if (checker1.size() < 1) {
                            System.out.println("Data Kosong, Silahkan isi data terlebih dahulu.");
                            break;
                        } else {
                            System.out.println("Pilih data:");
                            int choose = new Scanner(System.in).nextInt();
                            for (int i = 0; i < checker1.size(); i++) {
                                if (choose != checker1.get(i)) {
                                    valid = true;
                                } else {
                                    valid = false;
                                }
                            }
                            if (valid == true) {
                                System.out.println("Data yang anda Pilih tidak valid...");
                                break;
                            }
                            System.out.println("Yakin ingin menghapus data tersebut? Y/N");
                            String chooseAlpha = new Scanner(System.in).next();
                            if (chooseAlpha.equalsIgnoreCase("Y")) {
                                sql = "DELETE FROM nota WHERE id=" + choose;
                                state.executeUpdate(sql);
                                System.out.println("Data telah dihapus...");
                            } else {
                                System.out.println("Data tidak jadi dihapus...");
                            }
                        }
                        break;

                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void UserAuth() {
        try {
            boolean valid = false;
            String stat = null, nama = null;
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/responsippd2", "root", "");
            Statement state = (Statement) conn.createStatement();

            System.out.print("Input Username: ");
            String user = new Scanner(System.in).next();
            System.out.print("Input Password: ");
            String password = new Scanner(System.in).next();
            sql = "SELECT idUser, nama, password, status FROM user order by idUser";
            rs = state.executeQuery(sql);
            while (rs.next()) {
                String usr = rs.getString("idUser");
                String pass = rs.getString("password");

                if (usr.equalsIgnoreCase(user) && pass.equalsIgnoreCase(password)) {
                    stat = rs.getString("status");
                    nama = rs.getString("nama");
                    valid = true;
                }
            }
            if (valid == true) {
                System.out.println("Login Berhasil.");
                if (stat.equalsIgnoreCase("admin")) {
                    System.out.println(nama + " Login sebagai Admin");
                    AdminAct();
                } else if (stat.equalsIgnoreCase("owner")) {
                    System.out.println(nama + " Login sebagai Owner");
                    OwnerAct();
                }
            } else {
                System.out.println("Login Gagal.");
                UserAuth();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
