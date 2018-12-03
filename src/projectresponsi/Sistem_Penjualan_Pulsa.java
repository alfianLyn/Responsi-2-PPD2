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

    String name;
    String no_hp;
    String mail;
    int value;
    int price;
    String stats;

    public Sistem_Penjualan_Pulsa(String nm, String no, String em, int nml, int prc, String sts) {
        this.name = nm;
        this.no_hp = no;
        this.mail = em;
        this.value = nml;
        this.price = prc;
        this.stats = sts;
    }

    public static void main(String[] args) {
        // Check Connection to Databases
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/responsippd2", "root", "");
            Statement state = (Statement) conn.createStatement();
            String table = null;

            ArrayList<Sistem_Penjualan_Pulsa> data = new ArrayList<Sistem_Penjualan_Pulsa>();
            Scanner input = new Scanner(System.in);
            int bayar = 0;
            String status = null;

            System.out.println("Sistem Penjualan Pulsa");
            while (true) {
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
                        } else if (nominal == 10) {
                            System.out.print("Terbayar : ");
                            bayar = input.nextInt();
                            if (bayar >= 10000) {
                                status = "Lunas";
                            } else {
                                status = "Hutang";
                            }
                        } else if (nominal == 20) {
                            System.out.print("Terbayar : ");
                            bayar = input.nextInt();
                            if (bayar >= 20000) {
                                status = "Lunas";
                            } else {
                                status = "Hutang";
                            }
                        } else if (nominal == 50) {
                            System.out.print("Terbayar : ");
                            bayar = input.nextInt();
                            if (bayar >= 50000) {
                                status = "Lunas";
                            } else {
                                status = "Hutang";
                            }
                        } else if (nominal == 100) {
                            System.out.print("Terbayar : ");
                            bayar = input.nextInt();
                            if (bayar >= 100000) {
                                status = "Lunas";
                            } else {
                                status = "Hutang";
                            }
                        } else {
                            System.out.println("Nominal Tidak Tersedia");
                        }
                        System.out.println("");
                        data.add(new Sistem_Penjualan_Pulsa(nama, nomor, email, nominal, bayar, status));
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
                        table = "INSERT INTO nota(Nama, Nominal, Pembayaran, Status)VALUES('" + data.get(i).name + "','" + data.get(i).value + "','" + data.get(i).price + "','" + data.get(i).stats + "')";
                        state.executeUpdate(table);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Data Berhasil Disimpan");
        }
    }
}
