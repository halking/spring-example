package com.hal.example.sql;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hal.sample.common.Constant;
import com.hal.sample.dto.AccountDto;
import com.hal.sample.dto.BindingMigration;
import com.hal.sample.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * @Author: Steven HUANG
 * @Date: 2019/8/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RwefSqlTest {

  private final static String STAFF_JSON_PATH = "D:\\work\\rwef\\one-binding\\data\\0827\\one2one_migration_tmp.json";

  private static String LINE = "\n";

  @Test
  public void get121BindingSa() throws Exception {
    String staffPath = "D:\\work\\rwef\\one-binding\\data\\121_bind.csv";
    try (
        Reader reader = Files.newBufferedReader(Paths.get(staffPath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim())) {

      Map<String, BindingMigration> accountMap = Maps.newHashMap();

      for (CSVRecord csvRecord : csvParser) {
        String avatar = csvRecord.get("avatarUrl");
        String name = csvRecord.get("name");
        String ldapAccount = csvRecord.get("ldapAccount");
        String cdbId = csvRecord.get("cdbId");
        String openId = csvRecord.get("openId");
        String boutiqueId = csvRecord.get("boutiqueId");

        BindingMigration migration = BindingMigration.builder().name(name).ldapAccount(ldapAccount).avatar(avatar)
            .cdbId(cdbId).openId(openId).boutiqueId(boutiqueId).build();

        accountMap.merge(ldapAccount, migration, (migration1, migration2) -> migration1);
      }

      createCSVFile(Lists.newArrayList(accountMap.values()), "121_staff.csv");
    }
  }

  @Test
  public void parseStaffCsv() throws Exception {
    String staffPath = "D:\\work\\rwef\\one-binding\\data\\0827\\one_to_tmp_0827.csv";
    try (
        Reader reader = Files.newBufferedReader(Paths.get(staffPath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim())) {
      File file = new File(STAFF_JSON_PATH);

 /*     Map<String, AccountDto> accountMap = parseAccountCsv();

      List<BindingMigration> migrationListWithOutCdb = Lists.newArrayList();*/

      for (CSVRecord csvRecord : csvParser) {
        String avatar = csvRecord.get("avatarUrl");
        String name = csvRecord.get("name");
        String ldapAccount = csvRecord.get("ldapAccount");
        String cdbId = csvRecord.get("cdbId");
        String openId = csvRecord.get("openId");
        String boutiqueId = csvRecord.get("boutiqueId");
        String unionId = csvRecord.get("unionid");
        String boutiqueName = csvRecord.get("boutiqueName");
        String qrCode = csvRecord.get("qrCode");

        BindingMigration migration = BindingMigration.builder().name(name).ldapAccount(ldapAccount).avatar(avatar)
            .cdbId(cdbId).openId(openId).unionId(unionId).boutiqueName(boutiqueName).qrCode(qrCode)
            .status(Constant.STATUS_UNBOUND).build();

/*        AccountDto account = accountMap.get(ldapAccount);
        if (account != null) {
          migration.setBoutiqueName(account.getBoutiqueName());
        }

        if (StringUtils.isBlank(cdbId) || StringUtils.equalsIgnoreCase(cdbId, "null") || account == null) {
          migration.setBoutiqueId(boutiqueId);
          migrationListWithOutCdb.add(migration);
          continue;
        }*/

        FileUtils.writeByteArrayToFile(file, JsonUtil.objectToJson(migration).getBytes(), true);
        FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);
      }

//      createCSVFile(migrationListWithOutCdb, "dimission_account.csv");

//      log.info("No cdbId size {}", migrationListWithOutCdb.size());
    }
  }

  public Map<String, String> parseBoutiqueCsv() throws Exception {
    String boutiquePath = "D:\\work\\rwef\\one-binding\\data\\boutiqueSa.csv";
    Map<String, String> boutiqueMap = Maps.newHashMap();

    try (
        Reader reader = Files.newBufferedReader(Paths.get(boutiquePath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim())) {

      for (CSVRecord csvRecord : csvParser) {
        String boutiqueName = csvRecord.get("boutiqueName");
        String boutiqueCode = csvRecord.get("boutiqueCode");
        boutiqueMap.put(boutiqueCode, boutiqueName);
      }
    }

    return boutiqueMap;
  }

  public Map<String, AccountDto> parseAccountCsv() throws Exception {
    String accountPath = "D:\\work\\rwef\\one-binding\\data\\warm_account_enabled.csv";
    Map<String, AccountDto> accountMap = Maps.newHashMap();

    try (
        Reader reader = Files.newBufferedReader(Paths.get(accountPath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
            .withFirstRecordAsHeader()
            .withIgnoreHeaderCase()
            .withTrim())) {

      for (CSVRecord csvRecord : csvParser) {
        String ldapName = csvRecord.get("ldap_name");
        String staffId = csvRecord.get("staff_id");
        String boutiqueCode = csvRecord.get("boutique_code");
        String boutiqueName = csvRecord.get("chinese_name");
        AccountDto account = AccountDto.builder().ldapName(ldapName).staffId(staffId).boutiqueId(boutiqueCode)
            .boutiqueName(boutiqueName).build();
        accountMap.put(ldapName, account);
      }
    }

    return accountMap;
  }

  public void createCSVFile(List<BindingMigration> migrations, String fileName) throws IOException {
    String dimissionPath = "D:\\work\\rwef\\one-binding\\data\\";
    String[] headers = {"openId", "cdbId", "name", "ldapAccount", "avatarUrl", "boutiqueId"};
    FileWriter out = new FileWriter(dimissionPath + fileName);
    try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
      migrations.forEach(migration -> {
        try {
          printer.printRecord(migration.getOpenId(), migration.getCdbId(), migration.getName(),
              migration.getLdapAccount(), migration.getAvatar(), migration.getBoutiqueId());
        } catch (Exception e) {
          log.error(e.getMessage(), e);
          throw new RuntimeException(e.getMessage(), e);
        }
      });
    }
  }
}
