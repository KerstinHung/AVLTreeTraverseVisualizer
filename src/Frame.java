import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
	private JTextField inputField;
	private JTextField searchField;
	private JTextArea preorderTextArea;
	private JTextArea postorderTextArea;
	private JTextArea inorderTextArea;
	private JLabel findLabel;
	private JLabel insertLabel;
	private JLabel preorderLabel;
	private JLabel postorderLabel;
	private JLabel inorderLabel;
	private JLabel searchLabel;
	private JLabel countLabel;
	private JLabel countResultLabel;
	private JButton okButton;
	private JButton cancelButton;
	private JButton clearAllButton;
	private JButton searchButton;
	
	// 建立Layout物件
	GroupLayout layout = new GroupLayout(getContentPane());
	// 獲取螢幕解析度
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    // 建立此Frame唯一的AVL Tree
    private AVLTree avlt;
    
	Frame() {
        createComponent();
        initListener();
        initView();
    }
	private void initView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("AVL Tree Traverse Visualizer");
        // 設定視窗大小佔螢幕四分之一
        this.setSize(dimension.width / 2, dimension.height / 2);
        initLayout();
        this.pack();
    }
    private void createComponent(){
    	inputField = new JTextField(20);
    	searchField = new JTextField();
    	
    	preorderTextArea = new JTextArea();
    	postorderTextArea = new JTextArea();
    	inorderTextArea = new JTextArea();
    	
    	insertLabel = new JLabel("Insert");
    	preorderLabel = new JLabel("preorder");
    	postorderLabel = new JLabel("postorder");
    	inorderLabel = new JLabel("inorder");
    	searchLabel = new JLabel("search");
    	findLabel = new JLabel("false");
    	countLabel = new JLabel("count");
    	countResultLabel = new JLabel("0");
    	
    	okButton = new JButton("OK");
    	cancelButton = new JButton("Cancel");
    	clearAllButton = new JButton("Clear All");
    	searchButton = new JButton("Search");
    	
    	avlt = new AVLTree();
    }
    private void initLayout() {
    	getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	    		.addComponent(insertLabel)
                		.addComponent(preorderLabel)
                		.addComponent(postorderLabel)
                		.addComponent(inorderLabel)
                		.addComponent(searchLabel)
                		.addComponent(countLabel))
        		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        				.addComponent(inputField)
                		.addComponent(preorderTextArea)
                		.addComponent(postorderTextArea)
        	    		.addComponent(inorderTextArea)
        	    		.addGroup(layout.createSequentialGroup()
        	    				.addComponent(searchField)
        	    				.addComponent(searchButton)
        	    				.addComponent(findLabel))
        	    		.addComponent(countResultLabel)
        	    		)
        	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        	    		.addComponent(okButton)
        	    		.addComponent(cancelButton)
        	    		.addComponent(clearAllButton))
        	);
        layout.setVerticalGroup(layout.createSequentialGroup()
        	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        	        .addComponent(insertLabel)
        	        .addComponent(inputField)
        	        .addComponent(okButton))
        	    .addComponent(cancelButton)
        	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            	    	.addComponent(preorderLabel)
            	    	.addComponent(preorderTextArea))
        	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            	    	.addComponent(postorderLabel)
            	    	.addComponent(postorderTextArea))
        	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            	    	.addComponent(inorderLabel)
            	    	.addComponent(inorderTextArea))
        	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            	    	.addComponent(searchLabel)
            	    	.addComponent(searchField)
            	    	.addComponent(searchButton)
            	    	.addComponent(findLabel))
        	    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            	    	.addComponent(countLabel)
            	    	.addComponent(countResultLabel)
            	    	.addComponent(clearAllButton))
        	);
    }
    private void initListener() {
    	okButton.addActionListener(new insertListener());
    	cancelButton.addActionListener(new cancelListener());
    	clearAllButton.addActionListener(new clearListener());
    	searchButton.addActionListener(new searchListener());
    }

	private class insertListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
        	String input = inputField.getText();
        	if(!input.equals("")) {
        		int data = Integer.valueOf(input);
            	avlt.insert(data);
            	preorderTextArea.setText(avlt.preorder());
            	postorderTextArea.setText(avlt.postorder());
            	inorderTextArea.setText(avlt.postorder());
        	}
        	inputField.setText("");
        	countResultLabel.setText(Integer.toString(avlt.countNodes()));
        }
    }
	private class cancelListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
        	inputField.setText("");
        }
    }
	private class clearListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
        	avlt.makeEmpty();
        	preorderTextArea.setText(avlt.preorder());
        	postorderTextArea.setText(avlt.postorder());
        	inorderTextArea.setText(avlt.postorder());
        	countResultLabel.setText(Integer.toString(avlt.countNodes()));
        	findLabel.setText("false");
        }
    }
	private class searchListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String input = searchField.getText();
			boolean flag = false;
			if(!input.equals("")) {
				int data = Integer.valueOf(input);
				flag = avlt.search(data);
        	}
			if(flag) {
				findLabel.setText("true");
			}
			else {
				findLabel.setText("false");
			}
			searchField.setText("");
        }
	}

}
