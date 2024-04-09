import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        String folderPath = "HashMessage";
        String Message = "Tôi là HOÀNG ĐỨC TRÌNH";

        File folder = new File(folderPath);

        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (created) {
                System.out.println("Thư mục đã được tạo thành công!");
            } else {
                System.out.println("Không thể tạo thư mục.");
            }
        } else {
            System.out.println("Thư mục đã tồn tại.");
        }
        try {
            // Tạo một đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Băm dữ liệu đầu vào
            byte[] hashedBytes = digest.digest(Message.getBytes());

            // Chuyển đổi mảng byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                String hex = Integer.toHexString(0xff & hashedByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // In ra mã băm SHA-256
            System.out.println("SHA-256 Hash: " + hexString.toString());

            // Tạo một tệp mới trong thư mục đã tạo và ghi mã băm vào tệp
            File outputFile = new File(folder, "hashed_message.txt");
            FileWriter writer = new FileWriter(outputFile);
            writer.write("SHA-256 Hash: " + hexString.toString());
            writer.close();
            System.out.println("Mã băm đã được ghi vào tệp: " + outputFile.getAbsolutePath());

        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

}
