import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Formatter;
import java.util.HashMap;

public class HashExample {
    public static void main(String[] args) {
        String input = "Hello, World!";
        String md5Hash = generateHash("MD5", input);
        System.out.println("MD5 Hash: " + md5Hash);

        String sha1Hash = generateHash("SHA-1", input);
        System.out.println("SHA-1 Hash: " + sha1Hash);

        String sha256Hash = generateHash("SHA-256", input);
        System.out.println("SHA-256 Hash: " + sha256Hash);



        String seedString = "ConsistentSeed";
        byte[] randomData = generateSeededRandomBytes(seedString, 16);

        String hashMD5 = generateHash("MD5", randomData);
        System.out.println("MD5 Hash: " + hashMD5);

        String hashSHA1 = generateHash("SHA-1", randomData);
        System.out.println("SHA-1 Hash: " + hashSHA1);

        String hashSHA256 = generateHash("SHA-256", randomData);
        System.out.println("SHA-256 Hash: " + hashSHA256);

        String hashSHA384 = generateHash("SHA-384", randomData);
        System.out.println("SHA-384 Hash: " + hashSHA384);

        String hashSHA512 = generateHash("SHA-512", randomData);
        System.out.println("SHA-512 Hash: " + hashSHA512);


        HashMap<StudentCorrect, String> correctMap = new HashMap<>();
        HashMap<IncorrectStudent, String> incorrectMap = new HashMap<>();

        StudentCorrect student1 = new StudentCorrect(1, "Alice");
        StudentCorrect student2 = new StudentCorrect(1, "Andrew");
        StudentCorrect student3 = new StudentCorrect(2, "Bob");

        IncorrectStudent incorrectStudent1 = new IncorrectStudent(1, "Alice");
        IncorrectStudent incorrectStudent2 = new IncorrectStudent(1, "Andrew");
        IncorrectStudent incorrectStudent3 = new IncorrectStudent(2, "Bob");

        correctMap.put(student1, "Data for Alice");
        correctMap.put(student2, "Data for Andrew");
        correctMap.put(student3, "Data for Bob");

        incorrectMap.put(incorrectStudent1, "Data for Alice");
        incorrectMap.put(incorrectStudent2, "Data for Andrew"); //Некоректно обробляється
        incorrectMap.put(incorrectStudent3, "Data for Bob");

        // Виведення результатів
        System.out.println("StudentCorrect HashMap:");
        for (StudentCorrect key : correctMap.keySet()) {
            System.out.println(key + " -> " + correctMap.get(key));
        }

        System.out.println("\nIncorrectStudent HashMap:");
        for (IncorrectStudent key : incorrectMap.keySet()) {
            System.out.println(key + " -> " + incorrectMap.get(key));
        }
    }
    private static byte[] generateSeededRandomBytes(String seedString, int length) {
        long seed = seedString.hashCode();
        SecureRandom randomGenerator = new SecureRandom();
        randomGenerator.setSeed(seed);
        byte[] randomData = new byte[length];
        randomGenerator.nextBytes(randomData);
        return randomData;
    }
    private static String generateHash(String algorithm, String input) {
        return generateHash(algorithm, input.getBytes());
    }
    private static String generateHash(String algorithm, byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = messageDigest.digest(data);
            return convertBytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Хеш алгоритм не знайдено: " + algorithm, e);
        }
    }
    private static String convertBytesToHex(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}