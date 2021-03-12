package main;

import jdk.internal.org.objectweb.asm.tree.analysis.Interpreter;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
//Replace the strings of $cdc in chromedriver with hex editor to avoid selenium detection
//https://github.com/yaneurao/YaneuraOu
//http://hgm.nubati.net/usi.html
//OPPONENT - pawn = Gfu.png, rook = Ghi.png, Bishop = Gkaku.png, lance = Gkyo.png, knight = Gkei.png, silver = Ggin
// .png, gold = Gkin.png
//sq0_1 = rook, sq0_2 = bishop, sq0_3 = gold, sq0_4 = silver, sq0_5 = knight, sq0_6 = lance, sq0_7 = pawn - GO FIRST
//sq-1_1 = rook, sq-1_2 = bishop, sq-1_3 = gold, sq-1_4 = silver, sq-1_5 = knight, sq-1_6 = lance, sq-1_7 = pawn - GO
// SECOND
//PROMOTIONS SENTE- lance = Snkyo, silver = Sngin, knight = Snkei, bishop = Suma, rook = Sryu, pawn = Sto
//PROMOTIONS GOTE- lance = Gnkyo, silver = Gngin, knight = Gnkei, bishop = Guma, rook = Gryu, pawn = Gto
//Button ID for promos to press: "promote-yes", "promote-no"

public class main {
    public static void main(String args[]) throws InterruptedException, IOException {
        ShogiDragon.main(new String[0]);
    }
}