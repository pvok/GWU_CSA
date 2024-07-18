/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simulatorxcsa;

/**
 *
 * @author sagar
 * This class has Multiple Methods
 * DecodeInst decodes the Instruction gets OP code, and returns what is to be done
 * GetEffectiveAddress calculates the EA for each Instruction
 * GetGPR Method, takes the 2 bit value as an input and returns the value of the corresponding GPR
 * SetGPR Method takes the 2 bit value as an input and a binary String and sets the respective GPR value.
 * Rest of the methods defined are repective OP Code Implementations.
 */
public class DecodeInst {

    Int2BinaryString I2BS=new Int2BinaryString(); 
    
    public String GetGPR(String R, UI ui)
    {
        String V1;
        switch(R)
            {
                case "00":
                    V1=ui.getTextField(ui.GPR0);
                    return V1;
                case "01":
                    V1=ui.getTextField(ui.GPR1);
                    return V1;
                case "10":
                    V1=ui.getTextField(ui.GPR2);
                    return V1;
                case "11":
                    V1=ui.getTextField(ui.GPR3);
                    return V1;    
            }
        return " ";
    }
    
    public void SetGPR(String R, UI ui,String Value)
    {
        
        switch(R)
            {
                case "00":
                   ui.setTextField(ui.GPR0, Value);break;
                case "01":
                   ui.setTextField(ui.GPR1, Value);break;
                case "10":
                   ui.setTextField(ui.GPR2, Value);break;
                case "11":
                   ui.setTextField(ui.GPR3, Value);break;   
            }

    }
    
    public String DoInst(int inst)
    {
        String inst_s=I2BS.Int2BinaryString(inst);
        
        String Op=inst_s.substring(0, 6);
        
        switch(Op)
        {
            case "000001":
                return "LDR";
            case "000010":
                return "STR";
            case "000011":
                return "LDA";
            case "100001":
                return "LDX";
            case "100010":
                return "STX";
            case "000000":
                return "HALT";
            case "001000":
                return "JZ";
            case "001001":
                return "JNE";    
            case "001010":
                return "JCC";  
            case "001011":
                return "JMA";
            case "001100":
                return "JSR";
            case "001101":
                return "RFS";
            case "001110":
                return "SOB";
            case "001111":
                return "JGE";
            case "000100":
                return "AMR"; 
            case "000101":
                return "SMR"; 
            case "000110":
                return "AIR"; 
            case "000111":
                return "SIR"; 
            case "010000":
                return "MLT"; 
            case "010001":
                return "DVD";
            case "010010":
                return "TRR"; 
            case "010011":
                return "AND"; 
            case "010100":
                return "ORR";
            case "010101":
                return "NOT";    
            case "011001":
                return "SRC"; 
            case "011010":
                return "RRC";
            case "110010":
                return "OUT";
            case "110001":
                return "IN";
            case "011011":
                return "FADD";
            case "011100":
                return "FSUB"; 
            case "011101":
                return "VADD"; 
            case "011110":
                return "VSUB";
            case "011111":
                return "CNVRT";
            case "101000":
                return "LDFR";
            case "101001":
                return "STFR";
            default:
                return "HALT";
                            
        }
        
    }
    
    public String GetEffectiveAddress(int n[], int inst, String IXR1, String IXR2, String IXR3)
    {
        String inst_s=I2BS.Int2BinaryString(inst);
        String R=inst_s.substring(6, 8);
        String IX=inst_s.substring(8,10);
        String I=inst_s.substring(10, 11);
        String Addr=inst_s.substring(11);
        
        int addr=Integer.parseInt(Addr, 2);
        
        int ixr1=Integer.parseInt(IXR1, 2);
        int ixr2=Integer.parseInt(IXR2, 2);
        int ixr3=Integer.parseInt(IXR3, 2);
        String S;
               
        
        if(I.equals("0"))
        {
            if(IX.equals("00"))
            {
                int c_addr=addr;
                S=I2BS.Int2BinaryString(c_addr);
            }
            else
            {
                switch(IX)
                {
                    case("01"):
                        int c_addr=n[ixr1]+addr;
                        S=I2BS.Int2BinaryString(c_addr);break;
                    case("10"):
                        c_addr=n[ixr2]+addr;
                        S=I2BS.Int2BinaryString(c_addr);break;
                    case("11"):
                        c_addr=n[ixr3]+addr;
                        S=I2BS.Int2BinaryString(c_addr);break;
                    default:
                        S="";break;
                }
            }
        }
        else
        {
            if(IX.equals("00"))
            {
                int c_addr=addr;
                int c_addr2=n[c_addr];
                S=I2BS.Int2BinaryString(c_addr2);
            }
            else
            {
                switch(IX)
                {
                    case("01"):
                        int c_addr=n[ixr1]+addr;
                        int c_addr2=n[c_addr];
                        S=I2BS.Int2BinaryString(c_addr2);break;
                    case("10"):
                        c_addr=n[ixr2]+addr;
                        c_addr2=n[c_addr];
                        S=I2BS.Int2BinaryString(c_addr2);break;
                    case("11"):
                        c_addr=n[ixr3]+addr;
                        c_addr2=n[c_addr];
                        S=I2BS.Int2BinaryString(c_addr2);break;
                    default:
                        S="";break;
                }
            }
        }
        return S;
    }
    
    public String LDR(int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        int ea=Integer.parseInt(EA, 2);
        int content=n[ea];
        String S=I2BS.Int2BinaryString(content);
        return S;
    }
    
    public String STR(int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        return EA;
    }
    
    public String LDA(int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        return EA;
    }
    
    public String LDX(int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        int ea=Integer.parseInt(EA, 2);
        int content=n[ea];
        String S=I2BS.Int2BinaryString(content);
        return S;
    }
    
    public String STX(int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        return EA;
    }
    
    public String JZ(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3, String PC)
    {   
        String EA=this.GetEffectiveAddress(ui.memory, inst, IXR1, IXR2, IXR3);
        String inst_s=I2BS.Int2BinaryString(inst);
        /*Calc the value of R*/
        String R=inst_s.substring(6, 8);
        String RegVal;
        int regval=0;
        /*Get Value of Register*/
        switch(R)
        {
            case "00":
                RegVal=ui.getTextField(ui.GPR0);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "01":
                RegVal=ui.getTextField(ui.GPR1);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "10":
                RegVal=ui.getTextField(ui.GPR2);
                regval=Integer.parseInt(RegVal, 2);
                break;                        
                
            case "11":
                RegVal=ui.getTextField(ui.GPR3);
                regval=Integer.parseInt(RegVal, 2);
                break;                       
                
            default:
                break;      
        }
        if(regval==0)
        {
            EA=EA.substring(4);
            return EA;
        }
        else
        {
            return PC;
        }
    }

    public String JNE(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3, String PC)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        String inst_s=I2BS.Int2BinaryString(inst);
        /*Calc the value of R*/
        String R=inst_s.substring(6, 8);
        String RegVal;
        int regval=0;
        /*Get Value of Register*/
        switch(R)
        {
            case "00":
                RegVal=ui.getTextField(ui.GPR0);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "01":
                RegVal=ui.getTextField(ui.GPR1);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "10":
                RegVal=ui.getTextField(ui.GPR2);
                regval=Integer.parseInt(RegVal, 2);
                break;                        
                
            case "11":
                RegVal=ui.getTextField(ui.GPR3);
                regval=Integer.parseInt(RegVal, 2);
                break;                       
                
            default:
                break;      
        }
        if(regval!=0)
        {
            EA=EA.substring(4);
            return EA;
        }
        else
        {
            return PC;
        }
    }    
    
    public String JCC(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3, String PC)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        String inst_s=I2BS.Int2BinaryString(inst);
        /*Calc the value of R*/
        String R=inst_s.substring(6, 8);
        
        if(R.equals("00")&&ui.CCR[1]==1)
        {
            EA=EA.substring(4);
            return EA;
        }
        else if(R.equals("01")&&ui.CCR[2]==1)
        {
            EA=EA.substring(4);
            return EA;
        }
        else if(R.equals("10")&&ui.CCR[3]==1)
        {
            EA=EA.substring(4);
            return EA;
        }
        else if(R.equals("11")&&ui.CCR[4]==1)
        {
            System.out.println("here");
            EA=EA.substring(4);
            return EA;
        }        
        else
        {
            return PC;    
        }
        
    }
    public String JMA(int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        EA=EA.substring(4);
        return EA;
    }

    public String JSR(int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        EA=EA.substring(4);
        return EA;
    }
    
    public String RFS(int inst)
    {   
        String inst_s=I2BS.Int2BinaryString(inst).substring(11);
        int imadd=Integer.parseInt(inst_s,2);
        String Imadd=I2BS.Int2BinaryString(imadd);
        return Imadd;
    }
    
    public String SOB(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3, String PC)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        String inst_s=I2BS.Int2BinaryString(inst);
        String R=inst_s.substring(6, 8);
        
        String RegVal;
        int regval=0;
        /*Get Value of Register*/
        switch(R)
        {
            case "00":
                RegVal=ui.getTextField(ui.GPR0);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "01":
                RegVal=ui.getTextField(ui.GPR1);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "10":
                RegVal=ui.getTextField(ui.GPR2);
                regval=Integer.parseInt(RegVal, 2);
                break;                        
                
            case "11":
                RegVal=ui.getTextField(ui.GPR3);
                regval=Integer.parseInt(RegVal, 2);
                break;                       
                
            default:
                break;      
        }
        regval--;
        
        if(regval>0)
        {
            RegVal=I2BS.Int2BinaryString(regval);
            this.SetGPR(R, ui, RegVal);
            EA=EA.substring(4);
            return EA;
        }
        else
        {
            RegVal=I2BS.Int2BinaryString(regval);
            this.SetGPR(R, ui, RegVal);
            ui.CCR[1]=1;
            return PC;    
        }
        
    }
    
    public String JGE(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3, String PC)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        String inst_s=I2BS.Int2BinaryString(inst);
        String R=inst_s.substring(6, 8);
        
        String RegVal;
        int regval=0;
        /*Get Value of Register*/
        switch(R)
        {
            case "00":
                RegVal=ui.getTextField(ui.GPR0);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "01":
                RegVal=ui.getTextField(ui.GPR1);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "10":
                RegVal=ui.getTextField(ui.GPR2);
                regval=Integer.parseInt(RegVal, 2);
                break;                        
                
            case "11":
                RegVal=ui.getTextField(ui.GPR3);
                regval=Integer.parseInt(RegVal, 2);
                break;                       
                
            default:
                break;      
        }
        
        if(regval>=0)
        {
            EA=EA.substring(4);
            System.out.println(EA);
            return EA;
        }
        else
        {
            return PC;    
        }
        
    }
    
    public String AMR(UI ui, int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        int ea=Integer.parseInt(EA, 2);
        int content=n[ea];
        
        String inst_s=I2BS.Int2BinaryString(inst);
        String R=inst_s.substring(6, 8);
        
        String RegVal;
        int regval=0;
        int amr=0;
        /*Get Value of Register*/
        switch(R)
        {
            case "00":
                RegVal=ui.getTextField(ui.GPR0);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "01":
                RegVal=ui.getTextField(ui.GPR1);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "10":
                RegVal=ui.getTextField(ui.GPR2);
                regval=Integer.parseInt(RegVal, 2);
                break;                        
                
            case "11":
                RegVal=ui.getTextField(ui.GPR3);
                regval=Integer.parseInt(RegVal, 2);
                break;                       
                
            default:
                break;      
        }
        amr=regval+content;
        /*setting overflow bit*/
        if(amr>65535)
        {
            ui.CCR[0]=1;
        }
        
        return I2BS.Int2BinaryString(amr);
    }
    
    public String SMR(UI ui, int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        int ea=Integer.parseInt(EA, 2);
        int content=n[ea];
        
        String inst_s=I2BS.Int2BinaryString(inst);
        String R=inst_s.substring(6, 8);
        
        String RegVal;
        int regval=0;
        int amr=0;
        /*Get Value of Register*/
        switch(R)
        {
            case "00":
                RegVal=ui.getTextField(ui.GPR0);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "01":
                RegVal=ui.getTextField(ui.GPR1);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "10":
                RegVal=ui.getTextField(ui.GPR2);
                regval=Integer.parseInt(RegVal, 2);
                break;                        
                
            case "11":
                RegVal=ui.getTextField(ui.GPR3);
                regval=Integer.parseInt(RegVal, 2);
                break;                       
                
            default:
                break;      
        }
        amr=regval-content;
        /*setting overflow bit*/
        if(amr<65535)
        {
            ui.CCR[1]=0;
        }
        
        return I2BS.Int2BinaryString(amr);
    }
    
    public String AIR(UI ui,int inst)
    {   
        String inst_s=I2BS.Int2BinaryString(inst);
        String IMAD=I2BS.Int2BinaryString(inst).substring(11);
        int imadd=Integer.parseInt(IMAD,2);
        String R=inst_s.substring(6, 8);
        
        String RegVal;
        int regval=0;
        /*Get Value of Register*/
        switch(R)
        {
            case "00":
                RegVal=ui.getTextField(ui.GPR0);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "01":
                RegVal=ui.getTextField(ui.GPR1);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "10":
                RegVal=ui.getTextField(ui.GPR2);
                regval=Integer.parseInt(RegVal, 2);
                break;                        
                
            case "11":
                RegVal=ui.getTextField(ui.GPR3);
                regval=Integer.parseInt(RegVal, 2);
                break;                       
                
            default:
                break;      
        }  
        
        regval=regval+imadd;
        
        if(regval>65535)
        {
            ui.CCR[0]=1;
        }
        
        return I2BS.Int2BinaryString(regval);
        
    }
    
    public void SIR(UI ui,int inst)
    {   
        String inst_s=I2BS.Int2BinaryString(inst);
        String IMAD=I2BS.Int2BinaryString(inst).substring(11);
        int imadd=Integer.parseInt(IMAD,2);
        String R=inst_s.substring(6, 8);
        
        String RegVal;
        int regval=0;
        /*Get Value of Register*/
        switch(R)
        {
            case "00":
                RegVal=ui.getTextField(ui.GPR0);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "01":
                RegVal=ui.getTextField(ui.GPR1);
                regval=Integer.parseInt(RegVal, 2);
                break;
                
            case "10":
                RegVal=ui.getTextField(ui.GPR2);
                regval=Integer.parseInt(RegVal, 2);
                break;                        
                
            case "11":
                RegVal=ui.getTextField(ui.GPR3);
                regval=Integer.parseInt(RegVal, 2);
                break;                       
                
            default:
                break;      
        }  
        
        if(regval==0)
        {
            ui.setTextField(ui.GPR0, I2BS.Int2BinaryString(-imadd));
        }
        
        regval=regval-imadd;
        
        if(regval<0)
        {
            ui.CCR[1]=1;
        }
        
        switch(R)
        {
            case "00":
                ui.setTextField(ui.GPR0, I2BS.Int2BinaryString(regval));
                break;
                
            case "01":
                ui.setTextField(ui.GPR1, I2BS.Int2BinaryString(regval));
                break;
                
            case "10":
                ui.setTextField(ui.GPR2, I2BS.Int2BinaryString(regval));
                break;                        
                
            case "11":
                ui.setTextField(ui.GPR3, I2BS.Int2BinaryString(regval));
                break;                       
                
            default:
                break;      
        }        
    }
    
    public void MLT(UI ui, int n[], int inst)
    {
        String inst_s=I2BS.Int2BinaryString(inst);
        String RX=inst_s.substring(6, 8);
        String RY=inst_s.substring(8,10);
        
        if((RX.equals("00") && RY.equals("10")) || (RY.equals("00") && RX.equals("10")))
        {
            String V1=ui.getTextField(ui.GPR0);
            String V2=ui.getTextField(ui.GPR2);
            int v1=Integer.parseInt(V1,2);
            int v2=Integer.parseInt(V2,2);
            int mul=v1*v2;
            String rx;
            String rx_p1;
            String Product=I2BS.Int2BinaryString(mul);
            if(Product.length()==16)
            {
                rx=I2BS.Int2BinaryString(0);
                switch(RX)
                {
                    case "00":                       
                       ui.setTextField(ui.GPR0, rx);
                       ui.setTextField(ui.GPR1, Product);
                       break;
                    case "10":
                       ui.setTextField(ui.GPR2, rx);
                       ui.setTextField(ui.GPR3, Product);
                    default:
                        break;       
                       
                }
            }
            else if(Product.length()==32)
            {
                rx=Product.substring(16);
                rx_p1=Product.substring(0, 16);
                switch(RX)
                {
                    case "00":                       
                       ui.setTextField(ui.GPR0, rx);
                       ui.setTextField(ui.GPR1, rx_p1);
                       break;
                    case "10":
                       ui.setTextField(ui.GPR2, rx);
                       ui.setTextField(ui.GPR3, rx_p1);
                    default:
                        break;       
                       
                }
            }
            else if(Product.length()>32)
            {
                int len=Product.length();
                Product=Product.substring(len-32);
                ui.CCR[0]=1;
                rx=Product.substring(16);
                rx_p1=Product.substring(0, 16);
                switch(RX)
                {
                    case "00":                       
                       ui.setTextField(ui.GPR0, rx);
                       ui.setTextField(ui.GPR1, rx_p1);
                       break;
                    case "10":
                       ui.setTextField(ui.GPR2, rx);
                       ui.setTextField(ui.GPR3, rx_p1);
                    default:
                        break;       
                       
                }
            }
        }
        
    }
    
    public void DVD(UI ui, int n[], int inst)
    {
        String inst_s=I2BS.Int2BinaryString(inst);
        String RX=inst_s.substring(6, 8);
        String RY=inst_s.substring(8,10);
        
        if((RX.equals("00") && RY.equals("10")) || (RY.equals("00") && RX.equals("10")))
        {
            String V1=ui.getTextField(ui.GPR0);
            String V2=ui.getTextField(ui.GPR2);
            int v1=Integer.parseInt(V1,2);
            int v2=Integer.parseInt(V2,2);
            int qt;
            int remainder;
            switch(RX)
            {
                case "00":
                    if(v2==0)
                    {
                        ui.CCR[3]=1;
                    }
                    else
                    {
                        qt=v1/v2;
                        remainder=v1%v2;
                        ui.setTextField(ui.GPR0, I2BS.Int2BinaryString(qt));
                        ui.setTextField(ui.GPR1, I2BS.Int2BinaryString(remainder));
                    }break;
                
                case "10":
                    if(v1==0)
                    {
                        ui.CCR[3]=1;
                    }
                    else
                    {
                        qt=v2/v1;
                        remainder=v2%v1;
                        ui.setTextField(ui.GPR2, I2BS.Int2BinaryString(qt));
                        ui.setTextField(ui.GPR3, I2BS.Int2BinaryString(remainder));
                    }break;
                default:
                    break;
                    
            }
          
        }
        
    }
    
    public void TRR(UI ui, int n[], int inst)
    {
        String inst_s=I2BS.Int2BinaryString(inst);
        String RX=inst_s.substring(6, 8);
        String RY=inst_s.substring(8,10);
        String V1 = null,V2 = null;
        int v1,v2;
        System.out.println(RX);
        System.out.println(RY);
        
        if(RX!=RY)
        {
            
            V1=this.GetGPR(RX, ui);
            V2=this.GetGPR(RY, ui);
            
            v1=Integer.parseInt(V1,2);
            v2=Integer.parseInt(V2,2);
            System.out.println(v1);
            System.out.println(v2);
            
            if(v1==v2)
            {
                ui.CCR[4]=1;
                System.out.println("CCR[4]=1");
                ui.appendTextArea(ui.FEC, "CCR[4]=1\n");
            }
            else
            {
                ui.CCR[4]=0;
                System.out.println("CCR[4]=0");
                ui.appendTextArea(ui.FEC, "CCR[4]=0\n");
            }
            
        }
        
    }
    
    public void AND(UI ui, int n[], int inst)
    {
        String inst_s=I2BS.Int2BinaryString(inst);
        String RX=inst_s.substring(6, 8);
        String RY=inst_s.substring(8,10);
        String V1 = null,V2 = null;
        int v1,v2;

        
        if(RX!=RY)
        {
            
            V1=this.GetGPR(RX, ui);
            V2=this.GetGPR(RY, ui);
            
            v1=Integer.parseInt(V1,2);
            v2=Integer.parseInt(V2,2);
            
            int v3=v1&v2;
            String Value=I2BS.Int2BinaryString(v3);
            this.SetGPR(RX, ui, Value);    
        }
        
    }
    
    public void ORR(UI ui, int n[], int inst)
    {
        String inst_s=I2BS.Int2BinaryString(inst);
        String RX=inst_s.substring(6, 8);
        String RY=inst_s.substring(8,10);
        String V1 = null,V2 = null;
        int v1,v2;

        
        if(RX!=RY)
        {
            
            V1=this.GetGPR(RX, ui);
            V2=this.GetGPR(RY, ui);
            
            v1=Integer.parseInt(V1,2);
            v2=Integer.parseInt(V2,2);
            
            int v3=v1|v2;
            String Value=I2BS.Int2BinaryString(v3);
            this.SetGPR(RX, ui, Value);    
        }
        
    }
    
    public void NOT(UI ui, int n[], int inst)
    {
        String inst_s=I2BS.Int2BinaryString(inst);
        String RX=inst_s.substring(6, 8);
        String V1 = null;
        int v1;
  
        V1=this.GetGPR(RX, ui);
        v1=Integer.parseInt(V1,2);
            
        int v3=~v1;
        String Value=I2BS.Int2BinaryString(v3);
        this.SetGPR(RX, ui, Value);    
        
        
    }
    
    public void SRC(UI ui, int n[], int inst)
    {
        String inst_s=I2BS.Int2BinaryString(inst);
        String RX=inst_s.substring(6, 8);
        String A_L=inst_s.substring(8, 9);
        String L_R=inst_s.substring(9, 10);
        
        int count=Integer.parseInt(inst_s.substring(11),2);
        
        String V1=this.GetGPR(RX, ui);
        System.out.println(V1);
        int v1=Integer.parseInt(V1,2);
        
        if("0".equals(A_L)) 
        {
            if("0".equals(L_R)) 
            {
                v1=v1 >> count;
            }
            if("1".equals(L_R)) 
            {
               System.out.println(v1); 
               System.out.println(count);
               v1=v1 << count;
               System.out.println(v1);
            }
        }
        else if("1".equals(A_L)) 
        {
            if("0".equals(L_R)) 
            {
                if(v1 >= 0)
                    v1=(v1 >>> count);
                else 
                {
                    String x=Integer.toBinaryString(v1 >>> count);
                    x=x.replace("1111111111111111", "");
                    v1=Integer.parseInt(x, 2);
                }
            }
            if("1".equals(L_R)) 
            {
                v1=v1 << count;
            }
        }
        String Result=I2BS.Int2BinaryString(v1);
        this.SetGPR(RX, ui, Result);    
    }

    public void RRC(UI ui, int n[], int inst)
    {
        String inst_s=I2BS.Int2BinaryString(inst);
        String RX=inst_s.substring(6, 8);
        String A_L=inst_s.substring(8, 9);
        String L_R=inst_s.substring(9, 10);
        
        int count=Integer.parseInt(inst_s.substring(11, 16),2);
        
        String V1=this.GetGPR(RX, ui);
        int v1=Integer.parseInt(V1,2);
        
        if("0".equals(A_L)) 
        {
            if("0".equals(L_R)) 
            {
                v1=v1 >> count;
            }
            if("1".equals(L_R)) 
            {
               v1=v1 << count;
            }
        }
        else if("1".equals(A_L)) 
        {
            if("0".equals(L_R)) 
            {
                if(v1 >= 0)
                    v1=(v1 >>> count);
                else 
                {
                    String x=Integer.toBinaryString(v1 >>> count);
                    x=x.replace("1111111111111111", "");
                    v1=Integer.parseInt(x, 2);
                }
            }
            if("1".equals(L_R)) 
            {
                v1=v1 << count;
            }
        }
        String Result=I2BS.Int2BinaryString(v1);
        this.SetGPR(RX, ui, Result);    
    }
    
    public void OUT(UI ui, int inst)
    {
        if(ui.prgm==1)
        {
            String inst_s=I2BS.Int2BinaryString(inst);
            String RX=inst_s.substring(6, 8);
            String V1=this.GetGPR(RX, ui);
            int v1=Integer.parseInt(V1,2);
            String S="Out: "+V1+", Integer Value"+v1;
            ui.setTextAreaT(ui.console_printer, S);
        }
        if(ui.prgm==2)
        {
            String inst_s=I2BS.Int2BinaryString(inst);
            String RX=inst_s.substring(6, 8);
            String V1=this.GetGPR(RX, ui);
            int v1=Integer.parseInt(V1,2);
            char c=(char) v1;
            
            String Con=ui.getTextAreaT(ui.console_printer);
            if(c=='.')
            {
                Con=Con+c+"\n";
            }
            else
            {
                Con=Con+c;
            }
            
            ui.setTextAreaT(ui.console_printer, Con);
        }
    }
    
    public void IN(UI ui, int inst)
    {
        String inst_s=I2BS.Int2BinaryString(inst);
        String RX=inst_s.substring(6, 8);
        String V1=ui.getTextField(ui.keyboard);
        String bit=inst_s.substring(15);
        System.out.println(bit);
        System.out.println(V1);
        int v1;
        if(bit.equals("0")&&V1.length()>0)
        {
            char c=V1.charAt(0);
            v1=c;
            String input=I2BS.Int2BinaryString(v1);
            this.SetGPR(RX, ui, input);
            ui.setTextField(ui.keyboard, V1.substring(1));
        }
        else if(V1.length()==0)
        {
            this.SetGPR(RX, ui, "0000000000000000");
        }

    }
    public void FADD(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        int ea=Integer.parseInt(EA,2);
        String inst_s=I2BS.Int2BinaryString(inst);
        String I=inst_s.substring(10, 11);
        String R=inst_s.substring(6, 8);
        int fr=0;
        String FR;
        switch(R)
        {
            case "00":
                if(I.equals("0"))
                {
                    fr=Integer.parseInt(ui.getTextField(ui.FR0),2)+n[ea];
                    System.out.println(fr);
                    FR=I2BS.Int2BinaryString(fr);
                    if(FR.length()>16)
                    {
                        FR=FR.substring(0,15);
                        ui.CCR[1]=1;
                    }
                    ui.setTextField(ui.FR0, FR);
                }
                else
                {
                    fr=Integer.parseInt(ui.getTextField(ui.FR0),2)+n[n[ea]];
                    FR=I2BS.Int2BinaryString(fr);
                    if(FR.length()>16)
                    {
                        FR=FR.substring(0,15);
                        ui.CCR[1]=1;
                    }
                    ui.setTextField(ui.FR0, FR);
                }
            break;
            
            case "01":
                if(I.equals("0"))
                {
                    fr=Integer.parseInt(ui.getTextField(ui.FR1),2)+n[ea];
                    FR=I2BS.Int2BinaryString(fr);
                    if(FR.length()>16)
                    {
                        FR=FR.substring(0,15);
                        ui.CCR[1]=1;
                    }
                    ui.setTextField(ui.FR0, FR);
                }
                else
                {
                    fr=Integer.parseInt(ui.getTextField(ui.FR1),2)+n[n[ea]];
                    FR=I2BS.Int2BinaryString(fr);
                    if(FR.length()>16)
                    {
                        FR=FR.substring(0,15);
                        ui.CCR[1]=1;
                    }
                    ui.setTextField(ui.FR0, FR);
                }
            break;
        }
        
    }
    public void FSUB(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        int ea=Integer.parseInt(EA,2);
        String inst_s=I2BS.Int2BinaryString(inst);
        String I=inst_s.substring(10, 11);
        String R=inst_s.substring(6, 8);
        int fr=0;
        String FR;
        switch(R)
        {
            case "00":
                if(I.equals("0"))
                {
                    fr=Integer.parseInt(ui.getTextField(ui.FR0),2)-n[ea];
                    if(fr<0)
                    {
                        fr=fr*-1;
                        ui.CCR[2]=1;
                    }
                    FR=I2BS.Int2BinaryString(fr);
                    ui.setTextField(ui.FR0, FR);
                }
                else
                {
                    fr=Integer.parseInt(ui.getTextField(ui.FR0),2)-n[n[ea]];
                    if(fr<0)
                    {
                        fr=fr*-1;
                        ui.CCR[2]=1;
                    }
                    FR=I2BS.Int2BinaryString(fr);
                    ui.setTextField(ui.FR0, FR);
                }
            break;
            
            case "01":
                if(I.equals("0"))
                {
                    fr=Integer.parseInt(ui.getTextField(ui.FR1),2)-n[ea];
                    if(fr<0)
                    {
                        fr=fr*-1;
                        ui.CCR[2]=1;
                    }
                    FR=I2BS.Int2BinaryString(fr);
                    ui.setTextField(ui.FR1, FR);
                }
                else
                {
                    fr=Integer.parseInt(ui.getTextField(ui.FR1),2)-n[n[ea]];
                    if(fr<0)
                    {
                        fr=fr*-1;
                        ui.CCR[2]=1;
                    }
                    FR=I2BS.Int2BinaryString(fr);
                    ui.setTextField(ui.FR1, FR);
                }
            break;
        }
        
    }
    
    public void VADD(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        int ea=Integer.parseInt(EA,2);
        String inst_s=I2BS.Int2BinaryString(inst);
        String I=inst_s.substring(10, 11);
        String R=inst_s.substring(6, 8);
        int vector=0;
        String FR;
        
        if(I.equals("0"))
        {
            vector=n[ea]+n[ea+1];
            FR=I2BS.Int2BinaryString(vector);
            if(FR.length()>16)
            {
                FR=FR.substring(0,15);
                ui.CCR[1]=1;
            }
            ui.memory[ea]=Integer.parseInt(FR,2);
        }
        else
        {
            vector=n[n[ea]]+n[n[ea+1]];
            FR=I2BS.Int2BinaryString(vector);
            if(FR.length()>16)
            {
                FR=FR.substring(0,15);
                ui.CCR[1]=1;
            }
            ui.memory[ea]=Integer.parseInt(FR,2);
        }
        
    }
    
    public void VSUB(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3)
    {        
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        int ea=Integer.parseInt(EA,2);
        String inst_s=I2BS.Int2BinaryString(inst);
        String I=inst_s.substring(10, 11);
        String R=inst_s.substring(6, 8);
        int vector=0;
        String FR;
        
        if(I.equals("0"))
        {
            vector=n[ea]-n[ea+1];
            if(vector<0)
            {
                vector=vector*-1;
                ui.CCR[2]=1;
            }
            ui.memory[ea]=vector;
        }
        else
        {
            vector=n[n[ea]]-n[n[ea+1]];
            if(vector<0)
            {
                vector=vector*-1;
                ui.CCR[2]=1;
            }
            ui.memory[ea]=vector;
        }
        
    }
    public void CNVRT(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3)
    {
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        int ea=Integer.parseInt(EA,2);
        String inst_s=I2BS.Int2BinaryString(inst);

        String R=inst_s.substring(6, 8);  
        String GPR=this.GetGPR(R, ui);
        int F=Integer.parseInt(GPR,2);

	 
	if( F==0)
        {
            this.SetGPR(R, ui,I2BS.Int2BinaryString(n[ea]));
	}
	if(F==1)
	{
		int fr = n[ea];
		String input=null;
		String exp="0000000";
		String man="00000000";
		String output=null;
		if(fr>=0)
		{
                    input = Integer.toBinaryString(fr);
                    man=input+man.substring(input.length());
                    String temp=Integer.toBinaryString(input.length());
                    exp=exp.substring(0, 7-temp.length())+temp;
                    output="0"+exp+man;
        	
                }
		else
		{
                    fr=-1*fr;
                    input=Integer.toBinaryString(fr);

                    char[] opp=input.toCharArray();
                    int k;
                    for(int j=0; j<input.length();j++)
                    {

                        if (opp[j]=='0')
                        {
                            opp[j]='1';
                        }
                        else
                        {
                            opp[j]='0';
                        }
        		
                    }
                    for(k=input.length()-1;k>=0;k--)
                    {
			if(opp[k]=='0')
			{
                            opp[k]='1';
                            break;
			}
			else 
			{
                            opp[k]='0';
                            continue;
			}
                    }	
                    String valid=new String(opp);	
                    man=valid+man.substring(input.length());
			
                    String temp=Integer.toBinaryString(input.length());
                    exp=exp.substring(0, 7-temp.length())+temp;
                    output="1"+exp+man;
            }

        

            switch(R)
            {
                case "00":
                    ui.setTextField(ui.FR0, output);
                    break;
                case "01":
                    ui.setTextField(ui.FR1, output);
                    break;
                default:

            }
        }
    }
    public void LDFR(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3)
    {
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        int ea=Integer.parseInt(EA,2);
        String inst_s=I2BS.Int2BinaryString(inst);
        String I=inst_s.substring(10, 11);
        if(I.equals("0"))
        {
           ui.setTextField(ui.FR0, I2BS.Int2BinaryString(n[ea]));
           ui.setTextField(ui.FR0, I2BS.Int2BinaryString(n[ea+1]));
        }
        else
        {
           ui.setTextField(ui.FR0, I2BS.Int2BinaryString(n[n[ea]]));
           ui.setTextField(ui.FR0, I2BS.Int2BinaryString(n[n[ea+1]]));    
        }
        
    }

    public void STFR(UI ui,int n[], int inst, String IXR1, String IXR2, String IXR3)
    {
        String EA=this.GetEffectiveAddress(n, inst, IXR1, IXR2, IXR3);
        int ea=Integer.parseInt(EA,2);
        String inst_s=I2BS.Int2BinaryString(inst);
        String I=inst_s.substring(10, 11);
        String FR0=ui.getTextField(ui.FR0);
        int fr0=Integer.parseInt(FR0,2);
        String FR1=ui.getTextField(ui.FR1);
        int fr1=Integer.parseInt(FR1,2);
        if(I.equals("0"))
        {
           ui.memory[ea]=fr0;
           ui.memory[ea+1]=fr1;
        }
        else
        {
           ui.memory[ui.memory[ea]]=fr0;
           ui.memory[ui.memory[ea+1]]=fr1;    
        }
        
    }    
}
