package com.example.grocerylist.model;

import java.util.HashMap;

//maps item display names to image names
    public class GroItemMap {
        HashMap<String, String> groItemMap;
        public GroItemMap () {
            // Create a HashMap to map display names to image names
            groItemMap = new HashMap<>();

            // Populate the HashMap
            // The key is the display name of the item
            // The value is the image name of the item
            // i used excel to get the image names of all the items: https://www.youtube.com/watch?v=F7SE5h7AUBg
            // then i used ai to generate the appropriate display names according to the image names and
            // add all these put statements to the hashmap (would have been tedious to do manually)
            // considered using a csv file but this makes it more efficient and easier to read
            groItemMap.put("All-Purpose Cleaner", "item_all-purpose_cleaner");
            groItemMap.put("Almond Milk", "item_almond_milk");
            groItemMap.put("Aluminum Foil", "item_aluminum_foil");
            groItemMap.put("Apple Juice", "item_apple_juice");
            groItemMap.put("Apples", "item_apples");
            groItemMap.put("Avocados", "item_avocados");
            groItemMap.put("Baby Food", "item_baby_food");
            groItemMap.put("Baby Formula", "item_baby_formula");
            groItemMap.put("Baby Wipes", "item_baby_wipes");
            groItemMap.put("Bacon", "item_bacon");
            groItemMap.put("Bagels", "item_bagels");
            groItemMap.put("Baking Powder", "item_baking_powder");
            groItemMap.put("Baking Soda", "item_baking_soda");
            groItemMap.put("Bananas", "item_bananas");
            groItemMap.put("Band-Aids", "item_band-aids");
            groItemMap.put("Basil", "item_basil");
            groItemMap.put("BBQ Sauce", "item_bbq_sauce");
            groItemMap.put("Beer", "item_beer");
            groItemMap.put("Bell Peppers", "item_bell_peppers");
            groItemMap.put("Black Pepper", "item_black_pepper");
            groItemMap.put("Body Wash", "item_body_wash");
            groItemMap.put("Bread", "item_bread");
            groItemMap.put("Bread Crumbs", "item_bread_crumbs");
            groItemMap.put("Broccoli", "item_broccoli");
            groItemMap.put("Broth", "item_broth");
            groItemMap.put("Butter", "item_butter");
            groItemMap.put("Caesar Salad Dressing", "item_caesar_salad_dressing");
            groItemMap.put("Canned Beans", "item_canned_beans");
            groItemMap.put("Canned Corn", "item_canned_corn");
            groItemMap.put("Canned Soup", "item_canned_soup");
            groItemMap.put("Canned Tomatoes", "item_canned_tomatoes");
            groItemMap.put("Canned Tuna", "item_canned_tuna");
            groItemMap.put("Canned Vegetables", "item_canned_vegetables");
            groItemMap.put("Carrots", "item_carrots");
            groItemMap.put("Cat Food", "item_cat_food");
            groItemMap.put("Cat Litter", "item_cat_litter");
            groItemMap.put("Cereal", "item_cereal");
            groItemMap.put("Cheese Slices", "item_cheese_slices");
            groItemMap.put("Chicken Breasts", "item_chicken_breasts");
            groItemMap.put("Chips", "item_chips");
            groItemMap.put("Cinnamon", "item_cinnamon");
            groItemMap.put("Coconut Milk", "item_coconut_milk");
            groItemMap.put("Coffee", "item_coffee");
            groItemMap.put("Cold Medicine", "item_cold_medicine");
            groItemMap.put("Conditioner", "item_conditioner");
            groItemMap.put("Cottage Cheese", "item_cottage_cheese");
            groItemMap.put("Crackers", "item_crackers");
            groItemMap.put("Croissants", "item_croissants");
            groItemMap.put("Cucumbers", "item_cucumbers");
            groItemMap.put("Cupcakes", "item_cupcakes");
            groItemMap.put("Curry Paste", "item_curry_paste");
            groItemMap.put("Deli Meats", "item_deli_meats");
            groItemMap.put("Diapers", "item_diapers");
            groItemMap.put("Dish Soap", "item_dish_soap");
            groItemMap.put("Dog Food", "item_dog_food");
            groItemMap.put("Dog Treats", "item_dog_treats");
            groItemMap.put("Eggs", "item_eggs");
            groItemMap.put("Feta Cheese", "item_feta_cheese");
            groItemMap.put("Flour", "item_flour");
            groItemMap.put("Frozen Chicken Nuggets", "item_frozen_chicken_nuggets");
            groItemMap.put("Frozen Dinner", "item_frozen_dinner");
            groItemMap.put("Frozen Fruits", "item_frozen_fruits");
            groItemMap.put("Frozen Pizza", "item_frozen_pizza");
            groItemMap.put("Frozen Veggies", "item_frozen_veggies");
            groItemMap.put("Garlic Powder", "item_garlic_powder");
            groItemMap.put("Granola Bars", "item_granola_bars");
            groItemMap.put("Grapes", "item_grapes");
            groItemMap.put("Ground Beef", "item_ground_beef");
            groItemMap.put("Hand Sanitizer", "item_hand_sanitizer");
            groItemMap.put("Hummus", "item_hummus");
            groItemMap.put("Ice Cream", "item_ice_cream");
            groItemMap.put("Italian Salad Dressing", "item_italian_salad_dressing");
            groItemMap.put("Jam", "item_jam");
            groItemMap.put("Ketchup", "item_ketchup");
            groItemMap.put("Laundry Detergent", "item_laundry_detergent");
            groItemMap.put("Lettuce", "item_lettuce");
            groItemMap.put("Maple Syrup", "item_maple_syrup");
            groItemMap.put("Mayonnaise", "item_mayonnaise");
            groItemMap.put("Milk", "item_milk");
            groItemMap.put("Mozzarella Cheese", "item_mozzarella_cheese");
            groItemMap.put("Muffins", "item_muffins");
            groItemMap.put("Mustard", "item_mustard");
            groItemMap.put("Nuts", "item_nuts");
            groItemMap.put("Oatmeal", "item_oatmeal");
            groItemMap.put("Oats", "item_oats");
            groItemMap.put("Olive Oil", "item_olive_oil");
            groItemMap.put("Olives", "item_olives");
            groItemMap.put("Onion Powder", "item_onion_powder");
            groItemMap.put("Orange Juice", "item_orange_juice");
            groItemMap.put("Oranges", "item_oranges");
            groItemMap.put("Oregano", "item_oregano");
            groItemMap.put("Pain Relievers", "item_pain_relievers");
            groItemMap.put("Pancake Mix", "item_pancake_mix");
            groItemMap.put("Paper Towels", "item_paper_towels");
            groItemMap.put("Paprika", "item_paprika");
            groItemMap.put("Pasta", "item_pasta");
            groItemMap.put("Pasta Sauce", "item_pasta_sauce");
            groItemMap.put("Pickles", "item_pickles");
            groItemMap.put("Plastic Wrap", "item_plastic_wrap");
            groItemMap.put("Popcorn", "item_popcorn");
            groItemMap.put("Pork Chops", "item_pork_chops");
            groItemMap.put("Potatoes", "item_potatoes");
            groItemMap.put("Pretzels", "item_pretzels");
            groItemMap.put("Quinoa", "item_quinoa");
            groItemMap.put("Red Wine", "item_red_wine");
            groItemMap.put("Rice", "item_rice");
            groItemMap.put("Rice Noodles", "item_rice_noodles");
            groItemMap.put("Salmon", "item_salmon");
            groItemMap.put("Salsa", "item_salsa");
            groItemMap.put("Salt", "item_salt");
            groItemMap.put("Sausages", "item_sausages");
            groItemMap.put("Shampoo", "item_shampoo");
            groItemMap.put("Shrimp", "item_shrimp");
            groItemMap.put("Soda", "item_soda");
            groItemMap.put("Sour Cream", "item_sour_cream");
            groItemMap.put("Soy Milk", "item_soy_milk");
            groItemMap.put("Soy Sauce", "item_soy_sauce");
            groItemMap.put("Spinach", "item_spinach");
            groItemMap.put("Spirits", "item_spirits");
            groItemMap.put("Sriracha Sauce", "item_sriracha_sauce");
            groItemMap.put("Strawberries", "item_strawberries");
            groItemMap.put("Sugar", "item_sugar");
            groItemMap.put("Tea", "item_tea");
            groItemMap.put("Toilet Paper", "item_toilet_paper");
            groItemMap.put("Tomato Sauce", "item_tomato_sauce");
            groItemMap.put("Tomatoes", "item_tomatoes");
            groItemMap.put("Toothpaste", "item_toothpaste");
            groItemMap.put("Tortillas", "item_tortillas");
            groItemMap.put("Trash Bags", "item_trash_bags");
            groItemMap.put("Turkey", "item_turkey");
            groItemMap.put("Vanilla Extract", "item_vanilla_extract");
            groItemMap.put("Vegetable Oil", "item_vegetable_oil");
            groItemMap.put("Vinegar", "item_vinegar");
            groItemMap.put("Vitamins", "item_vitamins");
            groItemMap.put("Water", "item_water");
            groItemMap.put("White Wine", "item_white_wine");
            groItemMap.put("Yogurt", "item_yogurt");
            groItemMap.put("Ziplock Bags", "item_ziplock_bags");
        }

        public String getImageName(String displayName) {
            return groItemMap.get(displayName);
        }
    }


