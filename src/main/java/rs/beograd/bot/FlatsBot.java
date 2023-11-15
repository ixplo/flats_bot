package rs.beograd.bot;

import rs.beograd.dao.FlatInfoDao;
import rs.beograd.dto.FlatInfo;
import rs.beograd.parsers.CityExpertFlatInfoParser;
import rs.beograd.parsers.FlatInfoParser;
import rs.beograd.parsers.HalooglasiFlatInfoParser;
import rs.beograd.telegram.TelegramSender;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FlatsBot {
    private static final long DELAY = 1000 * 60 * 3;
    public static final String NEW_LINE = "%0A";
    private static final List<FlatInfoParser> PARSERS = Arrays.asList(new CityExpertFlatInfoParser(), new HalooglasiFlatInfoParser());

    public void start() throws InterruptedException {
        while (true) {
            System.out.println(LocalDateTime.now() + " Checking flats...");
            checkFlats();
            Thread.sleep(DELAY);
        }
    }
    private void checkFlats() {
        Set<FlatInfo> old = FlatInfoDao.read();
        Map<String, FlatInfo> oldFlats = old.stream()
                .collect(Collectors.toMap(FlatInfo::getId, flatInfo -> flatInfo, (f1, f2) -> f1));
        PARSERS.forEach(flatInfoParser -> flatInfoParser.getFlats().stream()
                .filter(flatInfo -> !oldFlats.containsKey(flatInfo.getId()))
                .forEach(flatInfo -> {
                            old.add(flatInfo);
                            System.out.println(flatInfo);
                            TelegramSender.send(flatInfo.getUrl() + NEW_LINE + flatInfo.getPrice());
                        }
                ));
        FlatInfoDao.save(old);
    }
}
