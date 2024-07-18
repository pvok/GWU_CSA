/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simulatorxcsa;

/**
 *
 * @author sagar
 * This class has one method, it will read an integer value and convert it into a binary string of length 16 bits
 */
public class Int2BinaryString 
{
    public String Int2BinaryString(int n)
    {
        String bin = Integer.toBinaryString(n);
        int len=bin.length();
        if (len<=16)
        {
            while(len!=16)
            {
                bin="0"+bin;
                bin.trim();
                len++;
            }
        }
        else if( len<=32)
        {
            while(len!=32)
            {
                bin="0"+bin;
                bin.trim();
                len++;
            }
        }
        return bin;
    }    
}
