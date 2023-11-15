package rs.beograd.parsers;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import rs.beograd.dto.FlatInfo;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HalooglasiFlatInfoParser implements FlatInfoParser {

    public static final String BASE_URL = "https://www.halooglasi.com";
    public static final String URL = BASE_URL + "/nekretnine/izdavanje-stanova/beograd-novi-beograd?cena_d_from=490&cena_d_to=800";

    public HalooglasiFlatInfoParser() {
    }

    public Set<FlatInfo> getFlats() {
        try {
            return getFlatsInternal();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptySet();
    }

    private Set<FlatInfo> getFlatsInternal() throws IOException {
        Set<FlatInfo> flats = new HashSet<>();
        Document doc = Jsoup.connect(URL).get();
        doc.select("div.product-item").forEach(element -> {
            String id = element.id();
            String url = BASE_URL + element.select(".product-title a").attr("href");
            String price = element.select("div.central-feature span").text();
            String publishDate = element.select(".publish-date").text();
            if (!StringUtil.isBlank(price)) {
                flats.add(new FlatInfo(id, url, price, publishDate));
            }
        });
        return flats;
    }
}
