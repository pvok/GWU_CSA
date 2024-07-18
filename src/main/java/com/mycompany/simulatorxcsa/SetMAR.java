/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simulatorxcsa;

/**
 *This Method will take the Memory n[], Value of MAR and Value of MBR and set the Value of MBR in the Memory at address MAR
 * @author sagar
 */
public class SetMAR {
    public int[] SetMAR(UI ui,int n[], String MAR, String MBR)
    {
        int ads=Integer.parseInt(MAR, 2);
        int val=Integer.parseInt(MBR, 2);
        if(ads>=0&&ads<6)
        {
           ui.setTextField(ui.MFR, "0001");
        }
        else
        {
            n[ads]=val;
        }
        return n;    
    }
   
}
