import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Hangman {
	public static void main(String[] args) throws FileNotFoundException {
		Hangman h = new Hangman();
		int numOfValues;
		String userAmount = JOptionPane.showInputDialog("How many values?");
		numOfValues = Integer.parseInt(userAmount);
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<String> words = new ArrayList<String>();
		
		
		
		
		for(int i = 0; i < numOfValues; i++) {
			Random rand = new Random();
			int x = rand.nextInt(3000);
			list.add(x);
			
		}
		
		
		
		boolean sort = true;
		while(sort) {
		sort = false;
		for(int i = 0; i < numOfValues-1; i++) {
			if(list.get(i) > list.get(i+1)) {
				int holder = list.get(i);
				list.set(i, list.get(i+1));
				list.set(i+1, holder);
				sort = true;
			}
		}
		}
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		try {
			BufferedReader b = new BufferedReader(new FileReader("src/dictionary.txt"));
			
			String line = b.readLine();
			int counterWords = 1;
			int counterList = 0;
			while(line!= null && counterList < list.size()) {
				
				if(counterWords == list.get(counterList)) {
					
					words.add(line);
					counterList++;
				}
				
				counterWords++;
				line = b.readLine();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		for(int i = 0; i < words.size(); i++) {
			System.out.println(words.get(i));
		}
		
		
		
		h.buildGUI();
		
		
	}
	JFrame frame;
	JPanel panel;
	JTextField tf;
	JButton button;
	public void buildGUI() {
	frame = new JFrame();
	frame.setSize(600, 400);
	panel = new JPanel();
	tf = new JTextField(5);
	button = new JButton();	
	button.setText("Submit");
	frame.add(panel);
	panel.add(tf);
	panel.add(button);
	frame.setVisible(true);
	
	
	
	}
}
