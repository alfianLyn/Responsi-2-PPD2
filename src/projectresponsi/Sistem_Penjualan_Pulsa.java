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

    public Sistem_Penjualan_Pulsa(String nm, String no, String em) {
        this.name = nm;
        this.no_hp = no;
        this.mail = em;
    }
    
    // Global property for databases
    private static Connection connection;
    private static Statement statement;
    private static ResultSet result;

    private static Connection open_connection() {
        try {
            String url = "jdbc:mysql://localhost/responsippd2";
            String usr = "root";
            String pass = "1";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, usr, pass);

        } catch (Exception e) {
            System.err.println("koneksi gagal" + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        // Check Connection to Databases
        open_connection();

        ArrayList<Sistem_Penjualan_Pulsa> data = new ArrayList<Sistem_Penjualan_Pulsa>();
        Scanner input = new Scanner(System.in);

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
                    System.out.print("Ketikan Nama Anda   : ");
                    String nama = input.next();
                    System.out.print("Ketikan Nomor Anda  : ");
                    String nomor = input.next();
                    System.out.print("Ketikan E-Mail Anda : ");
                    String email = input.next();
                    System.out.println("");
                    data.add(new Sistem_Penjualan_Pulsa(nama, nomor, email));
                }

                for (int i = 0; i < data.size(); i++) {
                    System.out.println(
                            "Daftar Pelanggan Ke-" + (i + 1) + "\n"
                            + "Nama   : " + data.get(i).name + "\n"
                            + "Nomor  : " + data.get(i).no_hp + "\n"
                            + "E-Mail : " + data.get(i).mail + "\n"
                    );
                }
                break;
            }
        }
    }
}
