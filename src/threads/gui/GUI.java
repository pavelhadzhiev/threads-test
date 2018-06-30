package threads.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import threads.calculateE.CalculateE;
import threads.calculateE.StartThreads;

public class GUI {

    private JFrame mainFrame;
    private SpinnerModel model = new SpinnerNumberModel(8, 1, 10000, 1);
    private SpinnerModel model2 = new SpinnerNumberModel(250, 1, 10000, 1);
    JScrollPane scrollPane;
    private JLabel numThreadsLabel;
    private JLabel percisionLabel;
    JSpinner spinnerThreads;
    JSpinner spinnerPrecision;
    private JPanel controlPanel1;
    private JPanel controlPanel3;
    public JTextArea area;
    private JButton okButton;
    String output;

    public GUI(String output) {
        this.output = output;
        this.mainFrame = new JFrame("Calculate Euler Number");
        this.mainFrame.setSize(500, 380);
        this.mainFrame.setResizable(false);
        mainFrame.setLayout(new FlowLayout());

        controlPanel1 = new JPanel();
        mainFrame.add(controlPanel1);
        controlPanel1.setLayout(new FlowLayout());

        controlPanel3 = new JPanel();
        mainFrame.add(controlPanel3);
        controlPanel3.setLayout(new FlowLayout());

        this.numThreadsLabel = new JLabel("");
        this.percisionLabel = new JLabel("");

        spinnerThreads = new JSpinner(model);
        spinnerPrecision = new JSpinner(model2);

        okButton = new JButton("Calculate");
        area = new JTextArea();
        scrollPane = new JScrollPane(area);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(320, 200));
        area.setLineWrap(true);
        area.setEditable(false);
        area.setSize(110, 110);
        this.mainFrame.setVisible(true);
        show();
    }

    public void show() {
        numThreadsLabel.setText("Enter number of threads");
        percisionLabel.setText("Enter precision");

        this.controlPanel1.add(this.numThreadsLabel);
        this.controlPanel1.add(spinnerThreads);
        this.controlPanel1.add(this.percisionLabel);
        controlPanel1.add(spinnerPrecision);

        okButton.setActionCommand(output);
        okButton.addActionListener(new Listener());
        this.controlPanel3.add(okButton);
        controlPanel3.add(scrollPane);

    }

    class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int threadCount = (int) spinnerThreads.getValue();
            int precision = (int) spinnerPrecision.getValue();
            String output = e.getActionCommand();
            CalculateE.precision = precision;
            area.setText("");
            StartThreads.start(threadCount, area, output);
        }

    }
}