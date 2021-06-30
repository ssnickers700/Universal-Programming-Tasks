package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TravelData {

    List<String> inList = new ArrayList<>();
    List<String> dbList = new ArrayList<>();

    public TravelData(File dataDir) {

        try {
            Files.walk(Paths.get(dataDir.getPath())).forEach(e -> {
                try {
                    inList.addAll(Files.readAllLines(e, Charset.forName("Cp1250")));
                } catch (IOException e1) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {

        List<String> outList = new ArrayList<>();
        Locale outLocale = Locale.forLanguageTag(locale.replace("_","-"));
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) DateFormat.getDateInstance();
        simpleDateFormat.applyPattern(dateFormat);

        inList.forEach( e -> {

            String[] line = e.split("\t");
            Locale lineLocale = Locale.forLanguageTag(line[0].replace("_","-"));
            StringBuilder sb = new StringBuilder();

            for (Locale l : Locale.getAvailableLocales()) {
                if (l.getDisplayCountry(lineLocale).equals(line[1])) {
                    sb.append(l.getDisplayCountry(outLocale)).append("\t");
                    break;
                }
            }

            try {
                Date date = simpleDateFormat.parse(line[2]);
                sb.append(simpleDateFormat.format(date)).append("\t");

                date = simpleDateFormat.parse(line[3]);
                sb.append(simpleDateFormat.format(date)).append("\t");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            ResourceBundle resourceBundle = ResourceBundle.getBundle("TranslationData", outLocale);
            sb.append(resourceBundle.getString(line[4])).append("\t");

            try {
                NumberFormat numberFormatLine = NumberFormat.getInstance(lineLocale);
                NumberFormat numberFormatOut = NumberFormat.getInstance(outLocale);
                Number num = numberFormatLine.parse(line[5]);
                sb.append(numberFormatOut.format(num)).append("\t");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            Currency currency = Currency.getInstance(line[6]);
            sb.append(currency.getCurrencyCode());

            outList.add(sb.toString());
        });
        dbList.addAll(outList);
        return outList;
    }
}
