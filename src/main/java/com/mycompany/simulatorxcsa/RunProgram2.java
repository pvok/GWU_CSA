/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simulatorxcsa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author sagar
 */
public class RunProgram2 {
    public int[] readfile() throws FileNotFoundException, IOException
    {
        File file = new File("Program2.txt");
        BufferedReader br= new BufferedReader(new FileReader(file));
        String st;
/*     Defining The memeory block will will contain all the values once read from the file.  */
        int [] memory=new int[4095];
        int ads, val;
        /*initializing all values of memory to 0*/
        Arrays.fill(memory, 0);
        
        while ((st = br.readLine()) != null)
        {
            String[] split=st.split(" ");
            ads = Integer.parseInt(split[0], 16);
            val = Integer.parseInt(split[1], 16);
            
            memory[ads]=val;            
        }
        
        
        
        return memory;
    }
    public void readsSntences(UI ui) throws FileNotFoundException, IOException
    {
        File file = new File("sentence.txt");
        BufferedReader br= new BufferedReader(new FileReader(file));
        String st;
/*     Defining The memeory block will will contain all the values once read from the file.  */
        int [] memory=ui.memory;   
        int ads=1000, val;
        /*initializing all values of memory to 0*/
        val=br.read();
        
        while (val!=-1)
        {
            memory[ads]=val;
//            System.out.println(val);
            ads++;
            val=br.read();
        }
        ui.memory=memory;
    }
}
