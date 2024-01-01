package rs.beograd.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jetbrains.annotations.NotNull;
import rs.beograd.dto.FlatInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import static org.jsoup.helper.StringUtil.isBlank;

public class FlatInfoDao {

    public static final String FILE_NAME = "flats.txt";
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static Set<FlatInfo> read() {
        return fromJson(readJson());
    }

    private static String readJson() {
        Path path = Paths.get(FILE_NAME);
        createIfDoesNotExist(path);
        return read(path);
    }

    private static void createIfDoesNotExist(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @NotNull
    private static String read(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Error reading flats list", e);
        }
    }

    public static void save(Set<FlatInfo> flats) {
        save(toJson(flats));
    }

    private static void save(String json) {
        Path path = Paths.get(FILE_NAME);
        byte[] strToBytes = json.getBytes();
        try {
            Files.write(path, strToBytes);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal json: " + json, e);
        }
    }

    private static String toJson(Set<FlatInfo> flats) {
        try {
            return mapper.writeValueAsString(flats);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Illegal flats list", e);
        }
    }

    private static Set<FlatInfo> fromJson(String json) {
        if (isBlank(json)) {
            return new HashSet<>();
        }
        try {
            return mapper.readValue(json, new TypeReference<Set<FlatInfo>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Error reading Set from json", e);
        }
    }
}
