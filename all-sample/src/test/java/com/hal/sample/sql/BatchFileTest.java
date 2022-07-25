package com.hal.sample.sql;

import com.google.common.collect.Sets;
import com.hal.sample.util.JsonUtil;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Steven HUANG
 * @Date: 2021/9/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BatchFileTest {

  private RestTemplate restTemplate;

  private String access_token = "53_YEug2uM9yYdbTmCigaWROWjYd2drq0J42A_ICk1uiyT0tIJr7CcfBVKxEvJveSFsn6YxyjygsPNPk--11oYCvfU7gDT1ssN6zyAEDf_EGf9QCMOjUcAK1s1HR8GrJGYzXEsQ2Z5RBNR02yhKMIJeAJASOP";

  private String REQUEST_URL = "https://api.weixin.qq.com/wxa/getwxacode?access_token=%s";

  private String CSV_FILE = "D:\\work\\Decathlon\\02-Racket Service\\05-QRCode\\all_store.csv";

  private String REVIEW_CSV_FILE = "D:\\work\\Decathlon\\06-dktReview\\07-二维码\\01-门店或工作室\\tbl_store_202201211624.csv";

  private String REVIEW_CSV_FILE_V2 = "D:\\work\\Decathlon\\06-dktReview\\07-二维码\\01-门店或工作室\\tbl_store_v2.csv";

  private Set<String> existStore = Sets.newHashSet("0500018800188");

  @Before
  public void init() {
    restTemplate = new RestTemplate();
    restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    REQUEST_URL = String.format(REQUEST_URL, access_token);
  }

  @Test
  public void createFile() throws Exception {
    CSVParser csvResult = parse(Paths.get(REVIEW_CSV_FILE));
    for (CSVRecord record : csvResult.getRecords()) {
      String name = record.get("name");
      String storeId = record.get("store_id");

      if (existStore.contains(storeId)) {
        continue;
      }

      String qrCodeName = StringUtils.joinWith("-", storeId, name, "工作室") + ".png";
      log.info("开始生成门店太阳码， 门店名称-门店编号-工作室： {}", qrCodeName);
      QrCodeDTO dto = new QrCodeDTO();
      String path = String.format("packageReview/pages/store/store?storeNumber=%s&storeName=%s&type=WORKSHOP", storeId, name);
      dto.setPath(path);
      log.info("工作室二维码：{}", path);
      byte[] result = restTemplate.postForObject(REQUEST_URL,
          JsonUtil.objectToJson(dto), byte[].class);
      Files.write(Paths.get("D:\\work\\Decathlon\\06-dktReview\\07-二维码\\01-门店或工作室\\qrcode\\工作室2\\" + qrCodeName), result);
    }

  }

  @Test
  public void createFileStore() throws Exception {
    CSVParser csvResult = parse(Paths.get(REVIEW_CSV_FILE));
    for (CSVRecord record : csvResult.getRecords()) {
      String name = record.get("name");
      String storeId = record.get("store_id");

      if (existStore.contains(storeId)) {
        continue;
      }

      String qrCodeName = StringUtils.joinWith("-", storeId, name, "门店") + ".png";
      log.info("开始生成门店太阳码， 门店名称-门店编号-门店： {}", qrCodeName);
      QrCodeDTO dto = new QrCodeDTO();
      String path = String.format("packageReview/pages/store/store?storeNumber=%s&storeName=%s&type=STORE", storeId, name);
      dto.setPath(path);
      log.info("门店二维码：{}", path);
      byte[] result = restTemplate.postForObject(REQUEST_URL, JsonUtil.objectToJson(dto), byte[].class);
      Files.write(Paths.get("D:\\work\\Decathlon\\06-dktReview\\07-二维码\\01-门店或工作室\\qrcode\\门店2\\" + qrCodeName), result);
    }

  }

  @Test
  public void createSingeFile() throws Exception {
    QrCodeDTO dto = new QrCodeDTO();
    dto.setPath("packageRacket/pages/racketCheckout/racketCheckout?storeNumber=0070127001270");
    byte[] result = restTemplate.postForObject(REQUEST_URL,
        JsonUtil.objectToJson(dto), byte[].class);
    Files.write(Paths.get("D:\\work\\Decathlon\\02-Racket Service\\05-QRCode\\all store\\岳塘店_0070127001270.png"),
        result);
  }


  @Data
  public static class QrCodeDTO {

    private String path;
  }

  private CSVParser parse(Path path) throws IOException {
    Reader reader = Files.newBufferedReader(path);
    return new CSVParser(reader, CSVFormat.DEFAULT
        .withFirstRecordAsHeader()
        .withIgnoreHeaderCase()
        .withTrim());
  }

  private String newString = "门店";//新字符串,如果是去掉前缀后缀就留空，否则写上需要替换的字符串
  private String oldString = "工作室";//要被替换的字符串
  private String dir = "D:\\work\\Decathlon\\06-dktReview\\07-二维码\\01-门店或工作室\\qrcode\\门店2";

  @Test
  public void recursiveTraversalFolder() {
    File folder = new File(dir);
    if (folder.exists()) {
      File[] fileArr = folder.listFiles();
      if (null == fileArr || fileArr.length == 0) {
        System.out.println("文件夹是空的!");
        return;
      } else {
        File newDir = null;//文件所在文件夹路径+新文件名
        String newName = "";//新文件名
        String fileName = null;//旧文件名
        File parentPath = new File("");//文件所在父级路径
        for (File file : fileArr) {
          if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
            System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
//            recursiveTraversalFolder(file.getAbsolutePath());
          } else {//是文件，判断是否需要重命名
            fileName = file.getName();
            parentPath = file.getParentFile();
            if (fileName.contains(oldString)) {//文件名包含需要被替换的字符串
              newName = fileName.replaceAll(oldString, newString);//新名字
              newDir = new File(parentPath + "/" + newName);//文件所在文件夹路径+新文件名
              file.renameTo(newDir);//重命名
              System.out.println("修改后：" + newDir);
            }
          }
        }
      }
    } else {
      System.out.println("文件不存在!");
    }
  }
}
