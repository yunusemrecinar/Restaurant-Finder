
## Restaurant Finder

The aim of the project is to try to help people who do not have a firm idea about choosing places to go to eat along with a simple interface.


## Thing that can be done

- You can easily signup with your email.

- When you log in, you will see the listed restaurants.

- You can pick the restaurants for your "want to go" or "favorites".

- If you select a restaurant from restaurants list, you will see information about restaurant that you select.

- Restaurant's location is avaliable with Google Maps.

- Can filter the restaurants which listed.

- Lastly, you can find the restaurant that you want fast.

## Features

- FireBase Database for Login/SignUp

- FireBase Realtime Database for restaurants in the list

- FireBase Storage for restaurants's photos

- Google Maps


[Figma Link](https://www.figma.com/file/OQc7j1MbNVllJVznzKle6U/Project-Intermediate-Evaluation?node-id=0%3A1)
### What I did

- I designed ['activity_login.xml'](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/res/layout/activity_login.xml)

- I designed ['activity_main_menu.xml'](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/res/layout/activity_main_menu.xml)

- I designed ['activity_profile.xml'](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/res/layout/activity_profile.xml)

- I designed ['activity_sign_up.xml](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/res/layout/activity_sign_up.xml)

- I designed ['activity_restaurant.xml](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/res/layout/activity_restaurant.xml)

- I designed ['activity_favorites.xml'](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/res/layout/activity_favorites.xml)

- I designed ['activity_want_to_go.xml'](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/res/layout/activity_want_to_go.xml)

- I created ['LoginActivity.kt'](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/java/com/emrecinar/xxxx/aa/xa/x/restaurant_finder/LoginActivity.kt)
-- In LoginActivity, we take the email and password of user. If the email and password are in the my database record, user can log in into app.

- I created ['SignUpActivity.kt'](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/java/com/emrecinar/xxxx/aa/xa/x/restaurant_finder/SignUpActivity.kt)
  -- In SignUpActivity, I take email and password of user and I register the user in database. After that, the activity_sign_up finished and moved to LoginActivity.

- I created ['ProfileActivity.kt'](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/java/com/emrecinar/xxxx/aa/xa/x/restaurant_finder/ProfileActivity.kt)
  -- In ProfileActivity, I welcome the user, and the user can logout if he/she wants.

- I created ['MainMenuActivity.kt'](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/java/com/emrecinar/xxxx/aa/xa/x/restaurant_finder/MainMenuActivity.kt)
  -- In MainMenuActivity, Users can go favorites and wantToGo list interfaces in the upper left. They can search for restaurants with the search box.Also below the search box, they'll see restaurant list near their location.Lastly can click the profile circle in the upper right too.

- I created ['Restaurant.kt](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/java/com/emrecinar/xxxx/aa/xa/x/restaurant_finder/Restaurant.kt)
  -- In Restaurant, Users can see menus, photos of restaurant, location and rating of restaurant which they click in MainMenu. Also they can look at the location of restaurant. If they want, they can add their favorites list or want-to-go list.

- I created ['FavoritesActivity.kt'](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/java/com/emrecinar/xxxx/aa/xa/x/restaurant_finder/Favorites.kt)
  -- In Favorites, User can see restaurants that they select the star button in the restaurant page.

- I created ['WantToGoActivity.kt'](https://github.com/akdenizcse/cse234-2022-term-project-team24/blob/main/app/src/main/java/com/emrecinar/xxxx/aa/xa/x/restaurant_finder/WantToGo.kt)
  -- In wantToGoActivity, User can see restaurants that they select the heart button in the restaurant page.
