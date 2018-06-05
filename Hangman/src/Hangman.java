import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Hangman implements ActionListener {
	
	static char[] let; 
	static char[] letters;
	static String word;
	static ArrayList<Character> charList;
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
		
		Stack<String> wordStack = new Stack<String>();
		Random rand = new Random();
		while(wordStack.size() < words.size()) {
			int x = rand.nextInt(words.size());
			String holder = words.get(x);
			if(holder != null) {
			wordStack.add(holder);
			words.set(x, null);
			}
			
		}
		System.out.println(Arrays.toString(wordStack.toArray()));
		
		
		charList = new ArrayList<Character>();
		
		for(int i = 0; i < wordStack.size(); i++) {
			word = wordStack.pop();
			letters = word.toCharArray();
			let = new char[letters.length];
			for(int j = 0; j< letters.length; j++) {
				if(letters[j] != '-') {
				let[j] = '_';
				}
			}
			
			
			h.buildGUI(let);
			
		}
		
		
		
	}
	
	
	
	JFrame frame;
	JPanel panel;
	JTextField tf;
	JButton button;
	JLabel label, label2, label3, label4;
	int lives = 10;
	public void buildGUI(char[] let) {
	frame = new JFrame();
	frame.setSize(600, 400);
	panel = new JPanel();
	label = new JLabel();
	label2 = new JLabel();
	label3 = new JLabel();
	label4 = new JLabel();
	label.setText("Enter a letter:");
	panel.add(label);
	tf = new JTextField(5);
	button = new JButton();	
	button.setText("Submit");
	
	frame.add(panel);
	panel.add(tf);
	panel.add(button);
	panel.add(label2);
	panel.add(label3);
	panel.add(label4);
	
	label4.setText("Lives: " + lives);
	label3.setText("Guessed Letters: " + Arrays.toString(charList.toArray()));
	
	button.addActionListener(this);
	label2.setText(Arrays.toString(let));
	
	frame.setVisible(true);
	
	
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(button)) {
			String userInput = tf.getText();
			char value = userInput.charAt(0);
			boolean found = false;
			for(int i = 0; i < let.length; i++) {
				if(value == letters[i]) {
					found = true;
					let[i] = letters[i];
					label2.setText(Arrays.toString(let));
				}
			}
			
			if(!found) {
				charList.add(value);
				label3.setText("Guessed Letters: " + Arrays.toString(charList.toArray()));
				lives--;
				label4.setText("Lives: " + lives);
			}
			if(lives == 0) {
				label4.setText("Game Over");
				frame.remove(panel);
				frame.setTitle("Game Over");
			}
		
			int counter = 0;
			for(int i = 0; i < let.length; i++) {
				if(let[i] == '_') {
					counter++;
				}
			}
			
			if(counter == 0) {
				frame.remove(panel);
				frame.setTitle("Good Job");
			}
			System.out.println(word);
		}
	}
}
