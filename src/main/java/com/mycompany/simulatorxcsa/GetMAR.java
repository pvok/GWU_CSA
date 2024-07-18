/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simulatorxcsa;

/**
 *
 * @author sagar
 * This class has one method, it will retrieve the values of MAR
 */
public class GetMAR {
    Int2BinaryString I2BS=new Int2BinaryString();
    public String GetMARValue(int n[],String MAR)
    {
        int i=Integer.parseInt(MAR, 2);
        int val=n[i];
        String bin=I2BS.Int2BinaryString(val);
        return bin;
        
    }
}
