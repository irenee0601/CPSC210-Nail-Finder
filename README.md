# Nail Design Finder

## What will the application do?
This application allows users to search for various nail designs using a tag-based system and save their favorite designs to a personal collection.

## Who will use it?
Many people contemplate and search for nail designs before visiting a nail salon. This application is intended for individuals who want to get their nails done but only have a broad idea (such as short nails, red-colored nails, nails with gems, etc.) and need help finding a specific design.

## Why is this project of interest to you?
I have been doing my own nails for a long time and am very passionate about it. I also have experience working part-time as a nail technician in Canada. When I'm trying to decide on a nail design, I always search on Instagram or Pinterest. While I can search for simple terms like "red nail" or "red glitter nail," I've found it difficult and time-consuming to narrow down the results by adding more specific criteria, such as short vs. long nails, nails with gems vs. flat nails, or gradient vs. full-coat nails. My application would solve this problem by allowing users to easily find complex designs, like "long, red, glitter, gradient nails with gems," all in one search.

---
## User Stories


- As a user, I want to be able to search for desired nail designs by combining multiple tags (e.g., 'red', 'glitter', 'long nails').
- As a user, I want to be able to add a searched nail design to my personal collection.
- As a user, I want to be able to view a list of all nail designs saved in my personal collection.
- As a user, I want to be able to delete a specific nail design from my personal collection.
- As a user, I want to have the option to save my favorite nail designs to a file so I can keep them for later.
- As a user, I want to have the option to load my favorite nail designs from a file so I can see them again when I open the app next time. 



## Instructions for End User

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by **clicking on any design image/button** in the Catalog Gallery (left panel).
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by selecting a design from the 'My Favorites' list and clicking the **"Remove Selected"** button.
- You can view the panel that displays the Xs that have already been added to the Y by looking at the **'My Favorites' list** in the right panel.
- You can filter the designs by typing tags (e.g., "red", "long") into the search field and clicking **"Search"**.
- You can locate my visual component by starting the application; a **Splash Screen** showing a loading image will appear for 10 seconds.
- You can save the state of my application by clicking the **"Save"** button.
- You can reload the state of my application by clicking the **"Load"** button.


## Phase 4 : Task 2

Fri Nov 28 10:28:55 PST 2025
Design added to favorites: img7
Fri Nov 28 10:29:09 PST 2025
Design added to favorites: img20
Fri Nov 28 10:29:12 PST 2025
Design added to favorites: img21
Fri Nov 28 10:29:18 PST 2025
Design removed from favorites: img20
(base) irene@irenee0601s-MacBook-Air project-p1a4j %


## Phase 4 : Task 3
If I had more time, I would like to improve the `NailDesignGUI` class. Right now, this class is doing too many things at once. It handles the buttons, shows the gallery, and does the saving and loading. Because of this, the code is getting very long and hard to read.

To fix this, I would split this big class into smaller classes. For example, I could make a separate class just for the gallery part (`GalleryPanel`) or the search part (`SearchPanel`). I think this would make my code much cleaner and easier to understand.