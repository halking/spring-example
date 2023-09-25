package com.hal.example.sql;

import com.hal.sample.dto.MobileDto;
import com.hal.sample.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by Steven.HUANG on 2019/3/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MobileTest {

  @Autowired
  private ResourceLoader resourceLoader;

  private static String START_TRANS = "start transaction;";

  private static String COMMIT = "commit;";

  private static String LINE = "\n";

  private static String TARGET_FILE = "D:\\work\\lacoste\\03-data\\customer_mobile_md5.txt";

  private static String TARGET_CSV_FILE = "D:\\work\\lacoste\\03-data\\customer_mobile_md5.csv";

  @Before
  public void init() {
  }

  @Test
  public void init_mobile() throws Exception {
    String path = "file:D:\\work\\lacoste\\03-data\\过去一年有消费的会员_20200119.xlsx";
    Resource resource = resourceLoader.getResource(path);

    List<MobileDto> dtos = ExcelUtil.parseToObject(resource.getInputStream(),
        resource.getFilename(), MobileDto.class);
    File file = new File(TARGET_FILE);

    for (MobileDto dto : dtos) {
      if (StringUtils.isBlank(dto.getMobile())){
        continue;
      }
      String md5Value = DigestUtils.md5Hex(dto.getMobile());
      FileUtils.writeByteArrayToFile(file, md5Value.getBytes(StandardCharsets.UTF_8), true);
      FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);
      dto.setMobileMd5(md5Value);
    }

    this.createCSVFile(dtos);
  }


  public void createCSVFile(List<MobileDto> mobileDtos) throws IOException {
    String[] headers = {"card_no", "mobile", "mobile_md5"};
    FileWriter out = new FileWriter(TARGET_CSV_FILE);
    try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
      mobileDtos.forEach(mobileDto -> {
        try {
          printer.printRecord(mobileDto.getCardCode(), mobileDto.getMobile(), mobileDto.getMobileMd5());
        } catch (Exception e) {
          log.error(e.getMessage(), e);
          throw new RuntimeException(e.getMessage(), e);
        }
      });
    }
  }


}
