package com.botTools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class CunyBotMain {
	//static WebDriver driver = new ChromeDriver();
	public static void main(String[] args) throws InterruptedException {
		InputVars inputVars = new InputVars();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What's the college name? (It is case sensative)");
		
		inputVars.setCollegename(scan.nextLine());
		
		
		System.out.println("Term?(Format: [year] [Spring, Summer, Fall] Term)");
		String term = scan.nextLine();
		
		System.out.println("What Major?(Ex. Computer Science, Music ...)");
		String subjectname = scan.nextLine();
		//scan.nextLine();
		System.out.println("How many classes you want to search?");
		int classestosearch = scan.nextInt();
		
		//System.out.println("Do you want to reload to keep checking the class?");
		
		String[] classnumbers = new String[classestosearch];
		for (int i = 0; i < classnumbers.length; i++) {
			System.out.println("Enter Number Only For Class "+(i+1)+":");
			classnumbers[i] = scan.next();
		}
		
		Boolean reload = true;
		Boolean hornOnOrOff = true;
		
		System.out.println("College Selected:" + inputVars.getCollegename());
		System.out.println("Term:" + term);
		System.out.println("Major:" + subjectname);
		System.out.println("Classes:" + classnumbers.length);
		scan.close();
		
		
		String projectpath = System.getProperty("user.dir");
		System.out.println(projectpath);
		System.setProperty("webdriver.chrome.driver", projectpath + "/chromedriver/chromedriver.exe");
			
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);
		
		navigatetothesite(driver,inputVars.getCollegename(), term, subjectname);

		
		//getallclassinfo(driver, classname);
		do {
			driver.findElement(By.xpath("//span[@class='cunylitepagetext']//a//img")).click();

			//Gets all the classes available
			List<WebElement> classname = driver.findElements(By.xpath("//*[@id=\"contentDivImg_inst0\"]/table"));
			List<String> classnums = new ArrayList<String>();
			
			for (WebElement a : classname) {
				classnums.add(regexChecker("\\s\\d{2,}\\s", a.getText()));
			}

			for (int i = 0; i < classnumbers.length; i++) {
				
				if (classnums.contains(classnumbers[i])) {
					//System.out.println("Index: " + classnums.indexOf(classnumber));
					gettheclassinfo(driver, classname, classnums.indexOf(classnumbers[i]), hornOnOrOff);
				}
				else
				{
					System.out.println("There is no class by " + classnumbers[i]);
				}
			}
			classname.clear();
			classnums.clear();
			System.out.println("Reloading in 10 sec");
			Thread.sleep(10000);
			driver.navigate().refresh();
		} while (reload);
		
		driver.close();
		driver.quit();
	}

	public static void navigatetothesite(WebDriver driver, String collegename, String term, String subjectname ) {
		// open the cuny page
		driver.get("https://globalsearch.cuny.edu/CFGlobalSearchTool/search.jsp");
		
		System.out.println("Page 1");
		
		// check the college button
		driver.findElement(By.xpath("//label[contains(text(),'"+ collegename +"')]")).click();

		// select the term
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='term_value']")));
		dropdown.selectByVisibleText(term);

		// click the next button
		driver.findElement(By.xpath("//input[@name='next_btn']")).click();
		
		 System.out.println("Page 2");

		// select the subject
		Select subject = new Select(driver.findElement(By.xpath("//select[@id='subject_ld']")));
		subject.selectByVisibleText(subjectname);

		// select the subject
		Select course = new Select(driver.findElement(By.xpath("//select[@name='courseCareer']")));
		course.selectByValue("UGRD");

		// uncheck the open classes only
		driver.findElement(By.xpath("//label[contains(text(),'Show Open Classes Only')]")).click();
		
		// click search
		driver.findElement(By.xpath("//input[@id='btnGetAjax']")).click();
		
		System.out.println("Page 3");

	}

	public static void setAttribute(WebDriver driver, WebElement element, String attName, String attValue) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", 
                element, attName, attValue);
    }
	
	public static void gettheclassinfo(WebDriver driver, List<WebElement> classname, int index, boolean hornOnOrOff) {
		//Classes Info
		String OpenOrClose = "";
		String proName = "";
		String daysTime = "";
		
		setAttribute(driver ,driver.findElement(By.xpath("//*[@id=\"contentDivImg"+index+"\"]")), "style", "display: block;");
		
		List<WebElement> classopens = driver.findElements(By.xpath("//*[@id=\"contentDivImg" + index + "\"]/table/tbody/tr"));
		if (classopens.size() > 2) {
			for (int k = 2; k <= classopens.size(); k++) {
				
				OpenOrClose = driver.findElement(By.xpath("//*[@id=\"contentDivImg" + index + "\"]/table/tbody/tr[" + k + "]/td[9]/img")).getAttribute("title");
				proName = driver.findElement(By.xpath("//*[@id=\"contentDivImg" + index + "\"]/table/tbody/tr[" + k + "]/td[6]")).getText();
				daysTime = driver.findElement(By.xpath("//*[@id=\"contentDivImg"+index+"\"]/table/tbody/tr["+k+"]/td[4]")).getText();
			
				System.out.println(classname.get(index).getText() + "|| " + proName + "|| "+ daysTime +"|| " + OpenOrClose.toUpperCase());
				if(hornOnOrOff && OpenOrClose.equalsIgnoreCase("open")) {
					System.out.println(classname.get(index).getText() + " Is OPENN!! Bring in the horn!");
					playsound(System.getProperty("user.dir") + "/sound/dj-airhorn-sound-effect-kingbeatz_1.wav");
				}
				
			}
		} 
		else {
			OpenOrClose = driver.findElement(By.xpath("//*[@id=\"contentDivImg" + index + "\"]/table/tbody/tr[2]/td[9]/img")).getAttribute("title");
			proName = driver.findElement(By.xpath("//*[@id=\"contentDivImg" + index + "\"]/table/tbody/tr[2]/td[6]")).getText();
			daysTime = driver.findElement(By.xpath("//*[@id=\"contentDivImg"+index+"\"]/table/tbody/tr[2]/td[4]")).getText();
			System.out.println(classname.get(index).getText() + "|| " + proName + "|| "+ daysTime +"|| " + OpenOrClose.toUpperCase());
			if(hornOnOrOff && OpenOrClose.equalsIgnoreCase("open")) {
				System.out.println(classname.get(index).getText() + " Is OPENN!! Bring in the horn!");
				playsound(System.getProperty("user.dir") + "/sound/dj-airhorn-sound-effect-kingbeatz_1.wav");
			}
		}

		classopens.clear();
		
	}
	
	public static void getallclassinfo(WebDriver driver, List<WebElement> classname) {
		String OpenOrClose = "";
		String proName = "";
		String daysTime = "";
		
		for (int j = 0; j < classname.size(); j++) {
			setAttribute(driver ,driver.findElement(By.xpath("//*[@id=\"contentDivImg"+j+"\"]")), "style", "display: block;");
			
			List<WebElement> classopens = driver.findElements(By.xpath("//*[@id=\"contentDivImg" + j + "\"]/table/tbody/tr"));
			if (classopens.size() > 2) {
				for (int k = 2; k <= classopens.size(); k++) {
					
					OpenOrClose = driver.findElement(By.xpath("//*[@id=\"contentDivImg" + j + "\"]/table/tbody/tr[" + k + "]/td[9]/img")).getAttribute("title");
					proName = driver.findElement(By.xpath("//*[@id=\"contentDivImg" + j + "\"]/table/tbody/tr[" + k + "]/td[6]")).getText();
					daysTime = driver.findElement(By.xpath("//*[@id=\"contentDivImg"+j+"\"]/table/tbody/tr["+k+"]/td[4]")).getText();
				
					System.out.println(classname.get(j).getText() + "|| " + proName + "|| "+ daysTime +"|| " + OpenOrClose.toUpperCase());
				}
			} 
			else {
				OpenOrClose = driver.findElement(By.xpath("//*[@id=\"contentDivImg" + j + "\"]/table/tbody/tr[2]/td[9]/img")).getAttribute("title");
				proName = driver.findElement(By.xpath("//*[@id=\"contentDivImg" + j + "\"]/table/tbody/tr[2]/td[6]")).getText();
				daysTime = driver.findElement(By.xpath("//*[@id=\"contentDivImg"+j+"\"]/table/tbody/tr[2]/td[4]")).getText();
				System.out.println(classname.get(j).getText() + "|| " + proName + "|| "+ daysTime +"|| " + OpenOrClose.toUpperCase());
			}

			classopens.clear();
		}
	}
	
	public static void playsound(String filepath)
	{
		File musicPath = new File(filepath);
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(musicPath));
			clip.start();
			
			Thread.sleep(clip.getMicrosecondLength()/1000);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		try {
//			File musicPath = new File(filepath);
//			if (musicPath.exists()) {
//				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);
//				Clip clip = AudioSystem.getClip();
//				clip.open(audioInputStream);
//				clip.start();
//				JOptionPane.showMessageDialog(null, "press ok to stop playing");
//				clip.loop(clip.LOOP_CONTINUOUSLY);
//				
//			}
//			else {
//				System.out.println("Can't find file");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static String regexChecker(String theRegex, String str2Check) {

		// You define your regular expression (REGEX) using Pattern

		Pattern checkRegex = Pattern.compile(theRegex);

		// Creates a Matcher object that searches the String for
		// anything that matches the REGEX

		Matcher regexMatcher = checkRegex.matcher(str2Check);

		String tempString = "";

		// Cycle through the positive matches and print them to screen
		// Make sure string isn't empty and trim off any whitespace

		while (regexMatcher.find()) {
			if (regexMatcher.group().length() != 0) {
				tempString = regexMatcher.group().trim();
			}
		}

		return tempString;
	}
}

