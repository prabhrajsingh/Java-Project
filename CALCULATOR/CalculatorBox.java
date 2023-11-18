package CALCULATOR;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorBox extends JFrame
{

    JButton btnAdd, btnSubtract, btnDivide, btnMultiply, btnClear, btnDelete, btnEquals, btnDot;
    JButton numbtn[];
    JTextField output;
    String previous, current, operator;


    public void processOutputNumber()
    {
        if(current.length() > 0)
        {
            String integerpart = current.split("\\.")[0];
            String decimalpart = current.split("\\.")[1];

            if (decimalpart.equals("0"))
            {
                current = integerpart;
            }
        }
    }

    //utility methods
    public void delete()
    {
        if(current.length() > 0)
        {
            current = current.substring(0,current.length()-1);
        }
    }

    public void clear()
    {
        current = "";
        previous = "";
        operator = null;
    }

    public void updateOutput()
    {
        output.setText(current);

    }

    public void appendToOutput(String num)
    {
        if(num.equals(".") && current.contains("."))
        {
            return;
        }

        current += num;
    }

    public void selectOperator(String newOperator)
    {
        if(current.isEmpty())
        {
            operator = newOperator;
            return;
        }
        if(!previous.isEmpty())
        {
            calculate();
        }
        operator = newOperator;
        previous = current;
        current = "";
    }

    public void calculate()
    {
        if(previous.length() < 1 || current.length() < 1)
        {
            return;
        }
        double result = 0.0;
        double num1 = Double.parseDouble(previous);
        double num2 = Double.parseDouble(current);

        switch(operator)
        {
            case "*" : 
                result = num1 * num2;
                break;
            
            case "+" :
                result = num1 + num2;
                break;
            
            case "-" :
                result = num1 - num2;
                break;
            
            case "/" :
                result = num1 / num2;
                break;
            
            default : 
                break;
        }

        current = String.valueOf(result);
        operator = null;
        previous = "";

        processOutputNumber();

    }


    private class NumberBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JButton selectedBtn = (JButton) e.getSource();
            for(JButton btn : numbtn)
            {
                if(selectedBtn == btn)
                {
                    appendToOutput(btn.getText());
                    updateOutput();
                }
            }
        }
    }

    private class operatorBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JButton selectedBtn = (JButton) e.getSource();
            if(selectedBtn == btnMultiply)
            {
                selectOperator(btnMultiply.getText());
            }
            else if(selectedBtn == btnAdd)
            {
                selectOperator(btnAdd.getText());
            }
            else if (selectedBtn == btnSubtract)
            {
                selectOperator(btnSubtract.getText());
            }
            else if (selectedBtn == btnDivide)
            {
                selectOperator(btnDivide.getText());
            }
            updateOutput();
        }
    }

    private class otherBtnHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JButton selectedbtn = (JButton) e.getSource();
            if (selectedbtn == btnDelete)
            {
                delete();
            }
            else if (selectedbtn == btnClear)
            {
                clear();
            }
            else if (selectedbtn == btnEquals)
            {
                calculate();
            }
            updateOutput();
        }
    }


    public CalculatorBox()
    {

        super("BOX CALCULATOR");

        JPanel mainPanel = new JPanel();
        
        
        //initialize the calculator operands
        current = "";
        previous = "";

        // creating sub panel for main panel;
        JPanel row1 = new JPanel();
        JPanel row2 = new JPanel();
        JPanel row3 = new JPanel();
        JPanel row4 = new JPanel();
        JPanel row5 = new JPanel();

        //initialize components;
        output = new JTextField(16);
        btnSubtract = new JButton("-");
        btnAdd = new JButton("+");
        btnDivide = new JButton("/");
        btnMultiply = new JButton("*");
        btnDot = new JButton(".");
        btnEquals = new JButton("=");
        btnClear = new JButton("C");
        btnDelete = new JButton("D");

        //initialize, style and add action listeners to number buttons
        numbtn = new JButton[11];
        numbtn[10] = btnDot;

        for(int count=0; count<numbtn.length - 1; count++)
        {
            numbtn[count] = new JButton(String.valueOf(count));
            numbtn[count].setFont(new Font("Monospaced", Font.BOLD, 22));

        }


        //style other buttons
        btnDot.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnEquals.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnAdd.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnSubtract.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnDivide.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnMultiply.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnClear.setFont(new Font("Monospaced", Font.BOLD, 22));
        btnDelete.setFont(new Font("Monospaced", Font.BOLD, 22));


        //style the output display
        output.setMaximumSize(new Dimension(185,40));
        output.setFont(new Font("Monospaced", Font.BOLD, 27));
        output.setDisabledTextColor(new Color(0, 0,0));
        output.setMargin(new Insets(0,5,0,0));
        output.setText("0");

        //instantiate action listeners
        NumberBtnHandler numBtnHandler = new NumberBtnHandler();
        operatorBtnHandler opBtnHandler = new operatorBtnHandler();
        otherBtnHandler otherBtnHandler = new otherBtnHandler();

        //add action listener to number button
        for(int count=0; count<numbtn.length-1; count++)
        {
            numbtn[count] = new JButton(String.valueOf(count));
            numbtn[count].setFont(new Font("Monospaced", Font.BOLD, 22));
            numbtn[count].addActionListener(numBtnHandler);
        }

        //add action listeners to other buttons
        btnDot.addActionListener(numBtnHandler);
        btnDelete.addActionListener(otherBtnHandler);
        btnClear.addActionListener(otherBtnHandler);
        btnEquals.addActionListener(otherBtnHandler);

        //add action listeners to operation button
        btnMultiply.addActionListener(opBtnHandler);
        btnDivide.addActionListener(opBtnHandler);
        btnAdd.addActionListener(opBtnHandler);
        btnSubtract.addActionListener(opBtnHandler);
        

        //set the layout of each row in the panel
        row1.setLayout(new BoxLayout(row1, BoxLayout.LINE_AXIS));
        row2.setLayout(new BoxLayout(row2, BoxLayout.LINE_AXIS));
        row3.setLayout(new BoxLayout(row3, BoxLayout.LINE_AXIS));
        row4.setLayout(new BoxLayout(row4, BoxLayout.LINE_AXIS));
        row5.setLayout(new BoxLayout(row5, BoxLayout.LINE_AXIS));

        //add components to each of the row;
        row1.add(Box.createHorizontalGlue());
        row1.add(btnClear);
        row1.add(btnDelete);
        row2.add(numbtn[7]);
        row2.add(numbtn[8]);
        row2.add(numbtn[9]);
        row2.add(btnMultiply);

        row3.add(numbtn[4]);
        row3.add(numbtn[5]);
        row3.add(numbtn[6]);
        row3.add(btnAdd);

        row4.add(numbtn[1]);
        row4.add(numbtn[2]);
        row4.add(numbtn[3]);
        row4.add(btnSubtract);
        
        row5.add(btnDot);
        row5.add(numbtn[0]);
        row5.add(btnEquals);
        row5.add(btnDivide);


        //add all rows to the main panel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(output);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(row1);
        mainPanel.add(row2);
        mainPanel.add(row3);
        mainPanel.add(row4);
        mainPanel.add(row5);

        //final touch
        this.add(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(205, 280);


    }

    public static void main(String[] args){
        new CalculatorBox();
        
    }
}
