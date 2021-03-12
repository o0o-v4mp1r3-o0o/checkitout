package main;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShogiMachine {
    private static WebDriver driver = new ChromeDriver();
    private static JavascriptExecutor executor = (JavascriptExecutor)driver;
    private static String playerusername = null;
    private static String username = null;
    private static String username1 = null;
    private static Integer ratingsize = null;
    private static Integer Machineratingsize = 0;
    private static String PlayerpromoG = "Sto Sryu Suma Snkyo Snkei Sngin";
    private static String[] PlayerpromoarrayG = PlayerpromoG.split(" ");
    private static Integer[] Playerpromoarray1G = new Integer[6];
    private static String MachinedropcoordG = "P* R* B* L* N* S* G*";
    private static String Machinedropcoord1G = "sq-1_7 sq-1_1 sq-1_2 sq-1_6 sq-1_5 sq-1_4 sq-1_3";
    private static String[] MachinedroparrayG = MachinedropcoordG.split(" ");
    private static String[] Machinedroparray1G = Machinedropcoord1G.split(" ");
    private static String PlayerdropcoordG = "Sfu Shi Skaku Skyo Skei Sgin Skin";
    private static String Playerdropcoord1G = "P* R* B* L* N* S* G*";
    private static String[] PlayerdroparrayG = PlayerdropcoordG.split(" ");
    private static String[] Playerdroparray1G = Playerdropcoord1G.split(" ");
    private static String Playerpromo = "Gto Gryu Guma Gnkyo Gnkei Gngin";
    private static String[] Playerpromoarray = Playerpromo.split(" ");
    private static Integer[] Playerpromoarray1 = new Integer[6];
    private static String Machinedropcoord = "P* R* B* L* N* S* G*";
    private static String Machinedropcoord1 = "sq0_7 sq0_1 sq0_2 sq0_6 sq0_5 sq0_4 sq0_3";
    private static String[] Machinedroparray = Machinedropcoord.split(" ");
    private static String[] Machinedroparray1 = Machinedropcoord1.split(" ");
    private static String Playerdropcoord = "Gfu Ghi Gkaku Gkyo Gkei Ggin Gkin";
    private static String Playerdropcoord1 = "P* R* B* L* N* S* G*";
    private static String[] Playerdroparray = Playerdropcoord.split(" ");
    private static String[] Playerdroparray1 = Playerdropcoord1.split(" ");
    private static String Humanstartingmove = null;
    private static String WHAT = null;
    private static String WHAT1 = null;
    private static String replace = null;
    private static String HERE = null;
    private static List<String> list = new ArrayList<String>();
    private static Integer i = 5;
    private static String First = null;
    private static String First1 = null;
    private static String Second = null;
    private static String Second1 = null;
    private static int pos = 0;
    private static int pos1 = 0;
    private static Integer Humanchar1 = 0;
    private static Integer Humanchar2 = 0;
    private static Integer Humanchar3 = 0;
    private static Integer Humanchar4 = 0;
    private static String Humanmovedelivery = null;
    private static String alpha1 = null;
    private static String alpha2 = null;
    private static Integer promo = 0;
    private static Integer Machinepromo = 0;
    private static Integer Firstturn = 0;
    private static void Starter(){
        Command.start();
    }
    private static void Exitgame() throws InterruptedException {
        while(driver.findElement(By.id("closeGameButton")).getAttribute("class").toString().equals("button " +
                "button-disabled")) {
            Thread.sleep(600);
        }
        executor.executeScript("document.getElementById(\"closeGameButton\").click();");
        NewLobbyGame();
    }
    private static void ChallengeChecker() throws InterruptedException, NoSuchElementException {
        ratingsize = Integer.valueOf(driver.findElement(By.id("challengerRate")).getText().substring(2,6).trim());
        if((Machineratingsize-400) > ratingsize){
            Thread.sleep(5000);
            driver.findElement(By.id("challengerRejectButton")).click();
            playerusername = driver.findElement(By.id("challengerTimerText")).getText().toString();
            WaitForChallenge();
        }
        if((Machineratingsize-400) <= ratingsize){
            Thread.sleep(5000);
            driver.findElement(By.id("challengerAcceptButton")).click();
            while (driver.findElements(By.xpath("//div[@id='sq2_7']")).isEmpty()) {
                Thread.sleep(600);
            }
            waiter();
        }
    }
    private static void WaitForChallenge() throws InterruptedException {
        while(driver.findElement(By.id("challengerTimerText")).getText().toString().equals("")){
            Thread.sleep(600);
        }
        while(driver.findElement(By.id("challengerTimerText")).getText().toString().equals(playerusername)){
            Thread.sleep(600);
        }
        ChallengeChecker();
    }
    private static void NewLobbyGame() throws InterruptedException {
        driver.findElement(By.xpath("//a[@data-i18n='lobby.wait'][1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='newGameType'][@value='4']")).click();
        Thread.sleep(1000);
        executor.executeScript("document.evaluate('/html/body/div[6]/div[3]/div/button[1]', document, null, " +
                "XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
        WaitForChallenge();
    }
    private static void Checkwin() throws InterruptedException {
        list.clear();
        Humanstartingmove = null;
        WHAT = null;
        WHAT1 = null;
        replace = null;
        HERE = null;
        i = 5;
        First = null;
        First1 = null;
        Second = null;
        Second1 = null;
        pos = 0;
        pos1 = 0;
        Humanchar1 = 0;
        Humanchar2 = 0;
        Humanchar3 = 0;
        Humanchar4 = 0;
        Humanmovedelivery = null;
        alpha1 = null;
        alpha2 = null;
        promo = 0;
        Machinepromo = 0;
        Firstturn = 0;
        Exitgame();
    }
    private static void waiter(){
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            while (driver.findElements(By.xpath("//div[@id='sq2_7']")).isEmpty()) {
                try {
                    Thread.sleep(600);
                    waiter();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Colorchecker();
        }catch(NoSuchElementException e){
            e.printStackTrace();
        }finally {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
    }
    private static void Colorchecker(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String Checksente = driver.findElement(By.xpath("//*[@id=\"senteInfo\"]/child::span[2]")).getText().toString();
        String Checkgote = driver.findElement(By.xpath("//*[@id=\"goteInfo\"]/child::span[2]")).getText().toString();
        if(Checkgote.equals(username1)){
            String wonder = "//*[contains(@class, 'square square-last')][1]";
            String wonder1 = "//*[contains(@class, 'square square-last')][2]";
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            try {
                while (driver.findElements(By.xpath(wonder)).size() < 1) {
                    Thread.sleep(500);
                }
            }catch(NoSuchElementException | InterruptedException e){
                e.printStackTrace();
            }finally{
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            }
            String Humanmove1 = driver.findElement(By.xpath(wonder)).getAttribute("id").toString();
            String Humanmove2 = driver.findElement(By.xpath(wonder1)).getAttribute("id").toString();
            List<WebElement> as1 = driver.findElements(By.xpath("//*[contains(@style, 'background-image: none')]"));
            Iterator<WebElement> itr1 = as1.iterator();
            while (itr1.hasNext()) {
                String name = itr1.next().getAttribute("id");
                System.out.println(name);
                if(Humanmove1.equals(name) || Humanmove2.equals(name)){
                    Humanstartingmove = name;
                }
            }
            if(Humanstartingmove.equals(Humanmove1)){
                Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2,3));
                Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4,5));
                Humanchar3 = Integer.valueOf(Humanmove2.substring(2,3));
                Humanchar4 = Integer.valueOf(Humanmove2.substring(4,5));
                alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2-1, Humanchar2);
                alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4-1, Humanchar4);
                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                list.add(Humanmovedelivery);
                HERE = Arrays.toString(list.toArray()).substring(1, i);
                i += 6;
                System.out.println(HERE);
                replace = HERE.replaceAll(",","");
                System.out.println(replace);
                System.out.println("position startpos moves " + replace);
                GoteInterpreter();
            }
            if(Humanstartingmove.equals(Humanmove2)){
                Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2,3));
                Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4,5));
                Humanchar3 = Integer.valueOf(Humanmove1.substring(2,3));
                Humanchar4 = Integer.valueOf(Humanmove1.substring(4,5));
                alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2-1, Humanchar2);
                alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4-1, Humanchar4);
                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                list.add(Humanmovedelivery);
                HERE = Arrays.toString(list.toArray()).substring(1, i);
                i += 6;
                System.out.println(HERE);
                replace = HERE.replaceAll(",","");
                System.out.println(replace);
                GoteInterpreter();
            }
        }
        if(Checksente.equals(username1)){
            MachineInterpreter();
        }
    }
    private static void MachineInterpreter(){
        try {
            Command.command("isready");
            Command.command("");
            Command.command("go");
            Thread.sleep(1000);
            Command.command("");
            WHAT = Command.atago.substring(9,11);
            WHAT1 = Command.atago.substring(11,13);
            list.add(WHAT + WHAT1);
            //i++;
            System.out.println(WHAT + WHAT1);
            HERE = Arrays.toString(list.toArray()).substring(1,i);
            i+=6;
            System.out.println(HERE);
            replace = HERE.replaceAll(",","");
            System.out.println(replace);
            First = WHAT.substring(0,1);
            First1 = WHAT1.substring(0,1);
            Second = WHAT.substring(1,2);
            Second1 = WHAT1.substring(1,2);
            pos = Second.charAt(0)-'a'+1;
            pos1 = Second1.charAt(0)-'a'+1;
            executor.executeScript("arguments[0].click();",
                    driver.findElement(By.xpath("//div[@id='sq" + First + "_" + pos + "']")));
            executor.executeScript("arguments[0].click();",
                    driver.findElement(By.xpath("//div[@id='sq" + First1 + "_" + pos1 + "']")));
            String MachineAttribute = "sq" + First + "_" + pos;
            String MachineAttribute1 = "sq" + First1 + "_" + pos1;
            String wonder = "//*[contains(@class, 'square square-last')][1]";
            String wonder1 = "//*[contains(@class, 'square square-last')][2]";
            while(driver.findElement(By.xpath(wonder)).getAttribute("id").toString().equals(MachineAttribute1) &&
                    driver.findElement(By.xpath(wonder1)).getAttribute("id").toString().equals(MachineAttribute)){
                Thread.sleep(500);
            }
            String Humanmove1 = driver.findElement(By.xpath(wonder)).getAttribute("id").toString();
            String Humanmove2 = driver.findElement(By.xpath(wonder1)).getAttribute("id").toString();
            List<WebElement> as1 = driver.findElements(By.xpath("//*[contains(@style, 'background-image: none')]"));
            Iterator<WebElement> itr1 = as1.iterator();
            while (itr1.hasNext()) {
                String name = itr1.next().getAttribute("id");
                System.out.println(name);
                if(Humanmove1.equals(name) || Humanmove2.equals(name)){
                    Humanstartingmove = name;
                }
            }
            if(Humanstartingmove.equals(Humanmove1)){
                Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2,3));
                Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4,5));
                Humanchar3 = Integer.valueOf(Humanmove2.substring(2,3));
                Humanchar4 = Integer.valueOf(Humanmove2.substring(4,5));
                alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2-1, Humanchar2);
                alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4-1, Humanchar4);
                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                list.add(Humanmovedelivery);
                HERE = Arrays.toString(list.toArray()).substring(1, i);
                i += 6;
                System.out.println(HERE);
                replace = HERE.replaceAll(",","");
                System.out.println(replace);
                System.out.println("position startpos moves " + replace);
                HumanInterpreter();
            }
            if(Humanstartingmove.equals(Humanmove2)){
                Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2,3));
                Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4,5));
                Humanchar3 = Integer.valueOf(Humanmove1.substring(2,3));
                Humanchar4 = Integer.valueOf(Humanmove1.substring(4,5));
                alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2-1, Humanchar2);
                alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4-1, Humanchar4);
                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                list.add(Humanmovedelivery);
                HERE = Arrays.toString(list.toArray()).substring(1, i);
                i += 6;
                System.out.println(HERE);
                replace = HERE.replaceAll(",","");
                System.out.println(replace);
                HumanInterpreter();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void GoteInterpreter() {
        driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
        while(driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() < 1) {
            try {
                Command.command("isready");
                //Command.command("");
                Thread.sleep(500);
                Command.command("position startpos moves " + replace);
                //Command.command("");
                Thread.sleep(500);
                Command.command("go");
                Thread.sleep(500);
                Command.command("");
                try {
                    if (Command.atago.substring(1, 15).contains("+")) {
                        Machinepromo++;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                WHAT = Command.atago.substring(9, 11);
                WHAT1 = Command.atago.substring(11, 13);
                if (Machinepromo.equals(0)) {
                    list.add(WHAT + WHAT1);
                } else {
                    list.add(WHAT + WHAT1 + "+");
                }
                System.out.println(WHAT + WHAT1);
                HERE = Arrays.toString(list.toArray()).substring(1, i + Machinepromo);
                i += 6 + Machinepromo;
                System.out.println(HERE);
                replace = HERE.replaceAll(",", "");
                System.out.println(replace);
                if (Machinedropcoord.contains(WHAT)) {
                    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    for (int i = 0; i < 7; i++) {
                        if (Machinedroparray[i].contains(WHAT)) {
                            WHAT = Machinedroparray1G[i];
                        }
                    }
                    First1 = WHAT1.substring(0, 1);
                    Second1 = WHAT1.substring(1, 2);
                    pos1 = Second1.charAt(0) - 'a' + 1;
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='" + WHAT + "']")));
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='sq" + First1 + "_" + pos1 + "']")));
                    String MachineAttribute = WHAT;
                    String MachineAttribute1 = "sq" + First1 + "_" + pos1;
                    String wonder = "//*[contains(@class, 'square square-last')][1]";
                    String wonder1 = "//*[contains(@class, 'square square-last')][2]";
                    String wonder3 = "//*[contains(@class, 'square square-last')]";
                    System.out.println(driver.findElements(By.xpath(wonder3)).size());
                    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    try {
                        for (int i = 0; i < 6; i++) {
                            Integer yes =
                                    driver.findElements(By.xpath("//*[contains(@style, '" + Playerpromoarray[i] + "')]")).size();
                            Playerpromoarray1[i] = yes;
                        }
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                    if (driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() > 0) {
                        continue;
                    }
                    try {
                        while (driver.findElement(By.xpath(wonder)).getAttribute("id").toString().equals(MachineAttribute1) && driver.findElements(By.xpath(wonder3)).size() < 2 && driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() < 1) {
                            Thread.sleep(500);
                        }
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                    if (driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() > 0) {
                        continue;
                    }
                    try {
                        for (int i = 0; i < 6; i++) {
                            Integer yes =
                                    driver.findElements(By.xpath("//*[contains(@style, '" + Playerpromoarray[i] + "')]")).size();
                            if (Playerpromoarray1[i] < yes) {
                                promo++;
                            }
                        }
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                    if (driver.findElements(By.xpath(wonder3)).size() > 1) {
                        String Humanmove1 = driver.findElement(By.xpath(wonder)).getAttribute("id").toString();
                        String Humanmove2 = driver.findElement(By.xpath(wonder1)).getAttribute("id").toString();
                        List<WebElement> as1 = driver.findElements(By.xpath("//*[contains(@style, 'background-image: none')]"));
                        Iterator<WebElement> itr1 = as1.iterator();
                        while (itr1.hasNext()) {
                            String name = itr1.next().getAttribute("id");
                            if (Humanmove1.equals(name) || Humanmove2.equals(name)) {
                                Humanstartingmove = name;
                            }
                        }
                        if (Humanstartingmove.equals(Humanmove1)) {
                            Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2, 3));
                            Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4, 5));
                            Humanchar3 = Integer.valueOf(Humanmove2.substring(2, 3));
                            Humanchar4 = Integer.valueOf(Humanmove2.substring(4, 5));
                            alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2 - 1, Humanchar2);
                            alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                            if (promo.equals(0)) {
                                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                            }
                            if (promo.equals(1)) {
                                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2 + "+";
                            }
                            list.add(Humanmovedelivery);
                            HERE = Arrays.toString(list.toArray()).substring(1, i + promo);
                            i += 6 + promo;
                            System.out.println(HERE);
                            replace = HERE.replaceAll(",", "");
                            System.out.println(replace);
                            System.out.println("position startpos moves " + replace);
                            promo = 0;
                            continue;
                        }
                        if (Humanstartingmove.equals(Humanmove2)) {
                            Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2, 3));
                            Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4, 5));
                            Humanchar3 = Integer.valueOf(Humanmove1.substring(2, 3));
                            Humanchar4 = Integer.valueOf(Humanmove1.substring(4, 5));
                            alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2 - 1, Humanchar2);
                            alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                            if (promo.equals(0)) {
                                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                            }
                            if (promo.equals(1)) {
                                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2 + "+";
                            }
                            list.add(Humanmovedelivery);
                            HERE = Arrays.toString(list.toArray()).substring(1, i + promo);
                            i += 6 + promo;
                            System.out.println(HERE);
                            replace = HERE.replaceAll(",", "");
                            System.out.println(replace);
                            promo = 0;
                            continue;
                        }
                    } else {
                        String Humanmove1 = driver.findElement(By.xpath(wonder)).getAttribute("id").toString();
                        String Humanmove2solver = driver.findElement(By.xpath(wonder)).getAttribute("style").toString();
                        for (int i = 0; i < 7; i++) {
                            if (Humanmove2solver.contains(Playerdroparray[i])) {
                                Humanstartingmove = Playerdroparray1[i];
                            }
                        }
                        Humanchar3 = Integer.valueOf(Humanmove1.substring(2, 3));
                        Humanchar4 = Integer.valueOf(Humanmove1.substring(4, 5));
                        alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                        Humanmovedelivery = Humanstartingmove + Humanchar3 + alpha2;
                        list.add(Humanmovedelivery);
                        HERE = Arrays.toString(list.toArray()).substring(1, i);
                        i += 6;
                        System.out.println(HERE);
                        replace = HERE.replaceAll(",", "");
                        System.out.println(replace);
                        continue;
                    }
                }
                First = WHAT.substring(0, 1);
                First1 = WHAT1.substring(0, 1);
                Second = WHAT.substring(1, 2);
                Second1 = WHAT1.substring(1, 2);
                pos = Second.charAt(0) - 'a' + 1;
                pos1 = Second1.charAt(0) - 'a' + 1;
                if (Machinepromo.equals(0)) {
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='sq" + First + "_" + pos + "']")));
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='sq" + First1 + "_" + pos1 + "']")));
                    Thread.sleep(500);
                    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    try {
                        if (driver.findElements(By.id("promote-no")).size() > 0) {
                            executor.executeScript("arguments[0].click();",
                                    driver.findElement(By.xpath("//*[@id=\"promote-no\"]")));
                        }
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    } finally {
                        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                    }
                } else {
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='sq" + First + "_" + pos + "']")));
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='sq" + First1 + "_" + pos1 + "']")));
                    Thread.sleep(500);
                    try {
                        executor.executeScript("arguments[0].click();",
                                driver.findElement(By.xpath("//*[@id=\"promote-yes\"]")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Machinepromo = 0;
                }
                String MachineAttribute = "sq" + First + "_" + pos;
                String MachineAttribute1 = "sq" + First1 + "_" + pos1;
                String wonder = "//*[contains(@class, 'square square-last')][1]";
                String wonder1 = "//*[contains(@class, 'square square-last')][2]";
                String wonder3 = "//*[contains(@class, 'square square-last')]";
                System.out.println(driver.findElements(By.xpath(wonder3)).size());
                driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                try {
                    for (int i = 0; i < 6; i++) {
                        Integer yes =
                                driver.findElements(By.xpath("//*[contains(@style, '" + Playerpromoarray[i] + "')]")).size();
                        Playerpromoarray1[i] = yes;
                    }
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
                if (driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() > 0) {
                    continue;
                }
                try {
                    while (driver.findElement(By.xpath(wonder)).getAttribute("id").toString().equals(MachineAttribute1) &&
                            driver.findElement(By.xpath(wonder1)).getAttribute("id").toString().equals(MachineAttribute) && driver.findElements(By.xpath(wonder3)).size() > 1 && driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() < 1 || driver.findElement(By.xpath(wonder)).getAttribute("id").toString().equals(MachineAttribute) &&
                            driver.findElement(By.xpath(wonder1)).getAttribute("id").toString().equals(MachineAttribute1) && driver.findElements(By.xpath(wonder3)).size() > 1 && driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() < 1) {
                        Thread.sleep(500);
                    }
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
                if (driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() > 0) {
                    continue;
                }
                try {
                    for (int i = 0; i < 6; i++) {
                        Integer yes =
                                driver.findElements(By.xpath("//*[contains(@style, '" + Playerpromoarray[i] + "')]")).size();
                        if (Playerpromoarray1[i] < yes) {
                            promo++;
                        }
                    }
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
                if (driver.findElements(By.xpath(wonder3)).size() > 1) {
                    String Humanmove1 = driver.findElement(By.xpath(wonder)).getAttribute("id").toString();
                    String Humanmove2 = driver.findElement(By.xpath(wonder1)).getAttribute("id").toString();
                    List<WebElement> as1 = driver.findElements(By.xpath("//*[contains(@style, 'background-image: none')]"));
                    Iterator<WebElement> itr1 = as1.iterator();
                    while (itr1.hasNext()) {
                        String name = itr1.next().getAttribute("id");
                        if (Humanmove1.equals(name) || Humanmove2.equals(name)) {
                            Humanstartingmove = name;
                        }
                    }
                    if (Humanstartingmove.equals(Humanmove1)) {
                        Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2, 3));
                        Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4, 5));
                        Humanchar3 = Integer.valueOf(Humanmove2.substring(2, 3));
                        Humanchar4 = Integer.valueOf(Humanmove2.substring(4, 5));
                        alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2 - 1, Humanchar2);
                        alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                        if (promo.equals(0)) {
                            Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                        }
                        if (promo.equals(1)) {
                            Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2 + "+";
                        }
                        list.add(Humanmovedelivery);
                        HERE = Arrays.toString(list.toArray()).substring(1, i + promo);
                        i += 6 + promo;
                        System.out.println(HERE);
                        replace = HERE.replaceAll(",", "");
                        System.out.println(replace);
                        System.out.println("position startpos moves " + replace);
                        promo = 0;
                        continue;
                    }
                    if (Humanstartingmove.equals(Humanmove2)) {
                        Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2, 3));
                        Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4, 5));
                        Humanchar3 = Integer.valueOf(Humanmove1.substring(2, 3));
                        Humanchar4 = Integer.valueOf(Humanmove1.substring(4, 5));
                        alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2 - 1, Humanchar2);
                        alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                        Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                        if (promo.equals(0)) {
                            Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                        }
                        if (promo.equals(1)) {
                            Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2 + "+";
                        }
                        list.add(Humanmovedelivery);
                        HERE = Arrays.toString(list.toArray()).substring(1, i + promo);
                        i += 6 + promo;
                        System.out.println(HERE);
                        replace = HERE.replaceAll(",", "");
                        System.out.println(replace);
                        promo = 0;
                        continue;
                    }
                } else {
                    String Humanmove1 = driver.findElement(By.xpath(wonder)).getAttribute("id").toString();
                    String Humanmove2solver = driver.findElement(By.xpath(wonder)).getAttribute("style").toString();
                    for (int i = 0; i < 7; i++) {
                        if (Humanmove2solver.contains(Playerdroparray[i])) {
                            Humanstartingmove = Playerdroparray1[i];
                        }
                    }
                    Humanchar3 = Integer.valueOf(Humanmove1.substring(2, 3));
                    Humanchar4 = Integer.valueOf(Humanmove1.substring(4, 5));
                    alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                    Humanmovedelivery = Humanstartingmove + Humanchar3 + alpha2;
                    list.add(Humanmovedelivery);
                    HERE = Arrays.toString(list.toArray()).substring(1, i);
                    i += 6;
                    System.out.println(HERE);
                    replace = HERE.replaceAll(",", "");
                    System.out.println(replace);
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Checkwin();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void HumanInterpreter() {
        driver.manage().timeouts().implicitlyWait(0,TimeUnit.SECONDS);
        while(driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() < 1) {
            try {
                Command.command("isready");
                //Command.command("");
                Thread.sleep(500);
                Command.command("position startpos moves " + replace);
                //Command.command("");
                Thread.sleep(500);
                Command.command("go");
                Thread.sleep(500);
                Command.command("");
                try {
                    if (Command.atago.substring(1, 15).contains("+")) {
                        Machinepromo++;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                WHAT = Command.atago.substring(9, 11);
                WHAT1 = Command.atago.substring(11, 13);
                if (Machinepromo.equals(0)) {
                    list.add(WHAT + WHAT1);
                } else {
                    list.add(WHAT + WHAT1 + "+");
                }
                System.out.println(WHAT + WHAT1);
                HERE = Arrays.toString(list.toArray()).substring(1, i + Machinepromo);
                i += 6 + Machinepromo;
                System.out.println(HERE);
                replace = HERE.replaceAll(",", "");
                System.out.println(replace);
                if (Machinedropcoord.contains(WHAT)) {
                    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    for (int i = 0; i < 7; i++) {
                        if (Machinedroparray[i].contains(WHAT)) {
                            WHAT = Machinedroparray1[i];
                        }
                    }
                    First1 = WHAT1.substring(0, 1);
                    Second1 = WHAT1.substring(1, 2);
                    pos1 = Second1.charAt(0) - 'a' + 1;
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='" + WHAT + "']")));
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='sq" + First1 + "_" + pos1 + "']")));
                    String MachineAttribute = WHAT;
                    String MachineAttribute1 = "sq" + First1 + "_" + pos1;
                    String wonder = "//*[contains(@class, 'square square-last')][1]";
                    String wonder1 = "//*[contains(@class, 'square square-last')][2]";
                    String wonder3 = "//*[contains(@class, 'square square-last')]";
                    System.out.println(driver.findElements(By.xpath(wonder3)).size());
                    try {
                        for (int i = 0; i < 6; i++) {
                            Integer yes =
                                    driver.findElements(By.xpath("//*[contains(@style, '" + Playerpromoarray[i] + "')]")).size();
                            Playerpromoarray1[i] = yes;
                        }
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                    if (driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() > 0) {
                        continue;
                    }
                    try {
                        while (driver.findElement(By.xpath(wonder)).getAttribute("id").toString().equals(MachineAttribute1) && driver.findElements(By.xpath(wonder3)).size() < 2 && driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() < 1) {
                            Thread.sleep(500);
                        }
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                    if (driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() > 0) {
                        continue;
                    }
                    try {
                        for (int i = 0; i < 6; i++) {
                            Integer yes =
                                    driver.findElements(By.xpath("//*[contains(@style, '" + Playerpromoarray[i] + "')]")).size();
                            if (Playerpromoarray1[i] < yes) {
                                promo++;
                            }
                        }
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                    if (driver.findElements(By.xpath(wonder3)).size() > 1) {
                        String Humanmove1 = driver.findElement(By.xpath(wonder)).getAttribute("id").toString();
                        String Humanmove2 = driver.findElement(By.xpath(wonder1)).getAttribute("id").toString();
                        List<WebElement> as1 = driver.findElements(By.xpath("//*[contains(@style, 'background-image: none')]"));
                        Iterator<WebElement> itr1 = as1.iterator();
                        while (itr1.hasNext()) {
                            String name = itr1.next().getAttribute("id");
                            if (Humanmove1.equals(name) || Humanmove2.equals(name)) {
                                Humanstartingmove = name;
                            }
                        }
                        if (Humanstartingmove.equals(Humanmove1)) {
                            Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2, 3));
                            Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4, 5));
                            Humanchar3 = Integer.valueOf(Humanmove2.substring(2, 3));
                            Humanchar4 = Integer.valueOf(Humanmove2.substring(4, 5));
                            alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2 - 1, Humanchar2);
                            alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                            if (promo.equals(0)) {
                                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                            }
                            if (promo.equals(1)) {
                                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2 + "+";
                            }
                            list.add(Humanmovedelivery);
                            HERE = Arrays.toString(list.toArray()).substring(1, i + promo);
                            i += 6 + promo;
                            System.out.println(HERE);
                            replace = HERE.replaceAll(",", "");
                            System.out.println(replace);
                            System.out.println("position startpos moves " + replace);
                            promo = 0;
                            continue;
                        }
                        if (Humanstartingmove.equals(Humanmove2)) {
                            Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2, 3));
                            Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4, 5));
                            Humanchar3 = Integer.valueOf(Humanmove1.substring(2, 3));
                            Humanchar4 = Integer.valueOf(Humanmove1.substring(4, 5));
                            alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2 - 1, Humanchar2);
                            alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                            if (promo.equals(0)) {
                                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                            }
                            if (promo.equals(1)) {
                                Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2 + "+";
                            }
                            list.add(Humanmovedelivery);
                            HERE = Arrays.toString(list.toArray()).substring(1, i + promo);
                            i += 6 + promo;
                            System.out.println(HERE);
                            replace = HERE.replaceAll(",", "");
                            System.out.println(replace);
                            promo = 0;
                            continue;
                        }
                    } else {
                        String Humanmove1 = driver.findElement(By.xpath(wonder)).getAttribute("id").toString();
                        String Humanmove2solver = driver.findElement(By.xpath(wonder)).getAttribute("style").toString();
                        for (int i = 0; i < 7; i++) {
                            if (Humanmove2solver.contains(Playerdroparray[i])) {
                                Humanstartingmove = Playerdroparray1[i];
                            }
                        }
                        Humanchar3 = Integer.valueOf(Humanmove1.substring(2, 3));
                        Humanchar4 = Integer.valueOf(Humanmove1.substring(4, 5));
                        alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                        Humanmovedelivery = Humanstartingmove + Humanchar3 + alpha2;
                        list.add(Humanmovedelivery);
                        HERE = Arrays.toString(list.toArray()).substring(1, i);
                        i += 6;
                        System.out.println(HERE);
                        replace = HERE.replaceAll(",", "");
                        System.out.println(replace);
                        continue;
                    }
                }
                First = WHAT.substring(0, 1);
                First1 = WHAT1.substring(0, 1);
                Second = WHAT.substring(1, 2);
                Second1 = WHAT1.substring(1, 2);
                pos = Second.charAt(0) - 'a' + 1;
                pos1 = Second1.charAt(0) - 'a' + 1;
                if (Machinepromo.equals(0)) {
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='sq" + First + "_" + pos + "']")));
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='sq" + First1 + "_" + pos1 + "']")));
                    Thread.sleep(500);
                    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    try {
                        if (driver.findElements(By.id("promote-no")).size() > 0) {
                            executor.executeScript("arguments[0].click();",
                                    driver.findElement(By.xpath("//*[@id=\"promote-no\"]")));
                        }
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    } finally {
                        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                    }
                } else {
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='sq" + First + "_" + pos + "']")));
                    executor.executeScript("arguments[0].click();",
                            driver.findElement(By.xpath("//div[@id='sq" + First1 + "_" + pos1 + "']")));
                    Thread.sleep(500);
                    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                    try {
                        executor.executeScript("arguments[0].click();",
                                driver.findElement(By.xpath("//*[@id=\"promote-yes\"]")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Machinepromo = 0;
                }
                String MachineAttribute = "sq" + First + "_" + pos;
                String MachineAttribute1 = "sq" + First1 + "_" + pos1;
                String wonder = "//*[contains(@class, 'square square-last')][1]";
                String wonder1 = "//*[contains(@class, 'square square-last')][2]";
                String wonder3 = "//*[contains(@class, 'square square-last')]";
                System.out.println(driver.findElements(By.xpath(wonder3)).size());
                driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                try {
                    for (int i = 0; i < 6; i++) {
                        Integer yes =
                                driver.findElements(By.xpath("//*[contains(@style, '" + Playerpromoarray[i] + "')]")).size();
                        Playerpromoarray1[i] = yes;
                    }
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
                if (driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() > 0) {
                    continue;
                }
                try {
                    while (driver.findElement(By.xpath(wonder)).getAttribute("id").toString().equals(MachineAttribute1) &&
                            driver.findElement(By.xpath(wonder1)).getAttribute("id").toString().equals(MachineAttribute) && driver.findElements(By.xpath(wonder3)).size() > 1 && driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() < 1 || driver.findElement(By.xpath(wonder)).getAttribute("id").toString().equals(MachineAttribute) &&
                            driver.findElement(By.xpath(wonder1)).getAttribute("id").toString().equals(MachineAttribute1) && driver.findElements(By.xpath(wonder3)).size() > 1 && driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() < 1) {
                        Thread.sleep(500);
                    }
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
                if (driver.findElements(By.xpath("//*[contains(@class, 'name-winner')]")).size() > 0) {
                    continue;
                }
                try {
                    for (int i = 0; i < 6; i++) {
                        Integer yes =
                                driver.findElements(By.xpath("//*[contains(@style, '" + Playerpromoarray[i] + "')]")).size();
                        if (Playerpromoarray1[i] < yes) {
                            promo++;
                        }
                    }
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
                if (driver.findElements(By.xpath(wonder3)).size() > 1) {
                    String Humanmove1 = driver.findElement(By.xpath(wonder)).getAttribute("id").toString();
                    String Humanmove2 = driver.findElement(By.xpath(wonder1)).getAttribute("id").toString();
                    List<WebElement> as1 = driver.findElements(By.xpath("//*[contains(@style, 'background-image: none')]"));
                    Iterator<WebElement> itr1 = as1.iterator();
                    while (itr1.hasNext()) {
                        String name = itr1.next().getAttribute("id");
                        if (Humanmove1.equals(name) || Humanmove2.equals(name)) {
                            Humanstartingmove = name;
                        }
                    }
                    if (Humanstartingmove.equals(Humanmove1)) {
                        Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2, 3));
                        Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4, 5));
                        Humanchar3 = Integer.valueOf(Humanmove2.substring(2, 3));
                        Humanchar4 = Integer.valueOf(Humanmove2.substring(4, 5));
                        alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2 - 1, Humanchar2);
                        alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                        if (promo.equals(0)) {
                            Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                        }
                        if (promo.equals(1)) {
                            Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2 + "+";
                        }
                        list.add(Humanmovedelivery);
                        HERE = Arrays.toString(list.toArray()).substring(1, i + promo);
                        i += 6 + promo;
                        System.out.println(HERE);
                        replace = HERE.replaceAll(",", "");
                        System.out.println(replace);
                        System.out.println("position startpos moves " + replace);
                        promo = 0;
                        continue;
                    }
                    if (Humanstartingmove.equals(Humanmove2)) {
                        Humanchar1 = Integer.valueOf(Humanstartingmove.substring(2, 3));
                        Humanchar2 = Integer.valueOf(Humanstartingmove.substring(4, 5));
                        Humanchar3 = Integer.valueOf(Humanmove1.substring(2, 3));
                        Humanchar4 = Integer.valueOf(Humanmove1.substring(4, 5));
                        alpha1 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar2 - 1, Humanchar2);
                        alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                        Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                        if (promo.equals(0)) {
                            Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2;
                        }
                        if (promo.equals(1)) {
                            Humanmovedelivery = Humanchar1 + alpha1 + Humanchar3 + alpha2 + "+";
                        }
                        list.add(Humanmovedelivery);
                        HERE = Arrays.toString(list.toArray()).substring(1, i + promo);
                        i += 6 + promo;
                        System.out.println(HERE);
                        replace = HERE.replaceAll(",", "");
                        System.out.println(replace);
                        promo = 0;
                        continue;
                    }
                } else {
                    String Humanmove1 = driver.findElement(By.xpath(wonder)).getAttribute("id").toString();
                    String Humanmove2solver = driver.findElement(By.xpath(wonder)).getAttribute("style").toString();
                    for (int i = 0; i < 7; i++) {
                        if (Humanmove2solver.contains(Playerdroparray[i])) {
                            Humanstartingmove = Playerdroparray1[i];
                        }
                    }
                    Humanchar3 = Integer.valueOf(Humanmove1.substring(2, 3));
                    Humanchar4 = Integer.valueOf(Humanmove1.substring(4, 5));
                    alpha2 = "abcdefghijklmnopqrstuvwxyz".substring(Humanchar4 - 1, Humanchar4);
                    Humanmovedelivery = Humanstartingmove + Humanchar3 + alpha2;
                    list.add(Humanmovedelivery);
                    HERE = Arrays.toString(list.toArray()).substring(1, i);
                    i += 6;
                    System.out.println(HERE);
                    replace = HERE.replaceAll(",", "");
                    System.out.println(replace);
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Checkwin();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void player(String args[]) throws InterruptedException, IOException {
        Starter();
        //MachineInterpreter();
        String projectPath = System.getProperty("user.home");
        String projectPathToTheDriver = "\\IdeaProjects\\81Dojo\\Resources\\chromedriver1.exe";
        System.setProperty("webdriver.chrome.driver", projectPath + projectPathToTheDriver);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(400, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://81dojo.com/client/?locale=en");
        Thread.sleep(1000);
        System.out.print(driver.findElement(By.xpath("//input[@id='usernameInput'][1]")));
        //driver.findElement(By.xpath("//input[@id='usernameInput'][1]")).sendKeys("YouLoveMe");
        //Thread.sleep(500);
        //driver.findElement(By.xpath("//input[@id='passwordInput'][1]")).sendKeys("a2632111");
        //driver.findElement(By.xpath("//input[@id='loginButton'][1]")).click();
        //Thread.sleep(4000);
        //driver.findElement(By.xpath("//input[@id='lobbyChatInput'][1]")).sendKeys("Hi");
        //driver.findElement(By.xpath("//input[@id='lobbyChatInput'][1]")).sendKeys(Keys.ENTER);
        //driver.findElement(By.xpath("//a[@data-i18n='lobby.wait'][1]")).click();
        //Thread.sleep(1000);
        //driver.findElement(By.xpath("//input[@name='newGameType'][@value='7']")).click();
        //Thread.sleep(1000);
        //executor.executeScript("document.evaluate('/html/body/div[6]/div[3]/div/button[1]', document, null, " +
        //        "XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
        //hread.sleep(500);
        while(driver.findElement(By.id("header-playerName")).getAttribute("innerText").toString().equals("")){
            Thread.sleep(600);
        }
        username = driver.findElement(By.id("header-playerName")).getText().toString();
        username1 = username.substring(0,username.length()-2);
        Machineratingsize = Integer.valueOf(driver.findElement(By.id("header-rate")).getText().substring(1,5).trim());
        Thread.sleep(3000);
        NewLobbyGame();
        while(driver.findElement(By.id("challengerTimerText")).getText().toString().equals("")){
            Thread.sleep(600);
        }
        ChallengeChecker();
        while (driver.findElements(By.xpath("//div[@id='sq2_7']")).isEmpty()) {
            Thread.sleep(600);
        }
        waiter();
    }
}
