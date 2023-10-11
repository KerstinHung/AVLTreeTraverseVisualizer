import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
	private JTextField inputField = new JTextField(20);
	private JTextField searchField = new JTextField();
	private JTextArea preorderTextArea = new JTextArea();
	private JTextArea postorderTextArea = new JTextArea();
	private JTextArea inorderTextArea = new JTextArea();
	private JLabel findLabel = new JLabel("false");
	private JLabel insertLabel = new JLabel("Insert");
	private JLabel preorderLabel = new JLabel("preorder");
	private JLabel postorderLabel = new JLabel("postorder");
	private JLabel inorderLabel = new JLabel("inorder");
	private JLabel searchLabel = new JLabel("search");
	private JLabel countLabel = new JLabel("count");
	private JLabel countResultLabel = new JLabel("0");
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private JButton clearAllButton = new JButton("Clear All");
	private JButton searchButton = new JButton("Search");
	
	// 建立Layout物件
	GroupLayout layout = new GroupLayout(getContentPane());
	// 獲取螢幕解析度
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    // 建立此Frame唯一的AVL Tree
    private AVLTree avlt = new AVLTree();
    
	Frame() {
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
