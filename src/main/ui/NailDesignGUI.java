package ui;

import model.Catalog;
import model.EventLog;
import model.Favorites;
import model.NailDesign;
import persistence.JsonReader;
import persistence.JsonWriter;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import model.Event;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//main graphical user interface for the nail design finder application
@ExcludeFromJacocoGeneratedReport
public class NailDesignGUI extends JFrame implements ActionListener {

    //data fields
    private Catalog catalog;
    private Favorites favorites;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/favorites.json";

    //ui components
    private JPanel catalogPanel;
    private JList<String> favoritesList;
    private DefaultListModel<String> listModel;
    private JLabel statusLabel;
    private JTextField searchField;

    //EFFECTS: initializes the application, loads data structures, and sets up the ui
    public NailDesignGUI() {
        super("Irene's Nail Design Finder");

        //initialize data
        catalog = new Catalog();
        favorites = new Favorites();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        //show splash screen
        new SplashScreen();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
            }
        });

        //frame settings
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //setup ui components
        initUI();

        //finalize
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: constructs the layout: top(search), center(split), bottom(actions)
    private void initUI() {
        //create top search panel
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        //create center split pane
        JSplitPane splitPane = createMainSplitPane();
        add(splitPane, BorderLayout.CENTER);

        //create bottom action panel
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    //MODIFIES: this
    //EFFECTS: creates the top panel with tag guide and search field
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Helper method 1: Add text labels
        addGuideLabels(panel);
        
        panel.add(Box.createVerticalStrut(10));

        // Helper method 2: Create input area
        JPanel inputPanel = createInputPanel();
        panel.add(inputPanel);

        return panel;
    }

    //MODIFIES: panel
    //EFFECTS: creates and adds the guide labels to the panel
    private void addGuideLabels(JPanel panel) {
        JLabel guide1 = new JLabel("Color: blue, red, white, black, pink");
        JLabel guide2 = new JLabel("Shape: long, short, round, square");
        JLabel guide3 = new JLabel("Style: full-color,glitter, french, gem");

        guide1.setAlignmentX(Component.CENTER_ALIGNMENT);
        guide2.setAlignmentX(Component.CENTER_ALIGNMENT);
        guide3.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(guide1);
        panel.add(guide2);
        panel.add(guide3);
    }

    //MODIFIES: this
    //EFFECTS: returns a panel containing the search field and buttons
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        JButton showAllBtn = new JButton("Show All");

        searchBtn.setActionCommand("search");
        searchBtn.addActionListener(this);
        
        showAllBtn.setActionCommand("showAll");
        showAllBtn.addActionListener(this);

        inputPanel.add(new JLabel("Enter Tags: "));
        inputPanel.add(searchField);
        inputPanel.add(searchBtn);
        inputPanel.add(showAllBtn);

        return inputPanel;
    }

    //MODIFIES: this
    //EFFECTS: creates the split pane with catalog (left) and favorites (right)
    private JSplitPane createMainSplitPane() {
        //catalog panel setup
        catalogPanel = new JPanel();
        catalogPanel.setLayout(new GridLayout(0, 3, 10, 10)); 
        catalogPanel.setBackground(Color.WHITE);
        
        //load initial designs
        updateCatalogGallery(catalog.getAllDesigns());

        JScrollPane catalogScroll = new JScrollPane(catalogPanel);
        catalogScroll.setBorder(new TitledBorder("Catalog Gallery (Click to Add)"));

        //favorites panel setup
        listModel = new DefaultListModel<>();
        favoritesList = new JList<>(listModel);
        JScrollPane favoritesScroll = new JScrollPane(favoritesList);
        favoritesScroll.setBorder(new TitledBorder("My Favorites"));
        favoritesScroll.setPreferredSize(new Dimension(300, 0));

        //create split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, catalogScroll, favoritesScroll);
        splitPane.setResizeWeight(0.7);
        return splitPane;
    }

    //MODIFIES: this
    //EFFECTS: creates the bottom panel with persistent buttons and status label
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        //button row
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton removeBtn = new JButton("Remove Selected");
        JButton saveBtn = new JButton("Save");
        JButton loadBtn = new JButton("Load");

        //register action commands
        removeBtn.setActionCommand("remove");
        removeBtn.addActionListener(this);

        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(this);

        loadBtn.setActionCommand("load");
        loadBtn.addActionListener(this);

        btnPanel.add(removeBtn);
        btnPanel.add(saveBtn);
        btnPanel.add(loadBtn);

        //status label
        statusLabel = new JLabel("Welcome! Enter tags to search or click designs to add!");
        statusLabel.setBorder(new EmptyBorder(5, 10, 5, 10));

        panel.add(btnPanel, BorderLayout.CENTER);
        panel.add(statusLabel, BorderLayout.SOUTH);

        return panel;
    }

    //MODIFIES: this
    //EFFECTS: updates the gallery grid with the given list of designs
    @SuppressWarnings("methodlength")
    private void updateCatalogGallery(ArrayList<NailDesign> designs) {
        catalogPanel.removeAll();

        if (designs.isEmpty()) {
            JLabel noResult = new JLabel("No designs found matching your tags.");
            catalogPanel.add(noResult);
        } else {
            for (NailDesign d : designs) {
                JButton designBtn = new JButton(d.getName());
                designBtn.setPreferredSize(new Dimension(120, 120));
                
                //image handling logic
                String imagePath = "./data/" + d.getImage() + ".png";
                ImageIcon icon = new ImageIcon(imagePath);
                
                if (icon.getIconWidth() > 0) {
                    //resize image if exists
                    Image img = icon.getImage().getScaledInstance(180, 150, Image.SCALE_SMOOTH);
                    designBtn.setIcon(new ImageIcon(img));
                    designBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
                    designBtn.setHorizontalTextPosition(SwingConstants.CENTER);
                } else {
                    //fallback if no image
                    designBtn.setBackground(new Color(240, 240, 255));
                }

                //add specific action listener using anonymous inner class
                designBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        addDesignToFavorites(d);
                    }
                });

                catalogPanel.add(designBtn);
            }
        }

        catalogPanel.revalidate();
        catalogPanel.repaint();
    }

    //MODIFIES: this
    //EFFECTS: adds design to favorites and updates ui
    private void addDesignToFavorites(NailDesign design) {
        favorites.addDesign(design);
        updateFavoritesList();
        statusLabel.setText("Added: " + design.getName());
    }

    //MODIFIES: this
    //EFFECTS: updates the jlist based on current favorites data
    private void updateFavoritesList() {
        listModel.clear();
        for (NailDesign d : favorites.getDesigns()) {
            listModel.addElement(d.getName() + " " + d.getTags().toString());
        }
    }

    //MODIFIES: this
    //EFFECTS: handles button actions (search, show all, remove, save, load)
    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("search")) {
            String text = searchField.getText();
            ArrayList<String> tags = new ArrayList<>();
            for (String s : text.split(",")) {
                tags.add(s.trim());
            }
            ArrayList<NailDesign> results = catalog.searchByTags(tags);
            updateCatalogGallery(results);
            statusLabel.setText("Found " + results.size() + " designs.");

        } else if (cmd.equals("showAll")) {
            updateCatalogGallery(catalog.getAllDesigns());
            searchField.setText("");
            statusLabel.setText("Showing all designs.");

        } else if (cmd.equals("remove")) {
            int index = favoritesList.getSelectedIndex();
            if (index != -1) {
                NailDesign toRemove = favorites.getDesigns().get(index);
                favorites.deleteDesign(toRemove);
                updateFavoritesList();
                statusLabel.setText("Removed design.");
            } else {
                statusLabel.setText("Please select a design to remove.");
            }

        } else if (cmd.equals("save")) {
            try {
                jsonWriter.open();
                jsonWriter.write(favorites);
                jsonWriter.close();
                statusLabel.setText("Saved to " + JSON_STORE);
            } catch (FileNotFoundException ex) {
                statusLabel.setText("Error: Unable to save file.");
            }

        } else if (cmd.equals("load")) {
            try {
                favorites = jsonReader.read();
                updateFavoritesList();
                statusLabel.setText("Loaded from " + JSON_STORE);
            } catch (IOException ex) {
                statusLabel.setText("Error: Unable to load file.");
            }
        }
    }

    public static void main(String[] args) {
        new NailDesignGUI();
    }
}