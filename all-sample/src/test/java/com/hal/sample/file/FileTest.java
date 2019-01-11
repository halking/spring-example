package com.hal.sample.file;

import org.apache.commons.io.FilenameUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Steven.HUANG on 2018/12/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {

  private final static String WINDOWS_PATH = "d:\\static\\images";
  private final static String LINUX_PATH = "/usr/local/static/images";

  @Test
  public void pathTest(){
    String result = FilenameUtils.getBaseName(WINDOWS_PATH);
    System.out.println("result:"+result);

    System.out.println("result1:"+FilenameUtils.getBaseName(FilenameUtils.getPathNoEndSeparator(WINDOWS_PATH)));

    System.out.println(FilenameUtils.separatorsToSystem(WINDOWS_PATH));

    MatcherAssert.assertThat(result, Matchers.equalTo("images"));
  }

}
