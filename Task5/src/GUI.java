import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI extends JFrame{

	private final int TEXT_SIZE = 10;
	
	private JPanel panel;
	private JLabel fileNameLabel = new JLabel("������ �� ����� ��� ������� �� ��� ������ ��� �������");
	private JTextField fileNameField = new JTextField(TEXT_SIZE);
	private JButton readButton = new JButton("��������");
	private JLabel readSuccess = new JLabel();
	private JLabel inputTapeLabel = new JLabel("������ �� ����������� ��� �������:");
	private JTextField inputTapeField = new JTextField(TEXT_SIZE);
	private JLabel inputHeadLabel = new JLabel("����� �� ���� ��� �������:");
	private JTextField inputHeadField = new JTextField(TEXT_SIZE);
	private JLabel resultLabel = new JLabel();
	private JLabel retryLabel = new JLabel("��� �� ���������� ����� ���� ��� ������ ��������");
	private JLabel stateLabel = new JLabel();
	private JButton executeButton  = new JButton("��������");
	private JButton exitButton = new JButton("������");

	public GUI(){
		
		panel = new JPanel();
		panel.add(fileNameLabel);
		panel.add(fileNameField);
		panel.add(readButton);
		panel.add(readSuccess);
		panel.add(inputTapeLabel);
		panel.add(inputTapeField);
		panel.add(inputHeadLabel);
		panel.add(inputHeadField);
		panel.add(stateLabel);
		panel.add(resultLabel);
		panel.add(retryLabel);
		panel.add(executeButton);
		panel.add(exitButton);
		
		this.setContentPane(panel);
		
		ButtonListener listener = new ButtonListener();
		
		readButton.addActionListener(listener);
		executeButton.addActionListener(listener);
		exitButton.addActionListener(listener);
		
		
		this.setSize(405, 250);
		this.setVisible(true);
		this.setTitle("��������: ������� 5");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	class ButtonListener implements ActionListener{

		ReadTuringDesc readTuringDesc = new ReadTuringDesc();
		Transition transitionMap = new Transition();
		LinkedList<Character> inputLinkedList = new LinkedList<Character>();
		String fileName = "";
		
		int startState, turingHeadPosition;
		String userInput;
		
		public void actionPerformed(ActionEvent actionEvent) {
			//������ ��������
			if (actionEvent.getSource().equals(readButton)) {
				fileName = fileNameField.getText();
				if (fileName.equals("")) 
					System.out.println("��� ����� ������� ����� �������");
				else{
					Boolean readingProcess = readTuringDesc.readData(fileName);
					if(readingProcess){
						readSuccess.setText("�� ������ �� ���� ������� ���������� ��������!");
						startState = readTuringDesc.getStartingState();
						transitionMap = readTuringDesc.getTransitionMap();
					}else
						readSuccess.setText("������ �������� ���������. ��������� �� ��� ������� ���������!");
				}	
			
			//K����� ��������
			}else if(actionEvent.getSource().equals(executeButton)){
				if(fileName.equals(""))
					resultLabel.setText("��� ����� ������ ������ ������ �� ������� ��� �� ������");
				else{
					userInput = inputTapeField.getText();
					char[] userInputToCharArray = userInput.toCharArray();
					while(!inputLinkedList.isEmpty())
						inputLinkedList.removeFirst();
					for(int i=0; i<userInputToCharArray.length; i++)
						inputLinkedList.add(i, userInputToCharArray[i]);					
					inputLinkedList.addLast('#');
					
					turingHeadPosition = Integer.parseInt(inputHeadField.getText());
					int goalMachineState = startState;	
					int goalTuringHeadPosition = turingHeadPosition;
				
					while(true){
						char tapeTransitionChar;						
						ArrayList<Character> arrayList = new ArrayList<Character>();
						
						arrayList = transitionMap.getGoalState(goalMachineState, inputLinkedList.get(goalTuringHeadPosition));
						if(arrayList == null || arrayList.isEmpty()){
							resultLabel.setText("������ �������� �� ������ �������� � � �������� ��� ������");
							break;
						}
						
						tapeTransitionChar = arrayList.get(0);
						goalMachineState = Integer.parseInt(String.valueOf(arrayList.get(1)));
						
						if(goalMachineState == 0){
							resultLabel.setText("� ������ ���������");
							break;
						}
						
						if(tapeTransitionChar == 'R')
							goalTuringHeadPosition++;
						else if(tapeTransitionChar == 'L')
							goalTuringHeadPosition--;
						else{
							inputLinkedList.set(goalTuringHeadPosition, tapeTransitionChar);						
						}						
					}
					
					retryLabel.setText("�� ������ �� �������������� ������ ���� ��� ������ ��������");					
				}				
			//K����� ������
			}else if(actionEvent.getSource().equals(exitButton))
				System.exit(0);
		}
		
	}
	
	
	
	
	
	
}
