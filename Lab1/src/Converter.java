import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Converter extends JPanel
{
    private CurrenciesStorage storage;
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JComboBox from;
    private JComboBox to;
    private JButton convertButton;
    private JTextField amountField;

    public Converter()
    {
        Font font1 = new Font("TimesNewRoman", Font.BOLD, 20);

        this.storage = CurrenciesStorage.getInstance();
        String[] items = this.storage.getCurrencyNamesArray();

        jcomp1 = new JLabel ("Amount");
        jcomp2 = new JLabel ("From");
        jcomp3 = new JLabel ("To");
        from = new JComboBox (items);
        to = new JComboBox (items);
        convertButton = new JButton ("Convert");
        amountField = new JTextField (100);

        amountField.setFont(font1);
        amountField.setHorizontalAlignment(JTextField.CENTER);
        setPreferredSize (new Dimension(750, 450));
        setLayout (null);

        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (from);
        add (to);
        add (convertButton);
        add (amountField);

        jcomp1.setBounds (90, 130, 110, 50);
        jcomp2.setBounds (340, 130, 200, 50);
        jcomp3.setBounds (600, 130, 200, 50);
        from.setBounds (250, 200, 200, 50);
        to.setBounds (510, 200, 200, 50);
        convertButton.setBounds (270, 350, 200, 50);
        amountField.setBounds (10, 200, 200, 50);

        ActionListener buttonListener = new ButtonActionHandler();
        convertButton.addActionListener(buttonListener);
    }

    private class ButtonActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (!checkInput(amountField.getText()))
                JOptionPane.showMessageDialog(null, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);

            else
            {
                String fromCurrency = ((String) from.getSelectedItem()).substring(0, 3);
                String toCurrency = ((String) to.getSelectedItem()).substring(0,3);
                BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(amountField.getText().replace(",", ".")));
                amount = convert(amount, fromCurrency, toCurrency);
                JOptionPane.showMessageDialog(null, "Amount after exchange: " + amount + " " + toCurrency, "Conversion Result", JOptionPane.PLAIN_MESSAGE);
            }

        }

        public boolean checkInput(String input)
        {
            String regex = "^[0-9]+([,.][0-9]{0,2})?$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            return matcher.matches();
        }

        public BigDecimal convert(BigDecimal amount, String from, String to)
        {
            BigDecimal fromRate = storage.getCurrency(from).getRatio();
            BigDecimal toRate = storage.getCurrency(to).getRatio();
            return amount.multiply(fromRate).divide(toRate, 2, RoundingMode.HALF_UP);
        }
    }

    public static void main(String[] args)
    {
        new Converter();
        JFrame frame = new JFrame ("CurrencyConverter");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Converter());
        frame.pack();
        frame.setVisible (true);
    }
}