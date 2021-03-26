package com.hal.sample.sql;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Sets;
import com.hal.sample.dto.QrCodeDto;
import com.hal.sample.sql.TmallResponse.InfoData;
import com.hal.sample.util.ExcelUtil;
import com.hal.sample.util.JsonUtil;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
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

/**
 * Created by Steven.HUANG on 2019/3/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QrCodeTest {

  @Autowired
  private ResourceLoader resourceLoader;

  private static String START_TRANS = "start transaction;";

  private static String COMMIT = "commit;";

  private static String LINE = "\n\n";

  private static String STORE_SCENE_SQL = "D:\\work\\lacoste\\project-swp-wechat\\store_scene.sql";

  private static String STORE_SQL = "D:\\work\\lacoste\\project-swp-wechat\\store_qrcode.sql";

  private static String OTHER_SCENE_SQL = "D:\\work\\lacoste\\project-swp-wechat\\other_scene.sql";

  private static String OTHER_SQL = "D:\\work\\lacoste\\project-swp-wechat\\other_qrcode.sql";

  private static String WX_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={0}";

  private static String IMAGE_PATH = "D:\\work\\lacoste\\project-swp-wechat\\qrcode";

  private static String OSS_URL = "https://lacoste-wechat.oss-cn-shanghai.aliyuncs.com/document/LAC-qrcode/202012/{0}";

  @Before
  public void init() {
  }

  @Test
  public void init_store() throws Exception {
    String path = "file:D:\\work\\lacoste\\project-swp-wechat\\store_qrcode.xlsx";
    Resource resource = resourceLoader.getResource(path);

    List<QrCodeDto> qrCodeDtos = ExcelUtil.parseToObject(resource.getInputStream(),
        resource.getFilename(), QrCodeDto.class);

    this.genSceneSql(qrCodeDtos, STORE_SCENE_SQL);
    this.genQrCodeSql(qrCodeDtos, STORE_SQL);
  }

  @Test
  public void init_other() throws Exception {
    String path = "file:D:\\work\\lacoste\\project-swp-wechat\\other_qrcode.xlsx";
    Resource resource = resourceLoader.getResource(path);

    List<QrCodeDto> qrCodeDtos = ExcelUtil.parseToObject(resource.getInputStream(),
        resource.getFilename(), QrCodeDto.class);

    this.genSceneSql(qrCodeDtos, OTHER_SCENE_SQL);
    this.genQrCodeSql(qrCodeDtos, OTHER_SQL);
  }

  private void genSceneSql(Collection<QrCodeDto> qrCodeDtos, String filePath) throws Exception {
    File file = new File(filePath);

    FileUtils.writeByteArrayToFile(file, START_TRANS.getBytes(), true);
    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);


    for (QrCodeDto qrCodeDto : qrCodeDtos) {
      StringBuilder builder = new StringBuilder();
      builder.append("INSERT INTO `scene`(`brand`, `scene`, `channel`, `store_number`, "
          + "`created_by`, `created_time`) VALUES ");
      builder.append("(").append("'").append("LAC").append("',")
          .append("'").append(StringUtils.trim(qrCodeDto.getScene())).append("',")
          .append("'").append(StringUtils.trim(qrCodeDto.getChannel())).append("',")
          .append("'").append(StringUtils.trim(qrCodeDto.getStoreNumber())).append("',")
          .append("'ETL', CURRENT_TIMESTAMP")
          .append(");");
      builder.append("\n");
      FileUtils.writeByteArrayToFile(file, builder.toString().getBytes(), true);
    }

    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);
    FileUtils.writeByteArrayToFile(file, COMMIT.getBytes(), true);
  }

  private void genQrCodeSql(Collection<QrCodeDto> qrCodeDtos, String filePath) throws Exception {
    File file = new File(filePath);

    FileUtils.writeByteArrayToFile(file, START_TRANS.getBytes(), true);
    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);


    for (QrCodeDto qrCodeDto : qrCodeDtos) {
      URL url = new URL(MessageFormat.format(WX_QRCODE_URL, qrCodeDto.getTicket()));
      URLConnection con = url.openConnection();
      InputStream imageIs = con.getInputStream();
      String jpgName = UUID.randomUUID().toString() + ".jpg";
      File targetFile = new File(IMAGE_PATH, jpgName);
      FileUtils.copyInputStreamToFile(imageIs, targetFile);
      String imageUrl = MessageFormat.format(OSS_URL, jpgName);

      StringBuilder builder = new StringBuilder();
      builder.append("INSERT INTO `lacoste_wechat`.`qr_code`(`brand`, `name`, `description`, `category_id`, `type`, `scene`, `ticket`, `url`, `data_url`, `deleted`, `created_by`, `created_time`) VALUES ");

      builder.append("(").append("'").append("LAC").append("',")
          .append("'").append(StringUtils.trim(qrCodeDto.getScene())).append("',")
          .append("'").append(StringUtils.trim(qrCodeDto.getScene())).append("',")
          .append(2).append(",")
          .append("'").append("Permanent").append("',")
          .append("'").append(StringUtils.trim(qrCodeDto.getScene())).append("',")
          .append("'").append(StringUtils.trim(qrCodeDto.getTicket())).append("',")
          .append("'").append(StringUtils.trim(imageUrl)).append("',")
          .append("'").append(StringUtils.trim(qrCodeDto.getDataUrl())).append("',")
          .append(0).append(",")
          .append("'ETL', CURRENT_TIMESTAMP")
          .append(");");
      builder.append("\n");
      FileUtils.writeByteArrayToFile(file, builder.toString().getBytes(), true);
    }

    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);
    FileUtils.writeByteArrayToFile(file, COMMIT.getBytes(), true);
  }


  @Test
  public void getTmallMember() throws Exception {
    String sqlFile = "D:\\work\\lacoste\\03-data\\Lacoste.members\\init_dml_20210115.sql";
    String path = "D:\\work\\lacoste\\03-data\\Lacoste.members\\member_data";
    File root = new File(path);
    File[] files = root.listFiles((fileFilter) -> !fileFilter.isDirectory() &&
        !StringUtils.startsWith(fileFilter.getName(), "."));

    File file = new File(sqlFile);
    FileUtils.writeByteArrayToFile(file, START_TRANS.getBytes(), true);
    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);
    Set<String> nickSet = Sets.newHashSet();
    Stream.of(files).forEachOrdered(msgFile -> {
      try {
        JsonNode jsonNode = JsonUtil.fileToJsonNode(msgFile);
        if (jsonNode.has("error_response")){
          log.debug("{} error_response is : {}", msgFile.getName(), jsonNode.get("error_response").asText());
          return;
        }
        TmallResponse response = JsonUtil.jsonToObject(jsonNode, TmallResponse.class);
        List<InfoData> datas = response.getData().getResultData().getExtData().getInfoData();
        for (InfoData infoData : datas) {
          String nick = StringUtils.trim(infoData.getBuyerNick());
          if(nickSet.contains(nick)){
            continue;
          }
          nickSet.add(nick);
          StringBuilder builder = new StringBuilder();
          builder.append("INSERT INTO `tmall_member`(`create_time`, `gmt_modified`, `buyer_nick`, `snapshot_info`, "
              + "`grade`, `grade_name`, `points`, `file_name`) VALUES ");
          builder.append("(").append("CURRENT_TIMESTAMP").append(",")
              .append("'").append(StringUtils.trim(infoData.getGmtModified())).append("',")
              .append("'").append(nick).append("',")
              .append("'").append(StringUtils.trim(infoData.getSnapshotInfo())).append("',")
              .append(infoData.getGrade()).append(",")
              .append("'").append(StringUtils.trim(infoData.getGradeName())).append("',")
              .append("'").append(StringUtils.trim(infoData.getPoints())).append("',")
              .append("'").append(StringUtils.trim(msgFile.getName())).append("'")
              .append(");");
          builder.append("\n");
          FileUtils.writeByteArrayToFile(file, builder.toString().getBytes(), true);
        }
      } catch (Exception e) {
        log.error("出现错误 msgfiles : {}", msgFile.getName());
        throw new RuntimeException(e.getMessage(), e);
      }
    });

    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);
    FileUtils.writeByteArrayToFile(file, COMMIT.getBytes(), true);
  }
}
