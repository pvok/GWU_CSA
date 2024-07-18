/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.simulatorxcsa;

/**
 *
 * @author sagar
 * This class has 2 Methods
 * SS, executes the single step
 * RUN, executes until HALT is acheived 
 */
public class RunInstruction {

    DecodeInst decodeinstobj=new DecodeInst();
    Int2BinaryString I2BS=new Int2BinaryString();
    Cache cacheobj=new Cache();

    public void SS(UI ui, int n[], String PC, String IXR1, String IXR2, String IXR3)
    {
        int pc=Integer.parseInt(PC, 2);
        int inst=n[pc];
        /*Decoding the instruction*/
        String OP=decodeinstobj.DoInst(inst);
        if(OP.equals("-1"))
        {
            ui.setTextField(ui.MFR, "0100");
        }
        String inst_s=I2BS.Int2BinaryString(inst);
        /*Calc the value of R and IX*/
        String R=inst_s.substring(6, 8);
        String IX=inst_s.substring(8,10);
        String S;
        
        /*printing instruction to be run to engineers console*/
        String Console="Instruction: ";
        Console=Console+inst_s+" OP Code:"+OP+" "+" Performed at Address: "+PC+" (int value):"+pc+"\n";
        System.out.println(Console);
        ui.appendTextArea(ui.FEC, Console);
        cacheobj.PrintCache(ui, PC, inst_s);
        
        /*incrementing the Program counter*/
        pc++;
        PC=I2BS.Int2BinaryString(pc);
        PC=PC.substring(4, 16);
        /*Setting the Instruction Register with the value of Current Instruction*/
        ui.setTextField(ui.IR, inst_s);


        /*Performing the instruction as per the Op code*/
        switch(OP)
        {
            case "LDR":
                S=decodeinstobj.LDR(n, inst, IXR1, IXR2, IXR3);
                System.out.println("setting register:"+R+" value:"+S);
                if(R.equals("00"))
                    {ui.setTextField(ui.GPR0, S);}  
                else if(R.equals("01"))
                    {ui.setTextField(ui.GPR1, S);}
                else if(R.equals("10"))
                    {ui.setTextField(ui.GPR2, S);}
                else 
                    {ui.setTextField(ui.GPR3, S);}
                ui.setTextField(ui.PC,PC);
                ui.SetLabelColorB(ui.HALT);
                break;
                
            case "STR":
                S=decodeinstobj.STR(n, inst, IXR1, IXR2, IXR3);
                int val=Integer.parseInt(S, 2);
                
                switch(R)
                {
                    case "00":
                        String RegVal=ui.getTextField(ui.GPR0);
                        int regval=Integer.parseInt(RegVal, 2);
                        n[val]=regval;
                        ui.memory=n;
                        System.out.println("Storing register:"+R+" address "+S+" "+val+" Value:"+regval);
                        break;
                    case "01":
                        RegVal=ui.getTextField(ui.GPR1);
                        regval=Integer.parseInt(RegVal, 2);
                        n[val]=regval;
                        ui.memory=n;
                        System.out.println("Storing register:"+R+" address "+S+" "+val+" Value:"+regval);
                        break;
                    case "10":
                        RegVal=ui.getTextField(ui.GPR2);
                        regval=Integer.parseInt(RegVal, 2);
                        n[val]=regval;
                        ui.memory=n;
                        System.out.println("Storing register:"+R+" address "+S+" "+val+" Value:"+regval);
                        break;
                    case "11":
                        RegVal=ui.getTextField(ui.GPR3);
                        regval=Integer.parseInt(RegVal, 2);
                        n[val]=regval;
                        ui.memory=n;
                        System.out.println("Storing register:"+R+" address "+S+" "+val+" Value:"+regval);
                        break;
                    default:
                        break;
                }
                ui.setTextField(ui.PC,PC);
                ui.SetLabelColorB(ui.HALT);
                break;
                
            case "LDA":
                S=decodeinstobj.LDA(n, inst, IXR1, IXR2, IXR3);
                
                System.out.println("setting register:"+R+" value:"+S);
                switch(R)
                {
                    
                    case "00":
                        ui.setTextField(ui.GPR0, S);
                        break;
                    case "01":
                        ui.setTextField(ui.GPR1, S);
                        break;
                    case "10":
                        ui.setTextField(ui.GPR2, S);
                        break;
                    case "11":
                        ui.setTextField(ui.GPR3, S);
                        break;
                    default:
                        break;
                }
                ui.setTextField(ui.PC,PC);
                ui.SetLabelColorB(ui.HALT);
                break;
                
            case "LDX":
                S=decodeinstobj.LDX(n, inst, IXR1, IXR2, IXR3);
                System.out.println("setting IX register:"+R+" value:"+S);
                switch(IX)
                {  
                    case "01":
                        ui.setTextField(ui.IXR1, S);break;
                    case "10":
                        ui.setTextField(ui.IXR2, S);break;
                    case "11":
                        ui.setTextField(ui.IXR3, S);break;
                    default:
                        ;break;
                }
                ui.setTextField(ui.PC,PC);
                ui.SetLabelColorB(ui.HALT);
                break;
                
            case "STX":
                S=decodeinstobj.STX(n, inst, IXR1, IXR2, IXR3);
                val=Integer.parseInt(S, 2);
                
                switch(IX)
                {
                    case "01":
                        String RegVal=ui.getTextField(ui.IXR1);
                        int regval=Integer.parseInt(RegVal, 2);
                        n[val]=regval;
                        System.out.println("Storing IX register:"+R+" address "+S+" "+val+" Value:"+regval);
                        ui.memory=n;break;
                    case "10":
                        RegVal=ui.getTextField(ui.IXR2);
                        regval=Integer.parseInt(RegVal, 2);
                        n[val]=regval;
                        System.out.println("Storing IX register:"+R+" address "+S+" "+val+" Value:"+regval);
                        ui.memory=n;break;
                    case "11":
                        RegVal=ui.getTextField(ui.IXR3);
                        regval=Integer.parseInt(RegVal, 2);
                        n[val]=regval;
                        System.out.println("Storing IX register:"+R+" address "+S+" "+val+" Value:"+regval);
                        ui.memory=n;break;
                    default:
                        ;break;
                }
                ui.setTextField(ui.PC,PC);
                ui.SetLabelColorB(ui.HALT);break;
            
            case "JZ":
                S=decodeinstobj.JZ(ui,n, inst, IXR1, IXR2, IXR3,PC);
                System.out.println("Setting value of PC as :"+S);
                ui.setTextField(ui.PC,S);
                ui.SetLabelColorB(ui.HALT);break;
                
            case "JNE":
                S=decodeinstobj.JNE(ui,n, inst, IXR1, IXR2, IXR3,PC);
                System.out.println("Setting value of PC as :"+S);
                ui.setTextField(ui.PC,S);
                ui.SetLabelColorB(ui.HALT);break;    
            
            case "JCC":
                S=decodeinstobj.JCC(ui,n, inst, IXR1, IXR2, IXR3,PC);
                System.out.println("Setting value of PC as :"+S);
                ui.setTextField(ui.PC,S);
                ui.SetLabelColorB(ui.HALT);break;  
                
            case "JMA":
                S=decodeinstobj.JMA(n, inst, IXR1, IXR2, IXR3);
                System.out.println("Setting value of PC as :"+S);
                ui.setTextField(ui.PC,S);
                ui.SetLabelColorB(ui.HALT);break;    
            
            case "JSR":
                S=decodeinstobj.JSR(n, inst, IXR1, IXR2, IXR3);
                System.out.println("Setting value of PC as :"+S);
                ui.setTextField(ui.PC,S);
                System.out.println("Setting value of GPR3 as :"+PC);
                ui.setTextField(ui.GPR3, PC);
                ui.SetLabelColorB(ui.HALT);break; 
                
            case "RFS":
                S=decodeinstobj.RFS(inst);
                ui.setTextField(ui.GPR0,S);
                System.out.println("Setting value of GPR0 as :"+S);
                ui.setTextField(ui.PC, ui.getTextField(ui.GPR3));
                System.out.println("Setting value of PC as :"+ui.getTextField(ui.GPR3));
                ui.SetLabelColorB(ui.HALT);break;     
             
            case "SOB":
                S=decodeinstobj.SOB(ui, n, inst, IXR1, IXR2, IXR3, PC);
                ui.setTextField(ui.PC,S);
                ui.SetLabelColorB(ui.HALT);break;
                
            case "JGE":
                S=decodeinstobj.JGE(ui, n, inst, IXR1, IXR2, IXR3, PC);
                ui.setTextField(ui.PC,S);
                ui.SetLabelColorB(ui.HALT);break;      
            
            case "AMR":
                S=decodeinstobj.AMR(ui, n, inst, IXR1, IXR2, IXR3);
                
                switch(R)
                {
                    
                    case "00":
                        ui.setTextField(ui.GPR0, S);
                        break;
                    case "01":
                        ui.setTextField(ui.GPR1, S);
                        break;
                    case "10":
                        ui.setTextField(ui.GPR2, S);
                        break;
                    case "11":
                        ui.setTextField(ui.GPR3, S);
                        break;
                    default:
                        break;
                }ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;
                
            case "SMR":
                S=decodeinstobj.SMR(ui, n, inst, IXR1, IXR2, IXR3);
                
                switch(R)
                {
                    
                    case "00":
                        ui.setTextField(ui.GPR0, S);
                        break;
                    case "01":
                        ui.setTextField(ui.GPR1, S);
                        break;
                    case "10":
                        ui.setTextField(ui.GPR2, S);
                        break;
                    case "11":
                        ui.setTextField(ui.GPR3, S);
                        break;
                    default:
                        break;
                }
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;
            
            case "AIR":
                S=decodeinstobj.AIR(ui, inst);
                
                ui.SetLabelColorB(ui.HALT);
                
                switch(R)
                {
                    
                    case "00":
                        ui.setTextField(ui.GPR0, S);
                        break;
                    case "01":
                        ui.setTextField(ui.GPR1, S);
                        break;
                    case "10":
                        ui.setTextField(ui.GPR2, S);
                        break;
                    case "11":
                        ui.setTextField(ui.GPR3, S);
                        break;
                    default:
                        break;
                }ui.setTextField(ui.PC,PC);break;   
                
            case "SIR":
                decodeinstobj.SIR(ui, inst);
                
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;    
                
            case "MLT":
                decodeinstobj.MLT(ui, n, inst);
                
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;
                
            case "DVD":
                decodeinstobj.DVD(ui, n, inst);
                
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break; 
                
            case "TRR":
                decodeinstobj.TRR(ui, n, inst);
                
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;  
            
            case "AND":
                decodeinstobj.AND(ui, n, inst);
                
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;
            
            case "ORR":
                decodeinstobj.ORR(ui, n, inst);
                
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;
            
            case "NOT":
                decodeinstobj.NOT(ui, n, inst);
                
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;     
            
            case "SRC":
                decodeinstobj.SRC(ui, n, inst);
                
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;     
            
            case "RRC":
                decodeinstobj.RRC(ui, n, inst);
                
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;   
            
            case "OUT":
                decodeinstobj.OUT(ui, inst);
                
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;
                
            case "IN":
                decodeinstobj.IN(ui, inst);
                
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;
            
            case "VADD":
                decodeinstobj.VADD(ui, n, inst, IXR1, IXR2, IXR3);
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;    
            
            case "VSUB":
                decodeinstobj.VSUB(ui, n, inst, IXR1, IXR2, IXR3);
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;
            
            case "FADD":
                decodeinstobj.FADD(ui, n, inst, IXR1, IXR2, IXR3);
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;      
            
            case "FSUB":
                decodeinstobj.FSUB(ui, n, inst, IXR1, IXR2, IXR3);
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;
            
            case "CNVRT":
                decodeinstobj.CNVRT(ui, n, inst, IXR1, IXR2, IXR3);
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;    

            case "LDFR":
                decodeinstobj.LDFR(ui, n, inst, IXR1, IXR2, IXR3);
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;    

            case "STFR":
                decodeinstobj.STFR(ui, n, inst, IXR1, IXR2, IXR3);
                ui.SetLabelColorB(ui.HALT);
                ui.setTextField(ui.PC,PC);break;                
                
            case "HALT":
                ui.setTextField(ui.PC,PC);
                ui.SetLabelColorR(ui.HALT);
                ;break;
               
            default:
                 ;break;
                            
        }
        
    }
    public void Run(UI ui, int n[], String PC, String IXR1, String IXR2, String IXR3)
    {
        int pc=Integer.parseInt(PC, 2);
        int inst=n[pc];
        String OP=decodeinstobj.DoInst(inst);
        int i=0;
               
        while(!OP.equals("HALT"))
        {
            
            this.SS(ui, n, PC, IXR1, IXR2, IXR3);
            
            n=ui.memory;
            PC=ui.getTextField(ui.PC);
            pc=Integer.parseInt(PC, 2);
            inst=n[pc];
            OP=decodeinstobj.DoInst(inst);
            
        }
        ui.SetLabelColorR(ui.HALT);
        
    }
}
