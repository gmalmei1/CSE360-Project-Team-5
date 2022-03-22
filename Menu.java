package com.restaurant;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Comparator;


class LowToHighPriceComparator implements Comparator<MenuItem>{

    @Override
    public int compare(MenuItem item1, MenuItem item2) {

        //if the first price is less than the second, return -1
        if (item1.getPrice() < item2.getPrice()){
            return -1;
        }
        //if the prices are equal, return 0
        else if (item1.getPrice() == item2.getPrice()){
            return 0;
        }
        //if the first price is more than the second, return 1
        return 1;
    }
}

class HighToLowPriceComparator implements Comparator<MenuItem>{

    @Override
    public int compare(MenuItem item1, MenuItem item2) {

        //if the first price is more than the second, return -1
        if (item1.getPrice() > item2.getPrice()){
            return -1;
        }
        //if the prices are equal, return 0
        else if (item1.getPrice() == item2.getPrice()){
            return 0;
        }
        //if the first price is less than the second, return 1
        return 1;
    }
}

public class Menu {

    public ArrayList<MenuItem> items;
    public int menuSize;
    public int itemMin;

    public ArrayList<MenuItem> items_byType;
    public ArrayList<MenuItem> items_byName;
    public ArrayList<MenuItem> items_byAllergen;

    public int matches;

    public ArrayList<MenuItem> items_notByType;
    public ArrayList<MenuItem> items_notByName;
    public ArrayList<MenuItem> items_notByAllergen;

    public ArrayList<String> types;
    public ArrayList<String> allergens;

    //create empty menu w/ specific types and allergens
    public Menu(ArrayList<String> types, ArrayList<String> allergens){
        items = new ArrayList<>();
        menuSize = 0;
        itemMin = 5;

        items_byType = new ArrayList<>();
        items_byName = new ArrayList<>();
        items_byAllergen = new ArrayList<>();

        matches = 0;

        items_notByType = new ArrayList<>();
        items_notByName = new ArrayList<>();
        items_notByAllergen = new ArrayList<>();

        this.types = types;
        this.allergens = allergens;
    }

    public MenuItem findItem(String name){
        for (MenuItem m : items){
            if (m.getName().equalsIgnoreCase(name)){
                return m;
            }
        }
        return null;
    }

    //function called if Admin adds to menu: create item from parameters
    // and add it to the menu, increase menu item count

    public void addToMenu(String name, double price, String type, String description, int timeToMake, Image picture){
        items.add(new MenuItem(name, price, type, description, timeToMake, picture));
        menuSize++;
    }

    //function called if Admin removes item in menu by name: search for items
    // that have the same name as the name given and if there is a match,
    // then remove that from the list

    public void removeFromMenu(String name){
        if (items.removeIf(menuItem -> name.equalsIgnoreCase(menuItem.getName()))){
            menuSize--;
        }
    }

    public ArrayList<MenuItem> searchForItemByType(String type){

        // figure out if there is a valid type (if not, return empty list
        boolean typeValid = false;
        for (String t : types){
            if (type.equals(t)) {
                typeValid = true;
                break;
            }
        }
        if (!typeValid){
            return null;
        }

        //reset found/not found list from last time called
        items_byType = new ArrayList<>();
        items_notByType = new ArrayList<>();

        //for each item in the menu, add it to the found list if matched:
        //else add to not found list
        for (MenuItem m : items){
            if (type.equalsIgnoreCase(m.getType())){
                items_byType.add(m);
            }
            else {
                items_notByType.add(m);
            }
        }

        //update menu such that items by type appear before the rest
        items.clear();
        items.addAll(items_byType);
        items.addAll(items_notByType);

        matches = items_byType.size();  //update how many items by type were found

        return items;
    }

    public ArrayList<MenuItem> searchForItemByAllergen(String allergen){

        // figure out if there is a valid type (if not, return empty list
        boolean allergenValid = false;
        for (String a : allergens){
            if (allergen.equals(a)) {
                allergenValid = true;
                break;
            }
        }
        if (!allergenValid){
            return null;
        }

        //reset type list from last time called
        items_byAllergen = new ArrayList<>();

        //add any item to found list if somewhere in the description contains the allergen queried
        for (MenuItem m: items){
            if (m.getDescription().toLowerCase().contains(allergen.toLowerCase())){
                items_byAllergen.add(m);
            }
            else {
                items_notByAllergen.add(m);
            }
        }
        //update menu such that items by allergen appear before the rest
        items.clear();
        items.addAll(items_byAllergen);
        items.addAll(items_notByAllergen);

        matches = items_byAllergen.size();

        return items;
    }

    public ArrayList<MenuItem> searchForItemByName(String name){

        //reset name list from last time called
        items_byName = new ArrayList<>();

        //add any item to found list if the name matches the query
        for (MenuItem m : items){
            if (name.equals(m.getName())){
                items_byName.add(m);
            }
            else {
                items_notByName.add(m);
            }
        }

        //update menu such that items by allergen appear before the rest
        items.clear();
        items.addAll(items_byName);
        items.addAll(items_notByName);

        matches = items_byName.size();

        return items_byName;
    }

    public ArrayList<MenuItem> searchByPrice(boolean lowToHigh){

        if (lowToHigh){
            items.sort(new LowToHighPriceComparator());
        }
        else {
            items.sort(new HighToLowPriceComparator());
        }
        return items;
    }

}
