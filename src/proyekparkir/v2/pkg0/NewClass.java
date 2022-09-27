/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewClass;

import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author 1121054_JosephGeraldo
 */
public class NewClass {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Character> huruf = new ArrayList<Character>();  
    static char[] ka1 = {'z', 'q', 'x', 'j', 'k'};
    static char[] ka2 = {'v', 'b', 'p', 'y', 'g'};
    static char[] ka3 = {'f','w','m','r','c'};
    static char[] ka4 = {'l','d','u','h','s'};
    static char[] ka5 = {'n','i','o','a','t','e'};
    static int cekNilai (){
        char kate, cek;
        int nilai = 0;
        for (int i = 0; i < ka1.length; i++) {
            for (int j = 0; j < huruf.size(); j++) {
                kate = ka1[i];
                cek = huruf.get(j);
                if (kate == cek){
                    nilai += 40;
                }
            }
        }
        for (int i = 0; i < ka2.length; i++) {
            for (int j = 0; j < huruf.size(); j++) {
                kate = ka2[i];
                cek = huruf.get(j);
                if (kate == cek){
                    nilai += 35;
                }
            }
        }
        for (int i = 0; i < ka3.length; i++) {
            for (int j = 0; j < huruf.size(); j++) {
                kate = ka3[i];
                cek = huruf.get(j);
                if (kate == cek){
                    nilai += 30;
                }
            }
        }
        for (int i = 0; i < ka4.length; i++) {
            for (int j = 0; j < huruf.size(); j++) {
                kate = ka4[i];
                cek = huruf.get(j);
                if (kate == cek){
                    nilai += 20;
                }
            }
        }
        for (int i = 0; i < ka5.length; i++) {
            for (int j = 0; j < huruf.size(); j++) {
                kate = ka5[i];
                cek = huruf.get(j);
                if (kate == cek){
                    nilai += 10;
                }
            }
        }
        return nilai;
    }
    
    static String status (int nilai){
        String status = null;
        if (nilai <= 40){
            status = "Hanya angan - angan";
        } else if (nilai >= 41 && nilai <= 60){
            status = "Tak ada yang tak mungkin";
        } else if (nilai >= 61 && nilai <= 80){
            status = "Agak bisa diperjuangkan";
        } else {
            status = "Inilah sang ayank";
        }
        return status;
    }
    public static void main(String[] args) {
        String _kata1, _kata2;
        int count = 0, nilai = 0;
        char kata1, kata2, cekKata, kate, cek;      
        _kata1 = scan.next();
        System.out.println(_kata1);
        _kata2 = scan.next();
        System.out.println(_kata2);
        for (int i = 0; i < _kata1.length(); i++) {
            for (int j = 0; j < _kata2.length(); j++) {
                kata1 = _kata1.charAt(i);
                kata2 = _kata2.charAt(j);
                kata1 = Character.toLowerCase(kata1);
                kata2 = Character.toLowerCase(kata2);
                if (kata1 == kata2){
                    for (int k = 0; k < huruf.size(); k++) {
                        cekKata = huruf.get(k);
                        if (cekKata == kata1){
                            count++;
                        }
                    }
                    if(count == 0){
                        huruf.add(kata1);
                    }
                }
            }
        }
        System.out.println(huruf);
        int x = cekNilai();
        System.out.println(x + "%");
        System.out.println(status(nilai));
    }
}
