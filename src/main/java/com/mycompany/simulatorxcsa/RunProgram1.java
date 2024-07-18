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
 * This class has 2 methods 
 * One will read the program1.txt file and load its contents to the memory
 * Second which will read the input from the Simulator and save the Values to memory
 */
public class RunProgram1 {
    
        
    public int[] readfile() throws FileNotFoundException, IOException
    {
        File file = new File("program1.txt");
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
    
    public void readKeyboard(UI ui)
    {
        String input=ui.getTextField(ui.keyboard);
        String [] numbers_s=input.split(",");
        int [] numbers=new int[22];
        int i;
        for(i=1;i<22;i++)
        {
            numbers[i]=Integer.parseInt(numbers_s[i-1]);
            ui.memory[i]=numbers[i];
        }
    }
    
}
