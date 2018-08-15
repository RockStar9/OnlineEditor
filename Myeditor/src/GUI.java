import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;
import org.omg.SendingContext.RunTime;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class GUI implements ActionListener {
       
     JFrame frame ;
JButton jb,jb1;
JMenuBar jmb;
JLabel jl;
String result ,result1,str,fname;
JTextField jtf;
JLabel l;
JTextArea jta,jta1;
JPanel panel,panel1;
JScrollPane sp,sp1;
Runtime r;
    public GUI() {
    	frame= new JFrame("MY Editor");
    	panel1=new JPanel(); 
    	r=Runtime.getRuntime();
    	 jl=new JLabel("Enter main class name");
    	jtf=new JTextField();
    	jl.setBounds(20,20,80,25);
    
        l=new JLabel("Output");
       // jtf.setBounds(150,20,10,10);
    	jb=new JButton("compile");
        jb1=new JButton("execute");
        jmb=new JMenuBar();
        jmb.add(jb);
        jmb.add(jb1);
        frame.setJMenuBar(jmb);
        String s=l.getText();
      
      
        // the clickable button
        //JButton button = new JButton("Click Me");
         jta=new JTextArea();
         jta1=new JTextArea();
        jta.setBounds(50,200,80,80);
        jta1.setBounds(200,200,100,100);
        //button.addActionListener(this);
        jta1.setText(s);
        // the panel with the button and text
         panel = new JPanel();
         sp = new JScrollPane (jta,     JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
         sp1 = new JScrollPane (jta1,     JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 2));
      //  panel.add(button);
        jta.setBorder(border);
       jta1.setBorder(border);
        //panel.add(jta);
       jtf.setBounds(100,200,50,25);
       panel1.setLayout(new GridLayout(1, 1));
        panel.add(sp);
       panel.add(sp1);
       panel1.add(jl);
       panel1.add(jtf);

       jb.addActionListener(this);
       jb1.addActionListener(this);
       
      //S jta.addFocusListener(new MyFocusListener1(this));
       frame.add(panel1, BorderLayout.NORTH);
        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        //frame.pack();
        frame.setSize(1256, 1080);
        frame.setVisible(true);
    }

    // process the button clicks
    public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jb)
		{
			str="";
			if(!jtf.getText().equals(""))
			{
				try{
					fname=jtf.getText().trim()+".java";
					FileWriter fw=new FileWriter(fname);
					String s1=jta.getText();
					PrintWriter pw=new PrintWriter(fw);
					pw.println(s1);
					pw.flush();
					//r=Runtime.getRuntime();
					//compile ke liya
					Process error=r.exec("javac "+fname);
					//here getErrorStream return InputStream and convert into buffer reader
					BufferedReader err=new BufferedReader(new InputStreamReader(error.getErrorStream()));
					while(true)
					{
						String temp=err.readLine();
						if(temp!=null)
						{
							result+=temp;
							result+="\n";
							System.out.println("while");
						}

						else 
							{
								break;
						    }

					}
					if(result.equals(""))
					{
						jta1.setText("compilation successful:"+fname);
						err.close();
					}
					else
					{
						jta1.setText(result+"hello compile");
					}
				}
				catch(Exception e4)
				{
					System.out.println(e4+"\n Hello exception");
				}

			}
			else
				jta1.setText("Please Enter the java programme name! ");
		}
		else if(e.getSource()==jb1)
		{
			int start=0;
			try
			{
				String fn=jtf.getText().trim();
				//r=Runtime.getRuntime();
				Process p=r.exec("java "+fn);
				BufferedReader output=new BufferedReader(new InputStreamReader(p.getInputStream()));
				BufferedReader error =new BufferedReader(new InputStreamReader(p.getErrorStream()));
				while(true)
				{
					String temp=output.readLine();
					if(temp!=null)
					{
						result+=temp;
						result+="\n";
					}
					else
					{
						break;
					}
				}
				while(true)
				{
					String temp=error.readLine();
					if(temp!=null)
					{
						result1+=temp;
					result1+="\n";
				}
					else
				{
					break;
				}
				}
				
			
			output.close();
			error.close();
			jta1.setText(result+"\n"+result1+"run");
		}
		catch(Exception e2)
		{
			System.out.println(e2);
			System.out.println("Hello1");
		}
		}
	}

    // create one Frame
    public static void main(String args[])
    {
    	new GUI();
    }}
    /*
     * class MyFocusListener1 extends FocusAdapter
     
    {
    	GUI e;
    	MyFocusListener1(GUI e)
    	{
    		this.e=e;
    	}
    	public void focusGained(FocusEvent fe)
    	{
    		String str=e.jtf.getText().trim();
    		e.jta.setText("public class"+" "+str+"\n"
    			+"{"+"\n"
    			+"public static void main(String... s)"+"\n"
    		+"{"+"\n"
    		+"                 "+"\n"
    		+"}"+"\n"
    		+"}");
    	}
    }*/