package util;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.jdesktop.swingx.JXTaskPane;


public class SuggestionTaskPane extends JXTaskPane {
	
	/**
	 * Create the panel.
	 */
	public SuggestionTaskPane(String name,String text)
	{	
		super(name);
		this.setCollapsed(true);
		GridBagConstraints gbc_taskPane = new GridBagConstraints();
		gbc_taskPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_taskPane.gridx = 0;
		gbc_taskPane.gridy = 0;
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea(text);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		this.getContentPane().add(textArea, BorderLayout.CENTER);
	
	}
	
}