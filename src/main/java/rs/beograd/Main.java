package rs.beograd;

import rs.beograd.bot.FlatsBot;
import rs.beograd.dao.FlatInfoDao;
import rs.beograd.dto.FlatInfo;
import rs.beograd.parsers.CityExpertFlatInfoParser;
import rs.beograd.parsers.FlatInfoParser;
import rs.beograd.parsers.HalooglasiFlatInfoParser;
import rs.beograd.telegram.TelegramSender;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        new FlatsBot().start();
    }
}
