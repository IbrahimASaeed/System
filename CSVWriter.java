import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVWriter {

    private static class CSVWriterHolder {
        private static final CSVWriter INSTANCE = new CSVWriter();
    }

    public static CSVWriter getInstance() {
        return CSVWriterHolder.INSTANCE;
    }

    private CSVWriter() {
        // Private constructor to prevent instantiation
    }

    public synchronized void writeToCSV(String userId, String postcode, double co2Concentration) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String dataLine = String.join(",", timestamp, userId, postcode, String.valueOf(co2Concentration));

        try (PrintWriter out = new PrintWriter(new FileWriter("CO2_readings.csv", true))) {
            out.println(dataLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
