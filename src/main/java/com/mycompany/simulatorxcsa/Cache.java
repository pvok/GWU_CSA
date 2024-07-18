/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simulatorxcsa;

/**
 *
 * @author sagar
 * Below Class has one method to implement the Cache for the simulator.
 * it takes the address and memory as input and shows it in FIFO manner on the UI. It will show upto mad 16 lines.
 */
public class Cache {
    public void PrintCache(UI ui, String PC, String inst_s)
    {
        String cache;
        int i;
        cache=PC+"  "+inst_s;
        if(ui.csh_count<15)
        {    
            ui.setTextArea(ui.cache, "");
            ui.csh[ui.csh_count]=cache;
            for(i=ui.csh_count;i>=0;i--)
            {
                ui.appendTextArea(ui.cache, ui.csh[i]);
                ui.appendTextArea(ui.cache, "\n");
            }
            ui.csh_count++;
        }
        else if(ui.csh_count==15)
        {
            ui.csh[0]="";
            for(i=0;i<15;i++)
            {
                ui.csh[i]=ui.csh[i+1];
            }
            ui.setTextArea(ui.cache, "");
            ui.csh[ui.csh_count]=cache;
            for(i=ui.csh_count;i>=0;i--)
            {
                ui.appendTextArea(ui.cache, ui.csh[i]);
                ui.appendTextArea(ui.cache, "\n");
            }
            
        }
    }
}
