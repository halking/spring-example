package com.hal.example.sql;

import com.google.common.collect.Sets;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

//@Component
@RunWith(SpringRunner.class)
@SpringBootTest
public class BoutiqueSAImport {

  private CSVParser parse(Path path) throws IOException {
    Reader reader = Files.newBufferedReader(path);
    return new CSVParser(reader, CSVFormat.DEFAULT
        .withFirstRecordAsHeader()
        .withIgnoreHeaderCase()
        .withTrim());
  }

  @Test
  public void run() throws Exception {

    String root = "D:\\work\\warm\\inti_data\\account";
    CSVParser result0419 = parse(Paths.get(root, "SA_0419_final.csv"));
    CSVParser result0429 = parse(Paths.get(root, "SA_0429_final.csv"));

    Set<String> ldap0419 = Sets.newHashSet();

    Set<String> boutiqueCode0419 = Sets.newHashSet();

/*
    for (CSVRecord record : result0419.getRecords()) {
      String saName = record.get("Sa_name");
      String ldapName = record.get("Sa_ldap");
      String boutiqueCode = StringUtils.leftPad(record.get("Boutique_code"), 4, '0');
      ldap0419.add(ldapName);
      boutiqueCode0419.add(boutiqueCode);
    }
*/

    //INSERT INTO warm_account
    //(boutique_id, display_name, last_login, ldap_name, `role`, create_time, update_time, staff_id, mp_user_id, image_url, enabled)
    //VALUES('', '', '', '', '', CURRENT_TIMESTAMP, '', '', '', '', b'1');

    Map<Boutique, List<Account>> map = new LinkedHashMap<>();

    Set<String> ldap0429 = Sets.newHashSet();

    for (CSVRecord record : result0429.getRecords()) {
      String ldapName = record.get("Sa_ldap");
      String staffId = record.get("staff_id");
      ldap0429.add(staffId);
      if (ldap0419.contains(ldapName)) {
        continue;
      }
//      System.out.println(ldapName);
      String saName = record.get("Sa_name");
      String email = record.get("Sa_email_address");
      String boutiqueCode = StringUtils.leftPad(record.get("Boutique_code"), 4, '0');
      String boutiqueChineseName = record.get("Boutique_display_name");
      String boutiqueName = record.get("Boutique_name");
      String region = record.get("Region");
      String role = record.get("BM_tag").equals("BM") ? "ROLE_BM" : "ROLE_SA";

//      System.out.println(staffId);
      Boutique boutique = new Boutique(boutiqueCode, boutiqueName, boutiqueChineseName, region);
      Account account = new Account(saName, ldapName, role, staffId, email);

      map.computeIfAbsent(boutique, b -> new ArrayList<>()).add(account);

    }

    for (CSVRecord record : result0419.getRecords()) {
      String saName = record.get("Sa_name");
      String ldapName = record.get("Sa_ldap");
      String staffId = record.get("staff_id");

      if (ldap0429.contains(staffId)) {
        continue;
      }

      System.out.println(staffId);
    }

    Path sqlPath = Paths.get("D:\\work\\warm\\inti_data\\account\\sql", "account_init_20190509.sql");
    Files.deleteIfExists(sqlPath);

    Files.createFile(sqlPath);
    Files.write(sqlPath, "start transaction;\n".getBytes(), CREATE);
    Files.write(sqlPath, "\n".getBytes(), APPEND);
    List<String> line = new ArrayList<>();

    for (Entry<Boutique, List<Account>> entry : map.entrySet()) {
      Boutique boutique = entry.getKey();

      String enabled =
          boutique.getBoutiqueChineseName().contains("杭州") && !boutique.getBoutiqueChineseName().contains("欧洲街")
              ? "b'1'" : "b'0'";
      String boutiqueId = UUID.randomUUID().toString().replaceAll("-", "");

      if (boutiqueCode0419.contains(boutique.getBoutiqueCode())) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT @").append(boutique.getBoutiqueCode()).append("_id := id ")
            .append("FROM  warm_car_boutique WHERE `boutique_code` = ")
            .append("'").append(boutique.getBoutiqueCode()).append("'").append(";");
        line.add(builder.toString());
      } else {

        String boutiqueInsertTemplate = "INSERT INTO warm_car_boutique\n"
            + "(id, boutique_code, chinese_name, english_name, region, rwf_boutique_id, enabled)\n"
            + "VALUES('${boutiqueId}', '${boutiqueCode}', '${boutiqueChineseName}', '${boutiqueName}', '${region}', '' ,${enabled});\n";
        Map<String, String> substitutes = new HashMap<>();
        substitutes.put("boutiqueId", boutiqueId);
        substitutes.put("boutiqueCode", boutique.getBoutiqueCode());
        substitutes.put("boutiqueChineseName", boutique.getBoutiqueChineseName());
        substitutes.put("boutiqueName", boutique.getBoutiqueName());
        substitutes.put("region", boutique.getRegion());
        substitutes.put("enabled", enabled);

        StringSubstitutor sub = new StringSubstitutor(substitutes);
        String boutiqueInsert = sub.replace(boutiqueInsertTemplate);
        line.add(boutiqueInsert);
      }

      for (Account account : entry.getValue()) {
        //probation

        String tmp = "INSERT INTO warm_account\n"
            + "(boutique_id, display_name, last_login, ldap_name, `role`, create_time, update_time, staff_id, mp_user_id, image_url, email, enabled)\n"
            + "VALUES('${boutiqueId}', '${displayName}', null, '${ldapName}', '${role}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ${staffId}, null, null, '${email}', ${enabled});\n";

        Map<String, String> userMap = new HashMap<>();
        userMap.put("boutiqueId", boutiqueId);
        userMap.put("displayName", account.getDisplayName());
        userMap.put("ldapName", account.getLdapName());
        userMap.put("role", account.getRole());
        userMap.put("staffId", account.getStaffId().equals("probation") ? "NULL" : "'" + account.getStaffId() + "'");
        userMap.put("enabled", enabled);
        userMap.put("email", account.getEmail());

        StringSubstitutor userSub = new StringSubstitutor(userMap);
        String accountInsert = userSub.replace(tmp);

        line.add(accountInsert);

      }
    }
    for (String s : line) {
      Files.write(sqlPath, s.getBytes(), APPEND);
      Files.write(sqlPath, "\n".getBytes(), APPEND);
    }
    Files.write(sqlPath, "\n".getBytes(), APPEND);
    Files.write(sqlPath, "commit;".getBytes(), APPEND);


  }

  private void genBoutiqueSql(Set<String> boutiqueCodes) throws Exception {
    for (String boutiqueCode : boutiqueCodes) {
      StringBuilder builder = new StringBuilder();
      builder.append("SELECT @").append(boutiqueCode).append("_id := id ")
          .append("FROM  warm_car_boutique WHERE `boutique_code` = ")
          .append("'").append(boutiqueCode).append("'").append(";");
    }
  }

  @Data
  private static class Boutique {

    private String boutiqueCode;

    private String boutiqueName;

    private String boutiqueChineseName;

    private String region;

    public Boutique(String boutiqueCode, String boutiqueName, String boutiqueChineseName, String region) {
      this.boutiqueCode = boutiqueCode;
      this.boutiqueName = boutiqueName;
      this.boutiqueChineseName = boutiqueChineseName;
      this.region = region;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Boutique boutique = (Boutique) o;
      return Objects.equals(boutiqueCode, boutique.boutiqueCode);
    }

    @Override
    public int hashCode() {
      return Objects.hash(boutiqueCode);
    }
  }

  @Data
  private static class Account {

    public Account(String displayName, String ldapName, String role, String staffId, String email) {
      this.displayName = displayName;
      this.ldapName = ldapName;
      this.role = role;
      this.staffId = staffId;
      this.email = email;
    }

    private String displayName;

    private String ldapName;

    private String role;

    private String staffId;

    private String email;

  }

}
