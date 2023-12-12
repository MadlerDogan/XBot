package utilities;

import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.List;


public class ReuseableMethods {



    //Wait with Java und second
    public static void wait(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //Click Method
    public static void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            js.executeScript("arguments[0].click();", element);
        }
    }

    /*
The method reads the numbers in a given file path, selects a random number, removes the selected number from the list, and writes the updated list to the file.     */
    public static int getRandomNumberAndRemove(String filePath) {
        List<Integer> numbers = null;
        numbers = readNumbersFromFile(filePath);

        if (numbers.isEmpty()) {
            throw new IllegalArgumentException("In the file could not been any number.");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(numbers.size());
        int selectedNumber = numbers.get(randomIndex);

        numbers.remove(randomIndex);
        writeNumbersToFile(numbers, filePath);

        return selectedNumber;
    }

    /*
    readNumbersFromFile :Reads the numbers in the given file path and returns them as a list.
     */
    public static List<Integer> readNumbersFromFile(String filePath) {
        List<Integer> numbers = new ArrayList<>();

        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            try {
                throw new FileNotFoundException("The specified file was not found: " + filePath);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    int number = Integer.parseInt(line);
                    numbers.add(number);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return numbers;
    }

    /*
    writeNumbersToFile writes the updated list of numbers to the file.
     */
    public static void writeNumbersToFile(List<Integer> numbers, String filePath) {
        Path path = Path.of(filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (int number : numbers) {
                writer.write(String.valueOf(number));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    If the sendkeys method from junit does not work, it adds images and videos.
    */
    public static void robotMethod(String picturesLink) {
        StringSelection stringSelection = new StringSelection(picturesLink);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        //pressing ctrl+v
        robot.keyPress(KeyEvent.VK_CONTROL);
        ReuseableMethods.wait(1);
        robot.keyPress(KeyEvent.VK_V);
        ReuseableMethods.wait(1);
        //releasing ctrl+v
        robot.keyRelease(KeyEvent.VK_CONTROL);
        ReuseableMethods.wait(1);
        robot.keyRelease(KeyEvent.VK_V);
        ReuseableMethods.wait(1);
        //pressing enter
        ReuseableMethods.wait(1);
        robot.keyPress(KeyEvent.VK_ENTER);
        ReuseableMethods.wait(1);
        //releasing enter
        robot.keyRelease(KeyEvent.VK_ENTER);
        ReuseableMethods.wait(60);

    }

    //Make the sublists the lines in the text
    public static List<List<String>> createSublists(List<String> inputList, int sublistSize) {
        List<List<String>> sublists = new ArrayList<>();
        int start = 0;
        int end = Math.min(sublistSize, inputList.size());

        while (start < inputList.size()) {
            List<String> sublist = inputList.subList(start, end);
            sublists.add(sublist);
            start = end;
            end = Math.min(end + sublistSize, inputList.size());
        }

        return sublists;
    }

    /*
Extracts data scraped from the internet
     */
    public static void fileYazdir() {
        try {
            // Okunacak dosyanın adı ve yolu
            File file = new File("ScreenSchots/screenshot.txt");
            // Dosyayı okumak için FileReader sınıfını kullanın
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                // Satırda @ işaretiyle başlayan kelimeleri al
                String[] words = line.split("\\s+");
                Set<String> kelimeler = new TreeSet<>(Arrays.asList(words));

                for (String word : kelimeler) {
                    if (word.startsWith("@")) {
                        stringBuilder.append(word + " ");
                    }
                }
            }
            reader.close();

            FileWriter writer = new FileWriter(file);
            // Dosyaya sadece @ ile başlayan kelimeleri yazdır
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
  This method opens the file in the file path it takes as a parameter, reads its content, selects only words starting with "@"" and returns it as a list.
     */
    public static List<String> extractSelectedWords(String filePath) {
        List<String> selectedWords = new ArrayList<String>();
        try {
            // Okunacak dosyanın adı ve yolu
            File file = new File(filePath);
            // Dosyayı okumak için FileReader sınıfını kullanın
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Satırda @ işaretiyle başlayan kelimeleri al
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.startsWith("@")) {
                        selectedWords.add(word);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> set = new TreeSet<String>(selectedWords);
        List<String> list = new ArrayList<String>(set);

        return list;
    }

    /*
    Method of scraping data from Twitter page, with screenshot
     */
    public void captureScreenshots(WebDriver driver, String filename) {
        int consecutiveFailedScrolls = 0;

        while (true) {
            String dosyaYolu = "ScreenSchots/" + filename + System.currentTimeMillis() + ".png";

            TakesScreenshot ts = (TakesScreenshot) driver;
            try {
                FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.PAGE_DOWN).perform();

            boolean scrolled = true; // Bu değeri sayfanın başarılı şekilde kaydırılıp kaydırılmadığını kontrol etmek için kullan

            if (!scrolled) {
                consecutiveFailedScrolls++;
                if (consecutiveFailedScrolls >= 3) {
                    try {
                        Thread.sleep(30000); // 30 saniye bekleme süresi
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    consecutiveFailedScrolls = 0;
                }
            } else {
                consecutiveFailedScrolls = 0;
            }
        }
    }

    /*
   Method of scraping data from Twitter page, with copy past
    */
    public void captureAndWriteData(WebDriver driver, String filename, int pageNumber) {
        Actions actions = new Actions(driver);
        String dosyaYolu = "ScreenSchots/" + filename + ".txt";

        try (FileWriter writer = new FileWriter(dosyaYolu, true)) {
            int count = 0;
            do {
                actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
                Thread.sleep(1000);

                actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
                Thread.sleep(1000);

                String veri = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);

                writer.append(veri);
                Thread.sleep(1000);

                actions.sendKeys(Keys.PAGE_DOWN).perform();
                Thread.sleep(1000);

                count++;
            } while (count < pageNumber);
        } catch (IOException | InterruptedException | UnsupportedFlavorException e) {
            e.printStackTrace();
        }
    }

    /*
     removeSelectedWordFromFile, Deletes a specific word in the given file path from the file.
      */
    public static void removeSelectedWordFromFile(String filePath, String selectedWord) {
        // Dosyayı aç
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Dosyadaki verileri bir List objesi olarak oku
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        // Dosyayı kapat
        scanner.close();

        // List objesindeki elemanı sil
        lines.remove(selectedWord);

        // Dosyayı aç ve güncel verileri dosyaya yaz
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line : lines) {
            try {
                writer.write(line + System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //read text lines and return as a list
    public static List<String> dosyaSatirlariniOku(String dosyaYolu) {
        List<String> satirlar = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                satirlar.add(satir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return satirlar;
    }

    //select randon centences from text
    public static String selectRandomSentence(String[] sentences) {
        if (sentences == null || sentences.length == 0) {
            return "Centences can not be finded.";
        }

        Random random = new Random();
        int randomIndex = random.nextInt(sentences.length);
        return sentences[randomIndex];
    }

    //generate random numbers
    public static int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Minimum value must be less than maximum value.");
        }

        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }


}

