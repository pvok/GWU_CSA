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
 * This class has one method that will read the .txt file and load its contents to the memory
 */
public class Init{
    
    public int[] readfile() throws FileNotFoundException, IOException{
        File file = new File("inst.txt");
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
}
