package adPortal;

import adPortal.command.Commands;
import adPortal.model.Advertisment;
import adPortal.model.Category;
import adPortal.model.Gender;
import adPortal.model.User;
import adPortal.storage.AdStorage;
import adPortal.storage.UserStorage;
import adPortal.storage.impl.AdStorageFileImpl;
import adPortal.storage.impl.AdvertismentImpl;
import adPortal.storage.impl.UserImpl;
import adPortal.storage.impl.UserStorageFileImpl;

import java.util.Date;
import java.util.Scanner;

public class AdPortalTest implements Commands {

    private static Scanner scanner = new Scanner(System.in);
    private static AdStorage adStorage;
    private static UserStorage userStorage;
    private static User currentUser = null;

    public static void main(String[] args) {

        System.out.println("Which storage you want to use");
        System.out.println("1 - HEAP[RAM]");
        System.out.println("2 - FILE[HDD]");
        int storage =Integer.parseInt(scanner.nextLine());
        if (storage == 2){
            adStorage = new AdStorageFileImpl();
            userStorage = new UserStorageFileImpl();
        }else {
            adStorage = new AdvertismentImpl();
            userStorage = new UserImpl();
        }

        boolean isRun = true;
        while (isRun) {
            Commands.printMainCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }

            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                case MAIN_PRINT_ALL_ADS:
                    printAllAds();
                    break;
                default:
                    System.out.println("Invalid comman,please try again!");
            }
        }

    }

    private static void printAllAds() {
        adStorage.printAllAds();
    }

    private static void register() {
        System.out.println("Please input name,surname,gender,age,phoneNumber,password");
        String userData = scanner.nextLine();
        String[] userDataArr = userData.split(",");
        User user = new User(userDataArr[0], userDataArr[1], Gender.valueOf(userDataArr[2]), Integer.parseInt(userDataArr[3])
                , userDataArr[4], userDataArr[5]);
        userStorage.add(user);
        System.out.println("Register was success!");
    }

    private static void login() {
        System.out.println("Please input phoneNumber,password");
        String userCred = scanner.nextLine();
        String[] userDataArr = userCred.split(",");
        User user = userStorage.getByPhone(userDataArr[0]);
        if (user == null) {
            System.out.println("User does not exists");
        } else {
            if (user.getPassword().equals(userDataArr[1])) {
                System.out.println("Welcome" + " " + user.getName() + "!");
                currentUser = user;
                userLogin();
            } else {
                System.out.println("Password is incorrect");
            }
        }

    }

    private static void userLogin() {
        boolean isRun = true;
        while (isRun) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    currentUser = null;
                    isRun = false;
                    break;
                case ADD_NEW_AD:
                    addAd();
                    break;
                case PRINT_ALL_AD:
                    printAllAds();
                    break;
                case PRINT_ALL_MY_ADS:
                    adStorage.printMyAds(currentUser);
                    break;
                case PRINT_ADS_BY_CATEGORY:
                    printAdsByCategory();
                    break;
                case DELETE_MY_ALL_ADS:
                    adStorage.deleteMyAds(currentUser);
                    break;
                case DELETE_MY_ALL_BY_TITLE:
                    deleteMyAdByTitle();
                    break;
                default:
                    System.out.println("Wrong command!");

            }
        }
    }

    private static void deleteMyAdByTitle() {
        System.out.println("Please select your ad title");
        adStorage.printMyAds(currentUser);
        String title = scanner.nextLine();
        adStorage.deleteAdByTitle(title, currentUser);


    }

    private static void printAdsByCategory() {
        for (Category value : Category.values()) {
            System.out.println(value);
        }
        String categoryStr = scanner.nextLine();
        adStorage.printByCategory(Category.valueOf(categoryStr));
    }

    private static void addAd() {
        System.out.println("Please input title,text,price,category");
        System.out.println("Here is the categoryList");
        for (Category value : Category.values()) {
            System.out.println(value);
        }
        String adData = scanner.nextLine();
        String[] adDataArr = adData.split(",");
        Advertisment ad = new Advertisment(adDataArr[0], adDataArr[1], Double.parseDouble(adDataArr[2]), new Date(), Category.valueOf(adDataArr[3]), currentUser);

        adStorage.add(ad);
        System.out.println("Ad was added");
    }
}




