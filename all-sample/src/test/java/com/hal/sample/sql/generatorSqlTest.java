package com.hal.sample.sql;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hal.sample.dto.BoutiqueBudgetDto;
import com.hal.sample.dto.BrandGiftDto;
import com.hal.sample.dto.CustomerDto;
import com.hal.sample.dto.CustomerSegmentDto;
import com.hal.sample.entity.KpiTargetEntity;
import com.hal.sample.enums.Segment;
import com.hal.sample.util.ExcelUtil;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
 * @Author: Steven HUANG
 * @Date: 2019/4/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class generatorSqlTest {

  @Autowired
  private ResourceLoader resourceLoader;

  private final static String CR_URL = "https://api.warm.richemont.cn/warm/images/brand-gift/";

  private final static String WARM_URL = "https://ric-warm.digibridge.cn/warm-backend/images/brand-gift/";

  private final static List<String> brandGiftHeads = Lists.newArrayList("name", "code", "inboundStock",
      "boutiqueCode", "price", "imageUrl");

  private static String CUSTOMER_SEGMENT_SQL =
      "D:\\work\\warm\\inti_data\\sql\\update_specified_cusotmer_segment_2019.sql";

  private static String KPI_TARGET_SQL = "D:\\work\\warm\\inti_data\\sql\\init_kpi_target.sql";

  private static String BRAND_GIFT_SQL = "D:\\work\\warm\\support\\stock\\20190717\\insert_brand_gift_20190717.sql";

  private static String BOUTIQUE_BUDGET_SQL = "D:\\work\\warm\\inti_data\\init_boutique_budget_fiscalyear2020.sql";

  private static String START_TRANS = "start transaction;";

  private static String COMMIT = "commit;";

  private static String LINE = "\n\n";

  private static Map<String, String> staffMap = Maps.newHashMap();

  @Before
  public void init() {
    staffMap.put("MICHAEL LI", "03813");
    staffMap.put("MONICA SHEN", "03817");
    staffMap.put("VIVI GUO", "05172");
    staffMap.put("CINDY QIN", "00302");
    staffMap.put("MARIO FAN", "05200");
  }

  @Test
  public void init_brandGift() throws Exception {
    String path = "file:D:\\work\\warm\\support\\stock\\20190716\\brand_gift_template_20190717.xlsx";
    Resource resource1 = resourceLoader.getResource(path);
//    Resource resource2 = resourceLoader.getResource("classpath:document/upload_brand_gift_template_20190515.xlsx");

    List<BrandGiftDto> brandGiftList = ExcelUtil.parseToObject(resource1.getInputStream(),
        resource1.getFilename(), BrandGiftDto.class);

    Map<String, BrandGiftDto> map = Maps.newLinkedHashMap();

    for (BrandGiftDto brandGiftDto : brandGiftList) {
      String key = brandGiftDto.getCode().trim() + ":" + brandGiftDto.getBoutiqueCode().trim();
      map.merge(key, brandGiftDto, (oldV, newV) -> newV);
    }

/*
    List<BrandGiftDto> brandGiftList2 = ExcelUtil.parseToObject(resource2.getInputStream(),
        resource2.getFilename(), BrandGiftDto.class);

    for (BrandGiftDto brandGiftDto : brandGiftList2) {
      String key = brandGiftDto.getCode().trim()+":"+brandGiftDto.getBoutiqueCode().trim();
      map.merge(key,brandGiftDto,(oldV, newV) -> newV);
    }
*/

    for (BrandGiftDto brandGiftDto : brandGiftList) {
//      System.out.println(CR_URL + brandGiftDto.getCode() + ".PNG");
      System.out.println(StringUtils.leftPad(brandGiftDto.getBoutiqueCode(),4, "0"));
    }

//    genBrandGiftSql(map.values());

  }

  private void genBrandGiftSql(Collection<BrandGiftDto> brandGiftList) throws Exception {
    File file = new File(BRAND_GIFT_SQL);

    FileUtils.writeByteArrayToFile(file, START_TRANS.getBytes(), true);
    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);

    Set<String> boutiqueCodes = Sets.newHashSet();
    for (BrandGiftDto brandGiftDto : brandGiftList) {
      boutiqueCodes.add(brandGiftDto.getBoutiqueCode());
    }

    genBoutiqueSql(boutiqueCodes, file);

    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);

    for (BrandGiftDto brandGiftDto : brandGiftList) {
      String boutiqueId = "@" + brandGiftDto.getBoutiqueCode() + "_id";
      StringBuilder builder = new StringBuilder();
      builder.append("INSERT INTO `warm_car_gift` (`enabled`, `month`, `create_time`, `update_time`, `boutique_id`, "
          + "`code`, `name`, `price`, `stocks`, `inbound_stocks`, `init_stocks`, `outbound_stocks` ,`image_url`) VALUES");
      builder.append("(").append("1,'2019/05',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,").append(boutiqueId).append(",")
          .append("'").append(brandGiftDto.getCode().trim()).append("',")
          .append("'").append(brandGiftDto.getName().trim().replaceAll("\n", "")
          .replaceAll("'", "\\\\'")).append("',")
          .append("'").append(brandGiftDto.getPrice()).append("',")
          .append(brandGiftDto.getInboundStock()).append(",")
          .append(brandGiftDto.getInboundStock()).append(",")
          .append("0,0,")
          .append("'").append(CR_URL + brandGiftDto.getCode() + ".PNG").append("'")
          .append(");");
      builder.append("\n");
      FileUtils.writeByteArrayToFile(file, builder.toString().getBytes(), true);
    }

    FileUtils.writeByteArrayToFile(file, COMMIT.getBytes(), true);
  }

  @Test
  public void init_customerSegment() throws Exception {
    Resource resource = resourceLoader.getResource("classpath:document/customer_segment_template.xlsx");
    List<CustomerSegmentDto> customers = ExcelUtil.parseToObject(resource.getInputStream(),
        resource.getFilename(), CustomerSegmentDto.class);

    File file = new File(CUSTOMER_SEGMENT_SQL);

    FileUtils.writeByteArrayToFile(file, START_TRANS.getBytes(), true);
    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);
    for (CustomerSegmentDto customer : customers) {
      StringBuilder builder = new StringBuilder();
      builder.append("update warm_customer set segment = ");
      builder.append("'").append(Segment.getCodeByDesc(customer.getSegment())).append("'");
      builder.append(" where cdb_number = ");
      builder.append("'").append(String.format("%07d", Integer.valueOf(customer.getCdbNumber()))).append("'");
      builder.append(";");
      builder.append("\n");
//      System.out.println(builder.toString());
      FileUtils.writeByteArrayToFile(file, builder.toString().getBytes(), true);
    }

    FileUtils.writeByteArrayToFile(file, COMMIT.getBytes(), true);
  }

  @Test
  public void init_customerTop70() throws Exception {
    Resource resource = resourceLoader.getResource("classpath:document/upload_top70_template_20190508.xlsx");
    List<CustomerDto> customers = ExcelUtil.parseToObject(resource.getInputStream(),
        resource.getFilename(), CustomerDto.class);

    for (CustomerDto customer : customers) {
//      System.out.println(String.format("%07d", Integer.valueOf(customer.getCdbNumber())));
      System.out.println(staffMap.get(customer.getStaffId().trim()));
    }


  }

  @Test
  public void init_kpiTarget() throws Exception {
    Resource resource = resourceLoader.getResource("classpath:document/boutique_kpi_target_template.xlsx");
    List<KpiTargetEntity> targetEntities = ExcelUtil.parseToObject(resource.getInputStream(),
        resource.getFilename(), KpiTargetEntity.class);
    Set<String> boutiqueCodes = Sets.newHashSet();
    for (KpiTargetEntity targetEntity : targetEntities) {
      boutiqueCodes.add(targetEntity.getBoutiqueCode());
      Integer value = Integer.valueOf(targetEntity.getBoutiqueCode());
//      System.out.println(String.format("%04d",value));
    }

    File file = new File(KPI_TARGET_SQL);
    FileUtils.writeByteArrayToFile(file, START_TRANS.getBytes(), true);
    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);

    genBoutiqueSql(boutiqueCodes, file);

    for (KpiTargetEntity targetEntity : targetEntities) {
      String boutiqueId = "@" + targetEntity.getBoutiqueCode() + "_id";
      StringBuilder builder = new StringBuilder();
      builder.append("INSERT INTO `warm_car_sa_report_kpi_target` (`create_time`, `update_time`, `boutique_id`, "
          + "`opportunity_communicate_rate`, `opportunity_purchase_amount`, `opportunity_purchase_rate`, "
          + "`prospect_collect_per_month`, `prospect_communicate_rate`, `prospect_convert_rate`, "
          + "`top70communicate_rate`, `top70purchase_amount`, `top70purchase_rate`) VALUES ");
      builder.append("(").append("CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,").append(boutiqueId).append(",")
          .append(targetEntity.getOpportunityCommunicateRate()).append(",")
          .append(targetEntity.getOpportunityPurchaseAmount()).append(",")
          .append(targetEntity.getOpportunityPurchaseRate()).append(",")
          .append(targetEntity.getProspectCollectPerMonth()).append(",")
          .append(targetEntity.getProspectCommunicateRate()).append(",")
          .append(targetEntity.getProspectConvertRate()).append(",")
          .append(targetEntity.getTop70CommunicateRate()).append(",")
          .append(targetEntity.getTop70PurchaseAmount()).append(",")
          .append(targetEntity.getTop70PurchaseRate()).append(");");
      builder.append("\n");
      FileUtils.writeByteArrayToFile(file, builder.toString().getBytes(), true);
    }

    FileUtils.writeByteArrayToFile(file, COMMIT.getBytes(), true);

  }

  @Test
  public void init_boutiqueBudget() throws Exception {
    Resource resource = resourceLoader.getResource("classpath:document/boutique_budget_template.xlsx");
    List<BoutiqueBudgetDto> budgets = ExcelUtil.parseToObject(resource.getInputStream(),
        resource.getFilename(), BoutiqueBudgetDto.class);
    Set<String> boutiqueCodes = Sets.newHashSet();
    for (BoutiqueBudgetDto budget : budgets) {
      boutiqueCodes.add(budget.getBoutiqueCode());
      Integer value = Integer.valueOf(budget.getBoutiqueCode());
//      System.out.println(String.format("%04d",value));
    }

    File file = new File(BOUTIQUE_BUDGET_SQL);
    FileUtils.writeByteArrayToFile(file, START_TRANS.getBytes(), true);
    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);

    genBoutiqueSql(boutiqueCodes, file);

    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);

    for (BoutiqueBudgetDto budget : budgets) {
      String boutiqueId = "@" + budget.getBoutiqueCode() + "_id";
      StringBuilder builder = new StringBuilder();
      builder.append("INSERT INTO `warm_car_boutique_budget` (`create_time`, `update_time`, "
          + "`boutique_id`, `budget`, `remaining`, `year`) VALUES");
      builder.append(" (");
      builder.append("CURRENT_TIMESTAMP,");
      builder.append("CURRENT_TIMESTAMP,");
      builder.append(boutiqueId).append(",");
      builder.append(budget.getBudget()).append(",");
      builder.append(budget.getRemaining()).append(",");
      builder.append("'2020'");
      builder.append(");");
      builder.append("\n");
      FileUtils.writeByteArrayToFile(file, builder.toString().getBytes(), true);
    }

    FileUtils.writeByteArrayToFile(file, LINE.getBytes(), true);
    FileUtils.writeByteArrayToFile(file, COMMIT.getBytes(), true);
  }

  private void genBoutiqueSql(Set<String> boutiqueCodes, File file) throws Exception {
    for (String boutiqueCode : boutiqueCodes) {
      StringBuilder builder = new StringBuilder();
      builder.append("SELECT @").append(boutiqueCode).append("_id := id ")
          .append("FROM  warm_car_boutique WHERE `boutique_code` = ")
          .append("'").append(boutiqueCode).append("'").append(";");
      builder.append("\n");
      FileUtils.writeByteArrayToFile(file, builder.toString().getBytes(), true);
    }
  }
}
