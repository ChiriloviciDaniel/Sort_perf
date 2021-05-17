package perf_sort;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class sort_perf {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sort_perf window = new sort_perf();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public sort_perf() {
		initialize();
	}

	public static int N = 9999; // 9999900;
	public static int[] sequence = new int[N];
	public static int[] a;
	public static int[] b;
	public static int[] c;

	public static void random_tablou() {
		Random rd = new Random();
		for (int i = 0; i < N; i++) {
			sequence[i] = Math.abs(rd.nextInt(sequence.length));

		}
		JOptionPane.showMessageDialog(null, "ARRAY GENERATE!!!");
		a = sequence.clone();
		b = sequence.clone();
		c = sequence.clone();
	}

	public void shellSort(int[] sequence) {

		int increment = sequence.length / 2;
		while (increment > 0) 
		{
			for (int i = increment; i < sequence.length; i++) 
			{
				int j = i;
				int temp = sequence[i];
				while (j >= increment && sequence[j - increment] > temp) 
				{
					sequence[j] = sequence[j - increment];
					j = j - increment;

				}
				sequence[j] = temp;
			}

			if (increment == 2)
				increment = 1;
			else
				increment *= (1 / 2);
		}
	}

	public void bubbleSort(int[] sequence) {

		int n = sequence.length;
		int temp = 0;

		for (int i = 0; i < n; i++) {

			for (int j = 1; j < (n - i); j++) {

				if (sequence[j - 1] > sequence[j]) {

					temp = sequence[j - 1];
					sequence[j - 1] = sequence[j];
					sequence[j] = temp;
				}

			}
		}
	}

	public static void quickSort(int[] sequence, int begin, int end) {

		if (begin < end) {
			int partitionIndex = partition(sequence, begin, end);

			quickSort(sequence, begin, partitionIndex - 1);
			quickSort(sequence, partitionIndex + 1, end);
		}
	}

	private static int partition(int sequence[], int begin, int end) {
		int pivot = sequence[end];
		int i = (begin - 1);

		for (int j = begin; j < end; j++) {
			if (sequence[j] <= pivot) {
				i++;

				int temp = sequence[i];
				sequence[i] = sequence[j];
				sequence[j] = temp;
			}
		}

		int temp = sequence[i + 1];
		sequence[i + 1] = sequence[end];
		sequence[end] = temp;
		return i + 1;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 577, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Generate array");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				random_tablou();
			}

		});
		btnNewButton.setForeground(Color.GREEN);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(104, 247, 151, 31);
		frame.getContentPane().add(btnNewButton);

		lblNewLabel = new JLabel("Time sorted in seconds:");

		lblNewLabel.setBounds(281, 68, 204, 14);
		frame.getContentPane().add(lblNewLabel);

		btnNewButton_1 = new JButton("Bubble sort");
		btnNewButton_1.setForeground(Color.GREEN);
		btnNewButton_1.setBackground(Color.YELLOW);
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				long startTime = System.currentTimeMillis();
				bubbleSort(b);
				long end_Time = System.currentTimeMillis();
				float running_time = (end_Time - startTime) / 1000F;
				lblNewLabel.setText(String.valueOf(running_time));

			}
		});

		btnNewButton_1.setBounds(128, 60, 143, 31);
		frame.getContentPane().add(btnNewButton_1);

		btnNewButton_2 = new JButton("Quick sort");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// quickSort
				long startTime = System.currentTimeMillis();
				quickSort(c, 0, c.length - 1);
				long end_Time = System.currentTimeMillis();
				float running_time = (end_Time - startTime) / 1000F;
				lblNewLabel_2.setText(String.valueOf(running_time));
			}
		});
		btnNewButton_2.setForeground(Color.GREEN);
		btnNewButton_2.setBackground(Color.BLUE);
		btnNewButton_2.setBounds(128, 106, 143, 31);
		frame.getContentPane().add(btnNewButton_2);

		btnNewButton_3 = new JButton("Shell Sort");
		btnNewButton_3.setForeground(Color.GREEN);
		btnNewButton_3.setBackground(Color.RED);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				long startTime = System.currentTimeMillis();
				shellSort(a);
				long end_Time = System.currentTimeMillis();
				float running_time = (end_Time - startTime) / 1000F; // seconds
				lblNewLabel_1.setText(String.valueOf(running_time));

			}

		});
		btnNewButton_3.setBounds(128, 18, 143, 31);
		frame.getContentPane().add(btnNewButton_3);

		lblNewLabel_1 = new JLabel("Time sorted in seconds:");
		lblNewLabel_1.setBounds(281, 26, 204, 14);
		frame.getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Time sorted in seconds:");
		lblNewLabel_2.setBounds(281, 114, 204, 14);
		frame.getContentPane().add(lblNewLabel_2);
	}

}
