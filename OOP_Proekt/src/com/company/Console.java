package com.company;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Console {
    public static void main(String[] args)
    {
        new Console();
    }

    public JFrame frame;
    public JTextPane console;
    public JTextField input;
    public JScrollPane scrollPane;

    public StyledDocument document;
    boolean trace = false;

    ArrayList<String> recent_used = new ArrayList<String>();
    int recent_used_id = 0;
    int recent_used_maximum = 10;

    public Console()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex){}
        frame = new JFrame();
        frame.setTitle("Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        console = new JTextPane();
        console.setEditable(false);
        console.setFont(new Font("Courier New",Font.PLAIN,12));
        console.setOpaque(false);

        document=console.getStyledDocument();

        input = new JTextField();
        input.setEditable(true);
        console.setFont(new Font("Courier New",Font.PLAIN,12));
        input.setForeground(Color.WHITE);
        input.setCaretColor(Color.WHITE);
        input.setOpaque(false);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = input.getText();
                if(text.length() >=1){
                    recent_used.add(text);
                    recent_used_id=0;
                    doCommand(text);
                    scrollBottom();
                    input.selectAll();

                }
            }
        });

        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    if (recent_used_id < (recent_used_maximum - 1) && recent_used_id < (recent_used.size() -1))
                    {
                        recent_used_id++;
                    }
                    input.setText(recent_used.get(recent_used.size() - 1 - recent_used_id ));
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    if (recent_used_id > 0)
                    {
                        recent_used_id--;
                    }
                    input.setText(recent_used.get(recent_used.size() - 1 - recent_used_id ));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        scrollPane = new JScrollPane(console);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        frame.add(input, BorderLayout.SOUTH);
        frame.add(scrollPane,BorderLayout.CENTER);
        frame.getContentPane().setBackground(new Color(50,50,50));

        frame.setSize(1000,650);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void scrollTop(){
        console.setCaretPosition(0);
    }

    public void scrollBottom(){
        console.setCaretPosition(console.getDocument().getLength());
    }

    public void print(String s,boolean trace){
        print(s,trace,new Color(255,255,255));
    }

    public void print(String s,boolean trace,Color c){
        Style style = console.addStyle("Style",null);
        StyleConstants.setForeground(style,c);
        if(trace)
        {
            Throwable t = new Throwable();
            StackTraceElement[] elements = t.getStackTrace();
            String caller = elements[0].getClassName();

            s = caller + "->" + s;
        }
        try{
            document.insertString(document.getLength(),s,style);
        } catch (Exception ex){}
    }

    public void println(String s,boolean trace){
        println(s,trace,new Color(255,255,255));
    }

    public void println(String s,boolean trace,Color c){
        print(s + "\n",trace,c);
    }

    public void clear(){
        try{
            document.remove(0, document.getLength());
        } catch (Exception ex) {}
    }

    public void doCommand(String s){
        final String[] commands =s.split(" ");
        try{
                if(commands[0].equalsIgnoreCase("clear"))
                {
                    clear();
                }
                else if(commands[0].equalsIgnoreCase("popup"))
                {
                    String message ="";
                    for (int i = 1; i < commands.length; i++) {
                        message += commands[i];
                        if(i != commands.length - 1){
                            message += " ";
                        }
                    }
                    JOptionPane.showMessageDialog(null,message,"Message",JOptionPane.INFORMATION_MESSAGE);
                }
                    else
                    {
                        println(s,trace,new Color(255,255,255));
                    }
        } catch (Exception ex) {
            println("Error -> " + ex.getMessage(),trace,new Color(255,155,155));
        }

    }
}
