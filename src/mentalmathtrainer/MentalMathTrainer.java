/*
 * The MIT License
 *
 * Copyright 2017 vaishantkameswaran.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mentalmathtrainer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author vaishantkameswaran
 */
public class MentalMathTrainer implements Runnable {

    public static Loading loading = new Loading();
    public static Home home;
    public static Addition addition;
    public static Subtraction subtraction;
    public static Multiplication multiplication;
    public static Division division;
    public static Squaring squaring;
    public static Rooting rooting;
    public static Settings settings;
    public static Credits credits;

    public static HashMap<String, String> rec = new HashMap<>();

    public static int rSA;
    public static int rSS;
    public static int rSM;
    public static int rSD;
    public static int rSQ;
    public static int rSR;

    static BufferedWriter bw;
    static BufferedReader br;
    static String data;
    static String[] records;

    @Override
    public void run() {

        home = new Home();
        addition = new Addition();
        subtraction = new Subtraction();
        multiplication = new Multiplication();
        division = new Division();
        squaring = new Squaring();
        rooting = new Rooting();
        settings = new Settings();
        credits = new Credits();
        loading.setVisible(false);
        home.setVisible(true);
    }

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        loading.setVisible(true);
        Thread loadingForms = (new Thread(new MentalMathTrainer()));
        loadingForms.start();

        br = new BufferedReader(new FileReader(new File("src/data.txt")));
        
        data = br.readLine();
        System.out.println(data);
        records = data.split(":")[2].split(";");

        bw = new BufferedWriter(new FileWriter(new File("src/data.txt")));

        rSA = Integer.parseInt(records[0].split("=")[1]);
        rSS = Integer.parseInt(records[1].split("=")[1]);
        rSM = Integer.parseInt(records[2].split("=")[1]);
        rSD = Integer.parseInt(records[3].split("=")[1]);
        rSQ = Integer.parseInt(records[4].split("=")[1]);
        rSR = Integer.parseInt(records[5].split("=")[1]);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                
                // Save records to file
                String save = ":Records:A=" + rSA + ";S=" + rSS + ";M=" + rSM + ";D=" + rSD + ";Q=" + rSQ + ";R=" + rSR + ";:Settings:Key=Value;:";
                bw.write(save, 0, save.length());
                
                // Finishing Up
                //br.close();
                bw.close();
                
            } catch (Exception e) {
            }
        }));

    }

}
