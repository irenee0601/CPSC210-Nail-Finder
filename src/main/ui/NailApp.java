package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Catalog;
import model.Favorites;
import model.NailDesign;
import persistence.JsonReader;
import persistence.JsonWriter;
import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class NailApp {
    private Catalog catalog;
    private Favorites favorites;
    private Scanner input;
    
    
    // EFFECTS: runs the design app
    public NailApp() {
        catalog = new Catalog();
        favorites = new Favorites();
        input = new Scanner(System.in);
        runApp();
    }
    
    /**
     * MODIFIES: this
     * EFFECTS: displays menu and processes user commands
     */
    private void runApp() {
        boolean flag = true;
        while (flag) {
            printMenu();
            String cmd = input.nextLine().toLowerCase();
            if (cmd.equals("q")) {
                flag = false;
            } else if (cmd.equals("1")) {
                searchDesigns();
            } else if (cmd.equals("2")) {
                viewFavorites();
            } else if (cmd.equals("3")) {
                addFavorite();
            } else if (cmd.equals("4")) {
                deleteFavorite();     
            } else if (cmd.equals("5")) {
                saveFavorites();
            } else if (cmd.equals("6")) {
                loadFavorites();
            } else {
                System.out.println("Invalid command.");
            }
        }

        System.out.println("Goodbye!<3");
    }

    private void printMenu() {
        System.out.println("\n----Irene's Nail Design Finder ----");
        System.out.println("1 -> search designs by tags");
        System.out.println("2 -> View my favorites");
        System.out.println("3 -> Add design to favorites");
        System.out.println("4 -> Delete design from favorites");
        System.out.println("5 -> Save favorites to file");
        System.out.println("6 -> Load favorites from file");
        System.out.println("q -> Quit");
        System.out.println("Enter choice: ");
    }
    
    /**
     * EFFECTS: prompts user for tags and displays matching designs
     */
    private void searchDesigns() {
        System.out.println("Enter tags (comma separated): ");
        String line = input.nextLine();
        ArrayList<String> tags = new ArrayList<>();
        for (String tag: line.split(",")) {   //쉼표기준으로 나눔 
            tags.add(tag.trim());  //앞뒤 공백 제거한다음에 추가
        }

        ArrayList<NailDesign> results = catalog.searchByTags(tags); //서치바이태그 메서드 호출해서 사용자가 입력한 태그 목록에 맞는 NailDesign들을 찾음
        if (results.isEmpty()) {
            System.out.println("No matching designs found.");
        } else {
            System.out.println("Matching designs");
            // Not generating Image because this is Console UI
            for (NailDesign d: results) {
                System.out.println(" - " + d.getName());
            }
        }
    }

    /**
     * EFFECTS: prompts user for a design name and adds it to favorites if it exists in catalog
     */
    private void addFavorite() {
        System.out.println("Enter design name to add: ");
        String name = input.nextLine();
        // find design by name
        for (NailDesign d : catalog.getAllDesigns()) {
            if (d.getName().equals(name)) {
                favorites.addDesign(d);
                System.out.println("Added to favorites: " + d.getName());
                return;
            }
        }
        System.out.println("Design not found in catalog.");
    }

    /**
     * EFFECTS: displays all designs currently in favorites
     */
    private void viewFavorites() {
        System.out.println("Your favorite designs: ");
        for (NailDesign d : favorites.getDesigns()) {
            System.out.println(" - " + d.getName());
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: prompts user for a design name and removes it from favorites if found
     */
    private void deleteFavorite() {
        System.out.print("Enter design name to remove: ");
        String name = input.nextLine();
        for (NailDesign d : new ArrayList<>(favorites.getDesigns())) { // 안전한 반복용 복사
            if (d.getName().equalsIgnoreCase(name)) {  //인풋 네임이랑 현재 디자인 이름이랑 같은지 확인
                favorites.deleteDesign(d);
                System.out.println("Removed from favorites: " + d.getName());
                return;
            }
        }
        System.out.println("Design not found in favorites.");
    }
    /**
    * EFFECTS: saves current favorites to ./data/favorites.json file
    */

    private void saveFavorites() {
        try {
            JsonWriter writer = new JsonWriter("./data/favorites.json");
            writer.open();
            writer.write(favorites);
            writer.close();
            System.out.println("Favorites saved successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to save favorites.");
        }
    }

    /**
     * MODIFIES: this
     * EFFECTS: loads favorites from ./data/favorites.json file if available
     */
    private void loadFavorites() {
        try {
            JsonReader reader = new JsonReader("./data/favorites.json");
            favorites = reader.read();
            System.out.println("Favorites loaded successfully!");
            viewFavorites();
        } catch (IOException e) {
            System.out.println("Error: Unable to load favorites.");
        }
    }
}
