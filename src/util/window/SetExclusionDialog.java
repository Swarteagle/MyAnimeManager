package util.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import main.AnimeIndex;
import util.SearchBar;
import util.SortedListModel;

import java.awt.CardLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;

public class SetExclusionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private SearchBar searchBarCheck;
	private SearchBar searchBarExlusions;
	private JComboBox comboBox;
	private JButton cancelButton;
	private SortedListModel totalModel = new SortedListModel();
	private SortedListModel excludedModel = new SortedListModel();
	private JButton excludeButton;
	private JButton includeButton;
	private JList listToCheck;
	private JList listToExclude;
	/**
	 * Create the dialog.
	 */
	public SetExclusionDialog() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				cancelButton.requestFocusInWindow();
				loadModel();
			}
		});
		setTitle("Esclusioni");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 450, 260);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{15, 196, 16, 95, 106, 0};
		gbl_contentPanel.rowHeights = new int[]{23, 0, 0, 31, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel("Anime da Controllare");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridwidth = 2;
			gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			Component rigidArea = Box.createRigidArea(new Dimension(1, 90));
			GridBagConstraints gbc_rigidArea = new GridBagConstraints();
			gbc_rigidArea.gridheight = 3;
			gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
			gbc_rigidArea.gridx = 2;
			gbc_rigidArea.gridy = 0;
			contentPanel.add(rigidArea, gbc_rigidArea);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Anime Esclusi");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.gridwidth = 2;
			gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_1.gridx = 3;
			gbc_lblNewLabel_1.gridy = 0;
			contentPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		}
		{
			searchBarCheck = new SearchBar();
			searchBarCheck.setFont(AnimeIndex.segui.deriveFont(11f));
			ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(AnimeIndex.class.getResource("/image/search.png")));
			searchBarCheck.setIcon(icon);
			searchBarCheck.setForeground(Color.LIGHT_GRAY);
			searchBarCheck.setBackground(Color.BLACK);
			GridBagConstraints gbc_searchBarCheck = new GridBagConstraints();
			gbc_searchBarCheck.gridwidth = 2;
			gbc_searchBarCheck.insets = new Insets(0, 0, 5, 5);
			gbc_searchBarCheck.fill = GridBagConstraints.HORIZONTAL;
			gbc_searchBarCheck.gridx = 0;
			gbc_searchBarCheck.gridy = 1;
			contentPanel.add(searchBarCheck, gbc_searchBarCheck);
			searchBarCheck.setColumns(10);
		}
		{
			searchBarExlusions = new SearchBar();
			searchBarExlusions.setFont(AnimeIndex.segui.deriveFont(11f));
			ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(AnimeIndex.class.getResource("/image/search.png")));
			searchBarExlusions.setIcon(icon);
			searchBarExlusions.setForeground(Color.LIGHT_GRAY);
			searchBarExlusions.setBackground(Color.BLACK);
			GridBagConstraints gbc_searchBarExlusions = new GridBagConstraints();
			gbc_searchBarExlusions.gridwidth = 2;
			gbc_searchBarExlusions.insets = new Insets(0, 0, 5, 0);
			gbc_searchBarExlusions.fill = GridBagConstraints.HORIZONTAL;
			gbc_searchBarExlusions.gridx = 3;
			gbc_searchBarExlusions.gridy = 1;
			contentPanel.add(searchBarExlusions, gbc_searchBarExlusions);
			searchBarExlusions.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
			scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 11));
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 2;
			gbc_scrollPane.gridheight = 6;
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 2;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				JPanel totalPane = new JPanel();
				scrollPane.setViewportView(totalPane);
				totalPane.setLayout(new CardLayout(0, 0));
				
				listToCheck = new JList(totalModel);
				listToCheck.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listToCheck.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						excludeButton.setEnabled(true);
					}
				});
				listToCheck.setBounds(0, 0, 196, 159);
				totalPane.add(listToCheck, "totalList");
				
				JList searchListToCheck = new JList();
				searchListToCheck.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				searchListToCheck.setBounds(0, 0, 196, 159);
				totalPane.add(searchListToCheck, "searchList");
			}
		}
		{
			excludeButton = new JButton(">>");
			excludeButton.setEnabled(false);
			excludeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = (String) listToCheck.getSelectedValue();
					totalModel.removeElement(name);
					excludedModel.addElement(name);
					if (totalModel.isEmpty())
						excludeButton.setEnabled(false);
					listToExclude.clearSelection();
				}
			});
			GridBagConstraints gbc_excludeButton = new GridBagConstraints();
			gbc_excludeButton.insets = new Insets(0, 0, 5, 5);
			gbc_excludeButton.gridx = 2;
			gbc_excludeButton.gridy = 3;
			contentPanel.add(excludeButton, gbc_excludeButton);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
			scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 11));
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 2;
			gbc_scrollPane.gridheight = 6;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 3;
			gbc_scrollPane.gridy = 2;
			contentPanel.add(scrollPane, gbc_scrollPane);
			
			JPanel excludedPane = new JPanel();
			scrollPane.setViewportView(excludedPane);
			excludedPane.setLayout(new CardLayout(0, 0));
			
			listToExclude = new JList(excludedModel);
			listToExclude.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listToExclude.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					includeButton.setEnabled(true);
				}
			});
			listToExclude.setFont(null);
			listToExclude.setBounds(0, 0, 176, 159);
			excludedPane.add(listToExclude, "excludedList");
			
			JList searchListToExclude = new JList();
			searchListToExclude.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			searchListToExclude.setFont(null);
			searchListToExclude.setBounds(0, 0, 176, 159);
			excludedPane.add(searchListToExclude, "excludedSearchedList");
		}
		{
			includeButton = new JButton("<<");
			includeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = (String) listToExclude.getSelectedValue();
					excludedModel.removeElement(name);
					String type = (String) comboBox.getSelectedItem();
					if (type.equalsIgnoreCase("anime completati"))
					{
						if (AnimeIndex.completedMap.containsKey(name))
						{			
							totalModel.addElement(name);
						}
					}
					
					if (type.equalsIgnoreCase("anime in corso"))
					{
						if (AnimeIndex.airingMap.containsKey(name))
							totalModel.addElement(name);
					}
					
					if (type.equalsIgnoreCase("oav"))
					{
						if (AnimeIndex.ovaMap.containsKey(name))
							totalModel.addElement(name);
					}
					
					if (type.equalsIgnoreCase("film"))
					{
						if (AnimeIndex.filmMap.containsKey(name))
							totalModel.addElement(name);
					}
					
					if (type.equalsIgnoreCase("completi da vedere"))
					{
						if (AnimeIndex.completedToSeeMap.containsKey(name))
							totalModel.addElement(name);
					}
					
					if (excludedModel.isEmpty())
						includeButton.setEnabled(false);
					listToCheck.clearSelection();
				}
			});
			includeButton.setEnabled(false);
			GridBagConstraints gbc_includeButton = new GridBagConstraints();
			gbc_includeButton.insets = new Insets(0, 0, 5, 5);
			gbc_includeButton.gridx = 2;
			gbc_includeButton.gridy = 4;
			contentPanel.add(includeButton, gbc_includeButton);
		}
		{
			Component rigidArea = Box.createRigidArea(new Dimension(1, 20));
			GridBagConstraints gbc_rigidArea = new GridBagConstraints();
			gbc_rigidArea.gridheight = 4;
			gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
			gbc_rigidArea.gridx = 2;
			gbc_rigidArea.gridy = 5;
			contentPanel.add(rigidArea, gbc_rigidArea);
		}
		{
			comboBox = new JComboBox();
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					excludeButton.setEnabled(false);
					includeButton.setEnabled(false);
					String list = (String) comboBox.getSelectedItem();
					
					if (list.equalsIgnoreCase("anime completati"))
						changeModel(AnimeIndex.completedModel);
					
					if (list.equalsIgnoreCase("anime in corso"))
						changeModel(AnimeIndex.airingModel);
					
					if (list.equalsIgnoreCase("oav"))
						changeModel(AnimeIndex.ovaModel);
					
					if (list.equalsIgnoreCase("film"))
						changeModel(AnimeIndex.filmModel);
					
					if (list.equalsIgnoreCase("completi da vedere"))
						changeModel(AnimeIndex.completedToSeeModel);
				}
			});
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Anime Completati", "Anime in Corso", "OAV", "Film", "Completi Da Vedere"}));
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 2;
			gbc_comboBox.insets = new Insets(0, 0, 0, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 0;
			gbc_comboBox.gridy = 8;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JButton okButton = new JButton("Salva");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AnimeIndex.exclusionAnime.clear();
					Object[] array = excludedModel.toArray();
					for (int i = 0; i < array.length; i++) {
						String name = (String) array[i];
						AnimeIndex.exclusionAnime.add(name);
					}
					JButton but = (JButton) e.getSource();
					JDialog dialog = (JDialog) but.getTopLevelAncestor();
					dialog.dispose();
				}
			});
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_okButton.insets = new Insets(0, 0, 0, 5);
			gbc_okButton.gridx = 3;
			gbc_okButton.gridy = 8;
			contentPanel.add(okButton, gbc_okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			cancelButton = new JButton("Annulla");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JButton but = (JButton) arg0.getSource();
					JDialog dialog = (JDialog) but.getTopLevelAncestor();
					dialog.dispose();
				}
			});
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_cancelButton.gridx = 4;
			gbc_cancelButton.gridy = 8;
			contentPanel.add(cancelButton, gbc_cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
	}
	
	private void loadModel()
	{
		String[] excludedArray = AnimeIndex.exclusionAnime.toArray(new String[0]);
		excludedModel.addAll(excludedArray);
		
		Object[] totalArray = AnimeIndex.completedModel.toArray();
		for (int i = 0; i < totalArray.length; i++) 
		{
			String name = (String) totalArray[i];
			if (!excludedModel.contains(name))
				totalModel.addElement(name);
		}
		
	}
	
	private void changeModel(SortedListModel model)
	{
		totalModel.clear();
		Object[] totalArray = model.toArray();
		for (int i = 0; i < totalArray.length; i++) 
		{
			String name = (String) totalArray[i];
			if (!excludedModel.contains(name))
				totalModel.addElement(name);
		}
	}
}
